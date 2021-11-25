package com.example.moneywallet.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.moneywallet.entity.Chi;
import com.example.moneywallet.entity.LoaiChi;
import com.example.moneywallet.entity.LoaiThu;
import com.example.moneywallet.entity.Nguoino;
import com.example.moneywallet.entity.No;
import com.example.moneywallet.entity.Thu;

import org.jetbrains.annotations.NotNull;

@Database(entities = {LoaiThu.class,LoaiChi.class,Thu.class,Chi.class, Nguoino.class, No.class},version = 7)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LoaiThuDao loaiThuDao();
    public abstract LoaiChiDao loaiChiDao();
    public abstract ThuDao thuDao();
    public abstract ChiDao chiDao();
    public abstract NguoinoDao nguoinoDao();
    public abstract NoDao noDao();


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
        private ThuDao thuDao;
        private ChiDao chiDao;
        private NguoinoDao nguoinoDao;
        private NoDao noDao;
        public PopulateData(AppDatabase db) {
            loaiThuDao = db.loaiThuDao();
            loaiChiDao = db.loaiChiDao();
            nguoinoDao =db.nguoinoDao();
            thuDao = db.thuDao();
            chiDao = db.chiDao();
            noDao = db.noDao();

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
            Thu thu = new Thu();
            thu.ten = "Luong thang 1";
            thu.sotien = 3000;
            thu.ltid =1;
            thu.ghichu = "Vợ cầm ";
            thuDao.insert(thu);

            String[] loaichis = new String[]{"Đi CHƠI", "sỞ thích", "ĂN uống"};
            for (String its : loaichis)
            {
                    LoaiChi lc = new LoaiChi();
                    lc.ten1 = its;
                    loaiChiDao.insert(lc);
            }
            Chi chi = new Chi();
            chi.ten1 = "đi biển ";
            chi.sotien1 = 5000;
            chi.ghichu="23/07 đi";
            chi.lcid=1;
            chiDao.insert(chi);

            Nguoino nn = new Nguoino();
            nn.nid=1;
            nn.ten="Linh";
            nn.add="Hà Nội";
            nn.sdt="09987";
            nguoinoDao.insert(nn);

            No n = new No();
            n.sotien=5000;
            n.loid = 1;
            n.ten = "Nơ đi chơi";
            n.ghichu = "oke";
            noDao.insert(n);

            Log.d("MoneyWallet","Insert data");
                return null;

        }
    }




}
