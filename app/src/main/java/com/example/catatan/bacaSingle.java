package com.example.catatan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.catatan.model.Catatan;

public class bacaSingle extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        EditText etJudul = findViewById(R.id.judul);
        EditText etIsi = findViewById(R.id.isi);
        EditText etWaktu = findViewById(R.id.waktu);
        Button btn_submit = findViewById(R.id.btn_tambah);

        etJudul.setEnabled(false);
        etIsi.setEnabled(false);
        etWaktu.setEnabled(false);
        btn_submit.setVisibility(View.GONE);

        Catatan catatan = (Catatan) getIntent().getSerializableExtra("data");
        if(catatan!=null){
            etJudul.setText(catatan.getJudul());
            etIsi.setText(catatan.getIsi());
            etWaktu.setText(catatan.getWaktu());
        }

    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, bacaSingle.class);
    }
    public void btn_lihat_kembali(View view) {
        Intent i = new Intent(this,TampilData.class);
        startActivity(i);
    }
}
