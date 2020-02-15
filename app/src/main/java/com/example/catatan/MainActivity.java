package com.example.catatan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,TampilData.class);
                startActivity(i);
                finish();

            }
        },2000);
    }

//    public void tambah_data(View view) {
//        startActivity(TambahData.getActIntent(MainActivity.this));
//    }
//
//    public void lihat_data(View view) {
////        Intent i = new Intent(this,TampilData.class);
////        startActivity(i);
//        startActivity(TampilData.getActIntent(MainActivity.this));
//    }
}
