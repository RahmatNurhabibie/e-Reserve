/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.LstRuangan;
import model.Ruangan;
/**
 *
 * @author Sptandi
 */
public class PemesananController implements Initializable {
    LstRuangan dbRuangan = new LstRuangan();
    
    @FXML Label usernameSession;
    
    @FXML TableView TVPemesanan;
    
    @FXML
    TableColumn TVColId, TVColNama, TVColRuang, TVColTglMulai, TVColTglSelesai;
    
    @FXML 
    DatePicker dpWaktuMulai, dpWaktuAkhir;
    
    @FXML
    ComboBox cbJamMulai, cbJamAkhir, cbRuangan;
    
    @FXML 
    TextField tfNamaKeg, tfInstitusi;
    
    @FXML
    private void bookingAction (ActionEvent e) {
        Ruangan ruangan = (Ruangan)cbRuangan.getValue();
        LocalDate waktuAwal = dpWaktuMulai.getValue();
        LocalDateTime awal = LocalDateTime.of(waktuAwal, LocalTime.of((Integer)cbJamMulai.getValue(), 0));
        LocalDate waktuAkhir = dpWaktuAkhir.getValue();
        LocalDateTime akhir = LocalDateTime.of(waktuAkhir, LocalTime.of((Integer)cbJamAkhir.getValue(), 0));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Ruangan> listRuangan = FXCollections.observableArrayList();
        for (Ruangan r : dbRuangan.get()){
            listRuangan.add(r);
        }
        cbRuangan.setItems(listRuangan);
    }
}
