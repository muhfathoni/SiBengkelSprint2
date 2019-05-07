package com.example.sibengkel.models;


import java.io.Serializable;


public class BarangModels implements Serializable {
    private String key,namaBarang,kategori,harga,foto;

    public BarangModels() {
    }

    public BarangModels(String namaBarang, String kategori, String harga, String foto) {
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.harga = harga;
        this.foto = foto;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return " "+namaBarang+"\n" +
                " "+kategori +"\n" +
                " "+harga+"\n" +
                " "+foto;
    }

}
