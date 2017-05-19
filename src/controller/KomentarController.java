/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
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
        String sementara;
        sementara = tfKomentar.getText();
        Komentar komen = new Komentar();
        
        komen.setKomentar(sementara);
        
        lbNotif.setText("Pesan Terkirim !");
       
    }
    
}
