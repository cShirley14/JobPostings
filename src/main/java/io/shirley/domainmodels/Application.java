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
    private boolean firstNameError;
    private boolean lastNameError;
    private boolean emailError;
    private boolean phoneError;
    private boolean resumeError;
    private boolean salaryError;
    private boolean startDateError;
    private boolean invalidDataError;
    private String invalidDataMessage;
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
        this.firstNameError = false;
        this.lastNameError = false;
        this.emailError = false;
        this.phoneError = false;
        this.resumeError = false;
        this.salaryError = false;
        this.startDateError = false;
        this.invalidDataError = false;
        this.invalidDataMessage = "Invalid application entry.";
        this.jobTitle = "";
    }

    public Application(String firstName, String lastName, String email, 
            String phone, Attachment attachment, double desiredSalary, 
            LocalDate earliestStartDate, int id, int jobId, 
            Instant dateTimeSubmitted, boolean active, boolean firstNameError, 
            boolean lastNameError, boolean emailError, boolean phoneError, 
            boolean resumeError, boolean startDateError, 
            boolean invalidDataError, String invalidDataMessage, 
            String jobTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.attachment = attachment;
        this.desiredSalary = desiredSalary;
        this.earliestStartDate = earliestStartDate;
        this.id = id;
        this.jobId = jobId;
        this.dateTimeSubmitted = dateTimeSubmitted;
        this.active = active;
        this.firstNameError = firstNameError;
        this.lastNameError = lastNameError;
        this.emailError = emailError;
        this.phoneError = phoneError;
        this.resumeError = resumeError;
        this.startDateError = startDateError;
        this.invalidDataError = invalidDataError;
        this.invalidDataMessage = invalidDataMessage;
        this.jobTitle = jobTitle;
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

    public boolean getFirstNameError() {
        return firstNameError;
    }

    public void setFirstNameError(boolean firstNameError) {
        this.firstNameError = firstNameError;
    }

    public boolean getLastNameError() {
        return lastNameError;
    }

    public void setLastNameError(boolean lastNameError) {
        this.lastNameError = lastNameError;
    }

    public boolean getEmailError() {
        return emailError;
    }

    public void setEmailError(boolean emailError) {
        this.emailError = emailError;
    }

    public boolean getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(boolean phoneError) {
        this.phoneError = phoneError;
    }

    public boolean getResumeError() {
        return resumeError;
    }

    public void setResumeError(boolean resumeError) {
        this.resumeError = resumeError;
    }

    public boolean getStartDateError() {
        return startDateError;
    }

    public void setStartDateError(boolean startDateError) {
        this.startDateError = startDateError;
    }

    public boolean getInvalidDataError() {
        return invalidDataError;
    }

    public void setInvalidDataError(boolean invalidDataError) {
        this.invalidDataError = invalidDataError;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getInvalidDataMessage() {
        return invalidDataMessage;
    }

    public void setInvalidDataMessage(String invalidDataMessage) {
        this.invalidDataMessage = invalidDataMessage;
    }

    public boolean isSalaryError() {
        return salaryError;
    }

    public void setSalaryError(boolean salaryError) {
        this.salaryError = salaryError;
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
