package com.example.workshop_fab_room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Siswa.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SiswaDAO siswaDAO();
}