package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

/* Add job Activity */
public class AddJob extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    /* Initializing Variables */
    String[] jobType = {"Full Time", "Part Time", "Permanent", "Contract"};
    String jobTypeSelected;
    Spinner jobTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        /* Getting the views by id from XML layout resource */
        EditText jobName, location, zipCode, jobOrganisation, description, salary, requirements, benefits, positions;
        Button addJob;
        jobName = findViewById(R.id.jobName);
        location = findViewById(R.id.location);
        zipCode =  findViewById(R.id.zipCode);
        jobOrganisation = findViewById(R.id.jobOrganisation);
        description = findViewById(R.id.description);
        salary = findViewById(R.id.salary);
        requirements = findViewById(R.id.requirements);
        benefits = findViewById(R.id.benefits);
        positions = findViewById(R.id.positions);
        addJob = findViewById(R.id.addJob);
        jobTypeSpinner = (Spinner) findViewById(R.id.jobTypeSpinner);

        /* Setting OnClick listener for spinner */
        jobTypeSpinner.setOnItemSelectedListener(this);

        /* Database Connection */
        dbConnect db = new dbConnect(this);

        /* Setting the Adapter for Spinner (Dropdown) */
        ArrayAdapter<String> myAdapater = new ArrayAdapter<String>(AddJob.this,
                android.R.layout.simple_spinner_item,jobType);
        myAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobTypeSpinner.setAdapter((myAdapater));

        /* Setting OnCLick Listeners to  Add Button */
        addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Getting Text from EditText's */
                String jName = jobName.getText().toString();
                String loc = location.getText().toString();
                String zip = zipCode.getText().toString();
                String jOrg = jobOrganisation.getText().toString();
                String desc = description.getText().toString();
                String sal = salary.getText().toString();

                String req = requirements.getText().toString();
                String jBenefits = benefits.getText().toString();
                String pos = positions.getText().toString();


                /* Validation for empty fields */
                if(jName.equals("") || loc.equals("") || zip.equals("") || jOrg.equals("") || desc.equals("") || sal.equals("") || req.equals("") || jBenefits.equals("")){
                    Toast.makeText(AddJob.this,"ALL FIELDS REQUIRED",Toast.LENGTH_SHORT).show();
                }

                /*  Validation and Toast for Job Name*/
                else if(!(isAlpha(jName))){
                    Toast.makeText(AddJob.this,"JOB NAME SHOULD CONTAIN ONLY ALPHABETS",Toast.LENGTH_SHORT).show();
                }

                /* Validation and Toast for Location */
                else if(!(isAlpha(loc))){
                    Toast.makeText(AddJob.this,"LOCATION SHOULD CONTAIN ONLY ALPHABETS",Toast.LENGTH_SHORT).show();
                }

                /* Validation and Toast for ZipCode */
                else if(!(isAlphaNumeric(zip))){
                    Toast.makeText(AddJob.this,"ZIPCODE SHOULD CONTAIN ONLY ALPHANUMERICS AND SPACES",Toast.LENGTH_SHORT).show();
                }

                /* Validation and Toast for Organisation */
                else if(!(isAlpha(jOrg))){
                    Toast.makeText(AddJob.this,"ORGANISATION SHOULD CONTAIN ONLY ALPHABETS",Toast.LENGTH_SHORT).show();
                }

                else{
                    int jobSalary = Integer.parseInt(sal);
                    int jobPositions = Integer.parseInt(pos);
                    /* Checking if Job Name, Organisation entered already exists in the database*/
                    Boolean checkJob = db.checkJob(jName.toLowerCase(),jOrg.toLowerCase());
                    if(!checkJob){
                        /* Creating Jobs Object with data from Textfields */
                        Jobs jobs = new Jobs(jName, loc, zip, jOrg, desc, jobTypeSelected, jobSalary, req, jBenefits, jobPositions);
                        Boolean insert= db.addJob(jobs);
                        /* If successfully inserted into database, show Success Toast Message*/
                        if(insert==true){
                            Toast.makeText(AddJob.this,"JOB ADDED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                                /* redirect to Home Activity and finish up Add Job Activity*/
                                Intent intent = new Intent(AddJob.this, Home.class);
                                startActivity(intent);
                                finish();

                        }
                        /* Else Show Error Message in Toast*/
                        else{
                            Toast.makeText(AddJob.this,"INSERTION FAILED.",Toast.LENGTH_SHORT).show();
                        }

                    }

                    /* Show error message in Toast that Job from same organisation already exists*/
                    else{
                        Toast.makeText(AddJob.this,"JOB ALREADY EXISTS. YOU CAN UPDATE NO. OF POSITIONS AVAILABLE.",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(AddJob.this,Home.class);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }

    /* OnItem selected for spinner*/
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        /* Getting the value and storing in local variable*/
        jobTypeSelected = jobType[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /* Validation for AlphaNumeric Patterns*/
    public boolean isAlphaNumeric(String s){
        String pattern= "^[a-zA-Z0-9 ]+$";
        return s.matches(pattern);
    }

    /* validation for ALphabetical Patterns */
    public boolean isAlpha(String s){
        String pattern= "^[a-zA-Z ]+$";
        return s.matches(pattern);
    }

}