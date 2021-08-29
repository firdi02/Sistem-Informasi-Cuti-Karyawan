package Sistem.Informasi.Cuti.Karyawan.Model.Repo;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.HakCuti;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.JenisCuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HakCutiRepo extends JpaRepository<HakCuti,Integer> {
    @Query("SELECT u.sisa_cuti FROM HakCuti u WHERE u.employee =?1 AND u.jenisCuti =?2")
    public Integer sisaCuti(Employee employee, JenisCuti jenisCuti);

    @Query("SELECT u.jenisCuti FROM HakCuti u WHERE NOT u.sisa_cuti = 0 AND u.employee=?1")
    List<JenisCuti> getJenisCuti(Employee employee);
}
