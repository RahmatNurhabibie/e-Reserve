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
public class Komentar {
    /* attributes */
    private int id;
    private Pengguna id_pengguna;
    private Ruangan id_ruangan;
    // private Komentar id_parent; --ga usah pake parent aja mungkin--
    private String isi;
    private int tgl; // ubah type data "date"
    
    /* constructor */
    public Komentar(String isi, int tgl){
        this.isi = isi;
        this.tgl = tgl;
    }
    
    
    /* methods */
    
    public String getKomentar () {
       return this.isi;
    }
    
}
