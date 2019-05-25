package com.example.workshop_fab_room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Siswa.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SiswaDAO siswaDAO();
}