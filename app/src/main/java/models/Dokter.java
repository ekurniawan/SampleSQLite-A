package models;

/**
 * Created by erick on 20/11/2017.
 */

public class Dokter {
    private String Nik;
    private String Nama;
    private String Spesialisasi;
    private String Alamat;

    public String getNik() {
        return Nik;
    }

    public void setNik(String nik) {
        Nik = nik;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getSpesialisasi() {
        return Spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        Spesialisasi = spesialisasi;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }
}
