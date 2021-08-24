package Sistem.Informasi.Cuti.Karyawan.Utils;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RandomPassword {
    public String randomPassword(){
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }
}
