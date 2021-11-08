package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yongliang.schoolyeartracker.Database.Repository;
import com.yongliang.schoolyeartracker.Entity.AssessmentEntity;
import com.yongliang.schoolyeartracker.Entity.CourseEntity;
import com.yongliang.schoolyeartracker.Entity.TermEntity;
import com.yongliang.schoolyeartracker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repo = new Repository(getApplication());
        TermEntity term1 = new TermEntity("summer","09-08-2021","12-09-2021 ");
        CourseEntity course1 = new CourseEntity(1, "math","03/01","06/05","complete","meme","999-9999-9993","xx@g,ail.com","first note", false);
        AssessmentEntity assessment1 = new AssessmentEntity(1, "objective", "new assessment", "06-07-2021");

        repo.insert(term1);
        repo.insert(course1);
        repo.insert(assessment1);

    }

    public void startApp(View view) {
        Intent intent = new Intent(MainActivity.this, TermActivity.class);
        startActivity(intent);
    }
}