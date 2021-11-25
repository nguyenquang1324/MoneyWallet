package com.example.moneywallet.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneywallet.entity.Chi;
import com.example.moneywallet.entity.LoaiChi;
import com.example.moneywallet.repository.ChiRepository;
import com.example.moneywallet.repository.LoaiChiRepository;
import com.example.moneywallet.repository.LoaiThuRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KhoanChiViewModel extends AndroidViewModel {
    private ChiRepository mChiRepository;
    private LiveData<List<Chi>> mAllChi;
    private LiveData<List<LoaiChi>> mAllLoaiChi;
    private LoaiChiRepository mLoaiChiRepository;
    public KhoanChiViewModel(@NonNull @NotNull Application application) {
        super(application);
        mChiRepository = new ChiRepository(application);
        mAllChi = mChiRepository.getAllChi();
        mLoaiChiRepository = new LoaiChiRepository(application);
        mAllLoaiChi = mLoaiChiRepository.getAllLoaiChi();
    }
    public LiveData<List<Chi>> getAllChi(){
        return mAllChi;
    }
    public void insert(Chi chi){
        mChiRepository.insert(chi);
    }
    public void delete(Chi chi){
        mChiRepository.detete(chi);
    }
    public void update(Chi chi){
        mChiRepository.update(chi);
    }

    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return mAllLoaiChi;
    }
}