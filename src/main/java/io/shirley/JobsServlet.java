/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.shirley;

import io.shirley.domainmodels.Job;
import io.shirley.logiclayer.JobsManager;
import java.io.IOException;
import java.util.SortedSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shirley
 */
public class JobsServlet extends HttpServlet {
    private JobsManager _jobsManager = new JobsManager();
    private SortedSet<Job> _preLoadedJobs;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "defaultList";
        }
        
        switch (action) {
            case "defaultList":
                viewList(request, response);
                break;
            case "viewJob":
                viewjob(request, response);
                break;
            default:
                viewList(request, response);
                break;
        }
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
        String test = "hello";
    }

    private void viewList(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (_preLoadedJobs == null) {
                String relativeWebPath = "/WEB-INF/Assets/job-data - Sheet1.tsv";
                String absoluteFilePath = getServletContext().getRealPath(relativeWebPath);
                _preLoadedJobs = _jobsManager.RetrieveJobs(absoluteFilePath);
            }
            
            String app = "application";
            String log = "login";
            int jobsPerPage = 4;
            int begin = 0;
            int end = 0;
            int maxPages = _preLoadedJobs.size() / jobsPerPage;
            if (_preLoadedJobs.size() % jobsPerPage != 0) {
                maxPages++;
            }

            String currPage = request.getParameter("page");
            int page = 1;
            if (currPage != null && !currPage.equals("")) {
                try {
                    page = Integer.parseInt(currPage);
                    if (page < 1) {
                        page = 1;
                    } else if(page > maxPages) {
                        page = maxPages;
                    }
                } catch (NumberFormatException nfe) {
                    page = 1;
                }
            }
            begin = (page - 1) * jobsPerPage;
            end = begin + jobsPerPage - 1;

            request.setAttribute("jobs", _preLoadedJobs);
            request.setAttribute("app", app);
            request.setAttribute("log", log);
            request.setAttribute("begin", begin);
            request.setAttribute("end", end);
            request.setAttribute("maxPages", maxPages);
            request.setAttribute("currPage", page);
            request.getRequestDispatcher("/WEB-INF/jsp/view/jobList.jsp").forward(request, response);
        } catch (Exception ex) {
            // Show error page
        }
    }   

    private void viewjob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedJobID = request.getParameter("job");
        if (_preLoadedJobs != null) {
            try {
                int jobID = Integer.parseInt(selectedJobID);
                for (Job job : _preLoadedJobs) {
                    if (job.getId() == jobID) {
                        Job selectedJob = job;
                        request.setAttribute("selectedJob", selectedJob);
                        request.getRequestDispatcher("/WEB-INF/jsp/view/job.jsp").forward(request, response);
                    }
                }
                // else show jobs
                viewList(request, response);
            } catch (NumberFormatException nfe) {
                request.setAttribute("action", null);
                viewList(request, response);
            }
        }
        else { // Take 
            viewList(request, response);
        }
    }
}