package com.arkeobla.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TimelineController {

    private List<TimelineEvent> timelineEvents = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            timelineEvents = mapper.readValue(
                    new ClassPathResource("timeline_data.json").getInputStream(),
                    new TypeReference<List<TimelineEvent>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
            timelineEvents.add(new TimelineEvent("MÖ ???", "Hata", "Veri yüklenemedi.", "Özet yok", "Detay yok", ""));
        }
    }

    @GetMapping("/timeline")
    public String timeline(Model model) {
        model.addAttribute("events", timelineEvents);
        return "timeline";
    }

    public static class TimelineEvent {
        public String year;
        public String era;
        public String title;
        public String summary; // Kısa, dikkat çekici özet
        public String details; // Detaylı, makale tadında içerik
        public String imageUrl;

        public TimelineEvent() {
        }

        public TimelineEvent(String year, String era, String title, String summary, String details, String imageUrl) {
            this.year = year;
            this.era = era;
            this.title = title;
            this.summary = summary;
            this.details = details;
            this.imageUrl = imageUrl;
        }
    }
}
