package com.yongliang.schoolyeartracker.Entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessment_table", foreignKeys = @ForeignKey(entity = CourseEntity.class,
        parentColumns = "id", childColumns = "course_id", onDelete = CASCADE))
public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int course_id; //foreign key

    private String assessmentType;
    private String assessmentTitle;
    private String endDate;

//    public AssessmentEntity(int id, int course_id, String assessmentType, String assessmentTitle, String endDate) {
//        this.id = id;
//        this.course_id = course_id;
//        this.assessmentType = assessmentType;
//        this.assessmentTitle = assessmentTitle;
//        this.endDate = endDate;
//    }

    public AssessmentEntity(int course_id, String assessmentType, String assessmentTitle, String endDate) {
        this.course_id = course_id;
        this.assessmentType = assessmentType;
        this.assessmentTitle = assessmentTitle;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
