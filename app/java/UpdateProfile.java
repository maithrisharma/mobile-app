package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/* Update Profile Activity */
public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        EditText fullNameUpdate, emailAddressUpdate, organisationUpdate, phoneNumberUpdate, userNameUpdate, passwordUpdate;
        Button updateProfile;
        /* Receiving Values from Intent */
        Intent intent = getIntent();
        int userId = intent.getIntExtra("UserId",0);
        String fullName = intent.getStringExtra("FullName");
        String emailAddress = intent.getStringExtra("Email");
        String organisation = intent.getStringExtra("Org");
        String phoneNumber = intent.getStringExtra("pNo");
        String userNameIntent = intent.getStringExtra("userName");
        String pass = intent.getStringExtra("pass");
        int rank = intent.getIntExtra("rank",0);

        /* Finding Views from Layout XML */
        fullNameUpdate = findViewById(R.id.fullNameUpdate);
        emailAddressUpdate = findViewById(R.id.emailAddressUpdate);
        organisationUpdate = findViewById(R.id.organisationUpdate);
        phoneNumberUpdate = findViewById(R.id.phoneNumberUpdate);
        userNameUpdate = findViewById(R.id.userNameUpdate);
        passwordUpdate = findViewById(R.id.passwordUpdate);
        updateProfile = findViewById(R.id.updateProfile);

        /* Database connection */
        dbConnect db = new dbConnect(this);

        /* Setting Fields with data received from Intent */
        fullNameUpdate.setText(fullName);
        emailAddressUpdate.setText(emailAddress);
        organisationUpdate.setText(organisation);
        phoneNumberUpdate.setText(phoneNumber);
        userNameUpdate.setText(userNameIntent);
        passwordUpdate.setText(pass);

        /* Setting onClick listener to Update Profile Button */
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = fullNameUpdate.getText().toString();
                String email = emailAddressUpdate.getText().toString();
                String org = organisationUpdate.getText().toString();
                String pNo = phoneNumberUpdate.getText().toString();
                String userName = userNameUpdate.getText().toString();
                String password = passwordUpdate.getText().toString();
                Boolean update;
                Intent intent1;

                /* Checking for Empty Fields */
                if(fname.equals("") || email.equals("") || org.equals("") || pNo.equals("") || userName.equals("") || password.equals("")){
                    Toast.makeText(UpdateProfile.this,"ALL FIELDS REQUIRED",Toast.LENGTH_SHORT).show();
                }
                /* Validation for Full Name containing only ALphabets */
                else if(!(isAlpha(fname))){
                    Toast.makeText(UpdateProfile.this,"FULL NAME SHOULD CONTAIN ONLY ALPHABETS",Toast.LENGTH_SHORT).show();
                }
                /* Validation for Email  */
                else if(!(isEmail(email))){
                    Toast.makeText(UpdateProfile.this, "ENTER VALID EMAIL ADDRESS",Toast.LENGTH_SHORT).show();
                }
                /* Validation for organisation containing only alphabets */
                else if(!(isAlpha(org))){
                    Toast.makeText(UpdateProfile.this,"ORGANISATION SHOULD CONTAIN ONLY ALPHABETS",Toast.LENGTH_SHORT).show();
                }
                /* Validation for phone number */
                else if(!(isPhoneNumber(pNo))){
                    Toast.makeText(UpdateProfile.this,"PHONE NUMBER SHOULD CONTAIN 10 DIGITS",Toast.LENGTH_SHORT).show();
                }
                /* Validation for Username and password */
                else if(!(isAlphaNumeric(userName)) || !(isAlphaNumeric(password))){
                    Toast.makeText(UpdateProfile.this,"USERNAME OR PASSWORD SHOULD CONTAIN ONLY ALPHANUMERICS",Toast.LENGTH_SHORT).show();
                }
                else {
                    /* If User Name and Email are not changed */
                    if (userNameIntent.equals(userName) && emailAddress.equals(email)) {
                        /* Creating Users object */
                        Users updatedUser = new Users(userId, fname, email, org, pNo, userName, password, rank);
                        update = db.updateUsers(updatedUser);
                        if (update) {
                            /* Displaying Success Message and Starting Profile Activity */
                            Toast.makeText(UpdateProfile.this, "UPDATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            intent1 = new Intent(UpdateProfile.this, Profile.class);
                            intent1.putExtra("UserId",userId);
                            /* Finish the current Update Profile Activity */
                            finish();
                            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);

                        } else {
                            /* Displaying the Error Message in Toast */
                            Toast.makeText(UpdateProfile.this, "UPDATION FAILED", Toast.LENGTH_SHORT).show();
                        }

                    }
                    /* If User Name is not changed but email is changed */
                    else if (userNameIntent.equals(userName) && !(emailAddress.equals(email))) {
                        /* Check if email updated already exists in database */
                        Boolean checkUserEmail = db.checkUserEmail(email);

                        if (!checkUserEmail) {
                            /* Creating Users Object */
                            Users updatedUser = new Users(userId, fname, email, org, pNo, userName, password, rank);
                            update = db.updateUsers(updatedUser);
                            /* If Updated succesfully, Show a Toast Message and Start Profile Activity */
                            if (update) {
                                Toast.makeText(UpdateProfile.this, "UPDATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                intent1 = new Intent(UpdateProfile.this, Profile.class);
                                intent1.putExtra("UserId",userId);
                                /* Finish the current Update Profile Activity */
                                finish();
                                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent1);

                            } else {
                                /* Displaying the error message in Toast if Updation failed */
                                Toast.makeText(UpdateProfile.this, "UPDATION FAILED", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            /* Display Error Message if email entered already exists in Database */
                            Toast.makeText(UpdateProfile.this, "EMAIL ALREADY TAKEN", Toast.LENGTH_SHORT).show();
                        }

                    }
                    /* If Username is changed and email is not changed */
                    else if (!(userNameIntent.equals(userName)) && emailAddress.equals(email)) {
                        /* check if username already exists in the database */
                        Boolean checkUser = db.checkUser(userName);
                        if (!checkUser) {
                            Users updatedUser = new Users(userId, fname, email, org, pNo, userName, password, rank);
                            update = db.updateUsers(updatedUser);
                            /* If Updated succesfully, Show a Toast Message and Start Profile Activity */
                            if (update) {
                                Toast.makeText(UpdateProfile.this, "UPDATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                intent1 = new Intent(UpdateProfile.this, Profile.class);
                                /* Finish the current Activity */
                                finish();
                                intent1.putExtra("UserId",userId);
                               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent1);

                            } else {
                                /* Display error message in Toast if updation failed */
                                Toast.makeText(UpdateProfile.this, "UPDATION FAILED", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            /* Display error message if username already exists in database*/
                            Toast.makeText(UpdateProfile.this, "USERNAME ALREADY TAKEN", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        /* If both username and email are changed */
                        /* check if any of them already exists in database */
                        Boolean checkUser = db.checkUserName(userName, email);
                        if (!checkUser) {
                            /* Creating Users Object */
                            Users updatedUser = new Users(userId, fname, email, org, pNo, userName, password, rank);
                            update = db.updateUsers(updatedUser);
                            /* If Updated succesfully, Show a Toast Message and Start Profile Activity */
                            if (update) {

                                Toast.makeText(UpdateProfile.this, "UPDATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                intent1 = new Intent(UpdateProfile.this, Profile.class);
                                intent1.putExtra("UserId",userId);
                                /* Finish the current Update Profile Activity */
                                finish();
                                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent1);

                            } else {
                                /* Display error message in Toast if updation failed*/
                                Toast.makeText(UpdateProfile.this, "UPDATION FAILED", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            /* Display error message if any of them is already taken by other user*/
                            Toast.makeText(UpdateProfile.this, "USERNAME OR EMAIL ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }

    /* Regex pattern and validation for strings containing alphanumberics with spaces */
    public boolean isAlphaNumeric(String s){
        String pattern= "^[a-zA-Z0-9]+$";
        return s.matches(pattern);
    }
    /* Regex pattern and validation for strings containing alphabets with spaces */
    public boolean isAlpha(String s){
        String pattern= "^[a-zA-Z ]+$";
        return s.matches(pattern);
    }
    /* Regex pattern and validation for phone numbers */
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