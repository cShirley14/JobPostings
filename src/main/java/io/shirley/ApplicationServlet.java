/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.shirley;

import io.shirley.domainmodels.Application;
import io.shirley.domainmodels.Attachment;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Shirley
 */
@WebServlet(name = "ApplicationServlet", urlPatterns = {"/applications"})
public class ApplicationServlet extends HttpServlet {
    private volatile int APPLICATION_ID_SEQUENCE = 1;
    List<Application> apps = new ArrayList<Application>();

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
        String action = request.getParameter("action");
        Application app = new Application();
        String firstName;
        String lastName;
        String email;
        LocalDate earliestStartDate;
        // Pattern from Oreilly Java Cookbook: https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s01.html
        // and https://www.baeldung.com/java-regex-validate-phone-numbers
        Pattern emailPattern = Pattern.compile("\\A[\\w!#$%&'*+/=?`{|}~^-]+"
                + "(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@↵\n"
                + "(?:[A-Z0-9-]+\\.)+[A-Z]{2,6}\\Z");
        String phone;
        Pattern phonePattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");
        double desiredSalary;
        int id;
        int jobId;
        boolean active;

        try {
            if (request.getParameter("firstName") != null
                    && !request.getParameter("firstName").equals("")) {
                firstName = request.getParameter("firstName");
            } else {
                app.setFirstNameError(true);
            }
            if (request.getParameter("lastName") != null
                    && !request.getParameter("lastName").equals("")) {
                lastName = request.getParameter("firstName");
            } else {
                app.setLastNameError(true);
            }
            Matcher emailMatcher = emailPattern.matcher(request.getParameter("email"));
            if (request.getParameter("email") != null
                    && !request.getParameter("email").equals("")
                    && emailMatcher.matches()) {
                email = request.getParameter("email");
            } else {
                app.setEmailError(true);
            }
            Matcher phoneMatcher = phonePattern.matcher(request.getParameter("phone"));
            if (request.getParameter("phone") != null
                    && !request.getParameter("phone").equals("")
                    && phoneMatcher.matches()) {
                phone = request.getParameter("phone");
            } else {
                app.setPhoneError(true);
            }
            try {
                desiredSalary = Double.parseDouble(request.getParameter("desiredSalary"));
            } catch (NumberFormatException nfe) {
                app.setSalaryError(true);
            }
            try {
                earliestStartDate = LocalDate.parse(request.getParameter("earliestStartDate"));
            } catch (Exception ex) {
                app.setStartDateError(true);
            }
            try {
                jobId = Integer.parseInt(request.getParameter("jobId"));
            } catch (Exception ex) {
                app.setInvalidDataError(true);
            }
            try {
                active = Boolean.parseBoolean(request.getParameter("active"));
            } catch (Exception ex) {
                app.setInvalidDataError(true);
            }
            // Upload attachment
            try {
                Part filePart = request.getPart("attachment");
                if (filePart != null && filePart.getSize() > 0) {
                    Attachment attachment = this.processAttachment(filePart);
                    if (attachment != null) {
                        app.setAttachment(attachment);
                    }
                }
            } catch (Exception ex) {
                app.setResumeError(true);
            }

            if (app.getEmailError() == true || app.getFirstNameError() == true
                    || app.getInvalidDataError() == true || app.getLastNameError() == true
                    || app.getPhoneError() == true || app.getResumeError() == true
                    || app.getStartDateError() == true) {
                // Redirect to application again to show error message(s).
                request.getRequestDispatcher("/WEB-INF/jsp/view/job.jsp").forward(request, response);
            } else {
                // Set application ID
                synchronized (this) {
                    id = this.APPLICATION_ID_SEQUENCE++;
                    app.setId(id);
                }
                // add successful job application
                apps.add(app);
                // Show success message on same page
                request.getRequestDispatcher("/WEB-INF/jsp/view/job.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("/WEB-INF/jsp/view/job.jsp").forward(request, response);
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

}
