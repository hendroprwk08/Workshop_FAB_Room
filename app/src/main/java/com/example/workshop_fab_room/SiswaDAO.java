package com.example.workshop_fab_room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SiswaDAO {
    @Query("SELECT * FROM siswa")
    List<Siswa> getAll(); //harus pake list karena akan di konversi menjadi cursor

    @Insert
    void insertAll(Siswa siswa); //tanpa id (karena id otomatis)

    @Update
    void update(Siswa siswa); //dengan id

    @Delete
    void delete(Siswa siswa); //dengan id
}
