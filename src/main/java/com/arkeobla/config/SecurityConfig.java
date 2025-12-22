package com.arkeobla.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable()) // Login hatalarını önlemek için CSRF kapatıldı
                                .authorizeHttpRequests((requests) -> requests
                                                // Herkese Açık Sayfalar
                                                .requestMatchers("/", "/home", "/register", "/login", "/verify/**")
                                                .permitAll()
                                                .requestMatchers("/games/**", "/museum/**", "/timeline/**",
                                                                "/excavation/**", "/puzzle/**", "/quiz/**",
                                                                "/time-machine/**", "/photos/**", "/ai-chat/**",
                                                                "/certificate/**", "/map/**", "/api/locations/**",
                                                                "/leaderboard/**", "/chat/**")
                                                .permitAll()
                                                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()

                                                // Sadece Giriş Yapmış Kullanıcılar
                                                .requestMatchers("/meeting/**", "/blog/**", "/profile/**",
                                                                "/add-content/**")
                                                .authenticated()

                                                // Admin
                                                .requestMatchers("/admin/**").hasRole("ADMIN")

                                                .anyRequest().authenticated())
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/", true)
                                                .permitAll())
                                .logout((logout) -> logout.permitAll());

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return NoOpPasswordEncoder.getInstance();
        }
}
