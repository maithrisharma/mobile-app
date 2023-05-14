package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

/* View Applications Activity */
public class ViewApplications extends AppCompatActivity {

    /* Variables Required */
    ArrayList<ApplicationModel> list = new ArrayList<>();

    private dbConnect db;
    private ViewAppAdapter viewAppAdapter;
    private RecyclerView viewAppRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applications);
        /* Finding the view from Layout XML */
        viewAppRec = findViewById(R.id.viewApplicationsRec);
        Intent intent =getIntent();
        int userId = intent.getIntExtra("UserId",0);
        /* Setting Layout Manager for Recycler View*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewApplications.this, RecyclerView.VERTICAL, false);
        viewAppRec.setLayoutManager(linearLayoutManager);

        /* Database Connection */
        db = new dbConnect(ViewApplications.this);

        list = db.getApplications(userId);
        //Log.i("List", "" + list);

        /* Setting up the View Applications Adapter by sending the list received from Database */
        viewAppAdapter = new ViewAppAdapter(list,ViewApplications.this);

        viewAppRec.setAdapter(viewAppAdapter);
    }


}