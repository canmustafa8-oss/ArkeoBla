package com.arkeobla.controller;

import com.arkeobla.service.AiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/ai-chat")
    public String aiChatPage() {
        return "ai-chat";
    }

    @PostMapping("/ai-chat")
    public String askAi(@RequestParam String question, Model model) {
        String answer = aiService.getAnswer(question);
        model.addAttribute("question", question);
        model.addAttribute("answer", answer);
        return "ai-chat";
    }
}
