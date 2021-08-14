package Sistem.Informasi.Cuti.Karyawan.Model.Repo;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.JenisCuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisCutiRepo extends JpaRepository<JenisCuti, Integer> {
}
