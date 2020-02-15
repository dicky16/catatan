package com.example.catatan.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.catatan.model.Catatan;

@Dao
public interface CatatanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCatatan(Catatan catatan);

    @Update
    int updateCatatan(Catatan catatan);

    @Delete
    int delCatatan(Catatan catatan);

    @Query("DELETE FROM tb_catatan")
    void delAll();

    @Query("SELECT * FROM tb_catatan")
    Catatan[] selectAllBarangs();

    @Query("SELECT * FROM tb_catatan WHERE judulId = :id LIMIT 1")
    Catatan selectCatatanDetail(int id);

}
