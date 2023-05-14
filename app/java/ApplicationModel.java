package com.example.assignment;

/* Model Class for Application */
public class ApplicationModel {

    /* Variable Required */
    private int appId;
    private int userId;
    private int jobId;
    private String JobName;
    private String JobOrganisation;
    private String JobLocation;

    /* Constructor for Application Class */
    public ApplicationModel(int appId, int userId, int jobId, String jobName, String jobOrganisation, String jobLocation) {
        this.appId = appId;
        this.userId = userId;
        this.jobId = jobId;
        JobName = jobName;
        JobOrganisation = jobOrganisation;
        JobLocation = jobLocation;
    }

    /* Getters and Setters */
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }

    public String getJobOrganisation() {
        return JobOrganisation;
    }

    public void setJobOrganisation(String jobOrganisation) {
        JobOrganisation = jobOrganisation;
    }

    public String getJobLocation() {
        return JobLocation;
    }

    public void setJobLocation(String jobLocation) {
        JobLocation = jobLocation;
    }
}
