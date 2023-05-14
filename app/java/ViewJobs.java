package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/* View Jobs for Admin Activity*/
public class ViewJobs extends AppCompatActivity {

    /*Variables Required */
    ArrayList<Jobs> list = new ArrayList<>();

    private dbConnect db;
    private JobAdapter jobAdapter;
    private RecyclerView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);

        /* Finding view from Layout XML */
        res = findViewById(R.id.res);

        /* Setting layout manager for Recycler view */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewJobs.this, RecyclerView.VERTICAL, false);
        res.setLayoutManager(linearLayoutManager);

        /* Database Conenction */
        db = new dbConnect(ViewJobs.this);

        list = db.getJobs();
        //Log.i("List", "" + list);

        /* Setting up Job Adapter and sending the list received from database */
        jobAdapter = new JobAdapter(list, ViewJobs.this);

        res.setAdapter(jobAdapter);

    }

}