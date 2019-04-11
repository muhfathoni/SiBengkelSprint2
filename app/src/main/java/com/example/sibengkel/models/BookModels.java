package com.example.sibengkel.models;

public class BookModels {

    private long id;
    private String namaKendaraan, email, jenisService, tanggal, jam;

    public BookModels() {
    }

    public BookModels(Long id, String namaKendaraan, String email, String jenisService, String tanggal, String jam) {
        this.id = id;
        this.namaKendaraan = namaKendaraan;
        this.email = email;
        this.jenisService = jenisService;
        this.tanggal = tanggal;
        this.jam = jam;
    }

    public BookModels(String namaKendaraan, String email, String jenisService, String tanggal, String jam) {
        this.namaKendaraan = namaKendaraan;
        this.email = email;
        this.jenisService = jenisService;
        this.tanggal = tanggal;
        this.jam = jam;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenisService() {
        return jenisService;
    }

    public void setJenisService(String jenisService) {
        this.jenisService = jenisService;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
}
