package com.example.assignment;

/* Model class for Jobs */
public class Jobs {

    /* Variables required */
    private int id;
    private String jobName;
    private String location;
    private String zipCode;
    private String organisation;
    private String description;
    private String jobType;
    private int salary;
    private String requirements;
    private String benefits;
    private int positions;
    private int applicantsNo;

    /* Constructor for all varaibles */
    public Jobs(int id, String jobName, String location, String zipCode, String organisation, String description, String jobType, int salary, String requirements, String benefits, int positions, int applicantsNo) {
        this.id = id;
        this.jobName = jobName;
        this.location = location;
        this.zipCode = zipCode;
        this.organisation = organisation;
        this.description = description;
        this.jobType = jobType;
        this.salary = salary;
        this.requirements = requirements;
        this.benefits = benefits;
        this.positions = positions;
        this.applicantsNo = applicantsNo;
    }

    /* Constructor for all variables excluding applicants No */
    public Jobs(int id, String jobName, String location, String zipCode, String organisation, String description, String jobType, int salary, String requirements, String benefits, int positions) {
        this.id = id;
        this.jobName = jobName;
        this.location = location;
        this.zipCode = zipCode;
        this.organisation = organisation;
        this.description = description;
        this.jobType = jobType;
        this.salary = salary;
        this.requirements = requirements;
        this.benefits = benefits;
        this.positions=positions;
    }

    /* Constructor for all variables except id and application no */


    public Jobs(String jobName, String location, String zipCode, String organisation, String description, String jobType, int salary, String requirements, String benefits, int positions) {
        this.jobName = jobName;
        this.location = location;
        this.zipCode = zipCode;
        this.organisation = organisation;
        this.description = description;
        this.jobType = jobType;
        this.salary = salary;
        this.requirements = requirements;
        this.benefits = benefits;
        this.positions=positions;
    }

    /* Getters and Setters*/
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getApplicantsNo() {
        return applicantsNo;
    }

    public void setApplicantsNo(int applicantsNo) {
        this.applicantsNo = applicantsNo;
    }

    public int getPositions() {
        return positions;
    }

    public void setPositions(int positions) {
        this.positions = positions;
    }

    public int getId() {
        return id;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }
}
