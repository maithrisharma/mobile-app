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
/* Adapter for View Jobs Normal User Recycler View */
public class JobAdapterNormal extends RecyclerView.Adapter<JobAdapterNormal.ViewHolder> {

    /* Variables Required */
    public ArrayList<Jobs> list;
    private Context context;
    public int userId;

    /*Constructor*/
    public JobAdapterNormal(int userId,ArrayList<Jobs> list,Context context) {
        this.userId = userId;
        this.list = list;
        this.context=context;
        if(list.size()==0){
            Toast.makeText(context,"NO JOBS TO VIEW",Toast.LENGTH_SHORT).show();
        }
    }

    /* Instantiate a Layout XML for single item in Recycler View items*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewjobsrow,parent,false);
        return new JobAdapterNormal.ViewHolder(view);
    }

    /* Filling the view holders with data*/
    @Override
    public void onBindViewHolder(@NonNull JobAdapterNormal.ViewHolder holder, int position) {

        holder.jobIdNormal.setText(String.valueOf(list.get(position).getId()));
        holder.jobNameNormal.setText(list.get(position).getJobName());
        holder.jobLocationNormal.setText(list.get(position).getLocation());
        holder.jobZipCodeNormal.setText(list.get(position).getZipCode());
        holder.jobOrganisationNormal.setText(list.get(position).getOrganisation());
        holder.jobDescriptionNormal.setText(list.get(position).getDescription());
        holder.jobTypeNormal.setText(list.get(position).getJobType());
        holder.jobSalaryNormal.setText(String.valueOf(list.get(position).getSalary()));
        holder.jobRequirementsNormal.setText(list.get(position).getRequirements());
        holder.jobBenefitsNormal.setText(list.get(position).getBenefits());
        holder.jobPositionsNormal.setText(String.valueOf(list.get(position).getPositions()));
        holder.noOfApplicants.setText(String.valueOf(list.get(position).getApplicantsNo()));

    }

    /* Returns List size */
    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView jobIdNormal,jobNameNormal, jobLocationNormal, jobZipCodeNormal, jobOrganisationNormal, jobDescriptionNormal;
        TextView jobTypeNormal, jobSalaryNormal, jobRequirementsNormal, jobBenefitsNormal, jobPositionsNormal, noOfApplicants;
        Button apply;

        public ViewHolder(@NonNull View itemView) {
            /* Finding the view from Layout XML */
            super(itemView);
            jobIdNormal =itemView.findViewById(R.id.jobIdNormal);
            jobNameNormal = itemView.findViewById(R.id.jobNameNormal);
            jobLocationNormal = itemView.findViewById(R.id.jobLocationNormal);
            jobZipCodeNormal = itemView.findViewById(R.id.jobZipCodeNormal);
            jobOrganisationNormal = itemView.findViewById(R.id.jobOrganisationNormal);
            jobDescriptionNormal = itemView.findViewById(R.id.jobDescriptionNormal);
            jobTypeNormal = itemView.findViewById(R.id.jobTypeNormal);
            jobSalaryNormal = itemView.findViewById(R.id.jobSalaryNormal);
            jobRequirementsNormal = itemView.findViewById(R.id.jobRequirementsNormal);
            jobBenefitsNormal = itemView.findViewById(R.id.jobBenefitsNormal);
            jobPositionsNormal = itemView.findViewById(R.id.jobPositionsNormal);
            noOfApplicants = itemView.findViewById(R.id.noOfApplicants);
            apply = itemView.findViewById(R.id.apply);


            /* Setting Onclick listener for Apply button*/
            apply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbConnect db = new dbConnect(context);
                    int jId = Integer.parseInt(jobIdNormal.getText().toString());
                    int applicants = Integer.parseInt(noOfApplicants.getText().toString());
                    /* Checking with user has already applied for the job */
                    Boolean checkApply = db.checkApply(userId,jId);
                    if(checkApply) {
                        int application = db.apply(userId, jId,applicants);
                        if (application != 0) {
                            Toast.makeText(context, "APPLIED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, ViewApplications.class);
                            intent.putExtra("UserId",userId);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                    }
                    else{
                        Toast.makeText(context, "YOU HAVE ALREADY APPLIED FOR THIS JOB", Toast.LENGTH_SHORT).show();
                    }



                }
            });



        }
    }
    /* To filter the List from Search and assigning the new list*/
    public void filterList(ArrayList<Jobs> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

}

