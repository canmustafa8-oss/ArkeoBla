package com.arkeobla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@arkeobla.com");
        message.setTo(toEmail);
        message.setSubject("ArkeoBla Kayıt Doğrulama");
        message.setText("Merhaba, ArkeoBla'ya hoş geldiniz!\n\n" +
                "Hesabınızı doğrulamak için aşağıdaki bağlantıya tıklayın:\n" +
                "http://localhost:8080/verify?code=" + code + "\n\n" +
                "Doğrulama Kodunuz: " + code);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("E-posta gönderilemedi: " + e.getMessage());
            // Demo ortamında mail sunucusu olmayabilir, log basıp geçiyoruz.
        }
    }
}
