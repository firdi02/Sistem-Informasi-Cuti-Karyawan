package Sistem.Informasi.Cuti.Karyawan.Services.Email;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;
import Sistem.Informasi.Cuti.Karyawan.Model.Repo.EmployeeRepo;
import Sistem.Informasi.Cuti.Karyawan.Services.UserAktif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmail {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    UserAktif userAktif;

    public void EmailRecrutmen(String email, String nama, String password, String divisi){
        Employee employee = employeeRepo.getUsername(userAktif.getUser());
        System.out.println(employee.getEmail());
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try{
            helper.setSubject("PT Pasim Utama");
            helper.setFrom(new InternetAddress(employee.getEmail(), employee.getNama()));
            helper.setTo(email);
            String link = "http://localhost:8080/login";
            boolean html = true;
            helper.setText("<b>Dear "+nama+"</b>," +
                            "<p>Thank you interest in joining <b>PT Pasim Utama</b></p>" +
                            "<p>We appreciate the time you've taken to apply for the position of <b>"+divisi+"</b></p>"+
                            "<p>After reviewing your profile, we would you like to extend an interview invitation so that<p>" +
                            "<p>we can further discuss on the above opportunity </p>." +
                            "<p>We look forward to hearing for you.</p>" +
                            "<p>your password <b>"+password+"</b> username <b>"+nama+"</b></p>"+
                            "<p>you can <b><a href="+link+">login</a></b> now</p><hr>"+
                            "Yours sincerely" +
                            "<p><b>PT Pasim Utama</b></p>" +
                            "<p><i>Suci Novianti</i><p/>"
                    , html);

            emailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void Send(){

    }

}
