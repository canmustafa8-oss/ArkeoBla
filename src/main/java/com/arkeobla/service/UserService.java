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

    public User registerUser(String username, String password, Role role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Bu kullanıcı adı zaten alınmış.");
        }
        User user = new User();
        user.setUsername(username);
        // Gerçek uygulamada şifreler hashlenmelidir. Şimdilik demo için düz metin.
        // user.setPassword(passwordEncoder.encode(password)); 
        // Şimdilik SecurityConfig'de NoOpPasswordEncoder kullanacağız.
        user.setPassword(password);
        user.setRole(role);
        user.setBadges("YENİ_ÜYE");
        return userRepository.save(user);
    }
}
