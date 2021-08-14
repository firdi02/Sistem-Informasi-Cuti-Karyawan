package Sistem.Informasi.Cuti.Karyawan.Model.Entity;

import Sistem.Informasi.Cuti.Karyawan.Model.Common.MyAudtableBase;

import javax.persistence.*;

@Entity
@Table(name = "tbl_libur")
public class Libur extends MyAudtableBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libur_id")
    private Integer libur_id;

    @Column(name = "nama_libur", length = 100)
    private String namaLibur;

    @Column(name = "deskripsi")
    private String deskripsi;

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
