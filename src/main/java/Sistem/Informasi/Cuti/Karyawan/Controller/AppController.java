package Sistem.Informasi.Cuti.Karyawan.Controller;

import Sistem.Informasi.Cuti.Karyawan.DTO.ChangePasswordDto;
import Sistem.Informasi.Cuti.Karyawan.DTO.PengajuanDto;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.DetailCuti;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.JenisCuti;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.*;
import Sistem.Informasi.Cuti.Karyawan.Services.Email.SendEmail;
import Sistem.Informasi.Cuti.Karyawan.Services.Empl.PengajuanCutiEmpl;
import Sistem.Informasi.Cuti.Karyawan.Services.Interface.PengajuanCutiService;
import Sistem.Informasi.Cuti.Karyawan.Services.UserAktif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

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

    @Autowired
    DetailCutiRepo detailCutiRepo;

    @Autowired
    PengajuanCutiService pengajuanService;

    @Autowired
    PengajuanCutiRepo pengajuanCutiRepo;


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
        model.addAttribute("cuti",pengajuanCutiRepo.getCutiMasuk());

        return "index";
    }

    @GetMapping({"/HRD/setting","/KARYAWAN/setting","/setting"})
    public String Setting(Model model){
        ChangePasswordDto setting = new ChangePasswordDto();
        model.addAttribute("setting",setting);
        return "form_setting";
    }

    @PostMapping("/simpanSetting")
    public String simpan(ChangePasswordDto dto){
        Employee employee1 = employeeRepo.getUsername(aktif.getUser());
        Employee employee = employeeRepo.findById(employee1.getEmploye_id()).get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hasil =encoder.encode(dto.getPassword());
        employee.setPassword(hasil);
        employee.setCreatedDate(employee.getCreatedDate());
        employee.setCreatedBy(employee.getCreatedBy());
        employeeRepo.save(employee);
        return "redirect:/login?logout";
    }

    @GetMapping({"HRD/detail/{id}","KARYAWAN/detail/{id}"})
    public String Detail(@PathVariable("id") Integer id, Model model){
        DetailCuti detailCuti = detailCutiRepo.findById(id).get();
        model.addAttribute("detail",detailCuti);
        return "form_detail_cuti";
    }

    @GetMapping("/404")
    public String error404(){
        return "page_404";
    }
}
