package com.yongliang.schoolyeartracker.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "term_table")
public class TermEntity {
    @PrimaryKey(autoGenerate = true)
    private int termID;

    private String TermName;
    private String StartDate;
    private String EndDate;

    public TermEntity() {
    }

    public TermEntity(int termID, String termName, String startDate, String endDate) {
        this.termID = termID;
        TermName = termName;
        StartDate = startDate;
        EndDate = endDate;
    }

    @Override
    public String toString() {
        return "TermEntity{" +
                "TermName='" + TermName + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", EndDate='" + EndDate + '\'' +
                '}';
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermName() {
        return TermName;
    }

    public void setTermName(String termName) {
        TermName = termName;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}
