package com.yongliang.schoolyeartracker.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yongliang.schoolyeartracker.Entity.CourseEntity;
import com.yongliang.schoolyeartracker.Entity.TermEntity;
import com.yongliang.schoolyeartracker.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{


    //inner class
    class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseTitle;
        private final TextView courseStartDate;
        private final TextView courseEndDate;
        private CourseViewHolder(View courseView){
            super (courseView);
            courseTitle = courseView.findViewById(R.id.courseView);
            courseStartDate = courseView.findViewById(R.id.courseView2);
            courseEndDate = courseView.findViewById(R.id.courseView3);

            courseView.setOnClickListener(new View.OnClickListener(){
                //individual item click event
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final CourseEntity current= mCourses.get(position);
                    //to next screen
                    Intent intent = new Intent(context, AssessmentActivity.class);
                    //pass extra course data to the next screen
                    intent.putExtra("course_id", current.getId());


                    context.startActivity(intent);
                }
            });
        }
    }

    private List<CourseEntity> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseView=mInflater.inflate(R.layout.list_course, parent, false);
        return new CourseAdapter.CourseViewHolder(courseView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if(mCourses != null){
            CourseEntity current = mCourses.get(position);
            holder.courseTitle.setText(current.getCourseTitle());
            holder.courseStartDate.setText(current.getStartDate());
            holder.courseEndDate.setText(current.getEndDate());
        }
        else{
            holder.courseTitle.setText("no course title");
        }

    }

    public void setCourses(List<CourseEntity> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

}
