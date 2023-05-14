package com.example.assignment;

/* Model Class for Applicants */
public class Applicants {

    /* Variables Required */
    private int appId;
    private String fullName;
    private String email;
    private String pNo;

    /* Constructor for Applicants Class */
    public Applicants(int appId, String fullName, String email, String pNo) {
        this.appId = appId;
        this.fullName = fullName;
        this.email = email;
        this.pNo = pNo;
    }
     /* Getters and Setters */
    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getpNo() {
        return pNo;
    }

    public void setpNo(String pNo) {
        this.pNo = pNo;
    }
}
