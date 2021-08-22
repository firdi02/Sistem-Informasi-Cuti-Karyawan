package Sistem.Informasi.Cuti.Karyawan.Model.Entity;

import Sistem.Informasi.Cuti.Karyawan.Model.Common.MyAudtableBase;

import javax.persistence.*;

@Entity
@Table(name = "tbl_employee")
public class Employee extends MyAudtableBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer employe_id;

    @Column(name = "nip", length = 18, unique = true, nullable = false)
    private String nip;

    @Column(name = "nama_lengkap", length = 100, nullable = false)
    private String nama;

    @Column(name = "divisi", length = 50)
    private String divisi;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable=false, updatable=false)
    private Role role;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(Integer employe_id) {
        this.employe_id = employe_id;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
