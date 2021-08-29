package Sistem.Informasi.Cuti.Karyawan.Services;

import Sistem.Informasi.Cuti.Karyawan.DTO.PengajuanDto;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.DetailCuti;
import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;

public interface PengajuanCutiService {
    public void Simpan(PengajuanDto pengajuanDto, Employee employee);
    public PengajuanDto getPengajuanId(Integer id, Employee employee);
}
