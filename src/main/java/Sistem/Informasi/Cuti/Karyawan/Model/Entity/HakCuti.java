package Sistem.Informasi.Cuti.Karyawan.Model.Entity;

import Sistem.Informasi.Cuti.Karyawan.Model.Common.MyAudtableBase;

import javax.persistence.*;

@Entity
@Table(name = "tb_hak")
public class HakCuti extends MyAudtableBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hak_cuti_id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "jenis_cuti_id")
    private JenisCuti jenisCuti;

    @Column(name = "sisa_cuti",length = 3)
    private Integer sisa_cuti;

    public Integer getHak_cuti_id() {
        return hak_cuti_id;
    }

    public void setHak_cuti_id(Integer hak_cuti_id) {
        this.hak_cuti_id = hak_cuti_id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public JenisCuti getJenisCuti() {
        return jenisCuti;
    }

    public void setJenisCuti(JenisCuti jenisCuti) {
        this.jenisCuti = jenisCuti;
    }

    public Integer getSisa_cuti() {
        return sisa_cuti;
    }

    public void setSisa_cuti(Integer sisa_cuti) {
        this.sisa_cuti = sisa_cuti;
    }
}
