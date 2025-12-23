package com.arkeobla.service;

import com.arkeobla.model.Role;
import com.arkeobla.model.User;
import com.arkeobla.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String email, String password, String firstName, String lastName,
            java.time.LocalDate birthDate) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Bu kullanıcı adı zaten alınmış.");
        }

        // E-posta kontrolü (Basit)
        // Gerçekte repo.findByEmail de olmalı ama şimdilik username=email varsayımı
        // yapmıyoruz.

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // BCrypt ile şifrele
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);
        user.setRole(Role.USER); // Varsayılan USER
        user.setBadges("YENİ_ÜYE");

        // Doğrulama Kodu
        String code = java.util.UUID.randomUUID().toString();
        user.setVerificationCode(code);
        user.setEnabled(false); // Başlangıçta pasif

        return userRepository.save(user);
    }

    public boolean verifyUser(String code) {
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

    public void addScore(User user, int points) {
        user.setTotalScore(user.getTotalScore() + points);
        user.setMonthlyScore(user.getMonthlyScore() + points);
        user.setWeeklyScore(user.getWeeklyScore() + points);
        userRepository.save(user);
    }
}
