package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ExcavationController {

        private final Map<String, List<Artifact>> regionArtifacts = new HashMap<>();

        public ExcavationController() {
                // 1. TÜRKİYE (Anadolu Medeniyetleri + İstanbul)
                List<Artifact> turkey = new ArrayList<>(Arrays.asList(
                                new Artifact("Çingene Kızı", "Zeugma/Gaziantep - MÖ 2. yy",
                                                "https://images.unsplash.com/photo-1541432901042-2d8bd64b4a9b?auto=format&fit=crop&w=800&q=80"), // Mozaik/Antik
                                                                                                                                                 // vibe
                                new Artifact("Hitit Güneş Kursu", "Alacahöyük/Çorum - Tunç Çağı",
                                                "https://images.unsplash.com/photo-1521587760476-6c12a4b040da?auto=format&fit=crop&w=800&q=80"), // Antik
                                                                                                                                                 // metalik
                                new Artifact("Troya Hazineleri", "Çanakkale - Priam'ın Hazinesi",
                                                "https://images.unsplash.com/photo-1628151015968-3a4429e9efc0?auto=format&fit=crop&w=800&q=80"), // Altın
                                                                                                                                                 // mücevher/hazine
                                new Artifact("Artemis Tapınağı", "Efes/İzmir - Antik Dünyanın 7 Harikası",
                                                "https://images.unsplash.com/photo-1558273760-4447387cc04a?auto=format&fit=crop&w=800&q=80"), // Efes
                                new Artifact("Göbeklitepe Sütunu", "Şanlıurfa - Tarihin Sıfır Noktası",
                                                "https://images.unsplash.com/photo-1544558635-667480601430?auto=format&fit=crop&w=800&q=80"), // Göbeklitepe
                                                                                                                                              // taşı
                                new Artifact("Medusa Başı", "Yerebatan Sarnıcı - Roma Dönemi",
                                                "https://images.unsplash.com/photo-1628359352924-f7b7cb553315?auto=format&fit=crop&w=800&q=80"), // Yerebatan
                                new Artifact("Çemberlitaş", "Forum Constantine - MS 330",
                                                "https://images.unsplash.com/photo-1614264639391-447551068a04?auto=format&fit=crop&w=800&q=80"), // Dikilitaş
                                                                                                                                                 // benzeri
                                new Artifact("Dikilitaş (Theodosius)", "Hipodrom - Antik Mısır'dan Getirildi",
                                                "https://images.unsplash.com/photo-1523531294919-4bcd7c65e216?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Valens Su Kemeri", "Bozdoğan Kemeri - MS 368",
                                                "https://images.unsplash.com/photo-1587315152865-c49c71923ce7?auto=format&fit=crop&w=800&q=80")));
                regionArtifacts.put("turkey", turkey);

                // 3. MISIR
                List<Artifact> egypt = Arrays.asList(
                                new Artifact("Tutankamon'un Maskesi", "Krallar Vadisi - MÖ 1323",
                                                "https://images.unsplash.com/photo-1560942485-b2a11cc13456?auto=format&fit=crop&w=800&q=80"), // Altın
                                                                                                                                              // Firavun
                                new Artifact("Nefertiti Büstü", "Amarna Dönemi",
                                                "https://images.unsplash.com/photo-1569420083980-60b69106263b?auto=format&fit=crop&w=800&q=80"), // Antik
                                                                                                                                                 // Büst
                                new Artifact("Rosetta Taşı", "Hiyerogliflerin Çözümü",
                                                "https://images.unsplash.com/photo-1540959733364-d31148ea941f?auto=format&fit=crop&w=800&q=80"), // Yazıtlı
                                                                                                                                                 // Taş
                                new Artifact("Büyük Giza Sfenksi", "Giza Platosu",
                                                "https://images.unsplash.com/photo-1539650116455-d2b585a2290c?auto=format&fit=crop&w=800&q=80")); // Sfenks
                regionArtifacts.put("egypt", egypt);

                // 4. MEZOPOTAMYA (Yeni) - Irak
                List<Artifact> mesopotamia = Arrays.asList(
                                new Artifact("İştar Kapısı", "Babil - Çini Aslan",
                                                "https://images.unsplash.com/photo-1561565552-320e838b006c?auto=format&fit=crop&w=800&q=80"), // Aslan/Çini
                                new Artifact("Hammurabi Kanunları", "Diorit Stel - Babil",
                                                "https://images.unsplash.com/photo-1596720212002-61ee879da83c?auto=format&fit=crop&w=800&q=80"), // Yazıt
                                new Artifact("Ur Standardı", "Sümer - MÖ 2600",
                                                "https://images.unsplash.com/photo-1542273917363-3b1817f69a2d?auto=format&fit=crop&w=800&q=80"), // Mozaik/Eski
                                new Artifact("Lamassu Heykeli", "Asur Koruyucu Boğa",
                                                "https://images.unsplash.com/photo-1601007421876-0610aa46d5fa?auto=format&fit=crop&w=800&q=80")); // Kanatlı
                                                                                                                                                  // Boğa
                regionArtifacts.put("mesopotamia", mesopotamia);

                // 5. İTALYA (Yeni) - Roma
                List<Artifact> italy = Arrays.asList(
                                new Artifact("Kolezyum", "Roma İmparatorluğu - Amfitiyatro",
                                                "https://images.unsplash.com/photo-1552832230-c0197dd311b5?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Augustus Heykeli", "Prima Porta - Vatikan",
                                                "https://images.unsplash.com/photo-1549248479-7dd2934444bc?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Pompeii Freskleri", "Vezüv Yanardağı Patlaması",
                                                "https://images.unsplash.com/photo-1605806616949-1e87b487bc2a?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Kapitolin Kurdu", "Romulus ve Remus - Roma",
                                                "https://images.unsplash.com/photo-1555626040-571f54452171?auto=format&fit=crop&w=800&q=80"));
                regionArtifacts.put("italy", italy);

                // 6. YUNANİSTAN
                List<Artifact> greece = Arrays.asList(
                                new Artifact("Parthenon Tapınağı", "Atina Akropolisi",
                                                "https://images.unsplash.com/photo-1555993539-1732b0258235?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Milo Venüsü", "Afrodit Heykeli",
                                                "https://images.unsplash.com/photo-1549487912-7067d5ce8501?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Disk Atan Atlet", "Myron'un Heykeli",
                                                "https://images.unsplash.com/photo-1563804809639-65139589d701?auto=format&fit=crop&w=800&q=80"));
                regionArtifacts.put("greece", greece);

                // 7. ÇİN (Yeni) - Uzak Doğu
                List<Artifact> china = Arrays.asList(
                                new Artifact("Terracotta Ordusu", "Savaşçı Heykelleri - Xian",
                                                "https://images.unsplash.com/photo-1601736768848-f62ce8b33538?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Çin Seddi", "Ming Hanedanı Sınır Duvarı",
                                                "https://images.unsplash.com/photo-1508804185872-d7badad00f7d?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Jada Lahana", "Qing Hanedanı - Yeşim Taşı",
                                                "https://images.unsplash.com/photo-1608371945634-3119c02a4433?auto=format&fit=crop&w=800&q=80"));
                regionArtifacts.put("china", china);

                // 8. HİNDİSTAN (Yeni)
                List<Artifact> india = Arrays.asList(
                                new Artifact("Tac Mahal", "Agra - Babür İmparatorluğu",
                                                "https://images.unsplash.com/photo-1564507592333-c60657eea523?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Dans Eden Kız", "Mohenjo-daro - MÖ 2500",
                                                "https://images.unsplash.com/photo-1588600742127-047b386400be?auto=format&fit=crop&w=800&q=80"), // Bronz
                                                                                                                                                 // heykel
                                new Artifact("Sanchi Stupası", "Budist Anıtı - MÖ 3. yy",
                                                "https://images.unsplash.com/photo-1610486872583-9b8705307b27?auto=format&fit=crop&w=800&q=80"));
                regionArtifacts.put("india", india);

                // 9. ÜRDÜN (Yeni) - Petra
                List<Artifact> jordan = Arrays.asList(
                                new Artifact("El-Hazne (Hazine)", "Petra Antik Kenti - Nebatiler",
                                                "https://images.unsplash.com/photo-1579606864757-07447814b76a?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Ölü Deniz Parşömenleri", "Kumran Yazıtları",
                                                "https://images.unsplash.com/photo-1590325419958-3d5236f06915?auto=format&fit=crop&w=800&q=80"), // Parşömen
                                new Artifact("Mesa Steli", "Moab Taşı - MÖ 840",
                                                "https://images.unsplash.com/photo-1554907984-15263bfd63bd?auto=format&fit=crop&w=800&q=80"));
                regionArtifacts.put("jordan", jordan);

                // 10. AMERİKA
                List<Artifact> americas = Arrays.asList(
                                new Artifact("Aztek Güneş Taşı", "Piedra del Sol - Meksika",
                                                "https://images.unsplash.com/photo-1569235078735-a6bf728d8b4e?auto=format&fit=crop&w=800&q=80"), // Aztek
                                                                                                                                                 // taşı
                                new Artifact("Machu Picchu", "İnka Antik Kenti - Peru",
                                                "https://images.unsplash.com/photo-1587595431973-160d0d94add1?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Olmec Dev Başı", "La Venta - Meksika",
                                                "https://images.unsplash.com/photo-1574349120677-1755716183e8?auto=format&fit=crop&w=800&q=80")); // Olmec
                                                                                                                                                  // head
                regionArtifacts.put("americas", americas);

                // 11. İNGİLTERE
                List<Artifact> uk = Arrays.asList(
                                new Artifact("Stonehenge", "Neolitik Dönem - Wiltshire",
                                                "https://images.unsplash.com/photo-1599839575945-a9e5af0c3fa5?auto=format&fit=crop&w=800&q=80"),
                                new Artifact("Sutton Hoo Miğferi", "Anglo-Sakson Hazinesi",
                                                "https://images.unsplash.com/photo-1627931327170-0785d0d88582?auto=format&fit=crop&w=800&q=80"), // Miğfer
                                                                                                                                                 // benzeri
                                new Artifact("Roma Hamamları", "Bath Şehri - Roma Britanyası",
                                                "https://images.unsplash.com/photo-1621689726207-6ba713ed000b?auto=format&fit=crop&w=800&q=80"));
                regionArtifacts.put("uk", uk);
        }

        @GetMapping("/excavation")
        public String selectRegion() {
                return "excavation-select"; // Bölge seçim ekranı
        }

        @GetMapping("/excavation/play")
        public String playExcavation(@RequestParam String region, Model model) {
                List<Artifact> artifacts = regionArtifacts.getOrDefault(region, regionArtifacts.get("turkey"));

                // Shuffle artifacts for randomness each time
                Collections.shuffle(artifacts);

                model.addAttribute("artifacts", artifacts);
                model.addAttribute("regionName", getRegionDisplayName(region));
                model.addAttribute("regionCode", region);

                return "excavation-game";
        }

        private String getRegionDisplayName(String code) {
                switch (code) {
                        case "turkey":
                                return "Türkiye (Anadolu)";

                        case "mesopotamia":
                                return "Mezopotamya (Irak/Babil)";
                        case "egypt":
                                return "Mısır (Nil Vadisi)";
                        case "italy":
                                return "İtalya (Antik Roma)";
                        case "greece":
                                return "Yunanistan (Antik Hellas)";
                        case "china":
                                return "Çin (Hanedanlıklar)";
                        case "india":
                                return "Hindistan (İndus Vadisi)";
                        case "jordan":
                                return "Ürdün (Petra/Nebati)";
                        case "americas":
                                return "Amerika (Maya/Aztek)";
                        case "uk":
                                return "İngiltere (Kelt/Roma)";
                        default:
                                return "Bilinmeyen Bölge";
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
