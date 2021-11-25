package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.yongliang.schoolyeartracker.Database.Repository;
import com.yongliang.schoolyeartracker.Entity.CourseEntity;
import com.yongliang.schoolyeartracker.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AssessmentActivity extends AppCompatActivity {

    int course_id;
    String course_note;
    TextView c_note;
    Repository repo=new Repository(getApplication());
    CourseEntity courseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //get course info from layout
        TextView c_title=(TextView) findViewById(R.id.c_title);
        TextView c_status=(TextView)findViewById(R.id.c_status);
        TextView i_name=(TextView)findViewById(R.id.i_name);
        TextView i_phone=(TextView)findViewById(R.id.i_phone);
        TextView i_email=(TextView)findViewById(R.id.i_email);
        c_note=(TextView)findViewById(R.id.c_note);


        Bundle extraInfo =getIntent().getExtras();
        course_id=extraInfo.getInt("course_id");
        courseObj = repo.getThisCourse(course_id);
//        System.out.println("course ID: "+course_id);
//        System.out.println(courseObj.getCourseTitle());
        c_title.setText("Course Title:  "+courseObj.getCourseTitle());
        c_status.setText("Course Status:  "+courseObj.getProgressStatus());
        i_name.setText("Instructor Name:  "+courseObj.getInstructorName());
        i_phone.setText("Instructor Phone:  "+courseObj.getInstructorPhone());
        i_email.setText("Instructor Email:  "+courseObj.getInstructorEmail());
        c_note.setText(courseObj.getCourseNote());

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
                sendIntent.putExtra(Intent.EXTRA_TEXT, courseObj.getCourseNote());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Title: Note Sharing");
                sendIntent.setType("text/plain");
                Intent shareIntent=Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

            //add_assessment, add_note, delete_course
            case R.id.add_note:
                Intent intent = new Intent(AssessmentActivity.this, AddNote.class);
                intent.putExtra("courseID", course_id);
                startActivity(intent);

            case R.id.add_assessment:

            case R.id.edit_course:

            case R.id.delete_course:


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