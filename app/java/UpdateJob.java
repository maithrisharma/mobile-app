package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/* Update Job Activity */
public class UpdateJob extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /* Variables Required */
    String[] jobType = {"Full Time", "Part Time", "Permanent", "Contract"};
    String jobTypeSelected;
    Spinner jobTypeUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_job);
        TextView jobIdUpdate;
        EditText jobNameUpdate,jobLocationUpdate, jobZipCodeUpdate, jobOrganisationUpdate, jobDescriptionUpdate, jobSalaryUpdate, jobRequirementsUpdate;
        EditText jobBenefitsUpdate, jobPositionsUpdate;
        Button updateJob;

        /* Finding views from Layout XML */
        jobIdUpdate = findViewById(R.id.jobIdUpdate);
        jobNameUpdate = findViewById(R.id.jobNameUpdate);
        jobLocationUpdate = findViewById(R.id.jobLocationUpdate);
        jobZipCodeUpdate = findViewById(R.id.jobZipCodeUpdate);
        jobOrganisationUpdate = findViewById(R.id.jobOrganisationUpdate);
        jobDescriptionUpdate = findViewById(R.id.jobDescriptionUpdate);
        jobSalaryUpdate = findViewById(R.id.jobSalaryUpdate);
        jobRequirementsUpdate = findViewById(R.id.jobRequirementsUpdate);
        jobBenefitsUpdate = findViewById(R.id.jobBenefitsUpdate);
        jobPositionsUpdate = findViewById(R.id.jobPositionsUpdate);
        jobTypeUpdate = (Spinner) findViewById(R.id.jobTypeUpdate);

        /* Receiving the data from Intent */
        Intent intent = getIntent();
        String jobNameIntent= intent.getStringExtra("JobName");
        String jobId = intent.getStringExtra("JobId");
        String jobLoc = intent.getStringExtra("JobLocation");
        String jobZip = intent.getStringExtra("JobZipCode");
        String jobOrg = intent.getStringExtra("JobOrg");
        String jobDesc = intent.getStringExtra("JobDesc");
        String jobTypeIntent = intent.getStringExtra("JobType");
        String jobSal = intent.getStringExtra("JobSalary");
        String jobReq = intent.getStringExtra("JobReq");
        String jobBen = intent.getStringExtra("JobBen");
        String jobPos = intent.getStringExtra("JobPos");
        int jobApplicants = intent.getIntExtra("ApplicantsNo",0);

        /* Setting the fields from data passed through Intent */
        jobNameUpdate.setText(jobNameIntent);
        jobIdUpdate.setText(String.valueOf(jobId));
        jobLocationUpdate.setText(jobLoc);
        jobZipCodeUpdate.setText(jobZip);
        jobOrganisationUpdate.setText(jobOrg);
        jobDescriptionUpdate.setText(jobDesc);
        jobSalaryUpdate.setText(String.valueOf(jobSal));
        jobRequirementsUpdate.setText(jobReq);
        jobBenefitsUpdate.setText(jobBen);
        jobPositionsUpdate.setText(String.valueOf(jobPos));

        /* Setting Onclick listener for Job Type Spinner */
        jobTypeUpdate.setOnItemSelectedListener(this);
        updateJob = findViewById(R.id.updateJob);

        /* Database Connection */
        dbConnect db = new dbConnect(this);
        /* Setting Adapter for spinner */
        ArrayAdapter<String> myAdapater = new ArrayAdapter<String>(UpdateJob.this,
                android.R.layout.simple_spinner_item,jobType);
        myAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobTypeUpdate.setAdapter((myAdapater));
        jobTypeUpdate.setSelection(myAdapater.getPosition(jobTypeIntent));

        /* Setting Onclick listener for Update Job Button */
        updateJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jobName = jobNameUpdate.getText().toString();
                String jobLocation = jobLocationUpdate.getText().toString();
                String jobZipCode = jobZipCodeUpdate.getText().toString();
                String jobOrg = jobOrganisationUpdate.getText().toString();
                String jobDesc = jobDescriptionUpdate.getText().toString();
                String jobSalary = jobSalaryUpdate.getText().toString();

                String jobReq = jobRequirementsUpdate.getText().toString();
                String jobBenefits = jobBenefitsUpdate.getText().toString();
                String jobPositions = jobPositionsUpdate.getText().toString();

                int jobIdNum = Integer.parseInt(jobId);

                /* Checking for empty fields */
                if(jobName.equals("") || jobLocation.equals("") || jobZipCode.equals("") || jobOrg.equals("") || jobDesc.equals("") || jobSalary.equals("") || jobReq.equals("") || jobBenefits.equals("") || jobPositions.equals("")){
                    Toast.makeText(UpdateJob.this,"ALL FIELDS REQUIRED",Toast.LENGTH_SHORT).show();
                }
                /* Validation for Job Name containing only Alphabets */
                else if(!(isAlpha(jobName))){
                    Toast.makeText(UpdateJob.this,"JOB NAME SHOULD CONTAIN ONLY ALPHABETS",Toast.LENGTH_SHORT).show();
                }
                /* Validation for Job Location containg only Alphabets */
                else if(!(isAlpha(jobLocation))){
                    Toast.makeText(UpdateJob.this,"LOCATION SHOULD CONTAIN ONLY ALPHABETS",Toast.LENGTH_SHORT).show();
                }
                /* Validation for Job ZipCode containg only AphaNumerics */
                else if(!(isAlphaNumeric(jobZipCode))){
                    Toast.makeText(UpdateJob.this,"ZIPCODE SHOULD CONTAIN ONLY ALPHANUMERICS",Toast.LENGTH_SHORT).show();
                }
                /* Validation for Job Organisation containg only Alphabets */
                else if(!(isAlpha(jobOrg))){
                    Toast.makeText(UpdateJob.this,"ORGANISATION SHOULD CONTAIN ONLY ALPHABETS",Toast.LENGTH_SHORT).show();
                }
                else {
                    int salary = Integer.parseInt(jobSalary);
                    int positions = Integer.parseInt(jobPositions);
                    /* Creating Jobs Object */
                    Jobs jobs = new Jobs(jobIdNum,jobName,jobLocation,jobZipCode,jobOrg,jobDesc,jobTypeSelected,salary,jobReq,jobBenefits,positions,jobApplicants);
                    Boolean update = db.updateJobs(jobs);
                    if(update){
                        /* Display Success Message and start View Jobs Activity*/
                        Toast.makeText(UpdateJob.this,"JOB UPDATED SUCCESSFULLY",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(UpdateJob.this, ViewJobs.class);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        /* Finish the current Update Job Activity */
                        finish();
                        startActivity(intent);
                        //finish();

                    }
                    else{
                        /* Display the error Message */
                        Toast.makeText(UpdateJob.this,"UPDATION FAILED.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    /* OnItem Selected for spinner */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        jobTypeSelected = jobType[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /* Regex pattern and validation for AlphaNumerics */
    public boolean isAlphaNumeric(String s){
        String pattern= "^[a-zA-Z0-9 ]+$";
        return s.matches(pattern);
    }
    /* Regex pattern and validation for containing only alphabets */
    public boolean isAlpha(String s){
        String pattern= "^[a-zA-Z ]+$";
        return s.matches(pattern);
    }
}