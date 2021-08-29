package Sistem.Informasi.Cuti.Karyawan.Controller;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.JenisCuti;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.EmployeeRepo;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.HakCutiRepo;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.JenisCutiRepo;
import Sistem.Informasi.Cuti.Karyawan.Services.UserAktif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @Autowired
    UserAktif aktif;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    HakCutiRepo hakCutiRepo;

    @Autowired
    JenisCutiRepo jenisCutiRepo;

    @GetMapping("/")
    public String Home(Model model){
        Employee employee = employeeRepo.getUsername(aktif.getUser());
        JenisCuti CutiTahunan = jenisCutiRepo.findById(1).get();
        JenisCuti CutiLintas = jenisCutiRepo.findById(2).get();

        model.addAttribute("Tahunan",hakCutiRepo.sisaCuti(employee,CutiTahunan));
        model.addAttribute("Lintas",hakCutiRepo.sisaCuti(employee,CutiLintas));
        model.addAttribute("total",employeeRepo.getTotalEmployee());
        model.addAttribute("totalHrd",employeeRepo.getTotalHrd());
        model.addAttribute("totalKaryawan",employeeRepo.getTotalKaryawan());

        return "index";
    }
}
