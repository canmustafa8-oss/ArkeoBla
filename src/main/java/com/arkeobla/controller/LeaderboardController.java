package com.arkeobla.controller;

import com.arkeobla.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaderboardController {

    private final UserRepository userRepository;

    public LeaderboardController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/leaderboard")
    public String leaderboard(Model model) {
        model.addAttribute("topUsers", userRepository.findTop10ByOrderByTotalScoreDesc());
        return "leaderboard";
    }
}
