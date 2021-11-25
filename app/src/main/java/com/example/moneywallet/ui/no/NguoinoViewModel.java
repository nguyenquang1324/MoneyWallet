package com.example.moneywallet.ui.no;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.moneywallet.entity.Nguoino;

import com.example.moneywallet.repository.NguoinoReposotory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NguoinoViewModel extends AndroidViewModel {
    private NguoinoReposotory mNguoinoRepository;
    private LiveData<List<Nguoino>> mAllNguoiNo;
    public NguoinoViewModel(@NonNull @NotNull Application application) {
        super(application);
        mNguoinoRepository = new NguoinoReposotory(application);
        mAllNguoiNo = mNguoinoRepository.getAllNguoiNo();
    }
    public LiveData<List<Nguoino>> getAllNguoiNo(){
        return mAllNguoiNo;
    }
    public void insert(Nguoino nguoino){
        mNguoinoRepository.insert(nguoino);
    }
    public void delete(Nguoino nguoino){
        mNguoinoRepository.delete(nguoino);
    }
    public void update(Nguoino nguoino){
        mNguoinoRepository.update(nguoino);
    }
    }