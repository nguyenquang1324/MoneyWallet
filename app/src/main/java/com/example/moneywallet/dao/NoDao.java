package com.example.moneywallet.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneywallet.entity.No;

import java.util.List;

@Dao
public interface NoDao {
    @Query("SELECT * FROM no")
    LiveData<List<No>> findAll() ;

    @Insert
    void insert(No no);

    @Update
    void update(No no);

    @Delete
    void delete(No no);
}
