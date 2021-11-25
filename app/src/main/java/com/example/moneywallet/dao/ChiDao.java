package com.example.moneywallet.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneywallet.entity.Chi;

import java.util.List;

@Dao
public interface ChiDao {
    @Query("Select * from chi")
    LiveData<List<Chi>> findAll();
    @Insert
    void insert(Chi chi);
    @Update
    void update (Chi chi);
    @Delete
    void delete (Chi chi);
}
