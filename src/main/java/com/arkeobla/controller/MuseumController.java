package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MuseumController {

        private final ObjectMapper objectMapper;
        private List<Artifact> loadedArtifacts = new ArrayList<>();

        public MuseumController(ObjectMapper objectMapper) {
                this.objectMapper = objectMapper;
        }

        @PostConstruct
        public void loadArtifactsData() {
                try {
                        ClassPathResource resource = new ClassPathResource("artifacts.json");
                        if (!resource.exists()) {
                                System.err.println("Museum artifacts.json not found!");
                                return;
                        }
                        loadedArtifacts = objectMapper.readValue(resource.getInputStream(),
                                        new TypeReference<List<Artifact>>() {
                                        });
                        System.out.println("✅ Museum artifacts loaded: " + loadedArtifacts.size());
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @GetMapping("/museum")
        public String museum(Model model) {
                model.addAttribute("artifacts", loadedArtifacts);
                return "museum";
        }

        // Basit bir DTO (Data Transfer Object)
        public static class Artifact {
                public int id;
                public String name;
                public String description;
                public String modelUrl;
                public String posterUrl;

                // Jackson için boş constructor
                public Artifact() {
                }

                public Artifact(String name, String description, String modelUrl, String posterUrl) {
                        this.name = name;
                        this.description = description;
                        this.modelUrl = modelUrl;
                        this.posterUrl = posterUrl;
                }
        }
}
