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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LstRuangan;
import model.Ruangan;

/**
 *
 * @author Sptandi
 */
public class RuanganController extends Controller implements Initializable{
    // RuanganForm
    @FXML 
    private TextField tfNamaRuang, tfKategori, tfHarga;
    @FXML private TextArea taFasilitas;
    
            
    // RuanganList
    @FXML
    private TableView<Ruangan> TableViewKomentar;
    @FXML
    private TableColumn ColId, ColNama, ColKategori, ColHarga, ColFasilitas;
    
    private LstRuangan dbRuangan = new LstRuangan();
    
    @FXML
    private void buatRuangAction(ActionEvent e){
        String nama = tfNamaRuang.getText();
        String kategori = tfKategori.getText();
        int harga = Integer.parseInt(tfHarga.getText());
        String fasilitas = taFasilitas.getText();
        try {
            dbRuangan.add(new Ruangan(dbRuangan.size() +1, nama, kategori, harga, fasilitas));           
        } catch (Exception ex){
            System.out.println("Ruangan tidak berhasil ditambahkan");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // RuanganForm
        
        
        // RuanganList
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
