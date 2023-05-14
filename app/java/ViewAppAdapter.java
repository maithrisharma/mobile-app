package com.example.assignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
/* Adapter for View Applications Recycler View*/
public class ViewAppAdapter extends RecyclerView.Adapter<ViewAppAdapter.ViewHolder> {
    /* Variables required */
    public ArrayList<ApplicationModel> list;
    private Context context;
    /* Constructor */
    public ViewAppAdapter( ArrayList<ApplicationModel> list,Context context) {
        this.list = list;
        this.context = context;
        if(list.size()==0){
            Toast.makeText(context,"NO APPLICATIONS SUBMITTED",Toast.LENGTH_SHORT).show();
        }

    }

    /* Instantiate a Layout XML for single item in Recycler View items*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleapplicationsrow,parent,false);
        return new ViewAppAdapter.ViewHolder(view);
    }

    /* Filling the view holders with data*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.applicationId.setText(String.valueOf(list.get(position).getAppId()));
        holder.jobNameApp.setText(list.get(position).getJobName());
        holder.jobLocationApp.setText(list.get(position).getJobLocation());
        holder.jobOrganisationApp.setText(list.get(position).getJobOrganisation());
    }

    /*  Returns the size of list */
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView applicationId,jobNameApp,jobOrganisationApp,jobLocationApp;
        Button viewStatus;


        public ViewHolder(@NonNull View itemView) {
            /* Finding views from Layout XML */
            super(itemView);
            applicationId = itemView.findViewById(R.id.applicationId);
            jobNameApp = itemView.findViewById(R.id.jobNameApp);
            jobOrganisationApp = itemView.findViewById(R.id.jobOrganisationApp);
            jobLocationApp = itemView.findViewById(R.id.jobLocationApp);
            viewStatus = itemView.findViewById(R.id.viewStatus);

            /* Setting Onclick listener for View Status Button */
            viewStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int appId = Integer.parseInt(applicationId.getText().toString());
                    dbConnect db = new dbConnect(context);
                    /* Checks for the status of Application and displays in a Toast message */
                    String status = db.checkStatus(appId);

                    Toast.makeText(context,"Status "+ status,Toast.LENGTH_SHORT ).show();
                }
            });

        }
    }
}
