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

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repo = new Repository(getApplication());


    }

    public void startApp(View view) {
        Intent intent = new Intent(MainActivity.this, TermActivity.class);
        startActivity(intent);
    }
}