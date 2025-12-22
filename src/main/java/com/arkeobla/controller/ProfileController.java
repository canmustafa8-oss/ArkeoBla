package com.arkeobla.controller;

import com.arkeobla.model.User;
import com.arkeobla.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
