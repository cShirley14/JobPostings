package io.shirley.domainmodels;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Represents Job Postings
 * for the Job Posting website.
 * 
 * @author Shirley
 */
public class Job implements Comparable<Job>{
    private int id;
    private boolean active;
    private LocalDate dateCreated;
    private Date convDateCreated;
    private String title;
    private String city;
    private String state;
    private boolean fullTime;
    private String department;
    private String experience;
    private String wageCategory;
    private double salary;
    private String jobDescription;

    /**
     * Zero-Argument constructor to
     * set default values for a job
     * posting.
     */
    public Job() {
        this.id = 0;
        this.active = false;
        this.dateCreated = LocalDate.now();
        this.title = "";
        this.city = "";
        this.state = "";
        this.fullTime = false;
        this.department = "";
        this.experience = "";
        this.wageCategory = "";
        this.salary = 0;
        this.jobDescription = "";
    }

    /**
     * Allows for initialization of
     * a job posting with parameters provided.
     * 
     * @param id
     * @param active
     * @param dateCreated
     * @param title
     * @param city
     * @param state
     * @param fullTime
     * @param department
     * @param experience
     * @param wageCategory
     * @param salary 
     */
    public Job(int id, boolean active, LocalDate dateCreated, String title, String city, String state, boolean fullTime, String department, String experience, String wageCategory, double salary, String jobDescription) {
        this.id = id;
        this.active = active;
        this.dateCreated = dateCreated;
        this.title = title;
        this.city = city;
        this.state = state;
        this.fullTime = fullTime;
        this.department = department;
        this.experience = experience;
        this.wageCategory = wageCategory;
        this.salary = salary;
        this.jobDescription = jobDescription;
    }

    /**
     * Accessor for retrieving
     * the unique identifying number
     * of a job posting.
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Mutator for the uniquely
     * identifying number of a job
     * application.
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Accessor which gets whether the
     * job is open to applications.
     * 
     * @return 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Mutator that sets whether
     * the job is currently open to
     * be applied to.
     * 
     * @param active 
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Accessor that gets the
     * date of the job posting.
     * 
     * @return 
     */
    public LocalDate getDateCreated() {
        return dateCreated;
    }

    /**
     * Mutator that sets the date
     * the job posting was created.
     * 
     * @param dateCreated 
     */
    public void setDateCreated(LocalDate dateCreated) {
        ZoneId userZoneId = ZoneId.systemDefault();
        Date date = Date.from(dateCreated.atStartOfDay().toInstant(ZoneOffset.UTC));
        setConvDateCreated(date);
        this.dateCreated = dateCreated;
    }

    /**
     * Accessor that gets the position's
     * title of the job posting.
     * 
     * @return 
     */
    public String getTitle() {
        return title;
    }

    /**
     * Mutator that sets the position's
     * title of the job posting.
     * 
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accessor that gets the city of
     * the job posting.
     * 
     * @return 
     */
    public String getCity() {
        return city;
    }

    /**
     * Mutator that sets the
     * city of the job posting.
     * 
     * @param city 
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Accessor that gets the
     * state of the job posting.
     * 
     * @return 
     */
    public String getState() {
        return state;
    }

    /**
     * Mutator that sets the state
     * of the job posting.
     * 
     * @param state 
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Accessor that gets whether
     * the job is full time which is
     * represented by true or part time
     * which is represented by false.
     * 
     * @return 
     */
    public boolean isFullTime() {
        return fullTime;
    }

    /**
     * Mutator that sets whether the
     * job is a full time for true
     * or a part time position for false.
     * 
     * @param fullTime 
     */
    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    /**
     * Accessor that gets the category
     * of the job posting.
     * 
     * @return 
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Mutator that sets the
     * category of the job posting.
     * 
     * @param department 
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Accessor that retrieves the
     * desired skill level of the 
     * job posting.
     * 
     * @return 
     */
    public String getExperience() {
        return experience;
    }

    /**
     * Mutator that sets the
     * desired experience level of
     * the job posting.
     * 
     * @param experience 
     */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * Accessor that gets the type
     * of wage the job presents (i.e.
     * hourly/salaried).
     * @return 
     */
    public String getWageCategory() {
        return wageCategory;
    }

    /**
     * Mutator that sets the job
     * as either being an hourly or
     * a salaried job posting.
     * 
     * @param wageCategory 
     */
    public void setWageCategory(String wageCategory) {
        this.wageCategory = wageCategory;
    }

    /**
     * Accessor that gets
     * the salary of the
     * job posting.
     * 
     * @return 
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Mutator that sets the
     * salary of the job posting.
     * 
     * @param salary 
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    /**
     * Accessor that returns the job
     * description.
     * 
     * @return 
     */
    public String getJobDescription() {
        return jobDescription;
    }
    
    public void setjobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
    
    /**
     * Accessor for converted LocalDate.
     * 
     * @return 
     */
    public Date getConvDateCreated() {
        return convDateCreated;
    }

    /**
     * Mutator for converted LocalDate
     * 
     * @param convDateCreated 
     */
    public void setConvDateCreated(Date convDateCreated) {
        this.convDateCreated = convDateCreated;
    }

    /**
     * To string method that prints out
     * details of the job posting in 
     * human-readable format.
     * 
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(this.title).append("\n");
        sb.append("Location: ").append(this.city).append(", ").append(
            this.state).append("\n");
        sb.append("Department: ").append(this.department).append("\n");
        sb.append("Status: ").append(
                this.active == true ? "Active" : "Inactive");
        return sb.toString();
    }
    
    /**
     * Compares job postings
     * by day they were created 
     * and if the dates are the same
     * then they are compared by 
     * their lexicographical titles.
     * 
     * @param other
     * @return 
     */
    @Override
    public int compareTo(Job other) {
        if (this.dateCreated.compareTo(other.dateCreated) != 0) {
            return this.dateCreated.compareTo(other.dateCreated);
        } else {
            return this.title.compareTo(other.title);
        }
    }
    
}
