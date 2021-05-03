/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.shirley;

import io.shirley.domainmodels.Application;
import io.shirley.domainmodels.Attachment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Shirley
 */
@WebServlet(name = "ApplicationServlet", urlPatterns = {"/applications"})
@MultipartConfig
public class ApplicationServlet extends HttpServlet {
    private volatile int APPLICATION_ID_SEQUENCE = 1;
    private List<Application> _apps = new ArrayList<Application>();

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = (String)session.getAttribute("action");
        if (action.equals("adminLogin")) {
            viewApplications(request, response);
        }
        response.sendRedirect("jobs");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "defaultList";
        }

        switch (action) {
            case "defaultList":
                viewList(request, response);
                break;
            case "submitApp":
                submitJobApplication(request, response);
                break;
            default:
                viewList(request, response);
                break;
        }

    }

    /**
     * Returns user to the job list page if they submit an invalid POST request.
     *
     * Returns
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void viewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("jobs");
    }

    private void submitJobApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Application app = new Application();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String date = request.getParameter("earlyStartDate");
        String phone = request.getParameter("phone");
        String desiredSal = request.getParameter("desiredSalary");
        String job = request.getParameter("job");
        String activeFlag = request.getParameter("active");
        String jobTitle = request.getParameter("jobTitle");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        // Pattern from Oreilly Java Cookbook: https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s01.html
        // and https://www.baeldung.com/java-regex-validate-phone-numbers and https://stackoverflow.com/questions/8204680/java-regex-email
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Pattern phonePattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");
        int id;

        try {
            if (firstName != null
                    && !firstName.equals("")) {
                app.setFirstName(firstName);
            } else {
                app.setFirstNameError(true);
            }
            if (lastName != null
                    && !lastName.equals("")) {
                app.setLastName(lastName);
            } else {
                app.setLastNameError(true);
            }
            Matcher emailMatcher = emailPattern.matcher(email);
            if (email != null
                    && !email.equals("")
                    && emailMatcher.matches()) {
                app.setEmail(email);
            } else {
                app.setEmailError(true);
            }
            Matcher phoneMatcher = phonePattern.matcher(phone);
            if (phone != null
                    && !phone.equals("")
                    && phoneMatcher.matches()) {
                app.setPhone(phone);
            } else {
                app.setPhoneError(true);
            }
            try {
                 app.setDesiredSalary(Double.parseDouble(desiredSal));
            } catch (NumberFormatException nfe) {
                app.setSalaryError(true);
            }
            try {
                LocalDate rawDate = LocalDate.parse(date,formatter);
                if (rawDate.isAfter(LocalDate.now())) {
                    app.setEarliestStartDate(rawDate);
                } else {
                    app.setStartDateError(true);
                }
            } catch (Exception ex) {
                app.setStartDateError(true);
            }
            try {
                app.setJobId(Integer.parseInt(job));
            } catch (Exception ex) {
                app.setInvalidDataError(true);
            }
            try {
                app.setActive(Boolean.parseBoolean(activeFlag));
            } catch (Exception ex) {
                app.setInvalidDataError(true);
            }
            // Upload attachment
            try {
                Part filePart = request.getPart("file1");
                if (filePart != null && filePart.getSize() > 0) {
                    Attachment attachment = this.processAttachment(filePart);
                    if (attachment != null) {
                        app.setAttachment(attachment);
                    }
                }
                else {
                    app.setResumeError(true);
                }
            } catch (Exception ex) {
                app.setResumeError(true);
            }

            if (app.getEmailError() == true || app.getFirstNameError() == true
                    || app.getInvalidDataError() == true || app.getLastNameError() == true
                    || app.getPhoneError() == true || app.getResumeError() == true
                    || app.getStartDateError() == true) {
                // Redirect to application again to show error message(s).
                HttpSession session = request.getSession();
                session.setAttribute("unsubmittedApp", app);
                response.sendRedirect("jobs?action=viewJob&job="+job);
            } else {
                // Set application ID
                synchronized (this) {
                    id = this.APPLICATION_ID_SEQUENCE++;
                    app.setId(id);
                }
                // add successful job application
                HttpSession session = request.getSession();
                session.setAttribute("submittedApp", app);
                app.setJobTitle(jobTitle);
                app.setDateTimeSubmitted(Instant.now());
                _apps.add(app);
                session.setAttribute("applications", _apps);
                // Show success message on same page
                session.setAttribute("unsubmittedApp", null);
                response.sendRedirect("jobs?action=viewJob&job="+job);
            }
        } catch (Exception ex) {
            response.sendRedirect("jobs?action=viewJob&job="+job);
        }
    }

    private Attachment processAttachment(Part filePart) throws IOException {
        InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int read;
        final byte[] bytes = new byte[1024];

        while((read = inputStream.read(bytes)) != -1)
        {
            outputStream.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();
        attachment.setName(filePart.getSubmittedFileName());
        attachment.setContents(outputStream.toByteArray());

        return attachment;
    }

    private void viewApplications(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (request.getParameter("logout") != null) {
            session.invalidate();
            response.sendRedirect("login");
            return;
        }
        
        if (_apps != null) {
            try {
                int appsPerPage = 4;
                int begin = 0;
                int end = 0;
                int maxPagesApp = _apps.size() / appsPerPage;
                if (_apps.size() % appsPerPage != 0) {
                    maxPagesApp++;
                }

                String currPageApp = request.getParameter("pageApp");
                int pageApp = 1;
                if (currPageApp != null && !currPageApp.equals("")) {
                    try {
                        pageApp = Integer.parseInt(currPageApp);
                        if (pageApp < 1) {
                            pageApp = 1;
                        } else if(pageApp > maxPagesApp) {
                            pageApp = maxPagesApp;
                        }
                    } catch (NumberFormatException nfe) {
                        pageApp = 1;
                    }
                }
                begin = (pageApp - 1) * appsPerPage;
                end = begin + appsPerPage - 1;

                request.setAttribute("apps", _apps);
                request.setAttribute("beginApp", begin);
                request.setAttribute("endApp", end);
                request.setAttribute("maxPagesApp", maxPagesApp);
                request.setAttribute("currPageApp", pageApp);
                request.getRequestDispatcher("/WEB-INF/jsp/view/applicationList.jsp").forward(request, response);
            } catch (Exception ex) {
                // Show error page
            }
        } 
    }

}
