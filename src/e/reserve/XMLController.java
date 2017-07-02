/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.reserve;

import model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
    private final LstPengguna dbPengguna = new LstPengguna();
    private final LstKomentar dbKomentar = new LstKomentar();
    private final LstRuangan dbRuangan = new LstRuangan();
    private final LstPemesanan dbPemesanan = new LstPemesanan();
    
    public void saveXML() throws ParserConfigurationException, TransformerException{
       	savePengguna(); 
       	savePemesanan(); 
       	saveKomentar();
       	saveRuangan();
        System.out.println("Tulis XML Selesai");
    }
    public void readXML() throws ParserConfigurationException, TransformerException, SAXException, IOException{
       try {
           loadPengguna(); 
           loadRuangan();
           loadPemesanan();
           loadKomentar();
           System.out.println("Load XML Selesai");
       } catch (FileNotFoundException f) {
           System.out.println("file tidak ditemukan");
       }
    }
    public void savePengguna() throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
    	Document docPengguna = pembuat.newDocument();
	docPengguna.setXmlStandalone(true);
	Element rootPengguna = docPengguna.createElement("XMLPengguna");
	docPengguna.appendChild(rootPengguna);

        //Tulis XML (Membangun Data XML dari List)
        for(int i=1; i <= dbPengguna.size(); i++){
            Element tagPengguna = docPengguna.createElement("Pengguna");
            tagPengguna.setAttribute("id", ""+ dbPengguna.get(i).getId());
            tagPengguna.setAttribute("role", ""+ dbPengguna.get(i).getJabatan());
            tagPengguna.setAttribute("active", ""+ dbPengguna.get(i).getIs_aktif());
            rootPengguna.appendChild(tagPengguna);
            // Nama
            Element tagName = docPengguna.createElement("Name");
            tagName.setTextContent(dbPengguna.get(i).getName());
            tagPengguna.appendChild(tagName);
            // Username
            Element tagUsername = docPengguna.createElement("Username");
            tagUsername.setTextContent(dbPengguna.get(i).getUsername());
            tagPengguna.appendChild(tagUsername);
            // Password
            Element tagPassword = docPengguna.createElement("Password");
            tagPassword.setTextContent(dbPengguna.get(i).getPassword());
            tagPengguna.appendChild(tagPassword);
            // Email
            Element tagEmail = docPengguna.createElement("Email");
            tagEmail.setTextContent(dbPengguna.get(i).getEmail());
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
                dbPengguna.add(tmp);
            }
        }
    }
   
    public void savePemesanan() throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
    	Document docPemesanan = pembuat.newDocument();
	docPemesanan.setXmlStandalone(true);
	Element rootPemesanan = docPemesanan.createElement("XMLPemesanan");
	docPemesanan.appendChild(rootPemesanan);

        //Tulis XML (Membangun Data XML dari List)
        for(int i=1; i <= dbPemesanan.size(); i++){
            Element tagPemesanan = docPemesanan.createElement("Pemesanan");
            tagPemesanan.setAttribute("id", ""+ dbPemesanan.get(i).getId());
            tagPemesanan.setAttribute("idPengguna", ""+ dbPemesanan.get(i).getPengguna().getId());
            tagPemesanan.setAttribute("idRuangan", ""+ dbPemesanan.get(i).getRuangan().getId());
            tagPemesanan.setAttribute("status", String.valueOf(dbPemesanan.get(i).getStatus()));
            rootPemesanan.appendChild(tagPemesanan);
            // Nama Kegiatan
            Element tagNama = docPemesanan.createElement("Nama");
            tagNama.setTextContent(dbPemesanan.get(i).getNama());
            tagPemesanan.appendChild(tagNama);
            // Institusi Pemesan
            Element tagInstitut = docPemesanan.createElement("Institusi");
            tagInstitut.setTextContent(dbPemesanan.get(i).getInstitusi());
            tagPemesanan.appendChild(tagInstitut);
            // Tanggal Pemesanan
            Element tagTglPesan = docPemesanan.createElement("TanggalPesan");
            tagTglPesan.setTextContent(dbPemesanan.get(i).getTglPemesanan().toString());
            tagPemesanan.appendChild(tagTglPesan);
            // Waktu Mulai Kegiatan
            Element tagWaktuMulaiKeg = docPemesanan.createElement("WaktuMulaiKegiatan");
            tagWaktuMulaiKeg.setTextContent(dbPemesanan.get(i).getWaktuKegiatan1().toString());
            tagPemesanan.appendChild(tagWaktuMulaiKeg);
            // Waktu Berakhir Kegiatan
            Element tagWaktuAkhirKeg = docPemesanan.createElement("WaktuAkhirKegiatan");
            tagWaktuAkhirKeg.setTextContent(dbPemesanan.get(i).getWaktuKegiatan2().toString());
            tagPemesanan.appendChild(tagWaktuAkhirKeg);
            
        }
        //Membuat file XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource dom = new DOMSource(docPemesanan);
        StreamResult result = new StreamResult(new File("Pemesanan.xml"));
        transformer.transform(dom, result);
    }
    private void loadPemesanan() throws IOException, ParserConfigurationException, SAXException {
        int id;
        Pengguna pengguna;
        Ruangan ruangan;
        LocalDate tglPesan;
        LocalDateTime mulai, akhir;
        String nama, institusi;
        boolean status;
        
        File fileXML = new File("Pemesanan.xml");
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
        Document dok = pembuat.parse(fileXML);
        dok.getDocumentElement().normalize();
        
        //Menginisialisasi tag yang akan dibaca
        NodeList listXML = dok.getElementsByTagName("Pemesanan");
        System.out.println(listXML.getLength());
        for(int i=0; i < listXML.getLength(); i++){
            //Membuat node (atribut) yang akan dibaca (di contoh ada 5 node)
            Node nodeXML = listXML.item(i);
            
            //Mengambil node untuk tiap iterasi (5 node = 5 iterasi)
            if(nodeXML.getNodeType() == Node.ELEMENT_NODE){
                Element elemenku = (Element) nodeXML;
                
                //Memindahkan ke variabel sementara
                id = Integer.parseInt(elemenku.getAttribute("id").trim());
                pengguna = dbPengguna.get(Integer.parseInt(elemenku.getAttribute("idPengguna").trim()));
                ruangan = dbRuangan.get(Integer.parseInt(elemenku.getAttribute("idRuangan").trim()));
                tglPesan = LocalDate.parse(elemenku.getElementsByTagName("TanggalPesan").item(0).getTextContent());
                mulai = LocalDateTime.parse(elemenku.getElementsByTagName("WaktuMulaiKegiatan").item(0).getTextContent());
                akhir = LocalDateTime.parse(elemenku.getElementsByTagName("WaktuAkhirKegiatan").item(0).getTextContent());
                nama = elemenku.getElementsByTagName("Nama").item(0).getTextContent();
                institusi = elemenku.getElementsByTagName("Institusi").item(0).getTextContent();
                
                //Memasukkan data yang didapat ke List
                Pemesanan tmp = new Pemesanan(id, pengguna, ruangan, institusi, nama, tglPesan, mulai, akhir);
                dbPemesanan.add(tmp);
            }
        }
    }
    public void saveRuangan() throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
    	Document docRuangan = pembuat.newDocument();
	docRuangan.setXmlStandalone(true);
	Element rootRuangan = docRuangan.createElement("XMLRuangan");
	docRuangan.appendChild(rootRuangan);

        //Tulis XML (Membangun Data XML dari List)
        for(int i=1; i <= dbRuangan.size(); i++){
            Element tagRuangan = docRuangan.createElement("Ruangan");
            tagRuangan.setAttribute("id", ""+ dbRuangan.get(i).getId());
            rootRuangan.appendChild(tagRuangan);
            // Nama
            Element tagNama = docRuangan.createElement("Nama");
            tagNama.setTextContent(dbRuangan.get(i).getNama());
            tagRuangan.appendChild(tagNama);
            // Kategori
            Element tagKategori = docRuangan.createElement("Kategori");
            tagKategori.setTextContent(dbRuangan.get(i).getKategori());
            tagRuangan.appendChild(tagKategori);
            // Harga
            Element tagHarga = docRuangan.createElement("Harga");
            tagHarga.setTextContent("" + dbRuangan.get(i).getHarga());
            tagRuangan.appendChild(tagHarga);
            // Fasilitas
            Element tagFasilitas = docRuangan.createElement("Fasilitas");
            tagFasilitas.setTextContent(dbRuangan.get(i).getFasilitas());
            tagRuangan.appendChild(tagFasilitas);
        }
        //Membuat file XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource dom = new DOMSource(docRuangan);
        StreamResult result = new StreamResult(new File("Ruangan.xml"));
        transformer.transform(dom, result);
    }
    private void loadRuangan() throws IOException, ParserConfigurationException, SAXException {
        int id, harga;
        String nama, kategori, fasilitas;
        
        File fileXML = new File("Ruangan.xml");
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
        Document dok = pembuat.parse(fileXML);
        dok.getDocumentElement().normalize();
        
        //Menginisialisasi tag yang akan dibaca
        NodeList listXML = dok.getElementsByTagName("Ruangan");
        System.out.println(listXML.getLength());
        for(int i=0; i < listXML.getLength(); i++){
            //Membuat node (atribut) yang akan dibaca (di contoh ada 5 node)
            Node nodeXML = listXML.item(i);
            
            //Mengambil node untuk tiap iterasi (5 node = 5 iterasi)
            if(nodeXML.getNodeType() == Node.ELEMENT_NODE){
                Element elemenku = (Element) nodeXML;
                
                //Memindahkan ke variabel sementara
                id = Integer.parseInt(elemenku.getAttribute("id").trim());
                nama = elemenku.getElementsByTagName("Nama").item(0).getTextContent();
                kategori = elemenku.getElementsByTagName("Kategori").item(0).getTextContent();
                fasilitas = elemenku.getElementsByTagName("Fasilitas").item(0).getTextContent();
                harga = Integer.parseInt(elemenku.getElementsByTagName("Harga").item(0).getTextContent().trim());
                //Memasukkan data yang didapat ke List
                Ruangan tmp = new Ruangan(id, nama, kategori, harga, fasilitas);
                dbRuangan.add(tmp);
            }
        }
    }
    
    public void saveKomentar() throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
    	Document docKomentar = pembuat.newDocument();
	docKomentar.setXmlStandalone(true);
	Element rootKomentar = docKomentar.createElement("XMLKomentar");
	docKomentar.appendChild(rootKomentar);

        //Tulis XML (Membangun Data XML dari List)
        for(int i=1; i <= dbKomentar.size(); i++){
            Element tagKomentar = docKomentar.createElement("Komentar");
            tagKomentar.setAttribute("id", ""+ dbKomentar.get(i).getId());
            tagKomentar.setAttribute("idPengguna", ""+ dbKomentar.get(i).getPengguna().getId());
            if (dbKomentar.get(i).getRuangan() != null)
                tagKomentar.setAttribute("idRuangan", ""+ dbKomentar.get(i).getRuangan().getId());
            else
                tagKomentar.setAttribute("idRuangan", "-");
            rootKomentar.appendChild(tagKomentar);
            // Tanggal
            Element tagTgl = docKomentar.createElement("Tanggal");
            tagTgl.setTextContent(dbKomentar.get(i).getDate().toString());
            tagKomentar.appendChild(tagTgl);
            // Isi
            Element tagIsi = docKomentar.createElement("Isi");
            tagIsi.setTextContent(dbKomentar.get(i).getIsi());
            tagKomentar.appendChild(tagIsi);    
        }
        //Membuat file XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource dom = new DOMSource(docKomentar);
        StreamResult result = new StreamResult(new File("Komentar.xml"));
        transformer.transform(dom, result);
    }
    private void loadKomentar() throws IOException, ParserConfigurationException, SAXException {
        int id;
        String isi;
        Pengguna pengguna;
        Ruangan ruangan;
        LocalDateTime tgl;
        
        File fileXML = new File("Komentar.xml");
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
        Document dok = pembuat.parse(fileXML);
        dok.getDocumentElement().normalize();
        
        //Menginisialisasi tag yang akan dibaca
        NodeList listXML = dok.getElementsByTagName("Komentar");
        System.out.println(listXML.getLength());
        for(int i=0; i < listXML.getLength(); i++){
            //Membuat node (atribut) yang akan dibaca (di contoh ada 5 node)
            Node nodeXML = listXML.item(i);
            
            //Mengambil node untuk tiap iterasi (5 node = 5 iterasi)
            if(nodeXML.getNodeType() == Node.ELEMENT_NODE){
                Element elemenku = (Element) nodeXML;
                
                //Memindahkan ke variabel sementara
                id = Integer.parseInt(elemenku.getAttribute("id").trim());
                isi = elemenku.getElementsByTagName("Isi").item(0).getTextContent();
                try {
                    ruangan = dbRuangan.get(Integer.parseInt(elemenku.getAttribute("idRuangan").trim()));
                } catch (NumberFormatException e){
                    ruangan = null;
                }
                pengguna = dbPengguna.get(Integer.parseInt(elemenku.getAttribute("idPengguna").trim()));
                tgl = LocalDateTime.parse(elemenku.getElementsByTagName("Tanggal").item(0).getTextContent());
                // Memasukkan data yang didapat ke List
                Komentar tmp = new Komentar(id, pengguna, ruangan, isi, tgl);
                dbKomentar.add(tmp);
            }
        }
    }
    
    
}
