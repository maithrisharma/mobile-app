package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

/* View Applicants Activity */
public class ViewApplicants extends AppCompatActivity {

    /* Variables Required */
    RecyclerView viewApplicantsRec;
    dbConnect db;
    ArrayList<Applicants> list = new ArrayList<>();
    ApplicantsAdapter applicantsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicants);

        /* Finding View from Layout XML */
        viewApplicantsRec = findViewById(R.id.applicantsRec);
        Intent intent =getIntent();
        /* Receiving the data from Intent */
        int jobId = intent.getIntExtra("JobId",0);
        /* Setting Linear Layout for the Recycler View */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewApplicants.this, RecyclerView.VERTICAL, false);
        viewApplicantsRec.setLayoutManager(linearLayoutManager);

        /* Database connection */
        db = new dbConnect(ViewApplicants.this);

        list = db.getApplicants(jobId);
        //Log.i("List", "" + list);

        /* Setting up the Applicants Adapter by sending the list received from Database */
        applicantsAdapter = new ApplicantsAdapter(list,ViewApplicants.this);

        viewApplicantsRec.setAdapter(applicantsAdapter);
        //int size = applicantsAdapter.getItemCount();
    }
}