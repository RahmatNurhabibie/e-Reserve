/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.reserve;

import java.io.File;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author NizomSidiq
 */
public class db {
    public static List<Komentar> TblKomentar = new ArrayList<Komentar>();
    public static List<Pengguna> TblPengguna = new ArrayList<Pengguna>();
    public static List<Ruangan> TblRuangan = new ArrayList<Ruangan>();
    public static List<Pemesanan> TblPemesanan = new ArrayList<Pemesanan>();
    
    
    // md5
    private static String MD5(String str){
   	try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	    byte[] array = md.digest(str.getBytes());
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < array.length; ++i) {
	      	sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
    	} catch (java.security.NoSuchAlgorithmException e) {
    		return null;
    	}
    }

    public static void saveXML() throws ParserConfigurationException, TransformerException{
       	savePengguna(); // done
       	// savePemesanan(); -- not ready 
       	// saveKomentar(); -- not ready
       	// saveRuangan(); -- not ready
        System.out.println("Tulis XML Selesai");
    }

    private static void savePengguna() throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
    	Document docPengguna = pembuat.newDocument();
	docPengguna.setXmlStandalone(true);
	Element rootPengguna = docPengguna.createElement("XMLPengguna");
	docPengguna.appendChild(rootPengguna);

        //Tulis XML (Membangun Data XML dari List)
        for(int i=0; i < TblPengguna.size(); i++){
            Element tagPengguna = docPengguna.createElement("Pengguna");
            tagPengguna.setAttribute("id", ""+ TblPengguna.get(i).getId());
            tagPengguna.setAttribute("role", ""+ TblPengguna.get(i).getJabatan());
            tagPengguna.setAttribute("active", ""+ TblPengguna.get(i).getIs_aktif());
            rootPengguna.appendChild(tagPengguna);
            // Nama
            Element tagName = docPengguna.createElement("Name");
            tagName.setTextContent(TblPengguna.get(i).getName());
            tagPengguna.appendChild(tagName);
            // Username
            Element tagUsername = docPengguna.createElement("Username");
            tagUsername.setTextContent(TblPengguna.get(i).getUsername());
            tagPengguna.appendChild(tagUsername);
            // Password
            Element tagPassword = docPengguna.createElement("Password");
            tagPassword.setTextContent(MD5(TblPengguna.get(i).getPassword()));
            tagPengguna.appendChild(tagPassword);
            // Email
            Element tagEmail = docPengguna.createElement("Email");
            tagEmail.setTextContent(TblPengguna.get(i).getEmail());
            tagPengguna.appendChild(tagEmail);
        }
        //Membuat file XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource dom = new DOMSource(docPengguna);
        StreamResult result = new StreamResult(new File("Pengguna.xml"));
        transformer.transform(dom, result);
    }
    
    /* ON PROGRESS */
  
  //   private void savePemesanan(){
  //   	Document docPemesanan = pembuat.newDocument();
		// docPemesanan.setXmlStandalone(true);
		// Element rootPemesanan = docPemesanan.createElement("Pemesanan");
		// docPemesanan.appendChild(rootPemesanan);
  //   }
  //   private void saveKomentar(){
  //   	Document docKomentar = pembuat.newDocument();
		// docKomentar.setXmlStandalone(true);
		// Element rootKomentar = dokumen.createElement("Komentar");
		// docKomentar.appendChild(rootKomentar);
  //   }
  //   private void saveRuangan(){
  //   	Document docRuangan = pembuat.newDocument();
		// docRuangan.setXmlStandalone(true);
		// Element rootRuangan = dokumen.createElement("Ruangan");
		// docRuangan.appendChild(rootRuangan);
  //   }
}