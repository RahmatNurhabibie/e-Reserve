/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author B.Aja
 */
public class Pemesanan {
    /* attributes */
    private int id;
    private Pengguna id_pengguna;
    private Ruangan id_ruangan;
    private LocalDate tgl_pemesanan; // ubah type data "date"
    private LocalDate waktu_kegiatan; // ubah type data "date"
    private String nama_kegiatan;
    private boolean status; // true if confirmed by admin
    
    /* constructor */
    public Pemesanan(int id, Pengguna pengguna, Ruangan ruangan, LocalDate tglPesan, LocalDate waktu, String nama){
        this.id = id;
        this.id_pengguna = pengguna;
        this.id_ruangan = ruangan;
        this.nama_kegiatan = nama;
        this.tgl_pemesanan = LocalDate.now();
        this.waktu_kegiatan = waktu;
        this.status = false;
    }
    
    /* methods */
    
    public int getId() {
        return this.id;
    }
    public Pengguna getPengguna() {
        return this.id_pengguna;
    }
    public Ruangan getRuangan() {
        return this.id_ruangan;
    }
    public LocalDate getTglPemesanan() {
        return this.tgl_pemesanan;
    }
    public LocalDate getWaktuKegiatan() {
        return this.waktu_kegiatan;
    }
    public String getNama() {
        return this.nama_kegiatan;
    }
    public boolean getStatus () {
        return this.status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
