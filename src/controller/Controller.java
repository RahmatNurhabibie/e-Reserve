/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import e.reserve.Main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Pengguna;

/**
 *
 * @author NizomSidiq
 */
public class Controller {
    @FXML protected Button btnPesan, btnRuangan, btnKritikSaran, btnUserList;
    @FXML protected Button btnLogOut;
    @FXML protected Label usernameSession;
    
    protected void SideBar(){
        usernameSession.setText(Main.getSession().getUsername());
             
        btnPesan.setAlignment(Pos.BASELINE_LEFT);
        btnRuangan.setAlignment(Pos.BASELINE_LEFT);
        btnKritikSaran.setAlignment(Pos.BASELINE_LEFT);
        btnUserList.setAlignment(Pos.BASELINE_LEFT);

        btnPesan.setGraphic(new ImageView(new Image("/view/Icon/bookmark.png")));
        btnRuangan.setGraphic(new ImageView(new Image("/view/Icon/room-door.png")));
        btnKritikSaran.setGraphic(new ImageView(new Image("/view/Icon/chat.png")));
        btnUserList.setGraphic(new ImageView(new Image("/view/Icon/team.png")));
        
        if (Main.getSession().getJabatan() == 1){
            btnPesan.setOnAction((e) -> {
                try {
                    gotoPemesananList(e);
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            btnKritikSaran.setOnAction((e) -> {
                try {
                    gotoKomentarList(e);
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        
        
        if(Main.getSession().getJabatan() == 2) btnUserList.setVisible(false);
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
        PemesananController a = new PemesananController();
        a.selectRuangan(null);
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
