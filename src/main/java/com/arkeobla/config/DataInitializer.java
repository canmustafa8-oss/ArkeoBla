package com.arkeobla.config;

import com.arkeobla.model.Role;
import com.arkeobla.model.User;
import com.arkeobla.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            // Eğer veritabanında hiç kullanıcı yoksa Admin oluştur
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin123"); // Değiştirmeyi unutmayın!
                admin.setRole(Role.ADMIN);
                admin.setBadges("KURUCU,YÖNETİCİ");
                userRepository.save(admin);
                System.out.println(">>> Varsayılan Admin kullanıcısı oluşturuldu: admin / admin123");
            }
        };
    }
}
