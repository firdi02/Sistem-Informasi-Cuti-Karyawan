package Sistem.Informasi.Cuti.Karyawan.Controller;

import Sistem.Informasi.Cuti.Karyawan.DTO.PengajuanDto;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.DetailCuti;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.JenisCuti;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.PengajuanCuti;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.DetailCutiRepo;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.EmployeeRepo;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.HakCutiRepo;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.PengajuanCutiRepo;
import Sistem.Informasi.Cuti.Karyawan.Services.PDF.ExportPdf;
import Sistem.Informasi.Cuti.Karyawan.Services.PengajuanCutiService;
import Sistem.Informasi.Cuti.Karyawan.Services.UserAktif;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/KARYAWAN")
public class KaryawanController {
    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    HakCutiRepo hakCutiRepo;

    @Autowired
    PengajuanCutiRepo pengajuanCutiRepo;

    @Autowired
    UserAktif userAktif;

    @Autowired
    PengajuanCutiService pengajuanCutiService;

    @Autowired
    DetailCutiRepo detailCutiRepo;

    @GetMapping("/pengajuan")
    public String Pengajuan(Model model){
        Employee employee = employeeRepo.getUsername(userAktif.getUser());
        List<JenisCuti> jenisCuti = hakCutiRepo.getJenisCuti(employee);
        List<Employee> pengganti = employeeRepo.getPengganti(employee.getEmploye_id());
        PengajuanDto pengajuanDto = new PengajuanDto();

        model.addAttribute("jenisCuti",jenisCuti);
        model.addAttribute("pengganti",pengganti);
        model.addAttribute("pengajuan",pengajuanDto);
        return "form_pengajuan";
    }

    @PostMapping("/simpan")
    public String simpan(PengajuanDto pengajuanDto){
        Employee employee = employeeRepo.getUsername(userAktif.getUser());
        pengajuanCutiService.Simpan(pengajuanDto,employee);

        return "redirect:/KARYAWAN/monitoring";
    }

    @GetMapping("/monitoring")
    public String Monitoring(Model model){
        Employee employee = employeeRepo.getUsername(userAktif.getUser());
        List<DetailCuti> detailCutis = detailCutiRepo.getDetailCuti(employee);
        model.addAttribute("pengajuan",detailCutis);

        return "MonitoringCuti";
    }

    @GetMapping("/ubah/{id}")
    public String Edit(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeRepo.getUsername(userAktif.getUser());
        List<JenisCuti> jenisCuti = hakCutiRepo.getJenisCuti(employee);
        List<Employee> pengganti = employeeRepo.getPengganti(employee.getEmploye_id());
        PengajuanDto dto = pengajuanCutiService.getPengajuanId(id,employee);

        model.addAttribute("pengajuan",dto);
        model.addAttribute("jenisCuti",jenisCuti);
        model.addAttribute("pengganti",pengganti);

        return "form_pengajuan";
    }

    @GetMapping("/hapus/{id}")
    public String Hapus(@PathVariable("id") Integer id){
        DetailCuti detailCuti = detailCutiRepo.getDetailId(id);
        detailCuti.setDetail_pengajuan_cuti_id(detailCuti.getDetail_pengajuan_cuti_id());
        detailCuti.setCreatedBy(detailCuti.getCreatedBy());
        detailCuti.setCreatedDate(detailCuti.getCreatedDate());
        detailCuti.setDeleted(false);
        detailCutiRepo.save(detailCuti);

        PengajuanCuti pengajuanCuti = pengajuanCutiRepo.findById(id).get();
        pengajuanCuti.setCreatedDate(pengajuanCuti.getCreatedDate());
        pengajuanCuti.setCreatedBy(pengajuanCuti.getCreatedBy());
        pengajuanCuti.setDeleted(false);
        pengajuanCutiRepo.save(pengajuanCuti);
        return "redirect:/KARYAWAN/monitoring";
    }

    @GetMapping("/detail/{id}")
    public String Detail(@PathVariable("id") Integer id, Model model){
        DetailCuti detailCuti = detailCutiRepo.findById(id).get();
        model.addAttribute("detail",detailCuti);
        return "form_detail_cuti";
    }

    @GetMapping("/cetak/{id}")
    public void ExsportPdf(HttpServletResponse response, @PathVariable("id") Integer id) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pengajuan_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        DetailCuti detailCutis = detailCutiRepo.findById(id).get();

        ExportPdf exporter = new ExportPdf(detailCutis);
        exporter.export(response);

    }


}
