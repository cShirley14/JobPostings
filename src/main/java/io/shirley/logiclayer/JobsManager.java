/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.shirley.logiclayer;

import io.shirley.dataaccess.CSVAccessor;
import io.shirley.domainmodels.Job;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Shirley
 */
public class JobsManager {
    private CSVAccessor _csvAccessor = new CSVAccessor();
    
    public SortedSet<Job> RetrieveJobs(String filePath) throws Exception {
        SortedSet<Job> jobs = new TreeSet<Job>();
        try {
            jobs = _csvAccessor.getAllJobs(filePath);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return jobs;
    }
}
