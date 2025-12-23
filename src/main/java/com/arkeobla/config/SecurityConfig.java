package com.arkeobla.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                                                // Herkese Açık Sayfalar (Giriş gerekmez)
                                                .requestMatchers("/", "/home", "/register", "/login", "/verify/**")
                                                .permitAll()
                                                .requestMatchers("/games/**", "/museum/**", "/timeline/**",
                                                                "/excavation/**", "/puzzle/**", "/quiz/**",
                                                                "/time-machine/**", "/photos/**",
                                                                "/certificate/**", "/map/**", "/api/locations/**",
                                                                "/leaderboard/**", "/news/**", "/profile/**",
                                                                "/blog/**", "/forum/**")
                                                .permitAll()
                                                .requestMatchers("/css/**", "/js/**", "/img/**", "/uploads/**",
                                                                "/manifest.json", "/sw.js", "/sitemap.xml",
                                                                "/robots.txt")
                                                .permitAll()

                                                // Sadece Giriş Yapmış Kullanıcılar (Chat ve Blog)
                                                // Sadece Giriş Yapmış Kullanıcılar (Chat, Forum Yazma ve İçerik Ekleme)
                                                .requestMatchers("/chat/**", "/ai-chat/**",
                                                                "/meeting/**", "/add-content/**", "/forum/new",
                                                                "/forum/reply/**")
                                                .authenticated()

                                                // Admin
                                                .requestMatchers("/admin/**").hasRole("ADMIN")

                                                .anyRequest().permitAll())
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/", true)
                                                .permitAll())
                                .logout((logout) -> logout.permitAll());

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
