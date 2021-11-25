package com.example.moneywallet.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moneywallet.dao.AppDatabase;
import com.example.moneywallet.dao.ThuDao;
import com.example.moneywallet.entity.Thu;

import java.util.List;

public class ThuRepository {
    private ThuDao mThuDao;
    private LiveData<List<Thu>> mAllthu;

    public ThuRepository(Application application) {
        this.mThuDao = AppDatabase.getDatabase(application).thuDao();
        mAllthu = mThuDao.findAll();
    }

    public LiveData<List<Thu>> getmAllthu() {
        return mAllthu;
    }
    public void delete(Thu thu)
    {
        new DeleteAsyncTask(mThuDao).execute(thu);
    }
    public void insert(Thu thu)
    {
        new InsertAsyncTask(mThuDao).execute(thu);
    }
    public void update(Thu thu)
    {
        new UpdateAsyncTask(mThuDao).execute(thu);
    }
    class InsertAsyncTask extends AsyncTask<Thu, Void, Void>
    {
        private ThuDao mThuDao;
        public InsertAsyncTask(ThuDao ThuDao) {
            this.mThuDao = ThuDao;
        }

        @Override
        protected Void doInBackground(Thu... Thus) {
            mThuDao.insert(Thus[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<Thu, Void, Void>
    {
        private ThuDao mThuDao;
        public DeleteAsyncTask(ThuDao thuDao) {
            this.mThuDao = thuDao;
        }

        @Override
        protected Void doInBackground(Thu... Thus) {
            mThuDao.delete(Thus[0]);
            return null;
        }
    }
    class UpdateAsyncTask extends AsyncTask<Thu, Void, Void>
    {
        private ThuDao mThuDao;
        public UpdateAsyncTask(ThuDao thuDao) {
            this.mThuDao = thuDao;
        }

        @Override
        protected Void doInBackground(Thu... Thus) {
            mThuDao.update(Thus[0]);
            return null;
        }
    }
}
