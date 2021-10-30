package com.yongliang.schoolyeartracker.Database;

import android.app.Application;

import com.yongliang.schoolyeartracker.DAO.TermDAO;
import com.yongliang.schoolyeartracker.Entity.TermEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TermRepository {
    private TermDAO mTermDao;
    private List<TermEntity> mAllTerms;

    //Threads
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public TermRepository(Application application){
        TermDatabaseBuilder db = TermDatabaseBuilder.getDatabase(application);
        mTermDao = db.termDao();
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



}
