package com.yongliang.schoolyeartracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yongliang.schoolyeartracker.Entity.TermEntity;

import java.util.List;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TermEntity term);

    @Update
    void update(TermEntity term);

    @Delete
    void delete(TermEntity term);

    @Query("SELECT * FROM term_table")
    List<TermEntity> getAllTerms();

    @Query("SELECT * FROM term_table WHERE id= :id")
    TermEntity getThisTerm(int id);



}
