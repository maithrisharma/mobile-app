package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/* Adapter for Applicants Recycler View*/
public class ApplicantsAdapter extends RecyclerView.Adapter<ApplicantsAdapter.ViewHolder>{

    /* Variables Required */
    public ArrayList<Applicants> list;
    private Context context;

    /* Constructor*/
    public ApplicantsAdapter( ArrayList<Applicants> list,Context context) {
        this.list = list;
        this.context = context;
        if(list.size()==0){
            Toast.makeText(context,"NO APPLICANTS FOR THIS JOB YET",Toast.LENGTH_SHORT).show();
        }

    }
    /* Instantiate a Layout XML for single item in Recycler View items*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.applicantsrow,parent,false);
        return new ApplicantsAdapter.ViewHolder(view);
    }

    /* Filling the view holders with data*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.aId.setText(String.valueOf(list.get(position).getAppId()));
        holder.fullNameApp.setText(list.get(position).getFullName());
        holder.emailApp.setText(list.get(position).getEmail());
        holder.pNoApp.setText(list.get(position).getpNo());
    }

    /* Returns the size of list*/
    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView aId,fullNameApp,emailApp,pNoApp;
        Button accept,reject;


        public ViewHolder(@NonNull View itemView) {
            /* Finding the views from Layout XML */
            super(itemView);
            aId = itemView.findViewById(R.id.aId);
            fullNameApp = itemView.findViewById(R.id.fullNameApp);
            emailApp = itemView.findViewById(R.id.emailApp);
            pNoApp = itemView.findViewById(R.id.pNoApp);
            accept = itemView.findViewById(R.id.accept);
            reject = itemView.findViewById(R.id.reject);

            /* Setting onClick listener for Accept Button*/
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int appId = Integer.parseInt(aId.getText().toString());
                    /* Database connection*/
                    dbConnect db = new dbConnect(context);
                    Boolean statusUpdate = db.acceptStatus(appId);
                    if(statusUpdate){
                        Toast.makeText(context,"STATUS UPDATED TO ACCEPTED",Toast.LENGTH_SHORT ).show();
                    }
                    else {
                        Toast.makeText(context,"STATUS UPDATION FAILED",Toast.LENGTH_SHORT).show();
                    }

                }
            });

            /* Setting onClick listener for Reject Button*/
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int appId = Integer.parseInt(aId.getText().toString());
                    dbConnect db = new dbConnect(context);
                    Boolean statusUpdate = db.rejectStatus(appId);
                    if(statusUpdate){
                        Toast.makeText(context,"STATUS UPDATED TO REJECTED",Toast.LENGTH_SHORT ).show();
                    }
                    else {
                        Toast.makeText(context,"STATUS UPDATION FAILED",Toast.LENGTH_SHORT).show();
                    }

                }
            });




        }
    }
}


