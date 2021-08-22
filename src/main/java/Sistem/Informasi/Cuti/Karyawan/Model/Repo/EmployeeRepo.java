package Sistem.Informasi.Cuti.Karyawan.Model.Repo;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    @Query("SELECT u FROM Employee u WHERE u.username =?1")
    public Employee getUsername(String username);
}
