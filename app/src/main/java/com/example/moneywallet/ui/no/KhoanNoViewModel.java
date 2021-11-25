package com.example.moneywallet.ui.no;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneywallet.entity.Nguoino;
import com.example.moneywallet.entity.No;
import com.example.moneywallet.repository.NguoinoReposotory;
import com.example.moneywallet.repository.NoReposotory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KhoanNoViewModel extends AndroidViewModel {
    private NoReposotory mNoRepository;
    private NguoinoReposotory mNguoinoRepository;
    private LiveData<List<No>> mAllNo;
    private LiveData<List<Nguoino>> mAllNguoino;
    public KhoanNoViewModel(@NonNull @NotNull Application application) {
        super(application);
        mNoRepository = new NoReposotory(application);
        mAllNo = mNoRepository.getAllNo();
        mNguoinoRepository = new NguoinoReposotory(application);
        mAllNguoino = mNguoinoRepository.getAllNguoiNo();

    }
    public  LiveData<List<Nguoino>> getmAllNguoino(){
        return mAllNguoino;
    }
    public LiveData<List<No>> getAllNo(){
        return mAllNo;
    }
    public void insert(No no){
        mNoRepository.insert(no);
    }
    public void delete(No no){
        mNoRepository.delete(no);
    }
    public void update(No no){
        mNoRepository.update(no);
    }
}