package com.example.laurent.velostation;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class VeloRepository {
    private VelostationDao velostationDao;
    private LiveData<List<Velostation>> mAllVelo;

    VeloRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        velostationDao = db.VelostationDao();
        mAllVelo = velostationDao.getAll();
    }

    LiveData<List<Velostation>> getmAllVelo() {
        return mAllVelo;
    }

    public void insert (Velostation velo) {
        new insertAsyncTask(velostationDao).execute(velo);
    }

    private static class insertAsyncTask extends AsyncTask<Velostation, Void, Void> {

        private VelostationDao mAsyncTaskDao;

        insertAsyncTask(VelostationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Velostation... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
