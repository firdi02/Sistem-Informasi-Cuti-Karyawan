package Sistem.Informasi.Cuti.Karyawan.Services.Empl;

import Sistem.Informasi.Cuti.Karyawan.DTO.PengajuanDto;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.*;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.DetailCutiRepo;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.JenisCutiRepo;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.PengajuanCutiRepo;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.StatusCutiRepo;
import Sistem.Informasi.Cuti.Karyawan.Services.Interface.PengajuanCutiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PengajuanCutiEmpl implements PengajuanCutiService {
    @Autowired
    PengajuanCutiRepo pengajuanCutiRepo;

    @Autowired
    DetailCutiRepo detailCutiRepo;

    @Autowired
    JenisCutiRepo jenisCutiRepo;

    @Autowired
    StatusCutiRepo cutiRepo;

    @Override
    public void Simpan(PengajuanDto pengajuanDto, Employee employee) {
           StatusCuti statusCuti = cutiRepo.getStatus(1);
           JenisCuti jenisCuti = jenisCutiRepo.getJenisCuti(pengajuanDto.getJenisCutiId());
           PengajuanCuti model = new PengajuanCuti();
           DetailCuti detailCuti = new DetailCuti();
           if(pengajuanDto.getPengajuanId()!=null){
               PengajuanCuti pengajuanCuti = pengajuanCutiRepo.findById(pengajuanDto.getPengajuanId()).get();
               model.setPengajuan_id(pengajuanDto.getPengajuanId());
               model.setAlamat(pengajuanDto.getAlamat());
               model.setKeterangan(pengajuanDto.getKeterangan());
               model.setPengganti_id(pengajuanDto.getPenggantiId());
               model.setLama_cuti(pengajuanDto.getLamaCuti());
               model.setNoTelp(pengajuanDto.getNoTelp());
               model.setEmployee(employee);
               model.setStatusCuti(statusCuti);
               model.setCreatedBy(pengajuanCuti.getCreatedBy());
               model.setCreatedDate(pengajuanCuti.getCreatedDate());
               model.setDeleted(true);
               pengajuanCutiRepo.save(model);

               DetailCuti detailCuti1 = detailCutiRepo.findById(pengajuanDto.getDetailId()).get();
               detailCuti.setCreatedBy(detailCuti1.getCreatedBy());
               detailCuti.setCreatedDate(detailCuti1.getCreatedDate());
               detailCuti.setDetail_pengajuan_cuti_id(pengajuanDto.getDetailId());
               detailCuti.setPengajuanCuti(model);
               detailCuti.setTanggal(pengajuanDto.getTanggalCuti());
               detailCuti.setJenisCuti(jenisCuti);
               detailCuti.setDeleted(true);
               detailCutiRepo.save(detailCuti);

           }else{
               model.setPengajuan_id(pengajuanDto.getPengajuanId());
               model.setAlamat(pengajuanDto.getAlamat());
               model.setKeterangan(pengajuanDto.getKeterangan());
               model.setPengganti_id(pengajuanDto.getPenggantiId());
               model.setLama_cuti(pengajuanDto.getLamaCuti());
               model.setNoTelp(pengajuanDto.getNoTelp());
               model.setEmployee(employee);
               model.setStatusCuti(statusCuti);
               model.setDeleted(true);
               pengajuanCutiRepo.save(model);

               detailCuti.setPengajuanCuti(model);
               detailCuti.setTanggal(pengajuanDto.getTanggalCuti());
               detailCuti.setJenisCuti(jenisCuti);
               detailCuti.setDeleted(true);
               detailCutiRepo.save(detailCuti);
           }

       }

    @Override
    public PengajuanDto getPengajuanId(Integer id, Employee employee) {
        PengajuanCuti model = pengajuanCutiRepo.findById(id).get();
        DetailCuti detailCuti = detailCutiRepo.getDetailId(id);
        PengajuanDto dto = new PengajuanDto();
        dto.setPengajuanId(id);
        dto.setPenggantiId(model.getEmployee().getEmploye_id());
        dto.setEmployeeId(employee.getEmploye_id());
        dto.setAlamat(model.getAlamat());
        dto.setKeterangan(model.getKeterangan());
        dto.setLamaCuti(model.getLama_cuti());
        dto.setNoTelp(model.getNoTelp());
        dto.setStatusId(model.getStatusCuti().getCuti_id());
        dto.setJenisCutiId(detailCuti.getJenisCuti().getJenis_cuti_id());
        dto.setTanggalCuti(detailCuti.getTanggal());
        dto.setDetailId(detailCuti.getDetail_pengajuan_cuti_id());
        return dto;
    }

    private PengajuanDto toDto(DetailCuti detailCuti){
        PengajuanDto dto = new PengajuanDto();
        dto.setDetailId(detailCuti.getDetail_pengajuan_cuti_id());
        dto.setPengajuanId(detailCuti.getPengajuanCuti().getPengajuan_id());
        dto.setTanggalCuti(detailCuti.getTanggal());
        dto.setJenisCutiId(detailCuti.getJenisCuti().getJenis_cuti_id());
        dto.setNoTelp(detailCuti.getPengajuanCuti().getNoTelp());
        dto.setLamaCuti(detailCuti.getPengajuanCuti().getLama_cuti());
        dto.setStatusId(detailCuti.getPengajuanCuti().getStatusCuti().getCuti_id());
        dto.setKeterangan(detailCuti.getPengajuanCuti().getKeterangan());
        dto.setAlamat(detailCuti.getPengajuanCuti().getAlamat());
        dto.setEmployeeId(detailCuti.getPengajuanCuti().getEmployee().getEmploye_id());
        dto.setPenggantiId(detailCuti.getPengajuanCuti().getPengganti_id());
        dto.setNamaEmploye(detailCuti.getPengajuanCuti().getEmployee().getNama());
        dto.setNamaStatus(detailCuti.getPengajuanCuti().getStatusCuti().getStatus_cuti());
        dto.setEmail(detailCuti.getPengajuanCuti().getEmployee().getEmail());
        dto.setHrdId(detailCuti.getPengajuanCuti().getHrd().getEmploye_id());
        return dto;
    }

    @Override
    public List<PengajuanDto> getAll() {
        List<DetailCuti> listAllDetail = (List<DetailCuti>) detailCutiRepo.findAll();
        List<PengajuanDto> listDto = new ArrayList<>();
        for (DetailCuti detailCuti: listAllDetail) {
            if(detailCuti.getPengajuanCuti().getStatusCuti().getCuti_id()!=1 && detailCuti.isDeleted()==true){
                listDto.add(toDto(detailCuti));
                System.out.println("test");
            }
        }
        return listDto;
    }

}
