package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/* Dashboard for Admin */
public class Home extends AppCompatActivity {
    /* CardViews for Add Jobs, View Jobs, Profile and Logout */
    CardView cardViewJobs,cardAddJob,cardProfile,cardLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /* Finding the Views*/
        cardViewJobs = findViewById(R.id.cardViewJobs);
        cardAddJob = findViewById(R.id.cardAddJob);
        cardProfile = findViewById(R.id.cardProfile);
        cardLogout = findViewById(R.id.cardLogout);

        /* Receiving the data from Intent*/
        Intent intent =getIntent();
        int userId = intent.getIntExtra("UserId",0);

        /* Setting onClick listener for Adding Jobs */
        cardAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,AddJob.class);
                startActivity(intent);
            }
        });

        /* Setting onClick listener for View Jobs*/
        cardViewJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,ViewJobs.class);
                startActivity(intent);
            }
        });

        /* Setting onClick listener for Profile*/
        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Profile.class);
                intent.putExtra("UserId", userId);
                startActivity(intent);
            }
        });

        /* Setting onClick listener for Logout*/
        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });



    }

    /*Overriding the back button and making the user press Logout to exit*/
    @Override
    public void onBackPressed() {
        Toast.makeText(this,"PRESS LOGOUT TO EXIT", Toast.LENGTH_SHORT).show();

    }
}