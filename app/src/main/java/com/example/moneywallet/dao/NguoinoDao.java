package com.example.moneywallet.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneywallet.entity.Nguoino;

import java.util.List;

@Dao
public interface NguoinoDao {
    @Query("SELECT * FROM nguoino")
    LiveData<List<Nguoino>> findAll();

    @Insert
    void insert(Nguoino nguoino);

    @Update
    void update(Nguoino nguoino);

    @Delete
    void delete(Nguoino nguoino);
}
