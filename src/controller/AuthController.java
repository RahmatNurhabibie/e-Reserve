/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import e.reserve.Main;
import e.reserve.XMLController;
import model.LstPengguna;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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
    private Button btnRegister, btnToRegister, btnLogin;
    
    private LstPengguna dbPengguna = new LstPengguna();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    @FXML
    private void loginAction (ActionEvent event) throws IOException {
        String username = tfUsername.getText().trim(); 
        String password = tfPassword.getText().trim();
        System.out.println(username);
        if (dbPengguna.isExist(username) != null) {
            Pengguna tmp = dbPengguna.isExist(username);
            if (Main.MD5(password).equals(tmp.getPassword())) {
                System.out.println("Success Login");
                Main m = new Main();
                m.setSession(tmp.getId());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Welcome to e-Reserve");
                alert.showAndWait();
                
                if (tmp.getJabatan() == 1) // admin
                    Main.RedirectPage(getClass(), btnLogin, "UserList");
                if (tmp.getJabatan() == 2) // pengelola
                    Main.RedirectPage(getClass(), btnLogin, "PemesananList");
                if (tmp.getJabatan() == 3) // user
                    Main.RedirectPage(getClass(), btnLogin, "RuanganList");
            } else {
                txPeringatan.setText("Wrong Password");
            }
        } else {
            txPeringatan.setText("Account has not registered");
        }
    }
    @FXML
    private void toRegisterAction (ActionEvent event) throws IOException {
        Main.RedirectPage(getClass(), btnToRegister, "RegisterForm");
    }
    
    @FXML
    private void registerAction(ActionEvent e) throws IOException{             
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
        } else if (dbPengguna.isExist(user) != null) {
            txPeringatan.setText("username is not available");
            clearPassword(); tfUsername.clear();
        } else {
            dbPengguna.add(new Pengguna(dbPengguna.size() + 1,nama, user, email, Main.MD5(pass)));
            try {
                XMLController xml = new XMLController();
                xml.savePengguna();
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setContentText("You have been registered");
            alert.showAndWait();
            
            Main.RedirectPage(getClass(), btnRegister, "LoginPage");
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
}
