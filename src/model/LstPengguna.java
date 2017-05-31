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
public class LstPengguna {
    // attribute
    private static List<Pengguna> TblPengguna = new ArrayList<Pengguna>();
        
    // Methods Pengguna
    public void add(Pengguna p){
        TblPengguna.add(p);
    }
    public Pengguna get(int id){
        return TblPengguna.get(id - 1);
    }
    public int size(){
        return TblPengguna.size();
    }
    public void edit(int id, Pengguna p){
        TblPengguna.set(id - 1, p);
    }
    /**
    getSortedBy(option)
    * "id" ||
    * "nama" ||
    * "username" ||
    * "email"
    @return sortedList
    */
    public List<Pengguna> getSortedBy(String str){
        List<Pengguna> tmp = TblPengguna;
        switch (str){
            case "id":
                tmp.sort((o1, o2) -> ((Integer)o1.getId()).compareTo((Integer)o2.getId()));
                break;
            case "nama":
                tmp.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                break;
            case "username":
                tmp.sort((o1, o2) -> o1.getUsername().compareTo(o2.getUsername()));
                break;
            case "email":
                tmp.sort((o1, o2) -> o1.getEmail().compareTo(o2.getEmail()));
                break;
        }
        return tmp;
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