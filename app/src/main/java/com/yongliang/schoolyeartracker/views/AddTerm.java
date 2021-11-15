package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.yongliang.schoolyeartracker.Database.Repository;
import com.yongliang.schoolyeartracker.Entity.TermEntity;
import com.yongliang.schoolyeartracker.R;

import java.util.Objects;

public class AddTerm extends AppCompatActivity {
    EditText addTerm;
    String termTitle;

    DatePicker sDate;
    String startDate;

    DatePicker eDate;
    String endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    public void saveTerm(View view) {
        addTerm=findViewById(R.id.addTermTitle);
        termTitle=addTerm.getText().toString();

        //convert date picker to string
        sDate=findViewById(R.id.startDatePicker);
        int sMonth = sDate.getMonth()+1;
        startDate=String.valueOf(sMonth)+"-"+String.valueOf(sDate.getDayOfMonth())+"-"+String.valueOf(sDate.getYear());
        //System.out.println("date: "+startDate);
        eDate=findViewById(R.id.endDatePicker);
        int eMonth = eDate.getMonth()+1;
        endDate=String.valueOf(eMonth)+"-"+String.valueOf(eDate.getDayOfMonth())+"-"+String.valueOf(eDate.getYear());
        //System.out.println("date: "+endDate);

        //validate term title input
        if(termTitle.isEmpty() || termTitle.length()>15){
            Toast.makeText(getApplicationContext(),"Please fill all fields before Saving",Toast.LENGTH_LONG).show();
        }
        else{
            Repository repo = new Repository(getApplication());
            TermEntity newTerm= new TermEntity(termTitle,startDate,endDate);
            repo.insert(newTerm);

            Intent intent = new Intent(AddTerm.this, TermActivity.class);
            startActivity(intent);
        }

    }
}