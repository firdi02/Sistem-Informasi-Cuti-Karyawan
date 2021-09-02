package Sistem.Informasi.Cuti.Karyawan.Services.Interface;

import Sistem.Informasi.Cuti.Karyawan.DTO.PengajuanDto;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;

import java.util.List;

public interface PengajuanCutiService {
    public void Simpan(PengajuanDto pengajuanDto, Employee employee);
    public PengajuanDto getPengajuanId(Integer id, Employee employee);
    public List<PengajuanDto> getAll();
}
