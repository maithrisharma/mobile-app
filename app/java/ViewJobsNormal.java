package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;

/* View Jobs Activity for Normal users */
public class ViewJobsNormal extends AppCompatActivity {

    /* Variables Required */
    ArrayList<Jobs> list = new ArrayList<>();

    private dbConnect db;
    private JobAdapterNormal jobAdapter;
    private RecyclerView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs_normal);
        /* Finding View from Layout XML */
        res = findViewById(R.id.recyclerView);
        EditText search = findViewById(R.id.search);
        /* Adding Text Changed Listener for search */
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                /* Filtering the list based on search */
                filter(editable.toString());

            }
        });
        /* Receiving data from Intent */
        Intent intent =getIntent();
        int userId = intent.getIntExtra("UserId",0);

        /* Setting layout manager for recycler view */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewJobsNormal.this, RecyclerView.VERTICAL, false);
        res.setLayoutManager(linearLayoutManager);

        /* Database Connection */
        db = new dbConnect(ViewJobsNormal.this);

        list = db.getJobs();
        //Log.i("List", "" + list);
        /* Setting up Job Adapter for Normal Users and sending the list received from database */
        jobAdapter = new JobAdapterNormal(userId,list, ViewJobsNormal.this);

        res.setAdapter(jobAdapter);

    }
    public  void filter(String text){
        /* Filtered Jobs List based on search */
        ArrayList<Jobs> filteredList = new ArrayList<>();
        for(Jobs job : list){
            if(job.getJobName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(job);
            }
        }
        /* Sending Filtered List to Adapter */
        jobAdapter.filterList(filteredList);
    }

}