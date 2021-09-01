package Sistem.Informasi.Cuti.Karyawan.Controller;

import Sistem.Informasi.Cuti.Karyawan.DTO.PengajuanDto;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.*;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.*;
import Sistem.Informasi.Cuti.Karyawan.Services.Email.SendEmail;
import Sistem.Informasi.Cuti.Karyawan.Services.Empl.PengajuanCutiEmpl;
import Sistem.Informasi.Cuti.Karyawan.Services.HakCutiNewEmployee;
import Sistem.Informasi.Cuti.Karyawan.Services.UserAktif;
import Sistem.Informasi.Cuti.Karyawan.Utils.RandomPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    HakCutiNewEmployee newEmployee;

    @Autowired
    PengajuanCutiRepo pengajuanCutiRepo;

    @Autowired
    PengajuanCutiEmpl pengajuanService;

    @Autowired
    StatusCutiRepo cutiRepo;

    @Autowired
    UserAktif userAktif;

    @GetMapping("/karyawan")
    public String Employee(Model model){
        List<Employee> employees = employeeRepo.employeeAktif();
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

    @GetMapping("/karyawan/{id}")
    public String Edit(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeRepo.findById(id).get();
        List<Role> roles = roleRepo.findAll();
        model.addAttribute("employee",employee);
        model.addAttribute("roles",roles);
        return "form_recruitmen";
    }

    @GetMapping("/hapus_karyawan/{id}")
    public String Hapus(@PathVariable("id") Integer id){
        Employee employee = employeeRepo.findById(id).get();
        employee.setCreatedBy(employee.getCreatedBy());
        employee.setCreatedDate(employee.getCreatedDate());
        employee.setDeleted(false);
        employeeRepo.save(employee);
        return "redirect:/HRD/karyawan";
    }

    @PostMapping("/simpan")
    public String simpan(Employee employee){
        boolean cek=false;
        String email = employee.getEmail();
        String nama = employee.getNama();
        String divisi = employee.getDivisi();
        if(employee.getEmploye_id()!=null){
            Employee employee1 = employeeRepo.findById(employee.getEmploye_id()).get();
            employee.setCreatedDate(employee1.getCreatedDate());
            employee.setCreatedBy(employee1.getCreatedBy());
            employee.setUsername(employee1.getUsername());
            employee.setPassword(employee1.getPassword());
        }else{
            cek=true;
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String kode = password.randomPassword();
            String hasil =encoder.encode(kode);
            employee.setUsername(nama);
            employee.setPassword(hasil);
            kirim.EmailRecrutmen(email,nama,kode,divisi);

        }
        employee.setDeleted(true);
        employee.setRole(employee.getRole());
        employeeRepo.save(employee);

        if(cek==true){
            newEmployee.Hak(employee);
        }
        return "redirect:/HRD/karyawan";
    }

    @GetMapping("/masuk")
    public String PengajuanMasuk(Model model){
        List<PengajuanDto> dtos = pengajuanService.getAll();
        model.addAttribute("masuk",dtos);

        return "pengajuan_masuk";
    }

    @GetMapping("/aprove/{id}")
    public String Aprove(@PathVariable("id") Integer id){
        Employee HRD = employeeRepo.getUsername(userAktif.getUser());
        PengajuanCuti pengajuanCuti = pengajuanCutiRepo.findById(id).get();
        StatusCuti statusCuti = cutiRepo.getStatus(3);
        pengajuanCuti.setStatusCuti(statusCuti);
        pengajuanCuti.setHrd(HRD);
        pengajuanCuti.setCreatedBy(pengajuanCuti.getCreatedBy());
        pengajuanCuti.setCreatedDate(pengajuanCuti.getCreatedDate());
        pengajuanCutiRepo.save(pengajuanCuti);

        return "redirect:/HRD/masuk";
    }

    @GetMapping("/reject/{id}")
    public String Reject(@PathVariable("id") Integer id){
        Employee HRD = employeeRepo.getUsername(userAktif.getUser());
        PengajuanCuti pengajuanCuti = pengajuanCutiRepo.findById(id).get();
        StatusCuti statusCuti = cutiRepo.getStatus(4);
        pengajuanCuti.setStatusCuti(statusCuti);
        pengajuanCuti.setHrd(HRD);
        pengajuanCuti.setCreatedBy(pengajuanCuti.getCreatedBy());
        pengajuanCuti.setCreatedDate(pengajuanCuti.getCreatedDate());
        pengajuanCutiRepo.save(pengajuanCuti);

        return "redirect:/HRD/masuk";
    }


}
