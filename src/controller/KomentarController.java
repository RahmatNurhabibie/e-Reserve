/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import model.DBPengguna;
import model.Komentar;

/**
 *
 * @author Sptandi
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class KomentarController {
    @FXML
    private TextField  tfKomentar,tfNama,tfRuang;
    
    @FXML
    private Button btSubmit;
    
    @FXML
    private Label lbNotif; 
    
    @FXML
    private void handleButtonSubmit (ActionEvent event) {
        String isi = tfKomentar.getText();
        if (!isi.isEmpty()){
            Komentar komen = new Komentar(isi, 123);
//            DBPengguna.TblKomentar.add(komen);
            lbNotif.setText("Pesan Terkirim !");    
        } else {
            lbNotif.setText("form komentar masih kosong!");    
        }
    }
    
}
