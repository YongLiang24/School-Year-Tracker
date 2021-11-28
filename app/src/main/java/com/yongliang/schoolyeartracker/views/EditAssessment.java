package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.yongliang.schoolyeartracker.Database.Repository;
import com.yongliang.schoolyeartracker.Entity.AssessmentEntity;
import com.yongliang.schoolyeartracker.R;

import java.util.Objects;

public class EditAssessment extends AppCompatActivity {

    private Repository repo=new Repository(getApplication());

    int a_id;
    AssessmentEntity astObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extraInfo =getIntent().getExtras();
        a_id=extraInfo.getInt("assessment_id");
        astObj=repo.getThisAssessment(a_id);

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