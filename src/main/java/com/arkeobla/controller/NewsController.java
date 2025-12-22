package com.arkeobla.controller;

import com.arkeobla.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news/read")
    public String readNews(@RequestParam String url, Model model) {
        Map<String, String> article = newsService.scrapeContent(url);
        model.addAttribute("article", article);
        return "news-reader";
    }
}
