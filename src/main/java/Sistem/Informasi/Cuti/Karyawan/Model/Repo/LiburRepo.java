package Sistem.Informasi.Cuti.Karyawan.Model.Repo;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Libur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiburRepo extends JpaRepository<Libur, Integer> {
    @Query("SELECT u FROM Libur u WHERE u.deleted =true")
    List<Libur> getLibur();

}
