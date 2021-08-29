package Sistem.Informasi.Cuti.Karyawan.Controller;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Libur;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.LiburRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/HRD")
public class LiburController {
    @Autowired
    LiburRepo liburRepo;

    @GetMapping("/libur")
    public String DataLibur(Model model){
        List<Libur> liburs = liburRepo.getLibur();
        model.addAttribute("liburs",liburs);
        return "tabel_libur";
    }

    @GetMapping("/newLibur")
    public String TambahLibur(Model model){
        model.addAttribute("Libur", new Libur());
        return "form_libur";
    }

    @GetMapping("/edit_libur/{id}")
    public String Edit(@PathVariable("id") Integer id, Model model){
        Libur libur = liburRepo.findById(id).get();
        model.addAttribute("Libur", libur);

        return "form_libur";
    }

    @GetMapping("/hapus_libur/{id}")
    public String Hapus(@PathVariable("id") Integer id){
        Libur libur = liburRepo.findById(id).get();
        libur.setDeleted(false);
        libur.setCreatedBy(libur.getCreatedBy());
        libur.setCreatedDate(libur.getCreatedDate());
        liburRepo.save(libur);
        return "redirect:/HRD/libur";
    }

    @PostMapping("/simpanLibur")
    public String SimpanLibur(Libur libur){
        if(libur.getLibur_id()!=null){
            libur.setCreatedBy(libur.getCreatedBy());
            libur.setCreatedDate(libur.getCreatedDate());
        }

        libur.setDeleted(true);
        liburRepo.save(libur);

        return "redirect:/HRD/libur";
    }
}
