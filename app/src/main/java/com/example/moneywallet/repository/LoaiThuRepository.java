package com.example.moneywallet.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import com.example.moneywallet.dao.AppDatabase;
import com.example.moneywallet.dao.LoaiThuDao;
import com.example.moneywallet.entity.LoaiThu;

import java.util.List;

public class LoaiThuRepository {
    private LoaiThuDao mLoaiThuDao;
    private LiveData<List<LoaiThu>> mAllloaithu;

    public LoaiThuRepository(Application application) {
        this.mLoaiThuDao = AppDatabase.getDatabase(application).loaiThuDao();
        mAllloaithu = mLoaiThuDao.findAll();
    }

    public LiveData<List<LoaiThu>> getmAllloaithu() {
        return mAllloaithu;
    }
    public void delete(LoaiThu loaiThu)
    {
        new DeleteAsyncTask(mLoaiThuDao).execute(loaiThu);
    }
    public void insert(LoaiThu loaiThu)
    {
        new InsertAsyncTask(mLoaiThuDao).execute(loaiThu);
    }
    public void update(LoaiThu loaiThu)
    {
        new UpdateAsyncTask(mLoaiThuDao).execute(loaiThu);
    }
    class InsertAsyncTask extends AsyncTask<LoaiThu, Void, Void>
    {
        private LoaiThuDao mLoaiThuDao;
        public InsertAsyncTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.insert(loaiThus[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<LoaiThu, Void, Void>
    {
        private LoaiThuDao mLoaiThuDao;
        public DeleteAsyncTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.delete(loaiThus[0]);
            return null;
        }
    }
    class UpdateAsyncTask extends AsyncTask<LoaiThu, Void, Void>
    {
        private LoaiThuDao mLoaiThuDao;
        public UpdateAsyncTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.update(loaiThus[0]);
            return null;
        }
    }
}
