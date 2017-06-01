/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.reserve;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author B.Aja
 */
public class Main extends Application {
    
    private static int session;
    
    public void setSession(int id){
        Main.session = id;
    }
    public static int getSession(){
        return Main.session;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
        
        Scene scene = new Scene(root);
        
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
