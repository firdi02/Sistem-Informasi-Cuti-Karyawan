package Sistem.Informasi.Cuti.Karyawan.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PengajuanDto {
    private Integer detailId;
    private Integer pengajuanId;
    private Integer employeeId;
    private Integer statusId;
    private Integer penggantiId;
    private Integer hrdId;
    private Integer jenisCutiId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalCuti;

    private Integer lamaCuti;
    private String alamat;
    private String noTelp;
    private String keterangan;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getPengajuanId() {
        return pengajuanId;
    }

    public void setPengajuanId(Integer pengajuanId) {
        this.pengajuanId = pengajuanId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getPenggantiId() {
        return penggantiId;
    }

    public void setPenggantiId(Integer penggantiId) {
        this.penggantiId = penggantiId;
    }

    public Integer getHrdId() {
        return hrdId;
    }

    public void setHrdId(Integer hrdId) {
        this.hrdId = hrdId;
    }

    public Integer getJenisCutiId() {
        return jenisCutiId;
    }

    public void setJenisCutiId(Integer jenisCutiId) {
        this.jenisCutiId = jenisCutiId;
    }

    public Date getTanggalCuti() {
        return tanggalCuti;
    }

    public void setTanggalCuti(Date tanggalCuti) {
        this.tanggalCuti = tanggalCuti;
    }

    public Integer getLamaCuti() {
        return lamaCuti;
    }

    public void setLamaCuti(Integer lamaCuti) {
        this.lamaCuti = lamaCuti;
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
}
