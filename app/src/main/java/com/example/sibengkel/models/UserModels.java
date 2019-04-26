package com.example.sibengkel.models;

public class UserModels {

    private long id;
    private String nama, address, password, phone, email;

    public UserModels(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id;    }

    public String getNama () { return nama; }

    public void setNama(String nama) { this.nama = nama; }

    public String getAddress () { return address; }

    public void setAddress (String alamat) { this.address = address; }

    public String getPassword () { return password; }

    public void setPassword (String password) { this.password = password; }

    public String getPhone () { return phone;}

    public void setPhone (String phone) { this.phone = phone; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}