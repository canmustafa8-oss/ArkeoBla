package com.arkeobla.controller;

import com.arkeobla.service.ContentService;
import com.arkeobla.service.NewsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.data.domain.PageRequest;

@Controller
public class HomeController {

    private final ContentService contentService;
    private final NewsService newsService;
    private final com.arkeobla.service.StatsService statsService;

    public HomeController(ContentService contentService, NewsService newsService,
            com.arkeobla.service.StatsService statsService) {
        this.contentService = contentService;
        this.newsService = newsService;
        this.statsService = statsService;
    }

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            java.util.List<ArtifactData> loadedArtifacts = mapper.readValue(
                    new ClassPathResource("daily_artifacts.json").getInputStream(),
                    new TypeReference<java.util.List<ArtifactData>>() {
                    });
            this.artifactPool = loadedArtifacts;
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback (Hata olursa boş kalmasın)
            this.artifactPool = java.util.Collections.singletonList(
                    new ArtifactData("Amenemhat III Büstü", "Orta Krallık, Mısır",
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Amenemhat_III.jpg/180px-Amenemhat_III.jpg"));
        }
    }

    @GetMapping("/")
    public String home(Model model) {
        // Ziyaret Sayacı
        statsService.incrementHomePageVisits();

        // Anasayfada son 10 içeriği gösteriyoruz
        model.addAttribute("contents", contentService.getAllContents(PageRequest.of(0, 10)).getContent());
        // Google'dan canlı çekiyoruz:
        model.addAttribute("archaeologyNews", newsService.getGoogleNews("Arkeoloji kazı"));
        model.addAttribute("agendaNews", newsService.getGoogleNews("Türkiye gündem"));

        // GÜNÜN ESERİ (Dinamik Seçim - JSON)
        model.addAttribute("dailyArtifact", getArtifactOfTheDay());

        return "index";
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy";
    }

    @GetMapping("/terms")
    public String terms() {
        return "terms";
    }

    private java.util.List<ArtifactData> artifactPool = new java.util.ArrayList<>();

    private ArtifactData getArtifactOfTheDay() {
        if (artifactPool.isEmpty())
            return new ArtifactData("Yükleniyor...", "", "");

        int dayOfYear = java.time.LocalDate.now().getDayOfYear();
        int index = dayOfYear % artifactPool.size();
        return artifactPool.get(index);
    }

    public static class ArtifactData {
        public String title;
        public String subtitle;
        public String imageUrl;

        public ArtifactData() {
        } // JSON için boş constructor

        public ArtifactData(String title, String subtitle, String imageUrl) {
            this.title = title;
            this.subtitle = subtitle;
            this.imageUrl = imageUrl;
        }
    }
}
