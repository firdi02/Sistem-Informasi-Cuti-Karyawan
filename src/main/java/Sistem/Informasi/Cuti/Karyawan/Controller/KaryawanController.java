package Sistem.Informasi.Cuti.Karyawan.Controller;

import Sistem.Informasi.Cuti.Karyawan.DTO.PengajuanDto;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.*;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.*;
import Sistem.Informasi.Cuti.Karyawan.Services.Email.SendEmail;
import Sistem.Informasi.Cuti.Karyawan.Services.PDF.ExportPdf;
import Sistem.Informasi.Cuti.Karyawan.Services.Interface.PengajuanCutiService;
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

    @Autowired
    StatusCutiRepo cutiRepo;

    @Autowired
    SendEmail email;

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

    @GetMapping("/send/{id}")
    public String Send(@PathVariable("id") Integer id){
        List<String> HRD = employeeRepo.getHrd();
        Employee aktif = employeeRepo.getUsername(userAktif.getUser());
        String[] emailArr = HRD.toArray(new String[HRD.size()]);
        PengajuanCuti pengajuanCuti = pengajuanCutiRepo.findById(id).get();
        StatusCuti statusCuti = cutiRepo.getStatus(2);
        pengajuanCuti.setStatusCuti(statusCuti);
        pengajuanCuti.setCreatedBy(pengajuanCuti.getCreatedBy());
        pengajuanCuti.setCreatedDate(pengajuanCuti.getCreatedDate());
        pengajuanCutiRepo.save(pengajuanCuti);
        email.SendCuti(aktif,emailArr,pengajuanCuti);
        return "redirect:/KARYAWAN/monitoring";
    }

    @GetMapping("/cancel/{id}")
    public String Cancel(@PathVariable("id") Integer id){
        List<String> HRD = employeeRepo.getHrd();
        Employee aktif = employeeRepo.getUsername(userAktif.getUser());
        String[] emailArr = HRD.toArray(new String[HRD.size()]);
        PengajuanCuti pengajuanCuti = pengajuanCutiRepo.findById(id).get();
        StatusCuti statusCuti = cutiRepo.getStatus(5);
        pengajuanCuti.setStatusCuti(statusCuti);
        pengajuanCuti.setCreatedBy(pengajuanCuti.getCreatedBy());
        pengajuanCuti.setCreatedDate(pengajuanCuti.getCreatedDate());
        pengajuanCutiRepo.save(pengajuanCuti);
        email.Sendcancel(aktif,emailArr);
        return "redirect:/KARYAWAN/monitoring";
    }


}
