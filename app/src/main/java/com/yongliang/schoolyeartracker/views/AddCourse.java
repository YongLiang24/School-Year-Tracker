package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.yongliang.schoolyeartracker.Database.Repository;
import com.yongliang.schoolyeartracker.Entity.CourseEntity;
import com.yongliang.schoolyeartracker.R;

import java.util.Objects;

public class AddCourse extends AppCompatActivity {

    Spinner mSpinner;
    int term_id;
    DatePicker sDate;
    DatePicker eDate;

    EditText cName;
    EditText iName;
    EditText iPhone;
    EditText iEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extraInfo =getIntent().getExtras();
        term_id=extraInfo.getInt("term_id");

        cName=findViewById(R.id.course_title);
        iName=findViewById(R.id.ins_name);
        iPhone=findViewById(R.id.ins_phone);
        iEmail=findViewById(R.id.ins_email);
        sDate =findViewById(R.id.startDatePicker);
        eDate = findViewById(R.id.endDatePicker);

        //drop down box for status
        mSpinner=findViewById(R.id.st_spinner);
        ArrayAdapter<CharSequence> adapt =ArrayAdapter.createFromResource(this, R.array.status_spinner, android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinner.setAdapter(adapt);
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

    public void saveCourse(View view) {
        //term_id
        int sMonth = sDate.getMonth()+1;
        int eMonth = eDate.getMonth()+1;
        String courseName=cName.getText().toString();
        String startDate =String.valueOf(sMonth)+"-"+String.valueOf(sDate.getDayOfMonth())+"-"+String.valueOf(sDate.getYear());
        String endDate= String.valueOf(eMonth)+"-"+String.valueOf(eDate.getDayOfMonth())+"-"+String.valueOf(eDate.getYear());
        String courseStatus =mSpinner.getSelectedItem().toString();
        String insName = iName.getText().toString();
        String insPhone = iPhone.getText().toString();
        String insEmail = iEmail.getText().toString();
        String courseNote="";
        boolean isAlert=false;

        if(courseName.isEmpty() || insName.isEmpty() || insPhone.isEmpty() || insEmail.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill all fields before Saving",Toast.LENGTH_LONG).show();
        }
        else{
            Repository repo = new Repository(getApplication());
            CourseEntity newCourse = new CourseEntity(term_id, courseName, startDate, endDate, courseStatus, insName, insPhone, insEmail, courseNote, isAlert);
            repo.insert(newCourse);

            Intent intent = new Intent(AddCourse.this, CourseActivity.class);
            startActivity(intent);
        }


    }
}