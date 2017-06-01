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
public class LstRuangan {
    // attribute
    private static List<Ruangan> TblRuangan = new ArrayList<Ruangan>();
    
    public List<Ruangan> get(){
        List<Ruangan> tmp = TblRuangan;
        return tmp;
    }
    // Methods
    public void add(Ruangan r){
        TblRuangan.add(r);
    }
    public Ruangan get(int id){
        return TblRuangan.get(id - 1);
    }
    public int size(){
        return TblRuangan.size();
    }
    public void edit(int id, Ruangan r){
        TblRuangan.set(id - 1, r);
    }
    public List<Ruangan> sortByNama() {
        List<Ruangan> tmp = TblRuangan;
        tmp.sort((o1, o2) -> o1.getNama().compareTo(o2.getNama()));
        return tmp;
    }
    public List<Ruangan> sortByKategori(){
        List<Ruangan> tmp = TblRuangan;
        tmp.sort((o1, o2) -> o1.getKategori().compareTo(o2.getKategori()));
        return tmp;            
    }
    public List<Ruangan> sortByHarga(){
        List<Ruangan> tmp = TblRuangan;
        tmp.sort((o1, o2) -> ((Integer)o1.getHarga()).compareTo((Integer)o2.getHarga()));
        return tmp;
    }
    
}
