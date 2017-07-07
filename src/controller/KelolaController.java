/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import e.reserve.Main;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.LstPemesanan;
import model.LstRuangan;
import model.Pemesanan;
import model.Ruangan;

/**
 *
 * @author Sptandi
 */
public class KelolaController extends Controller implements Initializable {
    private Ruangan selected; // Form
    private LstRuangan dbRuangan = new LstRuangan();
    private LstPemesanan dbPemesanan = new LstPemesanan();
    

    @FXML private TableView TVPemesanan;
    
    @FXML
    private TableColumn<Pemesanan,String> TVColId, TVColInstitusi, TVColRuang, TVColKegiatan, TVColTglMulai, TVColTglSelesai, TVColAction;
    
    private void konfirmasi(Pemesanan p){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(
                new ButtonType("Terima"), new ButtonType("Tolak"), new ButtonType("Kembali", ButtonData.CANCEL_CLOSE)
        );
        alert.setTitle("Pemesanan no. " + p.getId());
        alert.setHeaderText("Pemesanan ruang " + p.getRuangan().getNama());
        alert.setContentText(
                "Nama Pemesan\t: " + p.getNama() + "\n" +
                "Institusi\t\t\t: " + p.getInstitusi() + "\n" +
                "Ruangan\t\t\t: " + p.getRuangan().getNama() + "\n" +
                "Nama Kegiatan\t: " + p.getKegiatan() + "\n" +
                "Waktu Mulai\t\t: " + Main.formatTanggal(p.getWaktuKegiatan1()) + "\n" +
                "Waktu Mulai\t\t: " + Main.formatTanggal(p.getWaktuKegiatan1()) + "\n\n" +
                "Tanggal Pemesanan : " + p.getTglPemesanan().toString()
        );
        String result = alert.showAndWait().get().getText();
        if (result.equals("Terima")) {
            p.setStatus(true);
            p.setChecked(true);
        }
        else if (result.equals("Tolak")) {
            p.setStatus(false);
            p.setChecked(true);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TVColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TVColInstitusi.setCellValueFactory(new PropertyValueFactory<>("institusi"));
        TVColRuang.setCellValueFactory((CellDataFeatures<Pemesanan, String> p) -> new ReadOnlyStringWrapper(p.getValue().getRuangan().getNama()));
        TVColKegiatan.setCellValueFactory(new PropertyValueFactory<>("kegiatan"));
        TVColTglMulai.setCellValueFactory((CellDataFeatures<Pemesanan, String> p) -> new ReadOnlyStringWrapper(p.getValue().getWaktuKegiatan1().toLocalDate().toString()));
        TVColTglSelesai.setCellValueFactory((CellDataFeatures<Pemesanan, String> p) -> new ReadOnlyStringWrapper(p.getValue().getWaktuKegiatan2().toLocalDate().toString()));
//        TVColAction.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        TVColAction.setCellFactory((final TableColumn<Pemesanan,String> param) -> {
            final TableCell<Pemesanan,String> cell = new TableCell<Pemesanan,String>() {
                final Button btn = new Button("Konfirmasikan");
                
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btn.setOnAction((e) -> konfirmasi(getTableView().getItems().get(getIndex())));
                        setGraphic(btn);
                        setText(null);
                    }
                }
                
            };
            return cell;
        });
        
        ObservableList<Pemesanan> oData = FXCollections.observableArrayList(dbPemesanan.get());
        TVPemesanan.setItems(oData);
    }
    
}
