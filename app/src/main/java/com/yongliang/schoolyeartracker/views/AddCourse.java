package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.yongliang.schoolyeartracker.R;

import java.util.Objects;

public class AddCourse extends AppCompatActivity {

    Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
        String courseStatus =mSpinner.getSelectedItem().toString();


    }
}