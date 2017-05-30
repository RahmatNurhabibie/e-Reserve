/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.reserve;

import model.DBPengguna;
import static e.reserve.Main.MD5;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Pengguna;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author NizomSidiq
 */
public class XMLController {
    private DBPengguna db = new DBPengguna();
    public void saveXML() throws ParserConfigurationException, TransformerException{
       	savePengguna(); 
       	// savePemesanan(); -- not ready 
       	// saveKomentar(); -- not ready
       	// saveRuangan(); -- not ready
        System.out.println("Tulis XML Selesai");
    }
    public void readXML() throws ParserConfigurationException, TransformerException, SAXException, IOException{
       try {
           loadPengguna(); 
           // savePemesanan(); -- not ready 
           // saveKomentar(); -- not ready
           // saveRuangan(); -- not ready
           System.out.println("Load XML Selesai");
       } catch (FileNotFoundException f) {
           System.out.println("file tidak ditemukan");
       }
    }
    private void savePengguna() throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
    	Document docPengguna = pembuat.newDocument();
	docPengguna.setXmlStandalone(true);
	Element rootPengguna = docPengguna.createElement("XMLPengguna");
	docPengguna.appendChild(rootPengguna);

        //Tulis XML (Membangun Data XML dari List)
        for(int i=1; i <= db.sizePengguna(); i++){
            Element tagPengguna = docPengguna.createElement("Pengguna");
            tagPengguna.setAttribute("id", ""+ db.getPengguna(i).getId());
            tagPengguna.setAttribute("role", ""+ db.getPengguna(i).getJabatan());
            tagPengguna.setAttribute("active", ""+ db.getPengguna(i).getIs_aktif());
            rootPengguna.appendChild(tagPengguna);
            // Nama
            Element tagName = docPengguna.createElement("Name");
            tagName.setTextContent(db.getPengguna(i).getName());
            tagPengguna.appendChild(tagName);
            // Username
            Element tagUsername = docPengguna.createElement("Username");
            tagUsername.setTextContent(db.getPengguna(i).getUsername());
            tagPengguna.appendChild(tagUsername);
            // Password
            Element tagPassword = docPengguna.createElement("Password");
            tagPassword.setTextContent(db.getPengguna(i).getPassword());
            tagPengguna.appendChild(tagPassword);
            // Email
            Element tagEmail = docPengguna.createElement("Email");
            tagEmail.setTextContent(db.getPengguna(i).getEmail());
            tagPengguna.appendChild(tagEmail);
        }
        //Membuat file XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource dom = new DOMSource(docPengguna);
        StreamResult result = new StreamResult(new File("Pengguna.xml"));
        transformer.transform(dom, result);
    }
    private void loadPengguna() throws IOException, ParserConfigurationException, SAXException {
        int id, jabatan;
        String nama, username, password, email;
        boolean is_aktif;
        
        File fileXML = new File("Pengguna.xml");
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
        Document dok = pembuat.parse(fileXML);
        dok.getDocumentElement().normalize();
        
        //Menginisialisasi tag yang akan dibaca
        NodeList listXML = dok.getElementsByTagName("Pengguna");
        System.out.println(listXML.getLength());
        for(int i=0; i < listXML.getLength(); i++){
            //Membuat node (atribut) yang akan dibaca (di contoh ada 5 node)
            Node nodeXML = listXML.item(i);
            
            //Mengambil node untuk tiap iterasi (5 node = 5 iterasi)
            if(nodeXML.getNodeType() == Node.ELEMENT_NODE){
                Element elemenku = (Element) nodeXML;
                
                //Memindahkan ke variabel sementara
                id = Integer.parseInt(elemenku.getAttribute("id").trim());
                nama = elemenku.getElementsByTagName("Name").item(0).getTextContent();
                username = elemenku.getElementsByTagName("Username").item(0).getTextContent();
                email = elemenku.getElementsByTagName("Email").item(0).getTextContent();
                password = elemenku.getElementsByTagName("Password").item(0).getTextContent();
                jabatan = Integer.parseInt(elemenku.getAttribute("role").trim());
                is_aktif = Boolean.parseBoolean(elemenku.getAttribute("active"));
                
                //Memasukkan data yang didapat ke List
                Pengguna tmp = new Pengguna(id, nama, username, email, password); 
                tmp.setJabatan(jabatan); 
                tmp.setAktif(is_aktif);
                db.addPengguna(tmp);
            }
        }
    }
   
       /* ON PROGRESS */
     private void savePemesanan(){
  //   	Document docPemesanan = pembuat.newDocument();
		// docPemesanan.setXmlStandalone(true);
		// Element rootPemesanan = docPemesanan.createElement("Pemesanan");
		// docPemesanan.appendChild(rootPemesanan);
     }
     private void saveKomentar(){
  //   	Document docKomentar = pembuat.newDocument();
		// docKomentar.setXmlStandalone(true);
		// Element rootKomentar = dokumen.createElement("Komentar");
		// docKomentar.appendChild(rootKomentar);
     }
     private void saveRuangan(){
  //   	Document docRuangan = pembuat.newDocument();
		// docRuangan.setXmlStandalone(true);
		// Element rootRuangan = dokumen.createElement("Ruangan");
		// docRuangan.appendChild(rootRuangan);
     }
    
    /* tmp unused */
    private static void readPengguna() throws ParserConfigurationException, SAXException, IOException {
//        String Pengguna,Name,ID,Email,Jabatan,Status,Username,User;
//        
//        //list sementara untuk meanmpilkan
//        List <Pengguna> TblPenggunaa = new ArrayList ();
//        
//        //persiapan baca file xml
//        File fileXML = new File("Pengguna.xml");
//        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
//        DocumentBuilder pembuat = produsen.newDocumentBuilder();
//        Document docPengguna = pembuat.parse(fileXML);
//        docPengguna.getDocumentElement().normalize();
//        
//        
//        //membuat list pengguna
//        NodeList listPengguna = docPengguna.getElementsByTagName("Pengguna");
//        
//        
//        for(int i=0; i < listPengguna.getLength(); i++){
//            //Membuat node (atribut) yang akan dibaca (di contoh ada 5 node)
//            Node nodeXML = listPengguna.item(i);
//
//            //Mengambil node untuk tiap iterasi (5 node = 5 iterasi)
//            if(nodeXML.getNodeType() == Node.ELEMENT_NODE){
//                Element sementara = (Element) nodeXML;
//                
//                //Memindahkan ke variabel sementara
//                User = sementara.getAttribute("Pengguna");
//                Name = sementara.getElementsByTagName("Name").item(0).getTextContent();
//                Username = sementara.getElementsByTagName("Username").item(0).getTextContent();
//                Email = sementara.getElementsByTagName("Email").item(0).getTextContent();
//                
//                
//                //Memasukkan data yang didapat ke List sementara
//                TblPenggunaa.add(new Pengguna(User, Name, Username, Email));
//            }
//        }
//        
//        //menampilkan di console sementara
//        for(int i=0; i < TblPenggunaa.size(); i++){
//            System.out.println("ID  : " + TblPenggunaa.get(i).getId());
//            System.out.println("Nama : " + TblPenggunaa.get(i).getName());
//            System.out.println("Username  : " + TblPenggunaa.get(i).getUsername());
//            System.out.println("Email  : " + TblPenggunaa.get(i).getEmail());
//            System.out.println("Status  : " + TblPenggunaa.get(i).getIs_aktif());
//            System.out.println("Jabatan  : " + TblPenggunaa.get(i).getJabatan());
//            System.out.println();
//        }
    }
}
