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
        // Level 1: Zeugma (Kolay 4x4)
        levels.add(new PuzzleLevel(1, "Zeugma Çingene Kızı", "4x4", 4,
                "https://images.unsplash.com/photo-1541432901042-2d8bd64b4a9b?auto=format&fit=crop&w=600&q=80"));

        // Level 2: Göbeklitepe (Orta 6x6)
        levels.add(new PuzzleLevel(2, "Göbeklitepe Sütunları", "6x6", 6,
                "https://images.unsplash.com/photo-1544558635-667480601430?auto=format&fit=crop&w=600&q=80"));

        // Level 3: Truva Atı (Zor 8x8)
        levels.add(new PuzzleLevel(3, "Truva Atı", "8x8", 8,
                "https://images.unsplash.com/photo-1628151015968-3a4429e9efc0?auto=format&fit=crop&w=600&q=80"));

        // Level 4: Mısır Piramitleri (Final 10x10)
        levels.add(new PuzzleLevel(4, "Büyük Giza Piramitleri", "10x10", 10,
                "https://images.unsplash.com/photo-1539650116455-d2b585a2290c?auto=format&fit=crop&w=600&q=80"));
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
