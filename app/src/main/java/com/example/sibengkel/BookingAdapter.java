package com.example.sibengkel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sibengkel.models.BookModels;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

    private Context context;
    private List<BookModels> notesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaKendaraan;
        public TextView jenisService;
        public TextView waktu;

        public MyViewHolder(View view) {
            super(view);
            namaKendaraan = view.findViewById(R.id.namaKendaraan);
            jenisService = view.findViewById(R.id.jenisService);
            waktu = view.findViewById(R.id.waktu);
        }
    }


    public BookingAdapter(Context context, List<BookModels> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final BookModels bookings = notesList.get(position);

        holder.namaKendaraan.setText(bookings.getNamaKendaraan());
        holder.jenisService.setText(bookings.getJenisService());
        holder.waktu.setText(bookings.getTanggal()+" Jam:  "+bookings.getJam());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, bookings.getNamaKendaraan(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
