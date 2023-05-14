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
import android.widget.Toast;

/* Register Activity */
public class register extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    /* Variables Required */
    String[] ranks = {"Admin", "Normal User"};
    String rankSelected;
    Spinner mySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /* Finding the views from Layout XML */
        EditText fullName= findViewById(R.id.fullName);
        EditText email= findViewById(R.id.email);
        EditText organisation= findViewById(R.id.organisation);
        EditText phoneNumber= findViewById(R.id.phoneNumber);
        EditText userName= findViewById(R.id.regUserName);
        EditText password= findViewById(R.id.regPassword);
        Button login=findViewById(R.id.regLogin);
        Button register =findViewById(R.id.regRegister);
        /* Dropdown to select if the user is Admin or Normal User*/
        mySpinner = (Spinner) findViewById(R.id.spinner1);
        /* Setting Onclick Listener for Spinner (Drop down )*/
        mySpinner.setOnItemSelectedListener(this);

        /* Database Connection */
        dbConnect Db= new dbConnect(this);

        /* Setting Adapter for spinner */
        ArrayAdapter<String> myAdapater = new ArrayAdapter<String>(register.this,
                android.R.layout.simple_spinner_item,ranks);
        myAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter((myAdapater));

        /* Setting Onclick listener for Register Button */
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Getting the data from Edit Text Views */
                String fname= fullName.getText().toString();
                String emailId= email.getText().toString();
                String org=organisation.getText().toString();
                String pNumber= phoneNumber.getText().toString();
                String uName= userName.getText().toString();
                String pass= password.getText().toString();
                int rank;
                /* If Rank selected in Dropdown is Admin, insert rank=1 in database */
                if(rankSelected=="Admin"){
                    rank=1;
                }
                else{
                    rank=2;
                }

                /* Checking for empty fields */
                if(fname.equals("") || emailId.equals("") || org.equals("") || pNumber.equals("") || uName.equals("") || pass.equals("")){
                    Toast.makeText(register.this,"ALL FIELDS REQUIRED",Toast.LENGTH_SHORT).show();
                }

                /* Validation for Full Name consisting of only Alphabets */
                else if(!(isAlpha(fname))){
                    Toast.makeText(register.this,"FULL NAME SHOULD CONTAIN ONLY ALPHABETS",Toast.LENGTH_SHORT).show();
                }
                /* Validation for Email */
                else if(!(isEmail(emailId))){
                    Toast.makeText(register.this, "ENTER VALID EMAIL ADDRESS",Toast.LENGTH_SHORT).show();
                }
                /* Validation for Organisation consisting of only Alphabets */
                else if(!(isAlpha(org))){
                    Toast.makeText(register.this,"ORGANISATION SHOULD CONTAIN ONLY ALPHABETS",Toast.LENGTH_SHORT).show();
                }
                /* Validation for Phone Number containing 10 digits and starts with 6-9 */
                else if(!(isPhoneNumber(pNumber))){
                    Toast.makeText(register.this,"PHONE NUMBER SHOULD CONTAIN 10 DIGITS AND START WITH 6-9 ",Toast.LENGTH_SHORT).show();
                }
                /* Validation for UserName and Password conating AlphaNumeric Characters */
                else if(!(isAlphaNumeric(uName)) || !(isAlphaNumeric(pass))){
                    Toast.makeText(register.this,"USERNAME OR PASSWORD SHOULD CONTAIN ONLY ALPHANUMERICS",Toast.LENGTH_SHORT).show();
                }

                else{
                    Boolean checkuser=Db.checkUserName(uName,emailId);
                    if(checkuser==false){
                        Users user;
                            /* Creating User Object */
                            user = new Users(fname, emailId, org, pNumber, uName, pass, rank);
                            /*  Adding user to Users Table*/
                            int userId= Db.addUser(user);
                            if(userId!=0){
                                if(rank==1){
                                    /* Display Success Message and Start Home Activity */
                                    Toast.makeText(register.this,"REGISTERED SUCCESSFULLY.",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(register.this,Home.class);
                                    intent.putExtra("UserId",userId);
                                    startActivity(intent);
                                    /* Finish current Register Activity */
                                    finish();
                                }
                                else{
                                    /* Display success Message adn Start Dashboard Activity */
                                    Toast.makeText(register.this,"REGISTERED SUCCESSFULLY.",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(register.this,Dashboard.class);
                                    intent.putExtra("UserId",userId);
                                    startActivity(intent);
                                    /* Finish current Register Activity */
                                    finish();
                                }


                            }
                            else{
                                /* Display Error Message in Toast */
                                Toast.makeText(register.this,"REGISTERATION FAILED.",Toast.LENGTH_SHORT).show();
                            }



                    }
                    else{

                        /* Display Message if User already exists and start Main Activity to Log in */
                        Toast.makeText(register.this,"USER ALREADY EXISTS. PLEASE SIGN IN",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(register.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        });

        /* Setting Onclick Listener for Login Button */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Start Main Activity */
                Intent i= new Intent(register.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    /* On Item selected in the dropdown */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        rankSelected=ranks[i];
        //Toast.makeText(getApplicationContext(),ranks[i],Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /* Regex Pattern and validation for AlphaNumerics */
    public boolean isAlphaNumeric(String s){
        String pattern= "^[a-zA-Z0-9]+$";
        return s.matches(pattern);
    }

    /* Regex Pattern and validation for alphabet and spaces */
    public boolean isAlpha(String s){
        String pattern= "^[a-zA-Z ]+$";
        return s.matches(pattern);
    }
    /* Regex Pattern for Phone Number */
    public boolean isPhoneNumber(String s){
        String pattern= "^[6-9][0-9]{9}$";
        return s.matches(pattern);
    }
    /*Regex Pattern for email */
    public boolean isEmail(String s){
        String pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return s.matches(pattern);
    }
}