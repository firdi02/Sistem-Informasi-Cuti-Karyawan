package Sistem.Informasi.Cuti.Karyawan.Model.Entity;

import Sistem.Informasi.Cuti.Karyawan.Model.Common.MyAudtableBase;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_detail_pengajuan_cuti")
public class DetailCuti extends MyAudtableBase<String> {
    @Id
    @Column(name = "detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detail_pengajuan_cuti_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pengajuan_id", referencedColumnName = "pengajuan_id")
    private PengajuanCuti pengajuanCuti;

    @ManyToOne
    @JoinColumn(name = "jenis_cuti_id")
    private JenisCuti jenisCuti;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tgl_cuti")
    private Date tanggal;

    public Integer getDetail_pengajuan_cuti_id() {
        return detail_pengajuan_cuti_id;
    }

    public void setDetail_pengajuan_cuti_id(Integer detail_pengajuan_cuti_id) {
        this.detail_pengajuan_cuti_id = detail_pengajuan_cuti_id;
    }

    public PengajuanCuti getPengajuanCuti() {
        return pengajuanCuti;
    }

    public void setPengajuanCuti(PengajuanCuti pengajuanCuti) {
        this.pengajuanCuti = pengajuanCuti;
    }

    public JenisCuti getJenisCuti() {
        return jenisCuti;
    }

    public void setJenisCuti(JenisCuti jenisCuti) {
        this.jenisCuti = jenisCuti;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
