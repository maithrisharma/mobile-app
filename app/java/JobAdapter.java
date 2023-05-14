package com.example.assignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/* Adapter for View Jobs Admin Recycler View */
public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder>{

    /* Variable Required */
    public ArrayList<Jobs> list;
    private Context context;

    /* Constructor */
    public JobAdapter(ArrayList<Jobs> list,Context context) {
        this.list = list;
        this.context=context;
        if(list.size()==0){
            Toast.makeText(context,"NO JOBS ADDED",Toast.LENGTH_SHORT).show();
        }
    }

    /* Instantiate a Layout XML for single item in Recycler View items*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new ViewHolder(view);
    }

    /* Filling the view holders with data*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.jobIdRec.setText(String.valueOf(list.get(position).getId()));
        holder.jobNameRec.setText(list.get(position).getJobName());
        holder.jobLocation.setText(list.get(position).getLocation());
        holder.jobZipCode.setText(list.get(position).getZipCode());
        holder.jobOrganisationRec.setText(list.get(position).getOrganisation());
        holder.jobDescription.setText(list.get(position).getDescription());
        holder.jobTypeRec.setText(list.get(position).getJobType());
        holder.jobSalary.setText(String.valueOf(list.get(position).getSalary()));
        holder.jobRequirements.setText(list.get(position).getRequirements());
        holder.jobBenefits.setText(list.get(position).getBenefits());
        holder.jobPositions.setText(String.valueOf(list.get(position).getPositions()));
        holder.jobApplicants.setText(String.valueOf(list.get(position).getApplicantsNo()));
    }

    /* Returns the size of List */
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView jobIdRec,jobNameRec, jobLocation, jobZipCode, jobOrganisationRec, jobDescription, jobTypeRec, jobSalary, jobRequirements;
        TextView jobBenefits, jobPositions,jobApplicants;
        ImageView edit,del;
        Button viewApplicants;

        public ViewHolder(@NonNull View itemView) {
            /* Finding the views from Layout XML */
            super(itemView);
            jobIdRec = itemView.findViewById(R.id.jobIdRec);
            jobNameRec = itemView.findViewById(R.id.jobNameRec);
            jobLocation = itemView.findViewById(R.id.jobLocation);
            jobZipCode = itemView.findViewById(R.id.jobZipCode);
            jobOrganisationRec = itemView.findViewById(R.id.jobOrganisationRec);
            jobDescription = itemView.findViewById(R.id.jobDescription);
            jobTypeRec = itemView.findViewById(R.id.jobTypeRec);
            jobSalary = itemView.findViewById(R.id.jobSalary);
            jobRequirements = itemView.findViewById(R.id.jobRequirements);
            jobBenefits = itemView.findViewById(R.id.jobBenefits);
            jobPositions = itemView.findViewById(R.id.jobPositions);
            jobApplicants = itemView.findViewById(R.id.jobApplicants);
            edit = itemView.findViewById(R.id.edit);
            del = itemView.findViewById(R.id.del);
            viewApplicants = itemView.findViewById(R.id.viewApplicants);

            /* Setting the onclick listener for edit */
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /* Starting UpdateJob Activity */
                    Intent intent = new Intent(context,UpdateJob.class);
                    intent.putExtra("JobId", jobIdRec.getText());
                    intent.putExtra("JobName", jobNameRec.getText());
                    intent.putExtra("JobLocation", jobLocation.getText());
                    intent.putExtra("JobZipCode", jobZipCode.getText());
                    intent.putExtra("JobOrg", jobOrganisationRec.getText());
                    intent.putExtra("JobDesc", jobDescription.getText());
                    intent.putExtra("JobType", jobTypeRec.getText());
                    intent.putExtra("JobSalary", jobSalary.getText());
                    intent.putExtra("JobReq", jobRequirements.getText());
                    intent.putExtra("JobBen", jobBenefits.getText());
                    intent.putExtra("JobPos", jobPositions.getText());
                    intent.putExtra("ApplicantsNo",Integer.parseInt(jobApplicants.getText().toString()));
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            });

            /* Setting the onclick listener for View Applicants button */
            viewApplicants.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /* Starting ViewApplicants Activity */
                    int jId = Integer.parseInt(jobIdRec.getText().toString());
                    Intent intent = new Intent(context,ViewApplicants.class);
                    intent.putExtra("JobId",jId);
                    context.startActivity(intent);

                }
            });


            /* Setting the onclick listener for delete */
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /* Database Connection */
                    dbConnect db=new dbConnect(context);
                    Boolean d=db.deleteJob(Integer.parseInt(jobIdRec.getText().toString()));
                    if(d){
                        Toast.makeText(context,"DELETED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,ViewJobs.class);

                        context.startActivity(intent);
                        ((Activity)context).finish();



                    }
                    else{
                        Toast.makeText(context,"DELETION FAILED",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }


}












