package com.arkeobla.controller;

import com.arkeobla.repository.ContentRepository;
import com.arkeobla.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')") // Sadece Admin girebilir
public class AdminController {

    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    private final com.arkeobla.service.StatsService statsService;
    private final com.arkeobla.repository.AiLogRepository aiLogRepository;

    public AdminController(UserRepository userRepository, ContentRepository contentRepository,
            com.arkeobla.service.StatsService statsService,
            com.arkeobla.repository.AiLogRepository aiLogRepository) {
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
        this.statsService = statsService;
        this.aiLogRepository = aiLogRepository;
    }

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("contents", contentRepository.findAll());
        model.addAttribute("totalVisits", statsService.getHomePageVisits());
        model.addAttribute("aiLogs", aiLogRepository.findTop50ByOrderByTimestampDesc());
        return "admin";
    }

    @PostMapping("/delete-content/{id}")
    public String deleteContent(@PathVariable Long id) {
        contentRepository.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }
}
