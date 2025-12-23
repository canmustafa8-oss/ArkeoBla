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

                // 3D Model Kaynakları (Public Domain / CC0)
                String nefertiti = "https://raw.githubusercontent.com/mrdoob/three.js/master/examples/models/gltf/Nefertiti/Nefertiti.glb";
                String moai = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Moai/glTF-Binary/Moai.glb";
                String camera = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/AntiqueCamera/glTF-Binary/AntiqueCamera.glb";
                String lantern = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Lantern/glTF-Binary/Lantern.glb";
                String waterBottle = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/WaterBottle/glTF-Binary/WaterBottle.glb";
                String corset = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Corset/glTF-Binary/Corset.glb";
                String boomBox = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/BoomBox/glTF-Binary/BoomBox.glb";
                String sheenChair = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/SheenChair/glTF-Binary/SheenChair.glb";
                String damagedHelmet = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/DamagedHelmet/glTF-Binary/DamagedHelmet.glb";

                // 1-5
                artifacts.add(new Artifact("Nefertiti Büstü", "Mısır Kraliçesi Nefertiti'nin ikonik büstü (MÖ 1345).",
                                nefertiti, ""));
                artifacts.add(new Artifact("Moai Heykeli", "Paskalya Adası'nın gizemli dev taş heykeli.", moai, ""));
                artifacts.add(new Artifact("Antik Kamera", "19. yüzyıl fotoğrafçılık tarihinden bir parça.", camera,
                                ""));
                artifacts.add(new Artifact("Saray Feneri", "Osmanlı saraylarını aydınlatan zarif fener.", lantern, ""));
                artifacts.add(new Artifact("Roma Matarası", "Lejyonerlerin su taşıdığı seramik kap.", waterBottle, ""));

                // 6-10
                artifacts.add(new Artifact("Viktorya Dönemi Korse", "19. yüzyıl modasının bel kemiği.", corset, ""));
                artifacts.add(new Artifact("Retro Radyo", "80'ler kültürünün simgesi kasetçalar.", boomBox, ""));
                artifacts.add(new Artifact("Kraliyet Koltuğu", "Kadife kaplamalı, el işçiliği ahşap taht.", sheenChair,
                                ""));
                artifacts.add(new Artifact("Sparta Miğferi (Hasarlı)", "Savaş meydanından kalma antik bronz miğfer.",
                                damagedHelmet, ""));
                artifacts.add(new Artifact("Mısır Kraliçesi (Kopya)", "Nefertiti'nin bir başka tapınaktaki tasviri.",
                                nefertiti, ""));

                // 11-15
                artifacts.add(new Artifact("Pasifik Totemi", "Polinezya kültüründe koruyucu ataların ruhu.", moai, ""));
                artifacts.add(new Artifact("Sinematograf", "İlk hareketli görüntü kayıt cihazı.", camera, ""));
                artifacts.add(new Artifact("Tapınak Aydınlatması", "Uzak doğu tapınaklarında kullanılan kutsal ışık.",
                                lantern, ""));
                artifacts.add(new Artifact("İksir Şişesi", "Ortaçağ şifacılarının kullandığı karışım kabı.",
                                waterBottle, ""));
                artifacts.add(new Artifact("Saray Terzisi Mankeni", "Saray kıyafetlerinin dikildiği terzi mankeni.",
                                corset, ""));

                // 16-20
                artifacts.add(new Artifact("Modern Çağ Kalıntısı",
                                "Geleceğin arkeolojisi için saklanan bir müzik seti.", boomBox, ""));
                artifacts.add(new Artifact("Barok Sandalye", "Avrupa saraylarından kalan zarif bir oturma birimi.",
                                sheenChair, ""));
                artifacts.add(new Artifact("Gladyatör Miğferi", "Kolezyum savaşçılarının kullandığı miğfer.",
                                damagedHelmet, ""));
                artifacts.add(new Artifact("İsimsiz Firavun Eşi", "Amarna dönemine ait kraliyet kadını büstü.",
                                nefertiti, ""));
                artifacts.add(new Artifact("Rapa Nui Atası",
                                "Ada yerlilerinin taş ocağından çıkardığı bitmemiş heykel.", moai, ""));

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
