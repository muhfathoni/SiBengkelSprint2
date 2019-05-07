package com.example.sibengkel;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sibengkel.models.BarangModels;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PembelianAdapter extends RecyclerView.Adapter<PembelianAdapter.ViewHolder> {
    private ArrayList<BarangModels> daftarBarang;
    private Context context;
    private DatabaseReference database;

    public final static String TAG_EMAIL = "email";

    public static final String my_shared_preferences = "my_shared_preferences";

    SharedPreferences sharedPreferences;

    public PembelianAdapter(ArrayList<BarangModels> barangs, Context ctx){
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarBarang = barangs;
        context = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle,tvHarga,tvKategori;
        ImageView ivFoto;


        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namabarang);
            tvHarga = v.findViewById(R.id.tv_hargabarang);
            tvKategori = v.findViewById(R.id.tv_kategoribarang);
            ivFoto = v.findViewById(R.id.fotoBarang);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        final String name = daftarBarang.get(position).getNamaBarang();
        final String harga = daftarBarang.get(position).getHarga();
        final String kategori = daftarBarang.get(position).getKategori();
        final String foto = daftarBarang.get(position).getFoto();

        database = FirebaseDatabase.getInstance().getReference();

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Read detail data
                 */
                submitBarang(new BarangModels(name, kategori,harga,foto));
                Toast.makeText(context, "Klik barang "+name,Toast.LENGTH_SHORT).show();
            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Delete dan update data
                 */
                return true;
            }
        });
        holder.tvTitle.setText(name);
        holder.tvKategori.setText(kategori);
        holder.tvHarga.setText("Rp. "+harga);
        Glide.with(context).load(foto).into(holder.ivFoto);
    }

    private void submitBarang(BarangModels barang) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("pembelians").push().setValue(barang);
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarBarang.size();
    }
}
