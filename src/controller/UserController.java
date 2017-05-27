/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import e.reserve.db;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Pengguna;

/**
 *
 * @author Sptandi
 */
public class UserController implements Initializable {
    @FXML 
    Label lbId, lbName, lbUsername, lbEmail, lbJabatan, lbAktif,
          dataId, dataName, dataUsername, dataEmail, dataJabatan, dataAktif;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String id, name, username, email, jabatan, aktif;
        id = name = username = email = jabatan = aktif = "";
        
        for (Pengguna tmp : db.TblPengguna){
            id = id + tmp.getId() + "\n";
            name = name + tmp.getName() + "\n";
            username = username + tmp.getUsername() + "\n";
            email = email + tmp.getEmail() + "\n";
            jabatan = jabatan + tmp.getJabatan() + "\n";
            aktif = aktif + tmp.getIs_aktif() + "\n";
        }
        dataId.setText(id);
        dataName.setText(name);
        dataUsername.setText(username);
        dataEmail.setText(email);
        dataJabatan.setText(jabatan);
        dataAktif.setText(aktif);
    }
}
