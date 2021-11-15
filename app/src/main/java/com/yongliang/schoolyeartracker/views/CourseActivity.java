package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yongliang.schoolyeartracker.Database.Repository;
import com.yongliang.schoolyeartracker.Entity.CourseEntity;
import com.yongliang.schoolyeartracker.Entity.TermEntity;
import com.yongliang.schoolyeartracker.R;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseActivity extends AppCompatActivity {

    String mTermName;
    int mTermID;
    private Repository repo;
    TermEntity thisTerm;
    List<CourseEntity> filterCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extraInfo =getIntent().getExtras();
        mTermID=extraInfo.getInt("id");
        mTermName=extraInfo.getString("termName");

        repo=new Repository(getApplication());
        //get this term for deletion
        thisTerm= repo.getThisTerm(mTermID);

        //display the course list
        List<CourseEntity> allCourses=repo.getAllCourses();

        //use stream to filter courses by term id
        filterCourse= allCourses.stream().filter(c ->c.getTerm_id() == mTermID).collect(Collectors.toList());

        RecyclerView recyclerView =findViewById(R.id.recyclerview);
        final CourseAdapter courseAdapter=new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(filterCourse);


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

    public void addCourse(View view) {
        Intent intent = new Intent(CourseActivity.this, AddCourse.class);
        intent.putExtra("term_id", mTermID);
        startActivity(intent);
    }

    public void deleteTerm(View view) {
        confirmDelete("Term Deletion", "Delete this term?");
    }

    //two buttons confirmation box for delete term.
    public void confirmDelete(String setTitle, String setMessage){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(setTitle);
        alertDialog.setMessage(setMessage);

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //onClick Yes logic here
                        if(filterCourse.isEmpty()){
                            repo.delete(thisTerm);
                            Intent intent = new Intent(CourseActivity.this, TermActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Must delete all associated courses first.", Toast.LENGTH_LONG).show();
                        }

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