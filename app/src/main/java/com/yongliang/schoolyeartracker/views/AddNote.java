package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yongliang.schoolyeartracker.Database.Repository;
import com.yongliang.schoolyeartracker.Entity.CourseEntity;
import com.yongliang.schoolyeartracker.R;

import java.util.Objects;

public class AddNote extends AppCompatActivity {

    int courseID;
    String courseNote;
    CourseEntity updateCourseObj;
    EditText noteText;
    Repository repo=new Repository(getApplication());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extraInfo =getIntent().getExtras();
        courseID = extraInfo.getInt("courseID");
        //System.out.println("course ID: "+courseID);

        updateCourseObj =repo.getThisCourse(courseID);

        //System.out.println(updateCourseObj.getCourseNote());

        noteText =(EditText) findViewById(R.id.edit_note);
        noteText.setText(updateCourseObj.getCourseNote());
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

    public void addNote(View view) {
        String updatedNote = noteText.getText().toString();
        if(!updatedNote.isEmpty()){
            updateCourseObj.setCourseNote(updatedNote);
            repo.update(updateCourseObj);
            Intent intent = new Intent(AddNote.this, AssessmentActivity.class);
            intent.putExtra("course_id",courseID);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please add some text before Saving",Toast.LENGTH_LONG).show();
        }



    }
}