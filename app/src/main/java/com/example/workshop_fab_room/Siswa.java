package com.example.workshop_fab_room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Siswa {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nama")
    private String nama;

    @ColumnInfo(name = "kelas")
    private String kelas;

    @Ignore
    public Siswa(String nama, String kelas) {
        this.nama = nama;
        this.kelas = kelas;
    }

    public Siswa(int id, String nama, String kelas) {
        this.id = id;
        this.nama = nama;
        this.kelas = kelas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getKelas() {
        return kelas;
    }
}