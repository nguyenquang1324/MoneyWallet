package com.example.moneywallet.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class No {
    @PrimaryKey(autoGenerate = true)
    public int oid;
    @ColumnInfo(name="id")
    public int loid;
    @ColumnInfo(name = "Ten")
    public String ten;
    @ColumnInfo(name ="Sotien")
    public float sotien;
    @ColumnInfo(name="Ghichu")
    public String ghichu;
}
