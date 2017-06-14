/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import e.reserve.Main;
import java.time.LocalDate;
import model.LstPengguna;
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
import model.LstKomentar;
import model.Pengguna;


public class KomentarController {
    @FXML
    private TextField  tfKomentar,tfNama,tfRuang;
    
    @FXML
    private Button btSubmit;
    
    @FXML
    private Label lbNotif; 
    
    LstKomentar dbKomentar = new LstKomentar();
    Pengguna logged = Main.getSession();
    
    @FXML
    private void handleButtonSubmit (ActionEvent event) {
        String isi = tfKomentar.getText();
        if (!isi.isEmpty()){
            Komentar komen = new Komentar(dbKomentar.size(), logged.getId(), isi, LocalDate.now());
//            LstPengguna.TblKomentar.add(komen);
            lbNotif.setText("Pesan Terkirim !");    
        } else {
            lbNotif.setText("form komentar masih kosong!");    
        }
    }
    
}
