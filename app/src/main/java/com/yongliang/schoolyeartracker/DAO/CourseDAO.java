package com.yongliang.schoolyeartracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yongliang.schoolyeartracker.Entity.CourseEntity;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CourseEntity term);

    @Update
    void update(CourseEntity term);

    @Delete
    void delete(CourseEntity term);

    @Query("SELECT * FROM course_table")
    List<CourseEntity> getAllCourses();
}
