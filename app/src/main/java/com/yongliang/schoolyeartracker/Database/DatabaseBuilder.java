package com.yongliang.schoolyeartracker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yongliang.schoolyeartracker.DAO.AssessmentDAO;
import com.yongliang.schoolyeartracker.DAO.CourseDAO;
import com.yongliang.schoolyeartracker.DAO.TermDAO;
import com.yongliang.schoolyeartracker.Entity.AssessmentEntity;
import com.yongliang.schoolyeartracker.Entity.CourseEntity;
import com.yongliang.schoolyeartracker.Entity.TermEntity;

@Database(entities={TermEntity.class, CourseEntity.class, AssessmentEntity.class}, version=1, exportSchema = false)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDao();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();

    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (DatabaseBuilder.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "MyTermDB.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}
