package Sistem.Informasi.Cuti.Karyawan.Services;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.HakCuti;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.JenisCuti;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.HakCutiRepo;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.JenisCutiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HakCutiNewEmployee {
    @Autowired
    HakCutiRepo hakCutiRepo;

    @Autowired
    JenisCutiRepo jenisCutiRepo;

    public void Hak(Employee employee){
        // default hak cuti untuk karyawan baru
        for(int i=1;i<=2;i++){
            JenisCuti jenisTahunan = jenisCutiRepo.findById(i).get();
            HakCuti hakCuti = new HakCuti();
            hakCuti.setEmployee(employee);
            hakCuti.setJenisCuti(jenisTahunan);
            hakCuti.setDeleted(true);
            if(i==1){
                hakCuti.setSisa_cuti(12);
            }else {
                hakCuti.setSisa_cuti(0);
            }
            hakCutiRepo.save(hakCuti);
        }


    }

}
