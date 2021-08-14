package Sistem.Informasi.Cuti.Karyawan.Model.Entity;

import Sistem.Informasi.Cuti.Karyawan.Model.Common.MyAudtableBase;

import javax.persistence.*;

@Entity
@Table(name = "tbl_jenis_cuti")
public class JenisCuti extends MyAudtableBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jenis_cuti_id")
    private Integer jenis_cuti_id;

    @Column(name = "jenis_cuti", length = 50)
    private String jenis_cuti;

    @Column(name = "deskripsi")
    private String deskripsi;

    public Integer getJenis_cuti_id() {
        return jenis_cuti_id;
    }

    public void setJenis_cuti_id(Integer jenis_cuti_id) {
        this.jenis_cuti_id = jenis_cuti_id;
    }

    public String getJenis_cuti() {
        return jenis_cuti;
    }

    public void setJenis_cuti(String jenis_cuti) {
        this.jenis_cuti = jenis_cuti;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
