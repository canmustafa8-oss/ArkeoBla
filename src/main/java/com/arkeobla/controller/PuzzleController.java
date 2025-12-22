package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PuzzleController {

    private final List<PuzzleLevel> levels = new ArrayList<>();

    public PuzzleController() {
        // Yerel statik dosyalar kullanıyoruz - CORS sorunu olmaz
        levels.add(new PuzzleLevel(1, "Antik Mozaik", "3x3 Kolay", 3,
                "/img/puzzle1.jpg"));

        levels.add(new PuzzleLevel(2, "Tarihi Yapı", "4x4 Orta", 4,
                "/img/puzzle2.jpg"));

        levels.add(new PuzzleLevel(3, "Piramitler", "5x5 Zor", 5,
                "/img/puzzle3.jpg"));

        levels.add(new PuzzleLevel(4, "Antik Arena", "6x6 Uzman", 6,
                "/img/puzzle4.jpg"));

        levels.add(new PuzzleLevel(5, "Tapınak", "7x7 Usta", 7,
                "/img/puzzle5.jpg"));
    }

    @GetMapping("/puzzle")
    public String showPuzzle(@RequestParam(defaultValue = "1") int level, Model model) {
        // Geçersiz level kontrolü
        if (level < 1 || level > levels.size()) {
            level = 1;
        }

        PuzzleLevel currentLevel = levels.get(level - 1);
        boolean isFinal = (level == levels.size());

        model.addAttribute("level", currentLevel);
        model.addAttribute("nextLevel", isFinal ? null : level + 1);
        model.addAttribute("isFinal", isFinal);
        model.addAttribute("totalLevels", levels.size());

        return "puzzle";
    }

    public static class PuzzleLevel {
        public int id;
        public String title;
        public String difficulty; // "3x3"
        public int gridSize; // 3
        public String imageUrl;

        public PuzzleLevel(int id, String title, String difficulty, int gridSize, String imageUrl) {
            this.id = id;
            this.title = title;
            this.difficulty = difficulty;
            this.gridSize = gridSize;
            this.imageUrl = imageUrl;
        }
    }
}
