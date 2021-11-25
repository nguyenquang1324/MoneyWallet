package com.example.moneywallet.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Nguoino {
    @PrimaryKey(autoGenerate = true)
    public int nid;
    @ColumnInfo(name = "Ten")
    public String ten;
    @ColumnInfo(name ="Điachi")
    public String add;
    @ColumnInfo(name="sđt ")
    public String sdt;
}
