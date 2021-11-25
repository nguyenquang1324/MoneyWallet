package com.example.moneywallet.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moneywallet.dao.AppDatabase;
import com.example.moneywallet.dao.ChiDao;
import com.example.moneywallet.dao.LoaiChiDao;
import com.example.moneywallet.entity.Chi;
import com.example.moneywallet.entity.LoaiChi;

import java.util.List;

public class ChiRepository {
    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;

    public ChiRepository(Application application) {
        this.mChiDao = AppDatabase.getDatabase(application).chiDao();
        mAllChi = mChiDao.findAll();
    }
    public LiveData<List<Chi>> getAllChi(){
        return mAllChi;
    }
    public void insert(Chi chi){
        new InsertAsyncTask(mChiDao).execute(chi);
    }
    public void detete(Chi chi){
        new DeleteAsyncTask(mChiDao).execute(chi);
    }
    public void update(Chi chi){
        new UpdateAsyncTask(mChiDao).execute(chi);
    }


    class InsertAsyncTask extends AsyncTask<Chi,Void,Void>{
        private ChiDao mChiDao;
        public InsertAsyncTask(ChiDao chiDao){
            this.mChiDao = chiDao;
        }

         @Override
         protected Void doInBackground(Chi... chis) {
             mChiDao.insert(chis[0]);
             return null;
         }
     }
    class DeleteAsyncTask extends AsyncTask<Chi,Void,Void>{
        private ChiDao mChiDao;
        public DeleteAsyncTask(ChiDao chiDao){
            this.mChiDao = chiDao;
        }

        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.delete(chis[0]);
            return null;
        }
    }
    class UpdateAsyncTask extends AsyncTask<Chi,Void,Void>{
        private ChiDao mChiDao;
        public UpdateAsyncTask(ChiDao chiDao){
            this.mChiDao = chiDao;
        }

        @Override
        protected Void doInBackground(
                Chi... chis) {
            mChiDao.update(chis[0]);
            return null;
        }
    }
}
