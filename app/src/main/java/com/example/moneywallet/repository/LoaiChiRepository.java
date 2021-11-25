package com.example.moneywallet.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moneywallet.dao.AppDatabase;
import com.example.moneywallet.dao.LoaiChiDao;
import com.example.moneywallet.entity.LoaiChi;

import java.util.List;

public class LoaiChiRepository {
    private LoaiChiDao mLoaiChiDao;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public LoaiChiRepository(Application application) {
        this.mLoaiChiDao = AppDatabase.getDatabase(application).loaiChiDao();
        mAllLoaiChi = mLoaiChiDao.findAll();
    }
    public LiveData<List<LoaiChi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }
    public void insert(LoaiChi loaiChi){
        new InsertAsyncTask(mLoaiChiDao).execute(loaiChi);
    }
    public void detete(LoaiChi loaiChi){
        new DeleteAsyncTask(mLoaiChiDao).execute(loaiChi);
    }
    public void update(LoaiChi loaiChi){
        new UpdateAsyncTask(mLoaiChiDao).execute(loaiChi);
    }


    class InsertAsyncTask extends AsyncTask<LoaiChi,Void,Void>{
        private LoaiChiDao mLoaiChiDao;
        public InsertAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao = loaiChiDao;
        }

         @Override
         protected Void doInBackground(LoaiChi... loaiChis) {
             mLoaiChiDao.insert(loaiChis[0]);
             return null;
         }
     }
    class DeleteAsyncTask extends AsyncTask<LoaiChi,Void,Void>{
        private LoaiChiDao mLoaiChiDao;
        public DeleteAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao = loaiChiDao;
        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.delete(loaiChis[0]);
            return null;
        }
    }
    class UpdateAsyncTask extends AsyncTask<LoaiChi,Void,Void>{
        private LoaiChiDao mLoaiChiDao;
        public UpdateAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao = loaiChiDao;
        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.update(loaiChis[0]);
            return null;
        }
    }
}
