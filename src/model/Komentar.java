/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author B.Aja
 */
public class Komentar {
    /* attributes */
    private int id;
    private int id_pengguna;
    private int id_ruangan;
    // private Komentar id_parent; --ga usah pake parent aja mungkin--
    private String isi;
    private LocalDate tgl; // ubah type data "date"
    
    /* constructor */
    public Komentar(int id, int pengguna, int ruangan, String isi, LocalDate tgl){
        this.id = id;
        this.id_pengguna = pengguna;
        this.id_ruangan = ruangan;
        this.isi = isi;
        this.tgl = tgl;
    }
    public Komentar(int id, int pengguna, String isi, LocalDate tgl){
        this(id, pengguna, 0, isi, tgl);
    }
    
    
    /* methods */    
    public int getId () {
       return this.id;
    }
    public Pengguna getPengguna() {
        LstPengguna tmp = new LstPengguna();
        return tmp.get(this.id);
    }
    public Ruangan getRuangan() {
       LstRuangan tmp = new LstRuangan();
       return tmp.get(this.id);
    }
    public String getIsi () {
       return this.isi;
    }
    public LocalDate getDate () {
       return this.tgl;
    }
    
}
