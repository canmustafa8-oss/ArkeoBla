package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class TimeMachineController {

    private final List<Era> eras = Arrays.asList(
            new Era("prehistoric", "Tarih Öncesi Çağlar", "MÖ 2.5 Milyon - MÖ 3200",
                    "İnsanlığın doğuşu, avcı-toplayıcı yaşam ve ilk aletlerin icadı.",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Lascaux_II.jpg/600px-Lascaux_II.jpg"),

            new Era("ancient-egypt", "Antik Mısır", "MÖ 3100 - MÖ 30",
                    "Piramitler, firavunlar ve Nil'in bereketiyle yükselen bir medeniyet.",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/All_Gizah_Pyramids.jpg/600px-All_Gizah_Pyramids.jpg"),

            new Era("ancient-greece", "Antik Yunan & Roma", "MÖ 800 - MS 476",
                    "Felsefe, demokrasi, hukuk ve muazzam mimari eserlerin altın çağı.",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/The_Parthenon_in_Athens.jpg/600px-The_Parthenon_in_Athens.jpg"),

            new Era("medieval", "Orta Çağ", "MS 476 - MS 1453",
                    "Şovalyeler, kaleler, feodalizm ve İslam'ın Altın Çağı.",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Krak_des_Chevaliers_landscape.jpg/600px-Krak_des_Chevaliers_landscape.jpg"),

            new Era("early-modern", "Yeni Çağ & Rönesans", "1453 - 1789",
                    "Sanatın yeniden doğuşu, coğrafi keşifler ve bilimsel devrim.",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Da_Vinci_Vitruve_Luc_Viatour.jpg/450px-Da_Vinci_Vitruve_Luc_Viatour.jpg"),

            new Era("industrial", "Sanayi Devrimi", "1760 - 1900",
                    "Buharlı makineler, fabrikalar ve modern dünyanın temellerinin atılması.",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Opening_of_the_Stockton_and_Darlington_Railway.jpg/600px-Opening_of_the_Stockton_and_Darlington_Railway.jpg"));

    @GetMapping("/time-machine")
    public String timeMachine(@RequestParam(required = false) String era, Model model) {
        if (era != null) {
            Optional<Era> selectedEra = eras.stream()
                    .filter(e -> e.id.equals(era))
                    .findFirst();
            if (selectedEra.isPresent()) {
                model.addAttribute("selectedEra", selectedEra.get());
            }
        }
        model.addAttribute("eras", eras);
        return "time-machine";
    }

    public static class Era {
        public String id;
        public String title;
        public String period;
        public String description;
        public String imageUrl;

        public Era(String id, String title, String period, String description, String imageUrl) {
            this.id = id;
            this.title = title;
            this.period = period;
            this.description = description;
            this.imageUrl = imageUrl;
        }
    }
}
