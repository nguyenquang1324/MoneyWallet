package com.example.moneywallet.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chi {
    @PrimaryKey(autoGenerate = true)
    public int cid;
    @ColumnInfo(name = "lcid")
    public int lcid;
    @ColumnInfo(name = "ten")
    public String ten1;
    @ColumnInfo(name = "sotien")
    public float sotien1;
    @ColumnInfo(name = "ghichu")
    public String ghichu;

}
