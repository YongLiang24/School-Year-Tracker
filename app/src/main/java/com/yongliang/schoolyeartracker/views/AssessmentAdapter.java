package com.yongliang.schoolyeartracker.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yongliang.schoolyeartracker.Entity.AssessmentEntity;
import com.yongliang.schoolyeartracker.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessment_title;
        private final TextView assessment_type;
        private final TextView assessment_date;

        private AssessmentViewHolder(View aView) {
            super(aView);
            assessment_title=aView.findViewById(R.id.assessment_title);
            assessment_type=aView.findViewById(R.id.assessment_type);
            assessment_date=aView.findViewById(R.id.assessment_date);

            aView.setOnClickListener(new View.OnClickListener(){
                //individual item click event
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final AssessmentEntity current= mAssessment.get(position);
                    //to next screen
                    Intent intent = new Intent(context, EditAssessment.class);
                    //pass extra course data to the next screen
                    intent.putExtra("assessment_id", current.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<AssessmentEntity> mAssessment;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View a_View=mInflater.inflate(R.layout.list_assessment, parent, false);
        return new AssessmentAdapter.AssessmentViewHolder(a_View);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(mAssessment != null){
            AssessmentEntity current = mAssessment.get(position);
            holder.assessment_title.setText(current.getAssessmentTitle());
            holder.assessment_type.setText(current.getAssessmentType());
            holder.assessment_date.setText(current.getEndDate());
        }
        else{
            holder.assessment_title.setText("no course title");
        }

    }

    public void setAssessments(List<AssessmentEntity> assessments){
        mAssessment = assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mAssessment.size();
    }
}
