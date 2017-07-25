/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import e.reserve.Main;
import e.reserve.XMLController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import model.LstRuangan;
import model.Pemesanan;
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
    @FXML private Button btnTmbh;
    @FXML
    private TableView<Ruangan> TableViewKomentar;
    @FXML
    private TableColumn<Ruangan, String> ColId, ColNama, ColKategori, ColHarga, ColFasilitas, ColAction;
    
    private LstRuangan dbRuangan = new LstRuangan();
    
    @FXML
    private void buatRuangAction(ActionEvent e){
        if (!Main.isNumber(tfHarga.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Harga yang dimasukkan harus angka");
            alert.showAndWait();
        } else {
            String nama = tfNamaRuang.getText();
            String kategori = tfKategori.getText(); 
            int harga = Integer.parseInt(tfHarga.getText());
            String fasilitas = taFasilitas.getText();
            try {
                dbRuangan.add(new Ruangan(dbRuangan.size() +1, nama, kategori, harga, fasilitas));
                XMLController xml = new XMLController();
                xml.saveRuangan();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukses");
                alert.setContentText("Ruangan berhasil ditambahkan");
                alert.showAndWait();
                try {
                    Main.RedirectPage(getClass(), btnPesan, "RuanganList");
                } catch (IOException ex) {
                    Logger.getLogger(RuanganController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ParserConfigurationException | TransformerException ex){
                System.out.println("Ruangan tidak berhasil ditambahkan");
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SideBar();
        // RuanganForm
        
        
        // RuanganList
        if (Main.getSession().getJabatan() == 2) btnTmbh.setVisible(false);
        try {
        ColId.setCellValueFactory(
            new PropertyValueFactory<>("id"));
        ColNama.setCellValueFactory(
            new PropertyValueFactory<>("nama"));
        ColKategori.setCellValueFactory(
            new PropertyValueFactory<>("kategori"));
        ColHarga.setCellValueFactory(
            new PropertyValueFactory<>("harga"));
        ColFasilitas.setCellFactory((final TableColumn<Ruangan,String> param) -> {
            final TableCell<Ruangan,String> cell = new TableCell<Ruangan,String>() {
                final Button btn = new Button("Lihat");
                
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btn.setPrefWidth(getTableColumn().getWidth());
                        btn.setOnAction((e) -> {
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle(getTableView().getItems().get(getIndex()).getNama());
                            alert.setContentText(getTableView().getItems().get(getIndex()).getFasilitas());
                            alert.show();
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                }
                
            };
            return cell;
        });
        ColAction.setCellFactory((final TableColumn<Ruangan,String> param) -> {
            final TableCell<Ruangan,String> cell = new TableCell<Ruangan,String>() {
                final Button btn = new Button("Pesan");
                
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btn.setPrefWidth(getTableColumn().getWidth());
                        btn.setOnAction((e) -> {
                            try {
                                PemesananController a = new PemesananController();
                                a.selectRuangan(getTableView().getItems().get(getIndex()));
                                Main.RedirectPage(getClass(), btn, "PemesananForm");
                            } catch (IOException ex) {
                                Logger.getLogger(RuanganController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                }
                
            };
            return cell;
        });
        
        ObservableList<Ruangan> oData = FXCollections.observableArrayList(dbRuangan.get());
        TableViewKomentar.setItems(oData);
        } catch (Exception e) {
        }
    }
}
