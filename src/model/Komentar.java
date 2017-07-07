/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author B.Aja
 */
public class Komentar {
    /* attributes */
    private int id;
    private Pengguna id_pengguna;
    private Ruangan id_ruangan;
    // private Komentar id_parent; --ga usah pake parent aja mungkin--
    private String isi;
    private LocalDateTime tgl; // ubah type data "date"
    
    /* constructor */
    public Komentar(int id, Pengguna pengguna, Ruangan ruangan, String isi, LocalDateTime tgl){
        this.id = id;
        this.id_pengguna = pengguna;
        this.id_ruangan = ruangan;
        this.isi = isi;
        this.tgl = tgl;
    }
    public Komentar(int id, Pengguna pengguna, String isi, LocalDateTime tgl){
        this(id, pengguna, null, isi, tgl);
    }
    
    
    /* methods */    
    public int getId () {
       return this.id;
    }
    public Pengguna getPengguna() {
        return this.id_pengguna;
    }
    public Ruangan getRuangan() {
       return this.id_ruangan;
    }
    public String getIsi () {
       return this.isi;
    }
    public LocalDateTime getDate () {
       return this.tgl;
    }
    
}
