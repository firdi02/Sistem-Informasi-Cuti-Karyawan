package Sistem.Informasi.Cuti.Karyawan.Model.Entity;

import Sistem.Informasi.Cuti.Karyawan.Model.Common.MyAudtableBase;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_libur")
public class Libur extends MyAudtableBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libur_id")
    private Integer libur_id;

    @Column(name = "nama_libur", length = 100)
    private String namaLibur;

    @Column(name = "deskripsi", length = 255)
    private String deskripsi;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tgl_libur")
    private Date tanggal;

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getLibur_id() {
        return libur_id;
    }

    public void setLibur_id(Integer libur_id) {
        this.libur_id = libur_id;
    }

    public String getNamaLibur() {
        return namaLibur;
    }

    public void setNamaLibur(String namaLibur) {
        this.namaLibur = namaLibur;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
