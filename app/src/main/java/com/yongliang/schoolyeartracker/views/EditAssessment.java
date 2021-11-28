package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.yongliang.schoolyeartracker.Database.Repository;
import com.yongliang.schoolyeartracker.Entity.AssessmentEntity;
import com.yongliang.schoolyeartracker.Entity.CourseEntity;
import com.yongliang.schoolyeartracker.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class EditAssessment extends AppCompatActivity {

    private Repository repo=new Repository(getApplication());
    int a_id;
    AssessmentEntity astObj;

    EditText aTitle;
    Spinner aSpinner;
    DatePicker eDate;


    EditText cName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extraInfo =getIntent().getExtras();
        a_id=extraInfo.getInt("assessment_id");
        astObj=repo.getThisAssessment(a_id);

        aTitle = findViewById(R.id.a_title);
        aSpinner = findViewById(R.id.a_spinner);
        eDate=findViewById(R.id.a_endDatePicker);

        aTitle.setText(astObj.getAssessmentTitle());

        //set default value for spinner
        ArrayAdapter<CharSequence> adapt =ArrayAdapter.createFromResource(this, R.array.type_spinner, android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        aSpinner.setAdapter(adapt);
        String spinArray [] = getResources().getStringArray(R.array.type_spinner);
        int spinIndex =getSpinnerIndex(spinArray, astObj);
        //mSpinner.getSelectedItem();
        aSpinner.setSelection(spinIndex);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        //convert String to LocalDate
        LocalDate e_date = LocalDate.parse(astObj.getEndDate(), formatter);
        eDate.updateDate(e_date.getYear(), e_date.getMonthValue()+1, e_date.getDayOfYear());

    }

    //get index for spinner item preload
    int getSpinnerIndex(String str[], AssessmentEntity aObj){
        int index=0;
        for(String status: str){
            if(status.equals(aObj.getAssessmentType())){
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

    public void updateAssessment(View view) {
        astObj.setAssessmentTitle(aTitle.getText().toString());
        astObj.setAssessmentType(aSpinner.getSelectedItem().toString());
        //format date
        int eMonth = eDate.getMonth()+1;
        String endDate= String.valueOf(eMonth)+"/"+String.valueOf(eDate.getDayOfMonth())+"/"+String.valueOf(eDate.getYear());
        astObj.setEndDate(endDate);
        //update assessment
        repo.update(astObj);

        Intent intent =new Intent(EditAssessment.this, AssessmentActivity.class);
        intent.putExtra("course_id", astObj.getCourse_id());
        startActivity(intent);
    }

    public void deleteAssessment(View view) {
        confirmDelete("Delete this assessment?","Are you sure to delete this assessment?");
    }

    //two buttons confirmation box for deleting course.
    public void confirmDelete(String setTitle, String setMessage){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(setTitle);
        alertDialog.setMessage(setMessage);

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        repo.delete(astObj);
                        Intent intent = new Intent(EditAssessment.this, AssessmentActivity.class);
                        intent.putExtra("course_id", astObj.getCourse_id());
                        startActivity(intent);


                    }
                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //onClick No
                        dialog.cancel();
                    }
                });
        // Show Alert Dialog
        alertDialog.show();
    }
}