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
        // Level 1: Zeugma (Kolay 3x3)
        levels.add(new PuzzleLevel(1, "Zeugma Çingene Kızı", "3x3 Kolay", 3,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Gaziantep_Zeugma_Museum_Gypsy_Girl_mosaic_close-up_1926.jpg/600px-Gaziantep_Zeugma_Museum_Gypsy_Girl_mosaic_close-up_1926.jpg"));

        // Level 2: Göbeklitepe (Orta 4x4)
        levels.add(new PuzzleLevel(2, "Göbeklitepe Sütunları", "4x4 Orta", 4,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/G%C3%B6bekli_Tepe%2C_Urfa.jpg/600px-G%C3%B6bekli_Tepe%2C_Urfa.jpg"));

        // Level 3: Piramitler (Zor 5x5)
        levels.add(new PuzzleLevel(3, "Giza Piramitleri", "5x5 Zor", 5,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/All_Gizah_Pyramids.jpg/600px-All_Gizah_Pyramids.jpg"));

        // Level 4: Kolezyum (Uzman 6x6)
        levels.add(new PuzzleLevel(4, "Roma Kolezyumu", "6x6 Uzman", 6,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Colosseo_2020.jpg/600px-Colosseo_2020.jpg"));

        // Level 5: Parthenon (Final 7x7)
        levels.add(new PuzzleLevel(5, "Parthenon Tapınağı", "7x7 Usta", 7,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/The_Parthenon_in_Athens.jpg/600px-The_Parthenon_in_Athens.jpg"));
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
