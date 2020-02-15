package com.example.catatan.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.catatan.model.Catatan;

@Database(entities = {Catatan.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {

        public abstract CatatanDao catatanDao();
    }

