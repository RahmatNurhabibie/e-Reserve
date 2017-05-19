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
    private int id_pengguna;
    private int id_ruangan;
    private int id_parent;
    private String isi;
    private int tgl; // ubah type data "date"

    //List <Komentar> komen = new ArrayList();
    
    /* constructor */
    public Komentar(){
        
    }
    
    
    /* methods */
   
    public void setKomentar (String isi) {
        this.isi = isi;
    }
    
    public String getKomentar () {
       return this.isi;
    }
    
}
