package utama;
package tidakutama;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modul.mahasiswa;

public class Controller implements Initializable {
    
    @FXML
    public mahasiswa[] mhs = new mahasiswa[6];     //Membuat array mahasiswa
    
    @FXML
    private Label lbNo, lbNama, lbNim, lbUts, lbUas, lbRata2, lbHuruf;
    
    @FXML
    private TextField tfNo1, tfNo2, tfUts, tfUas;
    
    /*Bisa juga membuat method disini. Method ini hanya bisa diakses di sini
    karena sifatnya privat. Jangan lupa tambahkan @FXML sebelum deklarasi
    methodnya ya.*/
    @FXML
    private void tampil(){
        String dataNo, dataNama, dataNim, dataUts, dataUas, dataRata, dataHuruf;     //deklarasi variabel
        dataNo = dataNama = dataNim = dataUts = dataUas = dataRata = dataHuruf = "";   //inisialisasi variabel
        
        //Memilah-milah data, bagian ini agak bikin bingung, perlu fokus ekstra.
        for(int i=0; i < mhs.length; i++){  // mhs.length = panjang array mhs yang kita buat
            dataNo = dataNo + String.valueOf(i+1) + "\n";     //Ingat, array dimulai dari 0, jadi perlu ditambah 1
            dataNama = dataNama + mhs[i].bacaNama() + "\n";   //Karakter "\n" = "enter" (baris baru)
            dataNim = dataNim + mhs[i].bacaNim() + "\n";
            dataUts = dataUts + String.valueOf(mhs[i].bacaUTS()) + "\n";
            dataUas = dataUas + String.valueOf(mhs[i].bacaUAS()) + "\n";
            dataRata = dataRata + String.valueOf(mhs[i].bacaRata2()) + "\n";
            dataHuruf = dataHuruf + mhs[i].bacaHuruf() + "\n";
        }
        
        //Menampilkan data yang telah dipilah-pilah
        this.lbNo.setText(dataNo);
        this.lbNama.setText(dataNama);
        this.lbNim.setText(dataNim);
        this.lbUts.setText(dataUts);
        this.lbUas.setText(dataUas);
        this.lbRata2.setText(dataRata);
        this.lbHuruf.setText(dataHuruf);
    }
    
    @FXML
    private void eventTambahStok(ActionEvent event) {
        int i = Integer.parseInt(tfNo1.getText()) - 1;  //Ingat array dimulai dari 0, jadi perlu dikurangi 1
        int n = Integer.parseInt(tfUts.getText());
        tfNo1.setText("");
        tfUts.setText("");
        
        mhs[i].isiUTS(n);   //Ubah nilai
        this.tampil();      //Tampilkan data baru
    }
    
    @FXML
    private void eventTambahUas(ActionEvent event) {
        int i = Integer.parseInt(tfNo2.getText()) - 1;  //Ingat array dimulai dari 0
        int n = Integer.parseInt(tfUas.getText());
        tfNo2.setText("");
        tfUas.setText("");
        
        mhs[i].isiUAS(n);   //Ubah nilai
        this.tampil();      //Tampilkan data baru
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Bagian initialize digunakan untuk proses inisialisasi pada saat program
        pertama kali dijalankan.*/
        
        /*Pada contoh ini saya gunakan bagian inisialisasi untuk memasukkan data
        mahasiswa (nama, nim, makul, kelas) menggunakan method "isiData()".*/
        mhs[0] = new mahasiswa("Galang", "115231111", "FPA", "B");
        mhs[1] = new mahasiswa("Prihadi", "115232222", "FPA", "B");
        mhs[2] = new mahasiswa("Mahardhika", "115233333", "FPA", "B");
        mhs[3] = new mahasiswa("Gals", "115234444", "FPA", "B");
        mhs[4] = new mahasiswa("Adi", "115235555", "FPA", "B");
        mhs[5] = new mahasiswa("Dhika", "115236666", "FPA", "B");
        
        /*Kemudian saya akan menampilkan inisialisasi awal dari method yang telah
        dibuat pada kelas ini.*/
        this.tampil();
    }
    
}
