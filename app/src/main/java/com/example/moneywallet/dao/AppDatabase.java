package com.example.moneywallet.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.moneywallet.entity.LoaiChi;
import com.example.moneywallet.entity.LoaiThu;

import org.jetbrains.annotations.NotNull;

@Database(entities = {LoaiThu.class,LoaiChi.class},version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LoaiThuDao loaiThuDao();
    public abstract LoaiChiDao loaiChiDao();
    public static AppDatabase INSTANCE;
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateData(INSTANCE).execute();
        }
    };
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class)
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                         AppDatabase.class, "personal_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }
    public static class PopulateData extends AsyncTask<Void, Void, Void>
    {
        private LoaiThuDao loaiThuDao;
        private LoaiChiDao loaiChiDao;
        public PopulateData(AppDatabase db) {
            loaiThuDao = db.loaiThuDao();
            loaiChiDao = db.loaiChiDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] loaithus = new String[]{"Luong","Thuong","Co Phieu"};
            for (String it: loaithus)
            {
                LoaiThu lt = new LoaiThu();
                lt.ten = it;
                loaiThuDao.insert(lt);
            }
            String[] loaichis = new String[]{"Đi CHƠI", "sỞ thích", "ĂN uống"};
            for (String its : loaichis)
            {
                    LoaiChi lc = new LoaiChi();
                    lc.ten1 = its;
                    loaiChiDao.insert(lc);
            }
                return null;

        }
    }




}
