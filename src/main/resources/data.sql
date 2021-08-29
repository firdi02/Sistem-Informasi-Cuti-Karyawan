INSERT INTO tbl_role (role_id,nama,deleted,created_date,updated_date,created_by,updated_by)VALUES (1,'HRD',1,NOW(),NOW(),'SYSTEM','SYSTEM');
INSERT INTO tbl_role (role_id,nama,deleted,created_date,updated_date,created_by,updated_by) VALUES (2,'KARYAWAN',1,NOW(),NOW(),'SYSTEM','SYSTEM');

INSERT INTO tbl_jenis_cuti (jenis_cuti_id,jenis_cuti,deskripsi,deleted,created_date,updated_date,created_by,updated_by) VALUES (1,'tahunan','Cuti tahunan adalah cuti yang disediakan oleh perusahaan diluar cuti bersama pemerintah',1,NOW(),NOW(),'SYSTEM','SYSTEM');
INSERT INTO tbl_jenis_cuti (jenis_cuti_id,jenis_cuti,deskripsi,deleted,created_date,updated_date,created_by,updated_by) VALUES (2,'cuti lintas tahun','Cuti lintas tahun adalah sisa cuti tahunan yang tersisa dari tahun sebelumnya dan hanya bisa di pakai pada 6 bulan pertama saja',1,NOW(),NOW(),'SYSTEM','SYSTEM');

INSERT INTO tbl_status_cuti (status_cuti_id,status_cuti,deskripsi,deleted,created_date,updated_date,created_by,updated_by) VALUES (1,'Draft','Draft adalah status cuti dimana pengajuan cuti sudah dibuat tapi belum di ajukan',1,NOW(),NOW(),'SYSTEM','SYSTEM');
INSERT INTO tbl_status_cuti (status_cuti_id,status_cuti,deskripsi,deleted,created_date,updated_date,created_by,updated_by) VALUES (2,'Open','Open adalah status cuti dimana pengajuan cuti sudah diajukan kepada hrd',1,NOW(),NOW(),'SYSTEM','SYSTEM');
INSERT INTO tbl_status_cuti (status_cuti_id,status_cuti,deskripsi,deleted,created_date,updated_date,created_by,updated_by) VALUES (3,'Approved','Approved adalah status cuti dimana pengajuan cuti sudah disetujui oleh hrd',1,NOW(),NOW(),'SYSTEM','SYSTEM');
INSERT INTO tbl_status_cuti (status_cuti_id,status_cuti,deskripsi,deleted,created_date,updated_date,created_by,updated_by) VALUES (4,'Rejected','Rejected adalah status cuti dimana pengajuan cuti ditolak oleh hrd',1,NOW(),NOW(),'SYSTEM','SYSTEM');
INSERT INTO tbl_status_cuti (status_cuti_id,status_cuti,deskripsi,deleted,created_date,updated_date,created_by,updated_by) VALUES (5,'Cancelled','Cancelled adalah status cuti dimana pengajuan cuti yang sebelumnya open namun di gagalkan oleh karyawan itu sendiri',1,NOW(),NOW(),'SYSTEM','SYSTEM');

-- HRD UTAMA
INSERT INTO tbl_employee (employee_id,nip,nama_lengkap,divisi,email,password,username,role_id,deleted,created_date,updated_date,created_by,updated_by) VALUES (1,'01234567890','Bejo Nugroho','HRD UTAMA','bejo@gmail.com','$2a$10$Mm.oeaKbktUECCDIuOrgCe8nbOIID6LFKxKethMZgoBS2kWUvrIzi','bejo',1,1,NOW(),NOW(),'SYSTEM','SYSTEM');
