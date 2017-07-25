/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import e.reserve.Main;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

/**
 *
 * @author Sptandi
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;


public class KomentarController extends Controller implements Initializable {
    @FXML
    private TextField tfNama;
    @FXML
    private TextArea tfKomentar;
    
    @FXML 
    private CheckBox ckbRuangan;
    @FXML
    private ComboBox<Ruangan> cbRuangan;

    @FXML
    private Button btSubmit;
    
    @FXML
    private Label lbNotif; 
    
    private LstKomentar dbKomentar = new LstKomentar();
    private LstRuangan dbRuangan = new LstRuangan();

    @FXML
    private void submitKomentarAction (ActionEvent event) {
        String isi = tfKomentar.getText();
        if (ckbRuangan.isSelected()) {
            Ruangan ruangan = cbRuangan.getValue();
            dbKomentar.add(new Komentar(dbKomentar.size() + 1, Main.getSession(), ruangan, isi, LocalDateTime.now()));
        } else {
            dbKomentar.add(new Komentar(dbKomentar.size() + 1, Main.getSession(), isi, LocalDateTime.now()));
        }  
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setContentText("Kritik dan Saran berhasil dikirimkan");
        alert.showAndWait();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SideBar();
        // Form
        tfNama.setText(Main.getSession().getName());
        tfNama.setEditable(false);
        ckbRuangan.setOnAction((e) -> {
            if (ckbRuangan.isSelected()) cbRuangan.setDisable(false);
            else cbRuangan.setDisable(true);
        });
        ObservableList<Ruangan> listRuangan = FXCollections.observableArrayList();
        dbRuangan.get().forEach((r) -> {
            listRuangan.add(r);
        });
        dbRuangan.get().forEach((r) -> System.out.println(r.toString()));
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
    }
}
