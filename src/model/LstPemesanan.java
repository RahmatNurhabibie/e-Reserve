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
 * @author NizomSidiq
 */
public class LstPemesanan {
    // attribute
    private static List<Pemesanan> TblPemesanan = new ArrayList<Pemesanan>();
    
    // Methods
    public void add(Pemesanan p){
        TblPemesanan.add(p);
    }
    public Pemesanan get(int id){
        return TblPemesanan.get(id - 1);
    }
    public int size(){
        return TblPemesanan.size();
    }
    public void edit(int id, Pemesanan p){
        TblPemesanan.set(id - 1, p);
    }
}
