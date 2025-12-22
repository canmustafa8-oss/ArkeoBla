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
        // Admin kullanıcısını hariç tut (Mcquelss)
        var allTopUsers = userRepository.findTop10ByOrderByTotalScoreDesc();
        var filteredUsers = allTopUsers.stream()
                .filter(u -> !u.getUsername().equals("Mcquelss"))
                .limit(10)
                .toList();
        model.addAttribute("topUsers", filteredUsers);

        // Haftalık liderlik tablosu
        var weeklyTopUsers = userRepository.findTop10ByOrderByWeeklyScoreDesc();
        var filteredWeeklyUsers = weeklyTopUsers.stream()
                .filter(u -> !u.getUsername().equals("Mcquelss"))
                .limit(10)
                .toList();
        model.addAttribute("weeklyTopUsers", filteredWeeklyUsers);

        return "leaderboard";
    }
}
