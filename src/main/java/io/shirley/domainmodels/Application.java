package io.shirley.domainmodels;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author Shirley
 */
public class Application implements Comparable<Application> {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Attachment attachment;
    private double desiredSalary;
    private LocalDate earliestStartDate;
    private int id;
    private int jobId;
    private Instant dateTimeSubmitted;
    private boolean active;
    private String firstNameError;
    private String lastNameError;
    private String emailError;
    private String phoneError;
    private String resumeError;
    private String startDateError;
    private String invalidDataError;
    private String jobTitle;

    public Application() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phone = "";
        this.attachment = null;
        this.desiredSalary = 0;
        this.earliestStartDate = null;
        this.id = 0;
        this.jobId = 0;
        this.dateTimeSubmitted = null;
        this.active = false;
        this.firstNameError = "";
        this.lastNameError = "";
        this.emailError = "";
        this.phoneError = "";
        this.resumeError = "";
        this.startDateError = "";
        this.invalidDataError = "";
        this.jobTitle = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public double getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(double desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public LocalDate getEarliestStartDate() {
        return earliestStartDate;
    }

    public void setEarliestStartDate(LocalDate earliestStartDate) {
        this.earliestStartDate = earliestStartDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public Instant getDateTimeSubmitted() {
        return dateTimeSubmitted;
    }

    public void setDateTimeSubmitted(Instant dateTimeSubmitted) {
        this.dateTimeSubmitted = dateTimeSubmitted;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstNameError() {
        return firstNameError;
    }

    public void setFirstNameError(String firstNameError) {
        this.firstNameError = firstNameError;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getResumeError() {
        return resumeError;
    }

    public void setResumeError(String resumeError) {
        this.resumeError = resumeError;
    }

    public String getStartDateError() {
        return startDateError;
    }

    public void setStartDateError(String startDateError) {
        this.startDateError = startDateError;
    }

    public String getInvalidDataError() {
        return invalidDataError;
    }

    public void setInvalidDataError(String invalidDataError) {
        this.invalidDataError = invalidDataError;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Job Title: " + jobTitle + "\nFirst Name: " + firstName + " Last name: " + lastName;
    }

    @Override
    public int compareTo(Application other) {
        return this.dateTimeSubmitted.compareTo(other.dateTimeSubmitted);
    }
    
    
    
    
}
