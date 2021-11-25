package com.example.moneywallet.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moneywallet.dao.AppDatabase;
import com.example.moneywallet.dao.NoDao;
import com.example.moneywallet.entity.No;

import java.util.List;

public class NoReposotory {
    private NoDao mNoDao;
    private LiveData<List<No>> mAllNo;

    public NoReposotory(Application application) {
        this.mNoDao = (NoDao) AppDatabase.getDatabase(application).noDao();
        mAllNo = mNoDao.findAll();
    }

    public LiveData<List<No>> getAllNo() {
        return mAllNo;
    }
    public void delete(No no)
    {
        new NoReposotory.DeleteAsyncTask(mNoDao).execute(no);
    }
    public void insert(No no)
    {
        new NoReposotory.InsertAsyncTask(mNoDao).execute(no);
    }
    public void update(No no)
    {
        new NoReposotory.UpdateAsyncTask(mNoDao).execute(no);
    }



    class InsertAsyncTask extends AsyncTask<No, Void, Void>
    {
        private NoDao mNoDao;
        public InsertAsyncTask(NoDao noDao) {
            this.mNoDao = noDao;
        }

        @Override
        protected Void doInBackground(No... nos) {
            mNoDao.insert(nos[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<No, Void, Void>
    {
        private NoDao mNoDao;
        public DeleteAsyncTask(NoDao noDao) {
            this.mNoDao = noDao;
        }

        @Override
        protected Void doInBackground(No... nos) {
            mNoDao.delete(nos[0]);
            return null;
        }
    }
    class UpdateAsyncTask extends AsyncTask<No, Void, Void>
    {
        private NoDao mNoDao;
        public UpdateAsyncTask(NoDao noDao) {
            this.mNoDao = noDao;
        }

        @Override
        protected Void doInBackground(No... nos) {
            mNoDao.update(nos[0]);
            return null;
        }
    }
}
