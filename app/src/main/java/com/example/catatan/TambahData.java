package com.example.catatan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catatan.db.AppDatabase;
import com.example.catatan.model.Catatan;

public class TambahData extends AppCompatActivity {

private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "catatan_db").build();

        final EditText etJudul   = findViewById(R.id.judul);
        final EditText etIsi   = findViewById(R.id.isi);
        final EditText etWaktu  = findViewById(R.id.waktu);
        Button btSubmit         = findViewById(R.id.btn_tambah);

        final Catatan catatan = (Catatan) getIntent().getSerializableExtra("data");

        if(catatan!=null){
            etJudul.setText(catatan.getJudul());
            etIsi.setText(catatan.getIsi());
            etWaktu.setText(catatan.getWaktu());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    catatan.setJudul(etJudul.getText().toString());
                    catatan.setIsi(etIsi.getText().toString());
                    catatan.setWaktu(etWaktu.getText().toString());

                    updateBarang(catatan);
                }
            });
        }else{
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Catatan c = new Catatan();
                    c.setWaktu(etWaktu.getText().toString());
                    c.setIsi(etIsi.getText().toString());
                    c.setJudul(etJudul.getText().toString());
                    insertData(c);
                }
            });
        }
    }
//metohd edit
    private void updateBarang(final Catatan catatan){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.catatanDao().updateCatatan(catatan);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(TambahData.this, "Berhasil edit, status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
//method insert
    private void insertData(final Catatan catatan){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.catatanDao().insertCatatan(catatan);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(TambahData.this, "Catatan tersimpan, status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, TambahData.class);
    }

    public void btn_lihat_kembali(View view) {
        Intent i = new Intent(this,TampilData.class);
        startActivity(i);
    }
}
