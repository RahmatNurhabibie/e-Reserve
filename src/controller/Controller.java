/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import e.reserve.Main;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author NizomSidiq
 */
public class Controller {
    @FXML protected Button btnPemesananList;
    @FXML protected Button btnLogOut;
    
    protected void SideBar(){
        btnPemesananList.setGraphic(new ImageView(new Image("/view/Icon/bookmark.png")));        
    }
    @FXML
    protected void gotoRuanganList(ActionEvent e) throws IOException{
        Main.RedirectPage(getClass(), btnLogOut, "RuanganList");
    }
    @FXML
    protected void gotoRuanganForm(ActionEvent e) throws IOException{
        Main.RedirectPage(getClass(), btnLogOut, "RuanganForm");
    }
    @FXML
    protected void logOutAction(ActionEvent e) throws IOException{
        Main m = new Main();
        m.setSession(null);
        Main.RedirectPage(getClass(), btnLogOut, "LoginPage");
    }
    @FXML
    protected void gotoPemesananList(ActionEvent e) throws IOException{
        Main.RedirectPage(getClass(), btnLogOut, "PemesananList");
    }
    @FXML
    protected void gotoPemesananForm(ActionEvent e) throws IOException{
        Main.RedirectPage(getClass(), btnLogOut, "PemesananForm");
    }
    @FXML
    protected void gotoKomentarForm(ActionEvent e) throws IOException{
        Main.RedirectPage(getClass(), btnLogOut, "KomentarForm");
    }
    @FXML
    protected void gotoKomentarList(ActionEvent e) throws IOException{
        Main.RedirectPage(getClass(), btnLogOut, "KomentarList");
    }
    @FXML
    protected void gotoUserList(ActionEvent e) throws IOException{
        Main.RedirectPage(getClass(), btnLogOut, "UserList");
    }
    
}
