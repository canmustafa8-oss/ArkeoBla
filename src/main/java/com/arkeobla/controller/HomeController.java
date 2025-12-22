package com.arkeobla.controller;

import com.arkeobla.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ContentService contentService;
    private final com.arkeobla.service.NewsService newsService; // Yeni Servis

    public HomeController(ContentService contentService, com.arkeobla.service.NewsService newsService) {
        this.contentService = contentService;
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contents", contentService.getAllContents());
        // Google'dan canlı çekiyoruz:
        model.addAttribute("archaeologyNews", newsService.getGoogleNews("Arkeoloji kazı"));
        model.addAttribute("agendaNews", newsService.getGoogleNews("Türkiye gündem"));
        return "index";
    }
}
