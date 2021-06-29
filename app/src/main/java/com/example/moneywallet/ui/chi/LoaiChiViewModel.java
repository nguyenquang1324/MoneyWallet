package com.example.moneywallet.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneywallet.dao.LoaiChiDao;
import com.example.moneywallet.entity.LoaiChi;
import com.example.moneywallet.repository.LoaiChiRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoaiChiViewModel extends AndroidViewModel {
    private LoaiChiRepository mLoaiChiRepository;
    private LiveData<List<LoaiChi>> mAllLoaiChi;
    public LoaiChiViewModel(@NonNull @NotNull Application application) {
        super(application);
        mLoaiChiRepository = new LoaiChiRepository(application);
        mAllLoaiChi = mLoaiChiRepository.getAllLoaiChi();
    }
    public LiveData<List<LoaiChi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }
    public void insert(LoaiChi loaiChi){
        mLoaiChiRepository.insert(loaiChi);
    }

}