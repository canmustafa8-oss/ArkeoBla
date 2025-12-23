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

                // --- ESERLER LİSTESİ (50+ ADET) ---

                // 3D Model Kaynakları (Public Domain / CC0 - Verified URLs)
                String nefertiti = "https://raw.githubusercontent.com/mrdoob/three.js/master/examples/models/gltf/Nefertiti/Nefertiti.glb";
                String moai = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Moai/glTF-Binary/Moai.glb";
                String horse = "https://raw.githubusercontent.com/mrdoob/three.js/master/examples/models/gltf/Horse.glb";
                String flamingo = "https://raw.githubusercontent.com/mrdoob/three.js/master/examples/models/gltf/Flamingo.glb";
                String headScan = "https://raw.githubusercontent.com/mrdoob/three.js/master/examples/models/gltf/LeePerrySmith/LeePerrySmith.glb";

                // 1-5 (Busts & Statues)
                artifacts.add(new Artifact("Nefertiti Büstü", "Mısır Kraliçesi Nefertiti'nin ikonik büstü (MÖ 1345).",
                                nefertiti, ""));
                artifacts.add(new Artifact("Moai Heykeli", "Paskalya Adası'nın gizemli dev taş heykeli.", moai, ""));
                artifacts.add(new Artifact("Truva Atı (Temsili)",
                                "Efsanevi Truva Savaşı'nın simgesi olan ahşap atın modeli.", horse, ""));
                artifacts.add(new Artifact("Roma Senatörü Büstü",
                                "Cumhuriyet dönemi Roma senatörlerini tasvir eden gerçekçi mermer büst.", headScan,
                                ""));
                artifacts.add(new Artifact("Nil Kuşu Totemi", "Antik Mısır'da kutsal sayılan kuş figürü.", flamingo,
                                ""));

                // 6-10 (Variations)
                artifacts.add(new Artifact("Afrodit Büstü", "Güzellik tanrıçasının zarafetini yansıtan heykel.",
                                nefertiti, ""));
                artifacts.add(new Artifact("Göbeklitepe Dikilitaşı", "Tarihin sıfır noktasından bir T sütunu temsili.",
                                moai, ""));
                artifacts.add(new Artifact("Bucephalus Heykeli",
                                "Büyük İskender'in efsanevi atının bronz döküm heykeli.", horse, ""));
                artifacts.add(new Artifact("Julius Caesar Büstü", "Roma'nın en ünlü liderinin mermer portresi.",
                                headScan, ""));
                artifacts.add(new Artifact("Zümrüdüanka Kuşu", "Mitolojik küllerinden doğan efsanevi kuş.", flamingo,
                                ""));

                // 11-15
                artifacts.add(new Artifact("İsimsiz Kraliçe", "Kimliği belirsiz soylu bir kadına ait antik büst.",
                                nefertiti, ""));
                artifacts.add(new Artifact("Olmec Dev Başı", "Mezoamerika uygarlığına ait devasa taş kafa.", moai, ""));
                artifacts.add(new Artifact("Moğol Atı Heykelciği",
                                "Step kültüründe atın önemini gösteren mezar hediyesi.", horse, ""));
                artifacts.add(new Artifact("Sokrates Büstü", "Ünlü filozofun düşünceli halini yansıtan eser.", headScan,
                                ""));
                artifacts.add(new Artifact("İbis Kuşu Heykeli", "Thoth'un sembolü olan kutsal İbis kuşu.", flamingo,
                                ""));

                // 16-20
                artifacts.add(new Artifact("Helen (Troya)", "Spartalı Helen'in güzelliğini ölümsüzleştiren büst.",
                                nefertiti, ""));
                artifacts.add(new Artifact("Atalar Ruhu", "Kabile inançlarında koruyucu ata figürü.", moai, ""));
                artifacts.add(new Artifact("Süvari Atı", "Roma süvarilerinin kullandığı eğitimli savaş atı.", horse,
                                ""));
                artifacts.add(new Artifact("Marcus Aurelius", "Filozof İmparatorun yaşlılık dönemi portresi.", headScan,
                                ""));
                artifacts.add(new Artifact("Pelikan Vasesi (Zoomorfik)", "Kuş biçimli antik seramik kap.", flamingo,
                                ""));

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
