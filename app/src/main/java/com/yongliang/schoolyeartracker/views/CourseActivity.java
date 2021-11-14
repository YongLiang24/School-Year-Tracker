package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.yongliang.schoolyeartracker.Database.Repository;
import com.yongliang.schoolyeartracker.Entity.CourseEntity;
import com.yongliang.schoolyeartracker.R;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseActivity extends AppCompatActivity {

    String mTermName;
    int mTermID;
    private Repository repo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extraInfo =getIntent().getExtras();
        mTermID=extraInfo.getInt("id");
        mTermName=extraInfo.getString("termName");
//        System.out.println("term id: "+mTermID);
//        System.out.println(mTermName);

        //display the course list
        repo=new Repository(getApplication());
        List<CourseEntity> allCourses=repo.getAllCourses();
        //System.out.println("all courses: "+allCourses);
        //use stream to filter courses by term id
        List<CourseEntity> filterCourse= allCourses.stream().filter(c ->c.getTerm_id() == mTermID).collect(Collectors.toList());

        RecyclerView recyclerView =findViewById(R.id.recyclerview);

        final CourseAdapter courseAdapter=new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(filterCourse);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addCourse(View view) {
        Intent intent = new Intent(CourseActivity.this, AddCourse.class);
        startActivity(intent);
    }

    public void deleteTerm(View view) {
    }
}