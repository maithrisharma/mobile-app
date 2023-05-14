package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
 /* Profile Activity */
public class Profile extends AppCompatActivity {
    /* Variables Required */
    String rank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView fullNameProfile, emailAddressProfile, organisationProfile ,phoneNumberProfile ,userNameProfile, rankProfile;
        Button editProfile;

        /* Finding Views from Layout XML */

        fullNameProfile = findViewById(R.id.fullNameProfile);
        emailAddressProfile = findViewById(R.id.emailAddressProfile);
        organisationProfile = findViewById(R.id.organisationProfile);
        phoneNumberProfile = findViewById(R.id.phoneNumberProfile);
        userNameProfile = findViewById(R.id.userNameProfile);
        rankProfile = findViewById(R.id.rankProfile);


        editProfile = findViewById(R.id.editProfile);
        /* Database Connection */
        dbConnect db = new dbConnect(this);
        /* Getting UserId sent through Intent*/
        Intent intent = getIntent();
        int userId = intent.getIntExtra("UserId",0);
        Users user = db.viewUser(userId);
        fullNameProfile.setText(user.getFullName());
        emailAddressProfile.setText(user.getEmailAddress());
        organisationProfile.setText(user.getOrganisation());
        phoneNumberProfile.setText(user.getPhoneNumber());
        userNameProfile.setText(user.getUserName());
        if(user.getRank()==1){
            rank = "Admin";
        }
        else{
            rank = "Normal User";
        }
        rankProfile.setText(rank);

        /* Setting Onclick listener for Edit Profile Button */
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Starting Update Profile Activity and Sending the data through Intent */
                Intent intent1 = new Intent(Profile.this,UpdateProfile.class);
                intent1.putExtra("UserId",userId);
                intent1.putExtra("FullName",user.getFullName());
                intent1.putExtra("Email",user.getEmailAddress());
                intent1.putExtra("Org",user.getOrganisation());
                intent1.putExtra("pNo",user.getPhoneNumber());
                intent1.putExtra("userName",user.getUserName());
                intent1.putExtra("pass", user.getPassword());
                intent1.putExtra("rank",user.getRank());

                startActivity(intent1);
                /* Finish the Current Profile Activity*/
                finish();
            }
        });
    }
}