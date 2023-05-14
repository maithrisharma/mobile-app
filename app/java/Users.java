package com.example.assignment;

/* Model class for Users */
public class Users {

    /* Variables required */
    private int id;
    private String fullName;
    private String emailAddress;
    private String organisation;
    private String phoneNumber;
    private String userName;
    private String password;
    private int rank;

    /* Constructor for all variables */
    public Users(int id, String fullName, String emailAddress, String organisation, String phoneNumber, String userName, String password, int rank) {
        this.id = id;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.organisation = organisation;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.rank = rank;
    }

    /* constructor for all except id */
    public Users(String fullName, String emailAddress, String organisation, String phoneNumber, String userName, String password, int rank) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.organisation = organisation;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.rank = rank;
    }

    /* Getters and Setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
