/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LstRuangan;
import model.Ruangan;

/**
 *
 * @author Sptandi
 */
public class RuanganController implements Initializable{
    @FXML
    private TableView<Ruangan> TableViewKomentar;
    @FXML
    private TableColumn ColId, ColNama, ColKategori, ColHarga, ColFasilitas;
    
    LstRuangan dbRuangan = new LstRuangan();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 4; i++){
            Ruangan tmp = new Ruangan(dbRuangan.size(), "Ruang " + i, "Coba", 5000 * (i+1), "Kosong");
            dbRuangan.add(tmp);
        }
        ColId.setCellValueFactory(
            new PropertyValueFactory<Ruangan,String>("id"));
        ColNama.setCellValueFactory(
            new PropertyValueFactory<Ruangan,String>("nama"));
        ColKategori.setCellValueFactory(
            new PropertyValueFactory<Ruangan,String>("kategori"));
        ColHarga.setCellValueFactory(
            new PropertyValueFactory<Ruangan,String>("harga"));
        ColFasilitas.setCellValueFactory(
            new PropertyValueFactory<Ruangan,String>("fasilitas"));
        
        ObservableList<Ruangan> oData = FXCollections.observableArrayList(dbRuangan.get());
        TableViewKomentar.setItems(oData);
    }
}
