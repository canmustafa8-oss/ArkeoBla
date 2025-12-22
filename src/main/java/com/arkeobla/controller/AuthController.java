package com.arkeobla.controller;

import com.arkeobla.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final com.arkeobla.service.EmailService emailService;
    private final UserService userService;

    public AuthController(UserService userService, com.arkeobla.service.EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            Model model) {
        // Şifre Politikası: En az 6 karakter, 1 Büyük Harf
        if (password.length() < 6 || !password.matches(".*[A-Z].*")) {
            model.addAttribute("error", "Şifre en az 6 karakter olmalı ve 1 büyük harf içermelidir.");
            return "register";
        }

        try {
            com.arkeobla.model.User user = userService.registerUser(username, email, password, firstName, lastName);
            emailService.sendVerificationEmail(user.getEmail(), user.getVerificationCode());

            // Başarılı ama doğrulama lazım
            model.addAttribute("message", "Kayıt başarılı! Lütfen e-posta adresinize (" + email
                    + ") gelen bağlantıya tıklayarak hesabınızı doğrulayın.");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam String code, Model model) {
        boolean verified = userService.verifyUser(code);
        if (verified) {
            model.addAttribute("message", "Hesabınız başarıyla doğrulandı! Giriş yapabilirsiniz.");
        } else {
            model.addAttribute("error", "Geçersiz doğrulama kodu.");
        }
        return "login";
    }
}
