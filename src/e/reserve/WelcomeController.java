/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.reserve;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author B.Aja
 */
public class WelcomeController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btn1, btn2, btn3, btn4, btn5;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource()==btn1) {
            stage = (Stage) btn1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/PemesananForm.fxml"));
        } else if (event.getSource()==btn2){
            stage = (Stage) btn2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/DetailPage.fxml"));
        } else if (event.getSource()==btn3){
            stage = (Stage) btn3.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/KomentarForm.fxml"));
        } else if (event.getSource()==btn4){
            stage = (Stage) btn4.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/RuanganList.fxml"));
        } else {
            stage = (Stage) btn5.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/UserList.fxml"));
        }
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
