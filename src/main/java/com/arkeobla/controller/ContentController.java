package com.arkeobla.controller;

import com.arkeobla.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @GetMapping("/blog")
    public String blogPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String era,
            Model model) {
        int pageSize = 9; // Her sayfada 9 içerik
        Pageable pageable = PageRequest.of(page, pageSize);

        if (era != null && !era.isEmpty()) {
            model.addAttribute("contents", contentService.getContentsByEra(era, pageable));
            model.addAttribute("currentEra", era);
        } else {
            model.addAttribute("contents", contentService.getAllContents(pageable));
        }

        model.addAttribute("currentPage", page);
        return "blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable Long id, Model model) {
        // Service'e henüz getById eklemedik, repository'den veya service'e ekleyip
        // çekelim
        // Kolaylık olsun diye service'e ekleyelim
        model.addAttribute("content", contentService.getContentById(id));
        return "blog-detail";
    }

    @PostMapping("/add-content")
    public String addContent(@RequestParam String title,
            @RequestParam String body,
            @RequestParam String category,
            @RequestParam String summary,
            @RequestParam String imageUrl,
            Principal principal) {
        contentService.createContent(title, body, category, summary, imageUrl, principal.getName());
        return "redirect:/";
    }
}
