package com.example.catatan.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "tb_catatan")
public class Catatan implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int judulId;

    @ColumnInfo(name = "judul")
    public String judul;

    @ColumnInfo(name = "isi")
    public String isi;

    @ColumnInfo(name = "waktu")
    public String waktu;

    public int getJudulId() {
        return judulId;
    }

    public void setJudulId(int judulId) {
        this.judulId = judulId;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}

