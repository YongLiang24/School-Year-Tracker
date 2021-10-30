package com.yongliang.schoolyeartracker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yongliang.schoolyeartracker.DAO.TermDAO;
import com.yongliang.schoolyeartracker.Entity.TermEntity;

@Database(entities={TermEntity.class}, version=1, exportSchema = false)
public abstract class TermDatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDao();

    private static volatile TermDatabaseBuilder INSTANCE;

    static TermDatabaseBuilder getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (TermDatabaseBuilder.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),TermDatabaseBuilder.class, "MyTermDB.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}
