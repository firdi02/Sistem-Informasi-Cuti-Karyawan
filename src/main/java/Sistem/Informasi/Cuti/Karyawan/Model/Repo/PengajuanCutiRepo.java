package Sistem.Informasi.Cuti.Karyawan.Model.Repo;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.PengajuanCuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PengajuanCutiRepo extends JpaRepository<PengajuanCuti,Integer> {
    @Query("SELECT u FROM PengajuanCuti u WHERE u.pengajuan_id=?1")
    public PengajuanCuti getPengajuan(Integer id);

}
