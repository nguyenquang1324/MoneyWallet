package com.example.moneywallet.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneywallet.entity.LoaiThu;
import com.example.moneywallet.entity.Thu;
import com.example.moneywallet.repository.LoaiThuRepository;
import com.example.moneywallet.repository.ThuRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KhoanThuViewModel extends AndroidViewModel {
    private ThuRepository mThuRepository;
    private LiveData<List<Thu>> mAllThu;
    private LiveData<List<LoaiThu>> mAllLoaiThu;
    private LoaiThuRepository mLoaiThuRepository;
    public KhoanThuViewModel(@NonNull @NotNull Application application) {
        super(application);
        mThuRepository = new ThuRepository(application);
        mAllThu = mThuRepository.getmAllthu();
        mLoaiThuRepository = new LoaiThuRepository(application);
        mAllLoaiThu = mLoaiThuRepository.getmAllloaithu();
    }
    public LiveData<List<LoaiThu>> getAllLoaiThu(){
        return mAllLoaiThu;
    }
    public LiveData<List<Thu>> getAllThu(){
        return mAllThu;
    }
    public void insert(Thu thu){
        mThuRepository.insert(thu);
    }
    public void delete(Thu thu){
        mThuRepository.delete(thu);
    }
    public void update(Thu thu){
        mThuRepository.update(thu);
    }
}