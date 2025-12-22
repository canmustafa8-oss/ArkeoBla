package com.arkeobla.controller;

import com.arkeobla.model.User;
import com.arkeobla.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        if (currentUser == null) {
            return "redirect:/login";
        }
        User user = userRepository.findByUsername(currentUser.getUsername()).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);

            // Grafik için veri hazırlığı (basitçe sayıları gönderiyoruz)
            int total = user.getCorrectAnswers() + user.getWrongAnswers();
            model.addAttribute("totalAttempts", total);
        }
        return "profile";
    }

    @PostMapping("/profile/upload-photo")
    public String uploadPhoto(@AuthenticationPrincipal UserDetails currentUser,
            @RequestParam("photo") MultipartFile photo) {
        if (currentUser == null || photo.isEmpty()) {
            return "redirect:/profile";
        }

        try {
            User user = userRepository.findByUsername(currentUser.getUsername()).orElse(null);
            if (user != null) {
                // Dosyayı kaydet
                String filename = UUID.randomUUID().toString() + "_" + photo.getOriginalFilename();
                Path uploadDir = Paths.get("src/main/resources/static/uploads");
                Files.createDirectories(uploadDir);
                Path filePath = uploadDir.resolve(filename);
                Files.write(filePath, photo.getBytes());

                // URL'yi kullanıcıya kaydet
                user.setProfilePhotoUrl("/uploads/" + filename);
                userRepository.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/profile";
    }
}
