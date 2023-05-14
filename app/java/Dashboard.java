package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/* Dashboard for Normal Users */
public class Dashboard extends AppCompatActivity {

    /* CardViews for View Jobs, View Applications, Profile and Logout */
    CardView cardViewJobsNormal,cardViewApplicationsNormal,cardProfileNormal,cardLogoutNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /* Finding the Views*/
        cardViewJobsNormal = findViewById(R.id.cardViewJobsNormal);
        cardViewApplicationsNormal = findViewById(R.id.cardViewApplicationsNormal);
        cardProfileNormal = findViewById(R.id.cardProfileNormal);
        cardLogoutNormal = findViewById(R.id.cardLogoutNormal);

        /* Receiving the data from Intent*/
        Intent intent =getIntent();
        int userId = intent.getIntExtra("UserId",0);

        /* Setting onClick listener for View Applications*/
        cardViewApplicationsNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Start ViewApplications Activity */
                Intent intent = new Intent(Dashboard.this,ViewApplications.class);
                intent.putExtra("UserId", userId);
                startActivity(intent);
            }
        });

        /* Setting onClick Listener for View Jobs*/
        cardViewJobsNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Start ViewJobsNormal Activity*/
                Intent intent = new Intent(Dashboard.this,ViewJobsNormal.class);
                intent.putExtra("UserId", userId);
                startActivity(intent);
            }
        });

        /* Setting onClick Listener for Profile */
        cardProfileNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Start Profile Activity*/
                Intent intent = new Intent(Dashboard.this,Profile.class);
                intent.putExtra("UserId",userId);
                startActivity(intent);
            }
        });

        /* Setting onClick listener for Logout */
        cardLogoutNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Start Main Activity*/
                Intent intent = new Intent(Dashboard.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });



    }


    /*Overriding the Back button and Making tthe user to press Logout to exit*/
    @Override
    public void onBackPressed() {
        Toast.makeText(this,"PRESS LOGOUT TO EXIT", Toast.LENGTH_SHORT).show();
    }
}