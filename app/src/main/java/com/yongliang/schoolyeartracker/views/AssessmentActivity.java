package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yongliang.schoolyeartracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AssessmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.share_note:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "populate this text");
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Share this note");
                sendIntent.setType("text/plain");

                Intent shareIntent=Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

            case R.id.edit_course:
                String myFormat="11/21/2021";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate =null;
                try {
                    myDate=sdf.parse(myFormat);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger= myDate.getTime();

                System.out.println("trigger time: " +trigger);
                Intent intent = new Intent(AssessmentActivity.this, MyReceiver.class);
                intent.putExtra("key","message i want to test");

                PendingIntent sender = PendingIntent.getBroadcast(AssessmentActivity.this, ++MainActivity.numAlert, intent, 0);

                AlarmManager myAlarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                //this sets when the alert to trigger
                //myAlarm.set(AlarmManager.RTC_WAKEUP, trigger, sender);

                myAlarm.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 20000 ,sender);


        }
        return super.onOptionsItemSelected(item);
    }

    //add the 3 dots menu selection
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu items
        getMenuInflater().inflate(R.menu.menu01, menu);
        return true;
    }
}