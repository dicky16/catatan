package com.example.catatan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.catatan.Adapters.CatatanAdapter;
import com.example.catatan.db.AppDatabase;
import com.example.catatan.db.CatatanDao;
import com.example.catatan.model.Catatan;

import java.util.ArrayList;
import java.util.Arrays;

public class TampilData extends AppCompatActivity {
CatatanDao catatanDao;
private AppDatabase db;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Catatan> dartarCatatan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        /**
         * Initialize layout dan sebagainya
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);


        /**
         * Initialize ArrayList untuk data catatan
         */
        dartarCatatan = new ArrayList<>();

        /**
         * Initialize database
         */
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "catatan_db").allowMainThreadQueries().build();

        /**
         * Initialize recyclerview dan layout manager
         */
        rvView = findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        /**
         * Add all data to arraylist
         */
        dartarCatatan.addAll(Arrays.asList(db.catatanDao().selectAllBarangs()));

        /**
         * Set all data ke adapter, dan menampilkannya
         */
        adapter = new CatatanAdapter(dartarCatatan, this);
        rvView.setAdapter(adapter);
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, TampilData.class);
    }

    public void btn_add(View view) {
        Intent i = new Intent(this,TambahData.class);
        startActivity(i);
    }
    //inflate menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==R.id.about) {
            Toast.makeText(this, "Clearing data", Toast.LENGTH_SHORT).show();
            db.catatanDao().delAll();
        }
//       else if(id==R.id.setting){
//           startActivity(new Intent(this,nav.class));
//        }
        return true;
    }
    //delete all

}
