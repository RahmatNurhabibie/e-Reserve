/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import e.reserve.Main;
import java.io.IOException;
import model.LstPengguna;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import model.Pengguna;

/**
 *
 * @author Sptandi
 */
public class UserController implements Initializable {
    @FXML 
    Label usernameSession;
    @FXML 
    TableView TVPengguna;
    @FXML
    TableColumn TVColId, TVColNama, TVColUsername, TVColEmail, TVColJabatan, TVColAktif;
    @FXML
    Button btnLogOut;
    private LstPengguna dbpengguna = new LstPengguna();
    
    @FXML
    private void logOutAction (ActionEvent event) throws IOException {
        Main sess = new Main();
        sess.setSession(null);
        Main.RedirectPage(getClass(), btnLogOut, "LoginPage");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pengguna logged = Main.getSession();
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
        
    }
}