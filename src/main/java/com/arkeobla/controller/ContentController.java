package com.arkeobla.controller;

import com.arkeobla.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/add-content")
    public String addContentPage() {
        return "add-content";
    }

    @PostMapping("/add-content")
    public String addContent(@RequestParam String title, 
                             @RequestParam String body, 
                             @RequestParam String category,
                             Principal principal) {
        contentService.createContent(title, body, category, principal.getName());
        return "redirect:/";
    }
}
