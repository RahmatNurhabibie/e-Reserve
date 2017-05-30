/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;
import java.util.function.Predicate;
/**
 *
 * @author NizomSidiq
 */
public class DBPengguna {
    // attribute
    private static List<Komentar> TblKomentar = new ArrayList<Komentar>();
    private static List<Pengguna> TblPengguna = new ArrayList<Pengguna>();
    private static List<Ruangan> TblRuangan = new ArrayList<Ruangan>();
    private static List<Pemesanan> TblPemesanan = new ArrayList<Pemesanan>();
        
    // Methods Pengguna
    public void addPengguna(Pengguna p){
        TblPengguna.add(p);
    }
    public Pengguna getPengguna(int id){
        return TblPengguna.get(id - 1);
    }
    public int sizePengguna(){
        return TblPengguna.size();
    }
    public void editPengguna(int id, Pengguna p){
        TblPengguna.set(id - 1, p);
    }
    //username unique validator
    public Pengguna isExist(String username) {
        try {
            Predicate<Pengguna> predicate = c -> c.getUsername().equals(username);
            Pengguna  obj = TblPengguna.stream().filter(predicate).findFirst().get();
            return obj;
        } catch (NoSuchElementException s) {
            return null;
        }
    }
    
    // global tools
}