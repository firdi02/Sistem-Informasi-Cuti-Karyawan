package Sistem.Informasi.Cuti.Karyawan.Model.Repo;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {}
