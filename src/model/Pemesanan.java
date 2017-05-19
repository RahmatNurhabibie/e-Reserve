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
public class Pemesanan {
    /* attributes */
    private int id;
    private int id_pengguna;
    private int id_ruangan;
    private String tgl_pemesanan; // ubah type data "date"
    private String waktu_kegiatan; // ubah type data "date"
    private String nama_kegiatan;
    private boolean status;
    
    /* constructor */
    public Pemesanan(){

    }
    
    /* methods */
    /*
    public int getPemesanan () {
        //tipe data returnnya.
    }
    
    public boolean getStatus () {
        //boolean ?
    }
    
    */
}
