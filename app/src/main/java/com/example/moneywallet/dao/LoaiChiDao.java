package com.example.moneywallet.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneywallet.entity.LoaiChi;

import java.util.List;

@Dao
public interface LoaiChiDao {
    @Query("Select * from loaichi")
    LiveData<List<LoaiChi>> findAll();
    @Insert
    void insert(LoaiChi loaiChi);
    @Update
    void update (LoaiChi loaiChi);
}
