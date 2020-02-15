package com.example.catatan.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.catatan.R;
import com.example.catatan.TambahData;
import com.example.catatan.bacaSingle;
import com.example.catatan.db.AppDatabase;
import com.example.catatan.model.Catatan;

import java.util.ArrayList;
public class CatatanAdapter extends RecyclerView.Adapter<CatatanAdapter.ViewHolder> {

    private ArrayList<Catatan> daftarCatatan;
    private Context context;
    private AppDatabase db;

    public CatatanAdapter(ArrayList<Catatan> barangs, Context ctx) {
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarCatatan = barangs;
        context = ctx;

        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "catatan_db").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         */
        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_judul);
            cvMain = v.findViewById(R.id.cv_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        final String name = daftarCatatan.get(position).getJudul();

        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  baca detail catatan
                 */
                Catatan catatan = db.catatanDao().selectCatatanDetail(daftarCatatan.get(position).getJudulId());
                context.startActivity(bacaSingle.getActIntent((Activity) context).putExtra("data", catatan));
            }
        });
        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  memunculkan dialog
                 */
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.view_dialog);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = dialog.findViewById(R.id.bt_edit_data);
                Button delButton = dialog.findViewById(R.id.bt_delete_data);

                //saat tombol edit diklik
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onEditBarang(position);
                            }
                        }
                );

                //saat tombol delete diklik
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onDeteleBarang(position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    private void onDeteleBarang(int position) {
        db.catatanDao().delCatatan(daftarCatatan.get(position));
        daftarCatatan.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarCatatan.size());
    }

    private void onEditBarang(int position) {
        context.startActivity(TambahData.getActIntent((Activity) context).putExtra("data", daftarCatatan.get(position)));
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarCatatan.size();
    }
}