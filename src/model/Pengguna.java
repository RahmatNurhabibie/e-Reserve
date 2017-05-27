/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author B.Aja
 */
public class Pengguna {
    /* attributes */
    private int id;
    private String username;
    private String password;
    private String email;
    private String nama;
    private int jabatan;
    private boolean is_aktif;
    
    /* constructor */
    public Pengguna(int id, String nama, String username, String email, String password){
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.username = username;
        this.password = password;
        this.jabatan = 1;
        this.is_aktif = true;
    }
    
    /* methods */
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.nama;
    }
    public String getPassword() {
        return this.password;
    }
    public String getUsername() {
        return this.username;
    }
    public String getEmail() {
        return this.email;
    }
    public int getJabatan() {
        return this.jabatan;
    }
    public String getIs_aktif() {
        return this.is_aktif ? "True" : "False";
    }
    public void setJabatan(int role){
        this.jabatan = role;
    }
    public void setAktif(boolean active){
        this.is_aktif = active;
    }
}