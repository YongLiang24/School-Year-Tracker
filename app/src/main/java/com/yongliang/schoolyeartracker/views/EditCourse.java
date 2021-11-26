package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class EditCourse extends AppCompatActivity {

    Spinner mSpinner;
    int course_id;
    DatePicker sDate;
    DatePicker eDate;

    EditText cName;
    EditText iName;
    EditText iPhone;
    EditText iEmail;

    CourseEntity updateCourseObj;
    Repository repo=new Repository(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        Bundle extraInfo =getIntent().getExtras();
        course_id=extraInfo.getInt("courseID");
        updateCourseObj = repo.getThisCourse(course_id);


        cName=findViewById(R.id.course_title);
        cName.setText(updateCourseObj.getCourseTitle());

        iName=findViewById(R.id.ins_name);
        iName.setText(updateCourseObj.getInstructorName());

        iPhone=findViewById(R.id.ins_phone);
        iPhone.setText(updateCourseObj.getInstructorPhone());

        iEmail=findViewById(R.id.ins_email);
        iEmail.setText(updateCourseObj.getInstructorEmail());

        //not preload date picker and status dropbox
        sDate =findViewById(R.id.startDatePicker);
        eDate = findViewById(R.id.endDatePicker);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
//
//        //convert String to LocalDate
        LocalDate s_date = LocalDate.parse(updateCourseObj.getStartDate(), formatter);
        LocalDate e_date = LocalDate.parse(updateCourseObj.getEndDate(), formatter);
//        //System.out.println(s_date.getMonth() +" "+s_date.getDayOfMonth()+" "+ s_date.getYear());
        sDate.updateDate(s_date.getYear(), s_date.getMonthValue()-1, s_date.getDayOfMonth());
        eDate.updateDate(e_date.getYear(), e_date.getMonthValue()+1, e_date.getDayOfYear());

        //drop down box for status
        mSpinner=findViewById(R.id.st_spinner);
        ArrayAdapter<CharSequence> adapt =ArrayAdapter.createFromResource(this, R.array.status_spinner, android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinner.setAdapter(adapt);

        String spinArray [] = getResources().getStringArray(R.array.status_spinner);

        int spinIndex =getSpinnerIndex(spinArray, updateCourseObj);
        //mSpinner.getSelectedItem();
        mSpinner.setSelection(spinIndex);
    }

    //get index for spinner item preload
    int getSpinnerIndex(String str[], CourseEntity courseObj){
        int index=0;
        for(String status: str){
            if(status.equals(courseObj.getProgressStatus())){
                return index;
            }
        }
        index++;
        return 0;
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

    public void updateCourse(View view) {
        int sMonth = sDate.getMonth()+1;
        int eMonth = eDate.getMonth()+1;
        String courseName=cName.getText().toString();
        String startDate =String.valueOf(sMonth)+"/"+String.valueOf(sDate.getDayOfMonth())+"/"+String.valueOf(sDate.getYear());
        String endDate= String.valueOf(eMonth)+"/"+String.valueOf(eDate.getDayOfMonth())+"/"+String.valueOf(eDate.getYear());
        String courseStatus =mSpinner.getSelectedItem().toString();
        String insName = iName.getText().toString();
        String insPhone = iPhone.getText().toString();
        String insEmail = iEmail.getText().toString();

        if(courseName.isEmpty() || insName.isEmpty() || insPhone.isEmpty() || insEmail.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill all fields before Saving",Toast.LENGTH_LONG).show();

        }
        else{
            updateCourseObj.setCourseTitle(courseName);
            updateCourseObj.setStartDate(startDate);
            updateCourseObj.setEndDate(endDate);
            updateCourseObj.setProgressStatus(courseStatus);
            updateCourseObj.setInstructorName(insName);
            updateCourseObj.setInstructorPhone(insPhone);
            updateCourseObj.setInstructorEmail(insEmail);
            repo.update(updateCourseObj);

//            setDateAlert(startDate, "Course ("+courseName+ ") Start Date: "+startDate);
//            setDateAlert(endDate, "Course (" +courseName+ ") End Date: "+endDate);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(EditCourse.this, AssessmentActivity.class);
            intent.putExtra("course_id", course_id);
            startActivity(intent);
        }

    }

    //A broadcast method for start_date and end_date alert
//    void setDateAlert(String alertDate, String alertText){
//
//        String myFormat="MM/dd/yyyy";
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        Date myDate =null;
//        try {
//            myDate=sdf.parse(alertDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Long trigger= myDate.getTime();
//
//
//        Intent intent = new Intent(EditCourse.this, MyReceiver.class);
//        intent.putExtra("key", alertText);
//
//        PendingIntent sender = PendingIntent.getBroadcast(EditCourse.this, ++MainActivity.numAlert, intent, 0);
//
//        AlarmManager myAlarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        //this sets when the alert to trigger
//        myAlarm.set(AlarmManager.RTC_WAKEUP, trigger, sender);
//        //myAlarm.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 20000 ,sender);
//    }

}