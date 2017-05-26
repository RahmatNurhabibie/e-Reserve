/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import e.reserve.db;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.*;

/**
 *
 * @author Sptandi
 */

public class AuthController  {
     
    @FXML 
    private TextField tfUsername,tfPassword; // loginPage & RegisterForm
    @FXML
    private TextField tfName, tfEmail, tfPassword2;
    @FXML 
    private Button btLogin;
    
    String password = "123456";
    String username = "16523189"; 
    
    @FXML
    private void handleButtonSubmit (ActionEvent event) throws IOException {
        String a;
        String b;
        
//        a = tfUsername.getText();
//        b = tfPassword.getText();
//        
//        if (orang.getUsername(a) == username && orang.getPassword(b) == password) {
//             FXMLLoader.load(getClass().getResource("/view/UserList.fxml"));
//        }
    }
    
    private void registerAction(ActionEvent e){
        String nama = tfName.getText();
        String user = tfUsername.getText();
        String email = tfEmail.getText();
        String pass = tfPassword.getText();
        String pass2 = tfPassword2.getText();
        if (nama.isEmpty() || user.isEmpty() || email.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
            System.out.println("Please fill all field!");
        } else if (pass != pass2 || pass.length() <= 8) {
            System.out.println("password must be at least 8 character\n"
                                + "Confirm Password must be matched!");
        } else if (!email.contains("@.")){
            System.out.println("email not valid!");
        } else if (user.length() < 4 || user.contains("1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm")){
            System.out.println("Username must be at least 4 character\n"
                                + " and character only contain (0-9A-Za-z)");
        } else if (user.isEmpty()) { // Unique Username "username is not available"
            System.out.println("username is not available");
        } else {
            db.TblPengguna.add(new Pengguna(nama, user, email, pass));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setContentText("You have been registered");

            alert.showAndWait();
        }
    }
    
}
    

