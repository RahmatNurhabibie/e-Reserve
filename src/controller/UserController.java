/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import e.reserve.Main;
import model.LstPengguna;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Pengguna;

/**
 *
 * @author Sptandi
 */
public class UserController implements Initializable {
    @FXML 
    Label lbId, lbName, lbUsername, lbEmail, lbJabatan, lbAktif,
          dataId, dataName, dataUsername, dataEmail, dataJabatan, dataAktif,
          usernameSession;
    
    @FXML TableView TVPengguna;
    @FXML
    TableColumn TVColId, TVColNama, TVColUsername, TVColEmail, TVColJabatan, TVColAktif;
    
    private LstPengguna dbpengguna = new LstPengguna();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int uid = Main.getSession();
        Pengguna logged = dbpengguna.get(uid);
        usernameSession.setText(logged.getUsername());
        
        // With TableView
        TVColId.setCellValueFactory(new PropertyValueFactory<Pengguna,String>("id"));
        TVColNama.setCellValueFactory(new PropertyValueFactory<Pengguna,String>("name"));
        TVColUsername.setCellValueFactory(new PropertyValueFactory<Pengguna,String>("username"));
        TVColEmail.setCellValueFactory(new PropertyValueFactory<Pengguna,String>("email"));
        TVColJabatan.setCellValueFactory(new PropertyValueFactory<Pengguna,String>("jabatan"));
        TVColAktif.setCellValueFactory(new PropertyValueFactory<Pengguna,String>("is_aktif"));
        ObservableList<Pengguna> oData = FXCollections.observableArrayList(dbpengguna.get());
        TVPengguna.setItems(oData);
        // Text based Table
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
//        dataId.setText(id);
//        dataName.setText(name);
//        dataUsername.setText(username);
//        dataEmail.setText(email);
//        dataJabatan.setText(jabatan);
//        dataAktif.setText(aktif);
    }
}
