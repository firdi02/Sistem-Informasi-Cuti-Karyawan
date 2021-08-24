package Sistem.Informasi.Cuti.Karyawan.Services.Email;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SendEmail {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String email,String nama, String password,String divisi){
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try{
            helper.setSubject("PT Pasim Utama");
            helper.setFrom("Mr.DanilPub18@gmail.com");
            helper.setTo(email);

            boolean html = true;
            helper.setText("<b>Dear "+nama+"</b>," +
                            "<p>Thank you interest in joining <b>PT Pasim Utama</b></p>" +
                            "<p>We appreciate the time you've taken to apply for the position of <b>"+divisi+"</b></p>"+
                            "<p>After reviewing your profile, we would you like to extend an interview invitation so that<p>" +
                            "<p>we can further discuss on the above opportunity </p>." +
                            "<p>your password <b>"+password+"</b> username "+nama+"</p>"+
                            "<p>We look forward to hearing for you.</p><hr>" +
                            "Yours sincerely" +
                            "<p><b>PT Pasim Utama</b></p>" +
                            "<p><i>Suci Novianti</i><p/>"
                    , html);

            emailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
