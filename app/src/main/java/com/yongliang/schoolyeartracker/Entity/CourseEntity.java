package com.yongliang.schoolyeartracker.Entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table", foreignKeys = @ForeignKey(entity = TermEntity.class,
        parentColumns = "id", childColumns = "term_id", onDelete = CASCADE))
public class CourseEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int term_id; //foreign key

    private String courseTitle;
    private String startDate;
    private String endDate;
    private String progressStatus;

    private String instructorName;
    private String instructorPhone;
    private String instructorEmail;
    private String courseNote;
    private boolean isRunningAlert;

//    @Ignore
//    public CourseEntity() {
//    }

//    public CourseEntity(int id, int term_id, String courseTitle, String startDate, String endDate, String progressStatus, String instructorName, String instructorPhone, String instructorEmail, String courseNote, boolean isRunningAlert) {
//        this.id = id;
//        this.term_id = term_id;
//        this.courseTitle = courseTitle;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.progressStatus = progressStatus;
//        this.instructorName = instructorName;
//        this.instructorPhone = instructorPhone;
//        this.instructorEmail = instructorEmail;
//        this.courseNote = courseNote;
//        this.isRunningAlert = isRunningAlert;
//    }

    public CourseEntity(int term_id, String courseTitle, String startDate, String endDate, String progressStatus, String instructorName, String instructorPhone, String instructorEmail, String courseNote, boolean isRunningAlert) {
        this.term_id = term_id;
        this.courseTitle = courseTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progressStatus = progressStatus;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.courseNote = courseNote;
        this.isRunningAlert = isRunningAlert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    public boolean isRunningAlert() {
        return isRunningAlert;
    }

    public void setRunningAlert(boolean runningAlert) {
        isRunningAlert = runningAlert;
    }
}
