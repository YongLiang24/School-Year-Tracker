package com.yongliang.schoolyeartracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yongliang.schoolyeartracker.Entity.AssessmentEntity;
import com.yongliang.schoolyeartracker.Entity.CourseEntity;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AssessmentEntity term);

    @Update
    void update(AssessmentEntity term);

    @Delete
    void delete(AssessmentEntity term);

    @Query("SELECT * FROM assessment_table")
    List<AssessmentEntity> getAllAssessment();

    @Query("SELECT * FROM assessment_table WHERE id= :id")
    AssessmentEntity getThisAssessment(int id);
}
