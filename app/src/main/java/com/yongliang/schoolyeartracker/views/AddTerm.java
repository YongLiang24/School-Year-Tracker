package com.yongliang.schoolyeartracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.yongliang.schoolyeartracker.R;

import java.util.Objects;

public class AddTerm extends AppCompatActivity {

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
//        TermRepository rep = new TermRepository(getApplication());
//        TermEntity term = new TermEntity(1, "spring", "10-02/2021", "10-03/2021");
//        rep.insert(term);



        Intent intent = new Intent(AddTerm.this, TermActivity.class);
        startActivity(intent);
    }
}