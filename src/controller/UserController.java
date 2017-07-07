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
public class UserController extends Controller implements Initializable {
    @FXML 
    private Label usernameSession;
    @FXML 
    private TableView TVPengguna;
    @FXML
    private TableColumn<Pengguna, String> TVColId, TVColNama, TVColUsername, TVColEmail, TVColJabatan, TVColAktif;
    private final LstPengguna dbpengguna = new LstPengguna();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pengguna logged = Main.getSession();
        usernameSession.setText(logged.getUsername());
        
        // With TableView
        TVColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TVColNama.setCellValueFactory(new PropertyValueFactory<>("name"));
        TVColUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        TVColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        TVColJabatan.setCellValueFactory(new PropertyValueFactory<>("jabatan"));
        TVColAktif.setCellValueFactory(new PropertyValueFactory<>("is_aktif"));
        ObservableList<Pengguna> oData = FXCollections.observableArrayList(dbpengguna.get());
        TVPengguna.setItems(oData);
        
    }
}