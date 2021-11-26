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
import com.yongliang.schoolyeartracker.Entity.AssessmentEntity;
import com.yongliang.schoolyeartracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AddAssessment extends AppCompatActivity {
    EditText assessmentTitle;
    Spinner typeSpinner;
    DatePicker sDate;
    DatePicker eDate;
    int courseID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        assessmentTitle=(EditText) findViewById(R.id.addAssessmentTitle);
        typeSpinner=(Spinner) findViewById(R.id.type_spinner);
        sDate=findViewById(R.id.sDatePicker);
        eDate=findViewById(R.id.eDatePicker);

        Bundle extraInfo =getIntent().getExtras();
        courseID=extraInfo.getInt("courseID");

        ArrayAdapter<CharSequence> adapt =ArrayAdapter.createFromResource(this, R.array.type_spinner, android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        typeSpinner.setAdapter(adapt);

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

    public void saveAssessment(View view) {

        int sMonth = sDate.getMonth()+1;
        int eMonth = eDate.getMonth()+1;
        String assessmentName=assessmentTitle.getText().toString();
        String startDate =String.valueOf(sMonth)+"/"+String.valueOf(sDate.getDayOfMonth())+"/"+String.valueOf(sDate.getYear());
        String endDate= String.valueOf(eMonth)+"/"+String.valueOf(eDate.getDayOfMonth())+"/"+String.valueOf(eDate.getYear());
        String assessmentType =typeSpinner.getSelectedItem().toString();

        if(assessmentName.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill all fields before Saving",Toast.LENGTH_LONG).show();

        }
        else{
            setDateAlert(startDate, "Assessment ("+assessmentName+ ") Start Date: "+startDate);
            setDateAlert(endDate, "Assessment (" +assessmentName+ ") End Date: "+endDate);

            AssessmentEntity newAssessment = new AssessmentEntity(courseID,assessmentType,assessmentName,endDate);
            Repository repo = new Repository(getApplication());
            repo.insert(newAssessment);

            Intent intent = new Intent(AddAssessment.this, AssessmentActivity.class);
            intent.putExtra("course_id", courseID);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(intent);
        }
    }

    //A broadcast method for start_date and end_date alert
    void setDateAlert(String alertDate, String alertText){

        String myFormat="MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date myDate =null;
        try {
            myDate=sdf.parse(alertDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long trigger= myDate.getTime();


        Intent intent = new Intent(AddAssessment.this, MyReceiver.class);
        intent.putExtra("key", alertText);

        PendingIntent sender = PendingIntent.getBroadcast(AddAssessment.this, ++MainActivity.numAlert, intent, 0);

        AlarmManager myAlarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //this sets when the alert to trigger
        myAlarm.set(AlarmManager.RTC_WAKEUP, trigger, sender);
        //myAlarm.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 20000 ,sender);
    }
}