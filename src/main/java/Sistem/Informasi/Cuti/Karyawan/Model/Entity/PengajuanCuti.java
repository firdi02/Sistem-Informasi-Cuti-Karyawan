package Sistem.Informasi.Cuti.Karyawan.Model.Entity;

import Sistem.Informasi.Cuti.Karyawan.Model.Common.MyAudtableBase;

import javax.persistence.*;

@Entity
@Table(name = "tbl_pengajuan_cuti")
public class PengajuanCuti extends MyAudtableBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pengajuan_id")
    private Integer pengajuan_id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusCuti statusCuti;

    @Column(name = "pengganti_id")
    private Integer pengganti_id;

    @ManyToOne
    @JoinColumn(name = "hrd_id")
    private Employee hrd;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "noTelp", length = 15)
    private String noTelp;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "lama_cuti", length = 4)
    private Integer lama_cuti;

    @OneToOne(mappedBy = "pengajuanCuti")
    private DetailCuti detailCuti;

    public DetailCuti getDetailCuti() {
        return detailCuti;
    }

    public void setDetailCuti(DetailCuti detailCuti) {
        this.detailCuti = detailCuti;
    }

    public Integer getPengajuan_id() {
        return pengajuan_id;
    }

    public void setPengajuan_id(Integer pengajuan_id) {
        this.pengajuan_id = pengajuan_id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public StatusCuti getStatusCuti() {
        return statusCuti;
    }

    public void setStatusCuti(StatusCuti statusCuti) {
        this.statusCuti = statusCuti;
    }

    public Integer getPengganti_id() {
        return pengganti_id;
    }

    public void setPengganti_id(Integer pengganti_id) {
        this.pengganti_id = pengganti_id;
    }

    public Employee getHrd() {
        return hrd;
    }

    public void setHrd(Employee hrd) {
        this.hrd = hrd;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getLama_cuti() {
        return lama_cuti;
    }

    public void setLama_cuti(Integer lama_cuti) {
        this.lama_cuti = lama_cuti;
    }
}
