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
public class LstKomentar {
    // Attribute
    private static List<Komentar> TblKomentar = new ArrayList<Komentar>();
    
    // Methods
    public void add(Komentar k){
        TblKomentar.add(k);
    }
    public Komentar get(int id){
        return TblKomentar.get(id - 1);
    }
    public int size(){
        return TblKomentar.size();
    }
    public void editKomentar(int id, Komentar k){
        TblKomentar.set(id - 1, k);
    }
}
