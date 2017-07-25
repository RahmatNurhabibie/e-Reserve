/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.reserve;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import model.*;
import org.xml.sax.SAXException;

/**
 *
 * @author B.Aja
 */
public class Main extends Application {
    
    private static Pengguna session;
    
    public final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public final static String formatTanggal(LocalDateTime a){
        String tgl = a.format(formatter);
        
        HashMap<String,String> bulan = new HashMap<>();
        bulan.put("01", "Januari");
        bulan.put("02", "Februari");
        bulan.put("03", "Maret");
        bulan.put("04", "April");
        bulan.put("05", "Mei");
        bulan.put("06", "Juni");
        bulan.put("07", "Juli");
        bulan.put("08", "Agustus");
        bulan.put("09", "September");
        bulan.put("10", "Oktober");
        bulan.put("11", "November");
        bulan.put("12", "Desember");
        
        return tgl.substring(0,2) + " " + bulan.get(tgl.substring(3, 5)) + " " + tgl.substring(6,10) + " pukul " + tgl.substring(11);
    }
    public void setSession(Pengguna id){
        Main.session = id;
    }
    public static Pengguna getSession(){
        return Main.session;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
        
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException, TransformerException {            
        XMLController xml = new XMLController();
        xml.readXML();
                
        LstPengguna dbPengguna = new LstPengguna();
        LstRuangan dbRuangan = new LstRuangan();
        LstKomentar dbKomentar = new LstKomentar();
        LstPemesanan dbPemesanan = new LstPemesanan();
        
        launch(args);
    }

    @Override
    public void stop() throws ParserConfigurationException, TransformerException, SAXException, IOException{
        System.out.println("Stage is closing");
        XMLController xml = new XMLController();
        xml.saveXML();
    }
    
    // MD5 Hasher
    public static String MD5(String str){
   	try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	    byte[] array = md.digest(str.getBytes());
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < array.length; ++i) {
	      	sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
    	} catch (java.security.NoSuchAlgorithmException e) {
    		return null;
    	}
    }
    
    // FXML redirecting
    public static void RedirectPage(Class c, Button btn, String urlFXML) throws IOException {
        Stage stage;
        Parent root;
        
        stage = (Stage) btn.getScene().getWindow();
        root = FXMLLoader.load(c.getResource("/view/" + urlFXML + ".fxml"));
        Scene scene = new Scene(root);
        stage.setTitle(urlFXML);
        stage.setScene(scene);
        stage.show();
    }
    
    public static boolean isNumber(String x){
        try {
            int tmp = Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
