package com.arkeobla.service;

import com.arkeobla.model.Role;
import com.arkeobla.model.User;
import com.arkeobla.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Bu kullanıcı adı zaten alınmış.");
        }

        // E-posta kontrolü (Basit)
        // Gerçekte repo.findByEmail de olmalı ama şimdilik username=email varsayımı
        // yapmıyoruz.

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.USER); // Varsayılan USER
        user.setBadges("YENİ_ÜYE");

        // Doğrulama Kodu
        String code = java.util.UUID.randomUUID().toString();
        user.setVerificationCode(code);
        user.setEnabled(false); // Başlangıçta pasif

        return userRepository.save(user);
    }

    public boolean verifyUser(String code) {
        // Not: Burada repository.findByVerificationCode lazım.
        // Hız için tüm userları gezip bulalım (Performanssız ama demo için ok)
        // Doğrusu UserRepository'e metod eklemektir.

        for (User user : userRepository.findAll()) {
            if (code.equals(user.getVerificationCode())) {
                user.setEnabled(true);
                user.setVerificationCode(null);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
