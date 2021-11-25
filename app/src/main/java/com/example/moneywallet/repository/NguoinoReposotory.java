package com.example.moneywallet.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moneywallet.dao.AppDatabase;
import com.example.moneywallet.dao.NguoinoDao;
import com.example.moneywallet.entity.Nguoino;

import java.util.List;

public class NguoinoReposotory {
    private NguoinoDao mNguoinoDao;
    private LiveData<List<Nguoino>> mAllNguoiNo;

    public NguoinoReposotory(Application application) {
        this.mNguoinoDao = AppDatabase.getDatabase(application).nguoinoDao();
        mAllNguoiNo = mNguoinoDao.findAll();
    }

    public LiveData<List<Nguoino>> getAllNguoiNo() {
        return mAllNguoiNo;
    }
    public void delete(Nguoino nguoino)
    {
        new NguoinoReposotory.DeleteAsyncTask(mNguoinoDao).execute(nguoino);
    }
    public void insert(Nguoino nguoino)
    {
        new NguoinoReposotory.InsertAsyncTask(mNguoinoDao).execute(nguoino);
    }
    public void update(Nguoino nguoino)
    {
        new NguoinoReposotory.UpdateAsyncTask(mNguoinoDao).execute(nguoino);
    }
    class InsertAsyncTask extends AsyncTask<Nguoino, Void, Void>
    {
        private NguoinoDao mNguoinoDao;
        public InsertAsyncTask(NguoinoDao nguoinoDao) {
            this.mNguoinoDao = nguoinoDao;
        }

        @Override
        protected Void doInBackground(Nguoino... nguoinos) {
            mNguoinoDao.insert(nguoinos[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<Nguoino, Void, Void>
    {
        private NguoinoDao mNguoinoDao;
        public DeleteAsyncTask(NguoinoDao nguoinoDao) {
            this.mNguoinoDao = nguoinoDao;
        }

        @Override
        protected Void doInBackground(Nguoino... nguoinos) {
            mNguoinoDao.delete(nguoinos[0]);
            return null;
        }
    }
    class UpdateAsyncTask extends AsyncTask<Nguoino, Void, Void>
    {
        private NguoinoDao mNguoinoDao;
        public UpdateAsyncTask(NguoinoDao nguoinoDao) {
            this.mNguoinoDao = nguoinoDao;
        }

        @Override
        protected Void doInBackground(Nguoino... nguoinos) {
            mNguoinoDao.update(nguoinos[0]);
            return null;
        }
    }
}
