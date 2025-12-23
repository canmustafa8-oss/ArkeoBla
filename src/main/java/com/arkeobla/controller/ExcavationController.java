package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ExcavationController {

        private final Map<String, List<ExcavationLayer>> regionLayers = new HashMap<>();

        public ExcavationController() {
                initializeLayers();
        }

        private void initializeLayers() {
                // TURKEY LAYERS
                List<ExcavationLayer> turkeyLayers = new ArrayList<>();
                turkeyLayers.add(new ExcavationLayer(1, "Modern Yüzey",
                                "Çöp, gazoz kapakları ve yakın tarih kalıntıları.", "modern",
                                Arrays.asList(new Artifact("Eski Para", "Cumhuriyet dönemi madeni para.",
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/5_Kuru%C5%9F_1973_reverse.jpg/220px-5_Kuru%C5%9F_1973_reverse.jpg"))));

                turkeyLayers.add(new ExcavationLayer(2, "Osmanlı Dönemi", "Seramik parçaları, lüleler ve sikkeler.",
                                "ottoman",
                                Arrays.asList(new Artifact("İznik Çinisi", "16. yy mavi-beyaz çini parçası.",
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/Iznik_Dish.jpg/300px-Iznik_Dish.jpg"))));

                turkeyLayers.add(new ExcavationLayer(3, "Roma/Bizans Dönemi", "Mermer parçaları, cam eserler.", "roman",
                                Arrays.asList(new Artifact("Roma Sikkesi", "İmparator Hadrianus dönemi bronz sikke.",
                                                "https://upload.wikimedia.org/wikipedia/commons/e/ea/Sestertius_Hadrian.jpg"))));

                turkeyLayers.add(new ExcavationLayer(4, "Neolitik Çağ",
                                "Çakmaktaşı aletler ve figürinler. En değerli katman.", "neolithic",
                                Arrays.asList(new Artifact("Ana Tanrıça Figürini",
                                                "Çatalhöyük pişmiş toprak heykelcik.",
                                                "https://upload.wikimedia.org/wikipedia/commons/a/a2/Catalh%C3%BCy%C3%BCk_restauriertes_Haus_innen.jpg"),
                                                new Artifact("Göbeklitepe Kabartması",
                                                                "T-Sütunu üzerindeki hayvan tasviri.",
                                                                "https://upload.wikimedia.org/wikipedia/commons/1/10/G%C3%B6bekli_Tepe%2C_Urfa.jpg"))));

                regionLayers.put("turkey", turkeyLayers);
        }

        @GetMapping("/excavation")
        public String selectRegion() {
                return "excavation-select";
        }

        @GetMapping("/excavation/play")
        public String playExcavation(@RequestParam String region, Model model) {
                List<ExcavationLayer> layers = regionLayers.getOrDefault(region, regionLayers.get("turkey"));

                // Flatten artifacts for now just for simple logic, but ideally we pass layers
                // For the new UI, we will pass layers directly.

                model.addAttribute("layers", layers);
                model.addAttribute("totalEnergy", 100);
                model.addAttribute("budget", 5000); // 5000 TL budget
                model.addAttribute("regionName", getRegionDisplayName(region));
                model.addAttribute("regionCode", region);

                return "excavation-game";
        }

        private String getRegionDisplayName(String code) {
                switch (code) {
                        case "turkey":
                                return "Türkiye (Anadolu)";
                        case "mesopotamia":
                                return "Mezopotamya";
                        case "egypt":
                                return "Mısır";
                        default:
                                return "Bilinmeyen Bölge";
                }
        }

        // Inner Classes
        public static class ExcavationLayer {
                public int level;
                public String name;
                public String description;
                public String type; // modern, ottoman, roman, neolithic
                public List<Artifact> potentialFinds;

                public ExcavationLayer(int level, String name, String description, String type,
                                List<Artifact> potentialFinds) {
                        this.level = level;
                        this.name = name;
                        this.description = description;
                        this.type = type;
                        this.potentialFinds = potentialFinds;
                }
        }

        public static class Artifact {
                public String name;
                public String description;
                public String imageUrl;

                public Artifact(String name, String description, String imageUrl) {
                        this.name = name;
                        this.description = description;
                        this.imageUrl = imageUrl;
                }
        }
}
