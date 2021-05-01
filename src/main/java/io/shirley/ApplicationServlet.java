/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.shirley;

import io.shirley.domainmodels.Application;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        try {
            
        } catch (Exception ex) {
            // show errors
        }
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String 
    }


}
