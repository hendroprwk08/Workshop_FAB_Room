package com.example.workshop_fab_room;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder dialog;

    EditText etNama;
    Spinner spKelas;
    RvAdapter adapter;

    //panggil DB
    static MyDatabase db;

    List<Siswa> siswas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //bentuk (build) database
        db = Room.databaseBuilder(
                getApplicationContext(),
                MyDatabase.class,
                "db-siswa")
                .allowMainThreadQueries()
                .build();

        viewRecyclerView();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog.Builder(MainActivity.this);

                //tarik layout
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.form_input_layout,
                        null);

                dialog.setView(view);
                dialog.setCancelable(true);

                //definisi objek
                etNama = (EditText) view.findViewById(R.id.et_nama);
                spKelas = (Spinner) view.findViewById(R.id.sp_kelas);

                dialog.setPositiveButton("SIMPAN",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String nama, kelas;

                                nama = etNama.getText().toString();
                                kelas = spKelas.getSelectedItem().toString();

                                db.siswaDAO().insertAll(new Siswa(nama, kelas));

                                viewRecyclerView();

                                Toast.makeText(getApplicationContext(),
                                        "Data terisimpan",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                dialog.setNegativeButton("BATAL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                dialog.show();
            }
        });
    }

    private void viewRecyclerView() {
        //definisi
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_container);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        siswas = db.siswaDAO().getAll(); //ambil semua data

        adapter = new RvAdapter(siswas, this);
        rv.setAdapter(adapter);
    }

    //tambah resume untuk reload
    @Override
    protected void onResume() {
        super.onResume();

        viewRecyclerView();
    }
}
