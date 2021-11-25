package com.yongliang.schoolyeartracker.Database;

import android.app.Application;

import com.yongliang.schoolyeartracker.DAO.AssessmentDAO;
import com.yongliang.schoolyeartracker.DAO.CourseDAO;
import com.yongliang.schoolyeartracker.DAO.TermDAO;
import com.yongliang.schoolyeartracker.Entity.AssessmentEntity;
import com.yongliang.schoolyeartracker.Entity.CourseEntity;
import com.yongliang.schoolyeartracker.Entity.TermEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDAO mTermDao;
    private List<TermEntity> mAllTerms;

    private TermEntity mThisTerm;
    private CourseEntity mThisCourse;

    private CourseDAO mCourseDao;
    private List<CourseEntity> mAllCourses;

    private AssessmentDAO mAssessmentDao;
    private List<AssessmentEntity> mAllAssessments;

    //Threads
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);
        mTermDao = db.termDao();

        mCourseDao= db.courseDAO();
        mAssessmentDao = db.assessmentDAO();
    }

    //get all terms.
    public List<TermEntity> getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms=mTermDao.getAllTerms();

        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    //get a term by id.
    public TermEntity getThisTerm(int id){
        databaseExecutor.execute(()->{
            mThisTerm=mTermDao.getThisTerm(id);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mThisTerm;
    }

    //get a course by id
    public CourseEntity getThisCourse(int id){
        databaseExecutor.execute(()->{
            mThisCourse = mCourseDao.getThisCourse(id);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mThisCourse;
    }

    //get all courses
    public List<CourseEntity> getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses=mCourseDao.getAllCourses();

        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    //get all assessments
    public List<AssessmentEntity> getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments=mAssessmentDao.getAllAssessment();

        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    //insert terms
    public void insert(TermEntity term){
        databaseExecutor.execute(()->{
            mTermDao.insert(term);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //insert courses
    public void insert(CourseEntity course){
        databaseExecutor.execute(()->{
            mCourseDao.insert(course);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //insert assessments
    public void insert(AssessmentEntity assessment){
        databaseExecutor.execute(()->{
            mAssessmentDao.insert(assessment);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //update terms
    public void update(TermEntity term){
        databaseExecutor.execute(()->{
            mTermDao.update(term);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //update course
    public void update(CourseEntity course){
        databaseExecutor.execute(()->{
            mCourseDao.update(course);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //update assessment
    public void update(AssessmentEntity assessment){
        databaseExecutor.execute(()->{
            mAssessmentDao.update(assessment);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //delete terms
    public void delete(TermEntity term){
        databaseExecutor.execute(()->{
            mTermDao.delete(term);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //delete course
    public void delete(CourseEntity course){
        databaseExecutor.execute(()->{
            mCourseDao.delete(course);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //delete assessment
    public void delete(AssessmentEntity assessment){
        databaseExecutor.execute(()->{
            mAssessmentDao.delete(assessment);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
