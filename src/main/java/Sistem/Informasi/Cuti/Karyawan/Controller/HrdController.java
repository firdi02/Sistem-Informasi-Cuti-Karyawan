package Sistem.Informasi.Cuti.Karyawan.Controller;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Role;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.EmployeeRepo;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.RoleRepo;
import Sistem.Informasi.Cuti.Karyawan.Services.Email.SendEmail;
import Sistem.Informasi.Cuti.Karyawan.Utils.RandomPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/HRD")
public class HrdController {
    @Autowired
    RoleRepo roleRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    RandomPassword password;

    @Autowired
    SendEmail kirim;

    @GetMapping("/data")
    public String Employee(Model model){
        List<Employee> employees = employeeRepo.findAll();
        model.addAttribute("employee",employees);
        return "TabelKaryawan";
    }

    @GetMapping("/recruitmen")
    public String Recruitmen(Model model){
        List<Role> roles = roleRepo.findAll();
        model.addAttribute("roles",roles);
        model.addAttribute("employee",new Employee());
        return "form_recruitmen";
    }

    @PostMapping("/simpan")
    public String simpan(Employee employee){
        String email = employee.getEmail();
        String nama = employee.getNama();
        String divisi = employee.getDivisi();
        if(employee.getEmploye_id()!=null){
            Employee employee1 = employeeRepo.findById(employee.getEmploye_id()).get();
            employee.setCreatedDate(employee1.getCreatedDate());
            employee.setCreatedBy(employee1.getCreatedBy());
        }else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String kode = password.randomPassword();
            String hasil =encoder.encode(kode);
            employee.setUsername(nama);
            employee.setPassword(hasil);
            kirim.sendEmail(email,nama,kode,divisi);

        }
        employee.setDeleted(true);
        employee.setRole(employee.getRole());
        employeeRepo.save(employee);
        return "redirect:/HRD/data";
    }
}
