/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.LstPengguna;
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
    
    private LstPengguna dbpengguna = new LstPengguna();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String id, name, username, email, jabatan, aktif;
        id = name = username = email = jabatan = aktif = "";
        
        for (int i = 1; i <= dbpengguna.size(); i++ ){
            Pengguna tmp = dbpengguna.get(i);
            
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
