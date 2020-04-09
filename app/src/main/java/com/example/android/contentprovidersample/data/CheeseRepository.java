package com.example.android.contentprovidersample.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CheeseRepository {
    private CheeseDao mCheeseDao;
    private LiveData<List<Cheese>> mAllCheeses;

    CheeseRepository(Application application) {
        SampleDatabase db = SampleDatabase.getInstance(application);
        mCheeseDao = db.cheese();
        mAllCheeses = mCheeseDao.getAllCheeses();
    }

    LiveData<List<Cheese>> getAllCheeses() {
        return mAllCheeses;
    }

    public void insert (Cheese cheese) {
        new insertAsyncTask(mCheeseDao).execute(cheese);
    }

    private static class insertAsyncTask extends AsyncTask<Cheese, Void, Void> {

        private CheeseDao mAsyncTaskDao;

        insertAsyncTask(CheeseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Cheese... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
