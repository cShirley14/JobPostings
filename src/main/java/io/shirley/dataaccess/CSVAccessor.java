/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.shirley.dataaccess;

import io.shirley.domainmodels.Job;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Shirley
 */
public class CSVAccessor {
    private String FILE_PATH;
    private SortedSet<Job> jobs;
    
    /**
     * Decimal formatting: https://docs.oracle.com/javase/tutorial/i18n/format/decimalFormat.html
     */
    private void readFromFile() throws Exception {
//        String fileName = this.getClass().getClassLoader().getResourceAsStream("job-data - Sheet1.tsv");
//        File file = new File(fileName).getAbsoluteFile();
//        System.out.println(file
        File file = new File(FILE_PATH);
        //System.out.println(new File(".").getCanonicalPath());
        try (Scanner in = new Scanner(file)) {
            
            jobs = new TreeSet<>();
            int lineCount = 0;
            String line;
            String[] fields;
            int id;
            boolean active = false;
            LocalDate dateCreated;
            String title;
            String city;
            String state;
            boolean fullTime;
            String department;
            String experience;
            String wageCategory;
            double salary;
            String jobDescription;
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "MM-dd-yyyy");
            
            while(in.hasNextLine()) {
                lineCount++;
                line = in.nextLine();
                fields = line.split("\t");
                
                try {
                    active = Boolean.parseBoolean(fields[1].toLowerCase());
                    if (active) {
                        id = Integer.parseInt(fields[0]);
                        dateCreated = LocalDate.parse(fields[2]);
                        title = fields[3];
                        city = fields[4];
                        state = fields[5];
                        fullTime = Boolean.parseBoolean(fields[6].toLowerCase());
                        department = fields[7];
                        experience = fields[8];
                        wageCategory = fields[9];
                        salary = Double.parseDouble(fields[10]);
                        jobDescription = fields[11];
                        jobs.add(new Job(id, active, dateCreated,title,
                            city,state,fullTime,department,experience,
                            wageCategory,salary,jobDescription));
                    }
                } catch (Exception ex) {
                    throw new Exception(ex + "Exception occurred on line " + lineCount);
                }
            }
            
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
    public SortedSet<Job> getAllJobs(String filePath) throws Exception {
        FILE_PATH = filePath;
        verifyJobRecordList();
        return jobs;
    }

    private void verifyJobRecordList() throws Exception {
        if (null == jobs) {
            readFromFile();
        }
    }
}
