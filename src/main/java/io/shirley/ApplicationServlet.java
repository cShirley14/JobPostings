/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.shirley;

import io.shirley.domainmodels.Application;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shirley
 */
@WebServlet(name = "ApplicationServlet", urlPatterns = {"/applications"})
public class ApplicationServlet extends HttpServlet {
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
     * Returns user to the job list page if they submit
     * an invalid POST request.
     * 
     * Returns 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void viewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.getRequestDispatcher("/WEB-INF/jsp/view/jobList.jsp")
               .forward(request, response);
    }

    
    private void submitJobApplication(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        Application app = new Application();
        String firstName;
        String lastName;
        String email;
        LocalDate earliestStartDate;
        // Pattern from Oreilly Java Cookbook: https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s01.html
        // and https://www.baeldung.com/java-regex-validate-phone-numbers
        Pattern emailPattern = Pattern.compile("\\A[\\w!#$%&'*+/=?`{|}~^-]+"
                + "(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@↵\n" +
                "(?:[A-Z0-9-]+\\.)+[A-Z]{2,6}\\Z");
        String phone;
        Pattern phonePattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");
        double desiredSalary;
        
        try {
            if (request.getParameter("firstName") != null && 
                    !request.getParameter("firstName").equals("")) {
                firstName = request.getParameter("firstName");
            } else {
                app.setFirstNameError(true);
            }
            if (request.getParameter("lastName") != null && 
                    !request.getParameter("lastName").equals("")) {
                lastName = request.getParameter("firstName");
            } else {
                app.setLastNameError(true);
            }
            Matcher emailMatcher = emailPattern.matcher(request.getParameter("email"));
            if (request.getParameter("email") != null &&
                    !request.getParameter("email").equals("") &&
                    emailMatcher.matches()) {
                email = request.getParameter("email");
            } else {
                app.setEmailError(true);
            }
            Matcher phoneMatcher = phonePattern.matcher(request.getParameter("phone"));
            if (request.getParameter("phone") != null &&
                    !request.getParameter("phone").equals("") &&
                    phoneMatcher.matches()) {
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
                
            }
            
            
            // Convert Double
            String desiredSalary = request.getParameter("desiredSalary");
            // Convert Date
            String earliestStartDate = request.getParameter("earliestStartDate");
            // convert int
            String id = request.getParameter("id");
            // convert int
            String jobId = request.getParameter("jobId");
            // Get date time submitted
            // Convert bool
            String active = request.getParameter("active");
            
        } catch (Exception ex) {
            // show errors
        }
    }


}
