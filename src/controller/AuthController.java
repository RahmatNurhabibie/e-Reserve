/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.*;

/**
 *
 * @author Sptandi
 */

public class AuthController  {
    
    @FXML 
    private TextField tfUserName,tfPassword;
    
    @FXML 
    private Button btLogin;
    
    String pass = "123456";
    String user = "16523189"; 
    
    Pengguna orang = new Pengguna(" ", " ");
    
     @FXML
    private void handleButtonSubmit (ActionEvent event) throws IOException {
        String a;
        String b;
        
        a = tfUserName.getText();
        b = tfPassword.getText();
        
        if (orang.getUsername(a) == user && orang.getPassword(b) == pass) {
             FXMLLoader.load(getClass().getResource("/view/UserList.fxml"));
        }
    }
    
}
    

