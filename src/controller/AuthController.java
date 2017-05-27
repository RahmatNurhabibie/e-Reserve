/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import e.reserve.db;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.*;

/**
 *
 * @author Sptandi
 */

public class AuthController implements Initializable {
     
    @FXML 
    private TextField tfUsername,tfPassword; // loginPage & RegisterForm
    @FXML
    private TextField tfName, tfEmail, tfPassword2; // RegisterForm only
    @FXML 
    private Text txPeringatan;
    @FXML 
    private Button btLogin;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* contoh jika sudah ada data */
//        Pengguna tmp = new Pengguna(db.TblPengguna.size() + 1, "nizom", "nijom", "nijomx@gmail.com", "123456");
//        db.TblPengguna.add(tmp);
    }
    
    @FXML
    private void handleButtonSubmit (ActionEvent event) throws IOException {
        /* Mencoba LoginForm */
//        String a;
//        String b;
//        String password = "123456";
//        String username = "16523189"; 
//        a = tfUsername.getText();
//        b = tfPassword.getText();
//        
//        if (orang.getUsername(a) == username && orang.getPassword(b) == password) {
//             FXMLLoader.load(getClass().getResource("/view/UserList.fxml"));
//        }
    }
    
    @FXML
    private void registerAction(ActionEvent e){
        String nama = tfName.getText();
        String user = tfUsername.getText();
        String email = tfEmail.getText();
        String pass = tfPassword.getText().trim();
        String pass2 = tfPassword2.getText().trim();
        if (nama.isEmpty() || user.isEmpty() || email.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
            txPeringatan.setText("Please fill all field!");
            clearPassword();
        } else if (!pass.equals(pass2) || pass.length() < 5) {
            txPeringatan.setText("password must be at least 5 character\n"
                                + "and check both password field");
            clearPassword();
        } else if (!emailValidate(email)){
            txPeringatan.setText("email not valid!");
            clearPassword();
        } else if (user.length() < 4 || usernameValidate(user)){
            txPeringatan.setText("Username must be at least 4 character\n"
                                + "any symbol is not allowed");
            clearPassword(); tfUsername.clear();
        } else if (getPengguna(user) != null) { // Unique Username "username is not available"
            txPeringatan.setText("username is not available");
            clearPassword(); tfUsername.clear();
        } else {
            db.TblPengguna.add(new Pengguna(db.TblPengguna.size() + 1,nama, user, email, pass));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setContentText("You have been registered");

            alert.showAndWait();
        }
    }
    
    // clear password
    private void clearPassword(){
        tfPassword.clear();
        tfPassword2.clear();
    }
    //email validator
    private boolean emailValidate(String emailStr) {
        Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(emailStr);
        return matcher.find(); // true if email valid
    }
    
    // username no symbol allowed
    private boolean usernameValidate(String usernameStr){
        Matcher matcher = Pattern.compile("[^A-Za-z0-9]").matcher(usernameStr);
        return matcher.find(); // true if has special character
    }
    //username unique validator
    private Pengguna getPengguna(String user) {
        try {
            Predicate<Pengguna> predicate = c -> c.getUsername().equals(user);
            Pengguna  obj = db.TblPengguna.stream().filter(predicate).findFirst().get();
            return obj;
        } catch (NoSuchElementException s) {
            return null;
        }
    }
}
