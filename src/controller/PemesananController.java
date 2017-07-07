/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import e.reserve.Main;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class PemesananController extends Controller implements Initializable {
    private Ruangan selected; // Form
    private LstRuangan dbRuangan = new LstRuangan();
    private LstPemesanan dbPemesanan = new LstPemesanan();
    
    @FXML private Label usernameSession;
    
    
    // Form
    @FXML 
    private DatePicker dpWaktuMulai, dpWaktuAkhir;
    
    @FXML
    private ComboBox<String> cbJamMulai, cbJamAkhir;
    @FXML private ComboBox<Ruangan> cbRuangan;
    
    @FXML 
    private TextField tfNamaKeg, tfInstitusi;
    
    @FXML
    private void bookingAction (ActionEvent e) {
        // simpan input ke dalam variabel sementara
        Ruangan ruangan = cbRuangan.getValue();
        LocalDate waktuAwal = dpWaktuMulai.getValue();
        LocalDateTime awal = LocalDateTime.of(waktuAwal, LocalTime.of(Integer.parseInt(cbJamMulai.getValue()), 0));
        LocalDate waktuAkhir = dpWaktuAkhir.getValue();
        LocalDateTime akhir = LocalDateTime.of(waktuAwal, LocalTime.of(Integer.parseInt(cbJamAkhir.getValue()), 0));
        String institusi = tfInstitusi.getText();
        String namaKeg = tfNamaKeg.getText();
        
        // Buat pemesanan baru dan tambahkan ke dalam ListPemesanan
        Pemesanan tmp = new Pemesanan(dbPemesanan.size() + 1, Main.getSession(), ruangan, institusi, namaKeg, LocalDate.now(), awal, akhir);
        dbPemesanan.add(tmp);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameSession.setText(Main.getSession() != null ? Main.getSession().getUsername() : "not logged in");
        

        // inisiasi Form
        ObservableList<Ruangan> listRuangan = FXCollections.observableArrayList();
        dbRuangan.get().forEach((r) -> {
            listRuangan.add(r);
        });
        cbRuangan.setItems(listRuangan);
        Callback<ListView<Ruangan>, ListCell<Ruangan>> factory = lv -> new ListCell<Ruangan>() {
            @Override
            protected void updateItem(Ruangan item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNama());
            }
        };
        cbRuangan.setButtonCell(factory.call(null));
        cbRuangan.setCellFactory(factory);
        
        ObservableList<String> listJam = FXCollections.observableArrayList();
        for(int i = 0; i <= 2; i++){
            for (int j = 0; j <= 9; j++){
                if (i == 2 && j > 3) break;
                listJam.add(String.valueOf(i) + String.valueOf(j));
            }
        }
        cbJamMulai.setItems(listJam);
        cbJamAkhir.setItems(listJam);
        cbJamMulai.getSelectionModel().selectFirst();
        cbJamAkhir.getSelectionModel().selectFirst();
        if (selected != null) {
            cbRuangan.getSelectionModel().select(selected.getId()- 1);
        }
        
        // inisiasi PemesananList
        
    }
    
    public void selectRuangan(Ruangan r){
        this.selected = r;
    }
}
