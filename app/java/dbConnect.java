package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbConnect extends SQLiteOpenHelper {

    /* Database Name = JobAgency */
    private static final String dbName="JobAgency";
    private static  int dbVersion = 7;

    /* JobUsers Table and Column Names */
    private static final String dbTable="JobUsers";
    private static  String ID = "id";
    private static final String fullName = "FullName";
    private static  final String email = "Email";
    private static final String organisation = "Organisation";
    private static final String phoneNumber = "PhoneNumber";
    private static final String userName = "UserName";
    private static final String password = "Password";
    private static final String rank = "Rank";

    /*  Jobs Table and column names*/
    private static final String dbTable1="Jobs";
    private static  String jobID = "id";
    private static String jobName = "JobName";
    private static final String location = "Location";
    private static final String zipCode = "ZipCode";
    private static final String jobOrganisation = "Organisation";
    private static final String description = "Description";
    private static final String jobType = "JobType";
    private static final String salary = "Salary";
    private static final String requirements = "Requirements";
    private static final String benefits = "Benefits";
    private static final String positions = "Positions";
    private static String appNo = "Applications";

    /* Applications Table and Column Names*/
    private static final String dbTable2 = "Applications";
    private static String appId = "AppId";
    private static String userId = "UserId";
    private static String jId = "JobId";
    private static String status = "Status";

    /* Constructor */
    public dbConnect(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    /* Creating the Tables*/
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + dbTable + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + fullName +
                " TEXT," + email + " TEXT," + organisation + " TEXT," + phoneNumber + " TEXT," + userName
                + " TEXT,"+ password + " TEXT," + rank + " INTEGER)";
        String query1 = "CREATE TABLE " + dbTable1 + "(" + jobID + " INTEGER PRIMARY KEY AUTOINCREMENT," + jobName + " TEXT," + location + " TEXT," + zipCode + " TEXT," + jobOrganisation + " TEXT," + description + " TEXT," + jobType + " TEXT," + salary + " INTEGER,"+ requirements + " TEXT," + benefits + " TEXT," + positions + " INTEGER," + appNo + " INTEGER)";
        String query2 = "CREATE TABLE " + dbTable2 + "(" + appId + " INTEGER PRIMARY KEY AUTOINCREMENT," + userId + " INTEGER," + jId + " INTEGER," + status + " TEXT)";
        sqLiteDatabase.execSQL(query);

        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
    }

    /* onUpgrade, Dropping the tables if exists and recreating them */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbTable);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbTable1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbTable2);
        onCreate(sqLiteDatabase);
    }

    /* Function for User Registration */
    public  int addUser(Users users){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(fullName,users.getFullName());
        values.put(email,users.getEmailAddress());
        values.put(organisation,users.getOrganisation());
        values.put(phoneNumber,users.getPhoneNumber());
        values.put(userName,users.getUserName());
        values.put(password,users.getPassword());
        values.put(rank,users.getRank());
        long result=db.insert(dbTable,null,values);
        if(result>0){
            Cursor cursor=db.rawQuery("Select * from JobUsers where lower(UserName) = ? and Password= ?",new String[] {users.getUserName().toLowerCase(),users.getPassword()});
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                return cursor.getInt(0);
            }
        }

            return 0;

    }

    /* Function to check if Username or email already exists in database */
    public Boolean checkUserName(String userName,String emailId){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from JobUsers where lower(UserName) = ? or lower(Email) = ?",new String[] {userName.toLowerCase(),emailId.toLowerCase()});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    /* Function to check if email already exists in database*/
    public Boolean checkUserEmail(String emailId){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from JobUsers where lower(Email) = ?",new String[] {emailId.toLowerCase()});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    /* Function to check if username already exists in database */
    public Boolean checkUser(String userName){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from JobUsers where lower(UserName) = ?",new String[] {userName.toLowerCase()});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    /* Function to check if the username and password are matching with the information from Form*/
    public int checkPassword(String userName, String password){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from JobUsers where lower(UserName) = ? and Password= ?",new String[] {userName.toLowerCase(),password});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        else{
            return 0;
        }
    }

    /* Function to Update user Profile */
    public boolean updateUsers(Users users){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(fullName,users.getFullName());
        values.put(email,users.getEmailAddress());
        values.put(organisation,users.getOrganisation());
        values.put(phoneNumber,users.getPhoneNumber());
        values.put(userName,users.getUserName());
        values.put(password,users.getPassword());
        values.put(rank,users.getRank());
        Cursor cursor= db.rawQuery("Select * from JobUsers where id=?",new String[] {String.valueOf(users.getId())});
        if(cursor.getCount()>0) {
            long result=db.update(dbTable,values,ID + "=?" , new String[]{String.valueOf(users.getId())});
            db.close();
            if(result>0){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }

     /* Function to View User Profile */
    public Users viewUser(int userId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from JobUsers where id = ?", new String[]{String.valueOf(userId)});
        cursor.moveToFirst();
        Users users = new Users(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
        return users;
    }

    /*  Function to delete the user */
    public  boolean  delete(Users users){
        SQLiteDatabase db =this.getWritableDatabase();
        long result=db.delete(dbTable, ID= "=?", new String[]{String.valueOf(users.getId())});
        if(result>0){
            return true;
        }
        else{
            return false;
        }
    }

    /* Function to Add a new Job */
    public Boolean addJob(Jobs jobs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(jobName,jobs.getJobName());
        values.put(location,jobs.getLocation());
        values.put(zipCode, jobs.getZipCode());
        values.put(jobOrganisation,jobs.getOrganisation());
        values.put(description,jobs.getDescription());
        values.put(jobType,jobs.getJobType());
        values.put(salary,jobs.getSalary());
        values.put(requirements,jobs.getRequirements());
        values.put(benefits,jobs.getBenefits());
        values.put(positions,jobs.getPositions());
        values.put(appNo,0);
        long result=db.insert(dbTable1,null,values);
        if(result>0){
            return true;
        }
        else{
            return false;
        }
    }

    /* Function to check if the Job from the Organisation already exists in the database*/
    public Boolean checkJob(String jobName, String organisation){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from Jobs where lower(JobName) = ? and lower(Organisation)= ?",new String[] {jobName,organisation});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    /* Function to update Job information */
    public boolean updateJobs(Jobs jobs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(jobName,jobs.getJobName());
        values.put(location,jobs.getLocation());
        values.put(zipCode, jobs.getZipCode());
        values.put(jobOrganisation,jobs.getOrganisation());
        values.put(description,jobs.getDescription());
        values.put(jobType,jobs.getJobType());
        values.put(salary,jobs.getSalary());
        values.put(requirements,jobs.getRequirements());
        values.put(benefits,jobs.getBenefits());
        values.put(positions,jobs.getPositions());
        values.put(appNo,jobs.getApplicantsNo());
        Cursor cursor= db.rawQuery("Select * from Jobs where id=?",new String[] {String.valueOf(jobs.getId())});
        if(cursor.getCount()>0) {
            long result=db.update(dbTable1,values,jobID + "=?" , new String[]{String.valueOf(jobs.getId())});
            db.close();
            if(result>0){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }


    }

    /* Function to Delete a job */
    public  boolean  deleteJob(int jobId){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor= db.rawQuery("Select * from Jobs where id=?",new String[] {String.valueOf(jobId)});
        if(cursor.getCount()>0) {
            long result = db.delete(dbTable1, jobID + "=?" , new String[]{String.valueOf(jobId)});
            db.close();
            if(result>0){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }


    /* Function to View all Jobs */
    public ArrayList<Jobs> getJobs() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Jobs> jobList= new ArrayList<>();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursor = db.rawQuery("SELECT * FROM " + dbTable1, null);
        while(cursor.moveToNext()){
            Jobs jobs = new Jobs(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),cursor.getString(9),cursor.getInt(10),cursor.getInt(11));
            jobList.add(jobs);
        }
        return jobList;
        // on below line we are creating a new array list.

    }

    /* Function to View all Applicants */
    public ArrayList<Applicants> getApplicants(int jId){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Applicants> applicantsList = new ArrayList<>();
        String query = "select a.AppId,b.FullName,b.Email,b.PhoneNumber from Applications a inner join  JobUsers b on a.userId = b.id where a.JobId=?";
        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(jId)});
        while(cursor.moveToNext()){
            Applicants applicant = new Applicants(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            applicantsList.add(applicant);
        }
        return applicantsList;
    }

    /* Function for a normal user to apply for the job */
    public  int apply(int uId,int jobAppId,int applicantsNo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(userId,uId);
        values.put(jId,jobAppId);
        values.put(status,"Pending");
        Cursor cursor,cursor1;

        long result=db.insert(dbTable2,null,values);

        if(result>0){
            applicantsNo = applicantsNo+1;
            String query = "UPDATE Jobs SET Applications = "+ applicantsNo + " WHERE id = ?";
            cursor1=db.rawQuery(query,new String[]{String.valueOf(jobAppId)});
            cursor1.moveToFirst();
            cursor = db.rawQuery("Select * from Applications where UserId = ? and JobId= ?", new String[]{String.valueOf(uId), String.valueOf(jobAppId)});
            if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    return cursor.getInt(0);
            }

        }

        return 0;

    }

    /* Function to check if the user has already applied for the job */
    public  Boolean checkApply(int uId,int jobAppId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Applications where UserId = ? and JobId= ?",new String[] {String.valueOf(uId),String.valueOf(jobAppId)});
            if(cursor.getCount()>0){
                return false;
            }


        return true;

    }


    /* Function to view all Applications made by a Normal User */
    public ArrayList<ApplicationModel> getApplications(int uid) {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ApplicationModel> applicationList= new ArrayList<>();
        Cursor cursor = db.rawQuery("select a.AppId,a.UserId,a.JobId,b.JobName,b.Organisation,b.Location from Applications a inner join Jobs b on a.jobId=b.id where a.userId=?", new String[]{String.valueOf(uid)});
        while(cursor.moveToNext()){
           // Jobs jobs = new Jobs(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),cursor.getString(9),cursor.getInt(10));
            ApplicationModel application = new ApplicationModel(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            //jobList.add(jobs);
            applicationList.add(application);
        }
        return applicationList;


    }

    /* Function to check the status of the application */
    public String checkStatus(int aId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select Status from Applications where AppId=?",new String[]{String.valueOf(aId)});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else{
            return "NOT AVAILABLE";
        }
    }

    /* Function to change the status to Accepted */
    public Boolean acceptStatus(int aId){
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "UPDATE Applications SET Status='Accepted' WHERE AppId =?";
        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(aId)});
        cursor.moveToFirst();
        cursor = db.rawQuery("select Status from Applications where AppId=?",new String[]{String.valueOf(aId)});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            return true;
        }
        else{
            return false;
        }
    }

    /*Function to change the status to rejected */
    public Boolean rejectStatus(int aId){
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "UPDATE Applications SET Status = 'Rejected' WHERE AppId = ?";
        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(aId)});
        cursor.moveToFirst();
        cursor = db.rawQuery("select Status from Applications where AppId=?",new String[]{String.valueOf(aId)});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            return true;
        }
        else{
            return false;
        }
    }
}
