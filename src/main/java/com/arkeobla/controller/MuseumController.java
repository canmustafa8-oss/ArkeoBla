package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MuseumController {

    @GetMapping("/museum")
    public String museum(Model model) {
        List<Artifact> artifacts = new ArrayList<>();

        // Örnek Eserler (Telif Hakkı: Public Domain / CC0)

        // 1. Nefertiti Büstü (Mısır)
        artifacts.add(new Artifact(
                "Nefertiti Büstü",
                "Mısır Kraliçesi Nefertiti'nin ikonik kireçtaşı büstü. MÖ 1345. (Berlin Müzesi Tarama)",
                "https://raw.githubusercontent.com/mrdoob/three.js/dev/examples/models/gltf/Nefertiti/Nefertiti.glb",
                "" // Poster otomatik oluşsun
        ));

        // 2. Rosetta Taşı (Mısır/Helenistik) - YENİ
        artifacts.add(new Artifact(
                "Rosetta Taşı",
                "Hiyerogliflerin çözülmesini sağlayan efsanevi taş. (British Museum - 3D Tarama)",
                "https://raw.githubusercontent.com/alexbfree/rosetta-stone/main/rosetta_stone.glb",
                ""));

        // 3. Apollo 11 Elbisesi (Geleceğin Arkeolojisi)
        artifacts.add(new Artifact(
                "Apollo 11 Elbisesi",
                "İnsanlığın Ay'a ayak bastığı o tarihi anda giyilen uzay kostümü. Geleceğin arkeolojisi.",
                "https://modelviewer.dev/shared-assets/models/Astronaut.glb",
                "https://modelviewer.dev/shared-assets/models/Astronaut.png"));
        model.addAttribute("artifacts", artifacts);
        return "museum";
    }

    // Basit bir DTO (Data Transfer Object)
    public static class Artifact {
        public String name;
        public String description;
        public String modelUrl;
        public String posterUrl;

        public Artifact(String name, String description, String modelUrl, String posterUrl) {
            this.name = name;
            this.description = description;
            this.modelUrl = modelUrl;
            this.posterUrl = posterUrl;
        }
    }
}
