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
    
    public void setSession(Pengguna id){
        Main.session = id;
    }
    public static Pengguna getSession(){
        return Main.session;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/PemesananList.fxml"));
        
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
        
        // isi Ruangan
        dbRuangan.add(new Ruangan(dbRuangan.size() + 1, "Ruang Auditorium", "Auditorium", 10000, "AC, 30 kursi"));
        dbRuangan.add(new Ruangan(dbRuangan.size() + 1, "Hall", "Hall", 10000, "Kosong"));
        dbRuangan.add(new Ruangan(dbRuangan.size() + 1, "Laboratorium Terpadu FTI", "Laboratorium", 1500, "AC, 30 kursi, Komputer"));
        // isi Pemesanan
        dbPemesanan.add(
                new Pemesanan(
                    dbPemesanan.size() + 1, 
                    dbPengguna.get(1), 
                    dbRuangan.get(2),
                    "Porsematif", "Angkatan16",
                    LocalDate.now(),
                    LocalDateTime.of(LocalDate.parse("2017-07-01"), LocalTime.now()),
                    LocalDateTime.of(LocalDate.parse("2017-07-03"), LocalTime.parse("15:30"))     
                ));

        // isi Komentar
        dbKomentar.add(new Komentar(dbKomentar.size() + 1, dbPengguna.get(1), dbRuangan.get(1), "Good 1", LocalDateTime.now() ));
        dbKomentar.add(new Komentar(dbKomentar.size() + 1, dbPengguna.get(1), "Good 2", LocalDateTime.now() ));
        dbKomentar.add(new Komentar(dbKomentar.size() + 1, dbPengguna.get(2), dbRuangan.get(2), "Good 3", LocalDateTime.now() ));
        
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
        stage.setScene(scene);
        stage.show();
    }
}
