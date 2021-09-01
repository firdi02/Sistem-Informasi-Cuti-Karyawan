package Sistem.Informasi.Cuti.Karyawan.Model.Entity;

import Sistem.Informasi.Cuti.Karyawan.Model.Common.MyAudtableBase;

import javax.persistence.*;

@Entity
@Table(name = "tbl_status_cuti")
public class StatusCuti extends MyAudtableBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_cuti_id")
    private Integer cuti_id;

    @Column(name = "status_cuti", length = 50)
    private String status_cuti;

    @Column(name = "deskripsi")
    private String deskripsi;


    public Integer getCuti_id() {
        return cuti_id;
    }

    public void setCuti_id(Integer cuti_id) {
        this.cuti_id = cuti_id;
    }

    public String getStatus_cuti() {
        return status_cuti;
    }

    public void setStatus_cuti(String status_cuti) {
        this.status_cuti = status_cuti;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
