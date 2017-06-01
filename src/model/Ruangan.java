/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author B.Aja
 */
public class Ruangan {
    /* attributes */
    private int id;
    private String nama;
    private String kategori;
    private int harga;
    private String fasilitas;
        
    /* constructor */
    public Ruangan(int id,String nama,String kategori,int harga,String fasilitas){
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.fasilitas = fasilitas;
    }
    
    /* methods */
    public int getId(){
        return this.id;
    }
    public String getNama(){
        return this.nama;
    }
    public String getKategori(){
        return this.kategori;
    }
    public int getHarga(){
        return this.harga;
    }
    public String getFasilitas(){
        return this.fasilitas;
    }
}
