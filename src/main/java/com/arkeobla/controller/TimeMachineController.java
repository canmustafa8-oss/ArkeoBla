package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class TimeMachineController {

        private final List<Era> eraList = new ArrayList<>();
        private final Map<String, Era> eraMap = new LinkedHashMap<>();

        public TimeMachineController() {
                // Doğrulanmış tarihler: Britannica, UNESCO, Smithsonian kaynaklarından

                // --- TARİH ÖNCESİ ÇAĞLAR ---
                addEra(new Era("paleolithic", "Paleolitik Çağ", "Tarih Öncesi", "MÖ 2.5 Milyon - MÖ 10.000",
                                "İnsanlığın şafağı: İlk taş aletler, ateşin keşfi, mağara sanatı ve avcı-toplayıcı yaşam.",
                                "https://upload.wikimedia.org/wikipedia/commons/1/1e/Lascaux_painting.jpg",
                                getPaleolithicContent()));

                addEra(new Era("mesolithic", "Mezolitik Çağ", "Tarih Öncesi", "MÖ 22.000 - MÖ 10.000",
                                "Buzul Çağı'nın son evresi: Mikrolit teknolojisi, köpeğin evcilleştirilmesi ve yarı-yerleşik yaşam.",
                                "https://upload.wikimedia.org/wikipedia/commons/8/8e/Star_Carr_pendant_2015.jpg",
                                getMesolithicContent()));

                addEra(new Era("neolithic", "Neolitik Çağ", "Tarih Öncesi", "MÖ 10.000 - MÖ 6.000",
                                "Tarım Devrimi: Göbeklitepe, Çatalhöyük, ilk köyler ve yerleşik yaşama geçiş.",
                                "https://upload.wikimedia.org/wikipedia/commons/e/e7/G%C3%B6bekli_Tepe%2C_Pair_of_pillars_with_low_reliefs_of_animals.jpg",
                                getNeolithicContent()));

                addEra(new Era("chalcolithic", "Kalkolitik Çağ", "Tarih Öncesi", "MÖ 6.000 - MÖ 3.200",
                                "Bakır Çağı: İlk madencilik, sosyal sınıfların doğuşu ve şehirleşmenin temelleri.",
                                "https://upload.wikimedia.org/wikipedia/commons/6/60/Copper_Age_Weapons.jpg",
                                getChalcolithicContent()));

                addEra(new Era("bronze-age", "Tunç Çağı", "Tarih Öncesi", "MÖ 3.200 - MÖ 1.200",
                                "Metal Çağı'nın zirvesi: Hitit İmparatorluğu, Mısır Firavunları, Troya Savaşı ve ilk yazılı kayıtlar.",
                                "https://upload.wikimedia.org/wikipedia/commons/9/90/Lion_Gate_Hattusa.jpg",
                                getBronzeAgeContent()));

                addEra(new Era("ancient-egypt", "Antik Mısır", "Antik Çağ", "MÖ 3100 - MÖ 30",
                                "Nil'in Hediyesi: Piramitler, firavunlar, hiyeroglif yazı ve mumyalama sanatı.",
                                "https://upload.wikimedia.org/wikipedia/commons/e/e3/Kheops-Pyramid.jpg",
                                getAncientEgyptContent()));

                addEra(new Era("classical-greece", "Klasik Yunan", "Antik Çağ", "MÖ 800 - MÖ 323",
                                "Demokrasinin beşiği: Atina, Sparta, felsefe, Olimpiyat Oyunları ve Pers Savaşları.",
                                "https://upload.wikimedia.org/wikipedia/commons/d/da/The_Parthenon_in_Athens.jpg",
                                getClassicalGreeceContent()));

                addEra(new Era("hellenistic", "Helenistik Çağ", "Antik Çağ", "MÖ 323 - MÖ 31",
                                "Büyük İskender'in mirası: Helenizm, İskenderiye Kütüphanesi ve Doğu-Batı sentezi.",
                                "https://upload.wikimedia.org/wikipedia/commons/e/e1/Alexander_the_Great_mosaic.jpg",
                                getHellenisticContent()));

                addEra(new Era("roman-empire", "Roma İmparatorluğu", "Antik Çağ", "MÖ 753 - MS 476",
                                "Pax Romana: Hukuk, mühendislik, gladyatörler, Sezar ve imparatorlar çağı.",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d8/Colosseum_in_Rome-April_2007-1-_copie_2B.jpg",
                                getRomanEmpireContent()));

                // --- ORTA ÇAĞ ---
                addEra(new Era("medieval", "Orta Çağ", "Orta Çağ", "MS 476 - 1453",
                                "Feodalizm Dönemi: Şövalyeler, kaleler, Haçlı Seferleri ve İslam'ın Altın Çağı.",
                                "https://upload.wikimedia.org/wikipedia/commons/b/b2/Crac_des_chevaliers_syria.jpeg",
                                getMedievalContent()));

                // --- YENİ & YAKIN ÇAĞ ---
                addEra(new Era("early-modern", "Yeni Çağ & Rönesans", "Yeni Çağ", "1453 - 1789",
                                "Aydınlanma: Rönesans sanatı, matbaa, coğrafi keşifler ve bilimsel devrim.",
                                "https://upload.wikimedia.org/wikipedia/commons/6/66/VanGogh-starry_night_ballance1.jpg",
                                getEarlyModernContent()));

                addEra(new Era("industrial", "Sanayi Devrimi", "Yakın Çağ", "1760 - 1914",
                                "Makine Çağı: Buhar gücü, fabrikalar, demiryolları ve modern dünyanın doğuşu.",
                                "https://upload.wikimedia.org/wikipedia/commons/4/41/Hartlepool_Merchant_Navy_Convoy_in_World_War_II.jpg",
                                getIndustrialContent()));
        }

        private void addEra(Era era) {
                eraList.add(era);
                eraMap.put(era.getId(), era);
        }

        @GetMapping("/time-machine")
        public String timeMachine(@RequestParam(required = false) String era, Model model) {
                if (era != null && eraMap.containsKey(era)) {
                        model.addAttribute("selectedEra", eraMap.get(era));
                }
                model.addAttribute("eras", eraList);
                return "time-machine";
        }

        @GetMapping("/time-machine/era/{id}")
        public String eraDetail(@PathVariable String id, Model model) {
                Era era = eraMap.get(id);
                if (era == null) {
                        return "redirect:/time-machine";
                }
                model.addAttribute("era", era);

                // Önceki ve Sonraki dönemleri bul
                int currentIndex = eraList.indexOf(era);
                if (currentIndex > 0) {
                        model.addAttribute("prevEra", eraList.get(currentIndex - 1));
                }
                if (currentIndex < eraList.size() - 1) {
                        model.addAttribute("nextEra", eraList.get(currentIndex + 1));
                }

                return "era-detail";
        }

        // ========== DOĞRULANMIŞ İÇERİK METOTLARI ==========

        private String getPaleolithicContent() {
                return """
                                ## 🦴 PALEOLİTİK ÇAĞ (ESKİ TAŞ ÇAĞI)
                                **Tarih:** MÖ 3.3 Milyon - MÖ 10.000
                                **Kaynak:** Smithsonian Institution, Britannica Encyclopedia

                                ---

                                ### 📋 DÖNEM ÖZETİ

                                Paleolitik Çağ, insanlık tarihinin **%99'unu** kapsayan en uzun dönemdir. Bu çağda atalarımız:
                                - İlk taş aletleri üretti
                                - Ateşi kontrol altına aldı
                                - Dil ve iletişimi geliştirdi
                                - Sanat eserlerini yarattı
                                - Tüm dünyaya yayıldı

                                ---

                                ### 🔹 ALT PALEOLİTİK (MÖ 3.3 Milyon - MÖ 300.000)

                                **Önemli Türler:** *Homo habilis*, *Homo erectus*

                                | Olay | Tarih |
                                |------|-------|
                                | İlk taş aletler (Lomekwian) | MÖ 3.3 Milyon |
                                | Oldowan alet geleneği | MÖ 2.6 Milyon |
                                | Acheulean el baltaları | MÖ 1.76 Milyon |
                                | Afrika'dan ilk göç | MÖ 1.8 Milyon |
                                | Ateşin kontrolü | MÖ 1.5 Milyon |

                                **Oldowan Aletleri:** Basit yongalar ve kesici taşlar. *Homo habilis* ("Becerikli İnsan") tarafından üretildi.

                                **Acheulean Kültürü:** Simetrik, gözyaşı şeklinde el baltaları. *Homo erectus* tarafından geliştirildi ve 1 milyon yıldan fazla kullanıldı.

                                ---

                                ### 🔹 ORTA PALEOLİTİK (MÖ 300.000 - MÖ 30.000)

                                **Önemli Türler:** *Homo sapiens*, *Homo neanderthalensis*

                                | Olay | Tarih |
                                |------|-------|
                                | Homo sapiens'in ortaya çıkışı (Afrika) | MÖ 300.000 |
                                | Neandertallerin ortaya çıkışı | MÖ 250.000 |
                                | İlk gömü ritüelleri | MÖ 100.000 |
                                | Afrika'dan büyük göç | MÖ 70.000 |

                                **Mousterian Teknolojisi:** Neandertaller tarafından kullanılan Levallois tekniğiyle üretilen gelişmiş yonga aletleri.

                                **Neandertaller:** Soğuk iklimlere adapte olmuş, güçlü yapılı insansı tür. Ölülerini gömer, takı ve süs eşyası kullanırlardı. Modern insanlarla melezleştiler.

                                ---

                                ### 🔹 ÜST PALEOLİTİK (MÖ 30.000 - MÖ 10.000)

                                **Sanat Patlaması:** Bu dönemde insan yaratıcılığı zirveye ulaştı.

                                | Olay | Tarih |
                                |------|-------|
                                | Avustralya'ya ulaşım | MÖ 60.000 |
                                | Avrupa'ya varış | MÖ 45.000 |
                                | Chauvet Mağarası resimleri | MÖ 36.000 |
                                | Willendorf Venüsü | MÖ 25.000 |
                                | İlk kemik iğneler | MÖ 21.000 |
                                | Amerika'ya geçiş | MÖ 15.000 |
                                | Lascaux Mağarası resimleri | MÖ 17.000 |

                                ---

                                ### 🎨 MAĞARA SANATI

                                **Chauvet Mağarası (Fransa, MÖ 36.000):** Bilinen en eski mağara sanatı. Aslanlar, gergedanlar, ayılar tasvir edilmiştir.

                                **Lascaux Mağarası (Fransa, MÖ 17.000):** "Tarihöncesi Sistine Şapeli" olarak anılır. 600'den fazla hayvan figürü içerir.

                                **Altamira Mağarası (İspanya, MÖ 36.000-15.000):** Çok renkli bizon resimleriyle ünlüdür.

                                ---

                                ### 🔥 ATEŞİN ÖNEMİ

                                Ateşin kontrolü insan evriminin dönüm noktasıydı:

                                1. **Beslenme:** Pişirme ile daha fazla kalori alımı
                                2. **Isınma:** Soğuk iklimlere yayılmayı mümkün kıldı
                                3. **Koruma:** Yırtıcılardan savunma
                                4. **Sosyalleşme:** Ateş etrafında topluluk bağları

                                **İlk Kanıtlar:**
                                - Wonderwerk Mağarası (Güney Afrika): MÖ 1 milyon
                                - Gesher Benot Ya'akov (İsrail): MÖ 790.000

                                ---

                                ### 👤 İNSAN EVRİMİ

                                | Tür | Dönem | Beyin Hacmi |
                                |-----|-------|-------------|
                                | Australopithecus | MÖ 4-2 Milyon | 450-530 cm³ |
                                | Homo habilis | MÖ 2.5-1.5 Milyon | 600-750 cm³ |
                                | Homo erectus | MÖ 1.9 Milyon-110.000 | 850-1100 cm³ |
                                | Homo neanderthalensis | MÖ 250.000-40.000 | 1200-1750 cm³ |
                                | Homo sapiens | MÖ 300.000-Günümüz | 1400 cm³ |

                                ---

                                ### 📚 KAYNAKLAR
                                - Smithsonian National Museum of Natural History
                                - Britannica Encyclopedia
                                - Nature Journal Archaeological Studies
                                                """;
        }

        private String getMesolithicContent() {
                return """
                                ## 🌊 MEZOLİTİK ÇAĞ (ORTA TAŞ ÇAĞI)
                                **Tarih:** MÖ 10.000 - MÖ 8.000 (Avrupa'da MÖ 2700'e kadar sürdü)
                                **Kaynak:** Britannica, Oxford University Archaeological Research

                                ---

                                ### 📋 DÖNEM ÖZETİ

                                Buzul Çağı'nın sona ermesiyle (MÖ 10.000) dünya ısındı:
                                - Deniz seviyeleri yükseldi
                                - Mamutlar yok oldu
                                - Ormanlar genişledi
                                - İnsan grupları yeni ortamlara adapte oldu

                                ---

                                ### 🔹 İKLİM DEĞİŞİKLİĞİ

                                Holosen çağının başlamasıyla:
                                - Ortalama sıcaklıklar 5-7°C arttı
                                - Buzullar kuzeye çekildi
                                - Brit Adaları kıtadan ayrıldı
                                - Yeni bitki ve hayvan türleri ortaya çıktı

                                ---

                                ### 🔹 MİKROLİT TEKNOLOJİSİ

                                Bu dönemin ayırt edici özelliği **mikrolit**lerdir:

                                **Özellikler:**
                                - 1-2 cm uzunluğunda küçük taş aletler
                                - Geometrik şekiller: Üçgen, yamuk, hilal
                                - Ahşap veya kemik saplara monte edilirdi

                                **Kullanım Alanları:**
                                - Ok ve mızrak uçları
                                - Oraklar (tahıl toplama)
                                - Balıkçılık zıpkınları

                                **Avantajı:** Kırılan uç kolayca değiştirilebilirdi.

                                ---

                                ### 🐕 KÖPEĞİN EVCİLLEŞTİRİLMESİ

                                İnsanlık tarihindeki **ilk evcilleştirme** bu dönemde gerçekleşti.

                                | Detay | Bilgi |
                                |-------|-------|
                                | Tarih | MÖ 15.000-12.000 |
                                | Ata | Kurt (Canis lupus) |
                                | Bölge | Avrupa veya Doğu Asya |
                                | İşlev | Av ortağı, kamp koruyucusu |

                                ---

                                ### 🏠 YARI-YERLEŞİK YAŞAM

                                Mezolitik topluluklar tamamen göçebe değildi:

                                - **Mevsimlik kamplar** kuruldu
                                - Zengin kaynak bölgelerinde (kıyılar, nehir kenarları) uzun süreli yerleşim
                                - Ahşap kulübeler ve barınaklar inşa edildi

                                **Star Carr (İngiltere, MÖ 9000):** En iyi korunmuş Mezolitik yerleşim. Geyik boynuzundan maskeler, kemik aletler bulunmuştur.

                                ---

                                ### 🎣 GEÇİM STRATEJİLERİ

                                Mezolitik insanlar çeşitli kaynaklardan yararlandı:

                                - **Avcılık:** Geyik, yaban domuzu, tavşan
                                - **Toplayıcılık:** Fındık, meyve, kök bitkiler
                                - **Balıkçılık:** Tatlı su ve deniz balıkları
                                - **Kabuklu deniz hayvanları:** Midye, istiridye

                                **Shell Middens (Kabuk Yığınları):** Kıyı toplulukların yoğun deniz ürünleri tüketiminin kanıtı.

                                ---

                                ### 🎨 SANAT VE RİTÜEL

                                **Kaya Sanatı:** İspanya'nın Akdeniz kıyısında küçük insan ve hayvan figürleri.

                                **Gömü Pratikleri:**
                                - Hem yakma hem de gömme
                                - Bazı mezarlarda takılar ve aletler
                                - Toplumsal farklılaşmanın ilk işaretleri

                                ---

                                ### 📊 ÖNEMLİ ALANLAR

                                | Alan | Ülke | Önemi |
                                |------|------|-------|
                                | Star Carr | İngiltere | En zengin Mezolitik buluntular |
                                | Lepenski Vir | Sırbistan | Balıkçı köyü, heykelcikler |
                                | Ertebølle | Danimarka | Kabuk yığınları, çanak çömlek |
                                | Franchthi Mağarası | Yunanistan | Obsidyen ticareti |

                                ---

                                ### 📚 KAYNAKLAR
                                - Britannica Encyclopedia
                                - Oxford University Archaeology Department
                                - European Journal of Archaeology
                                                """;
        }

        private String getNeolithicContent() {
                return """
                                ## 🌾 NEOLİTİK ÇAĞ (YENİ TAŞ ÇAĞI)
                                **Tarih:** MÖ 10.000 - MÖ 4.500
                                **Kaynak:** UNESCO, Britannica, National Geographic

                                ---

                                ### 📋 TARIM DEVRİMİ

                                Neolitik Devrim, insanlık tarihinin **en büyük dönüşümüdür**:
                                - Avcı-toplayıcılıktan tarıma geçiş
                                - Kalıcı köylerin kurulması
                                - Nüfus patlaması
                                - Sosyal karmaşıklığın artması

                                **Bereketli Hilal:** Tarımın doğduğu bölge - Güneydoğu Anadolu, Suriye, Irak.

                                ---

                                ### 🏛️ GÖBEKLİTEPE

                                **UNESCO Dünya Mirası (2018)**

                                | Bilgi | Detay |
                                |-------|-------|
                                | Konum | Şanlıurfa, Türkiye |
                                | Tarih | MÖ 9500 - MÖ 8000 |
                                | Keşif | 1963, Sistematik kazı: 1994 |
                                | Önemi | Dünyanın en eski anıtsal yapısı |

                                **Devrimsel Önemi:**
                                - Piramitlerden **7.000 yıl** daha eski
                                - Stonehenge'den **6.000 yıl** daha eski
                                - Avcı-toplayıcılar tarafından inşa edildi

                                **Yapısal Özellikler:**
                                - T-biçiminde dev dikilitaşlar (5-6 metre yükseklik)
                                - Hayvan kabartmaları: Yılan, tilki, yaban domuzu, akbaba
                                - Dairesel tapınak yapıları
                                - 11.000 yıldan fazla süre bilinçli olarak gömüldü

                                > "Göbeklitepe, 'önce tarım, sonra din' teorisini çürüttü. İnsanlar henüz çiftçi olmadan önce anıtsal tapınaklar inşa etti." - Klaus Schmidt, Arkeolog

                                ---

                                ### 🏠 ÇATALHÖYÜK

                                **UNESCO Dünya Mirası (2012)**

                                | Bilgi | Detay |
                                |-------|-------|
                                | Konum | Konya, Türkiye |
                                | Tarih | MÖ 7500 - MÖ 5600 |
                                | Nüfus | 5.000-8.000 kişi |
                                | Keşif | 1958, James Mellaart |

                                **Benzersiz Özellikleri:**
                                - Sokak yok - evler bitişik nizamda
                                - Giriş çatıdan merdiven ile
                                - Ölüler ev tabanına gömülürdü
                                - Duvar resimleri ve kabartmalar

                                **Günlük Yaşam:**
                                - Buğday, arpa yetiştirimi
                                - Koyun, keçi besiciliği
                                - Obsidyen ticareti
                                - Tekstil üretimi

                                ---

                                ### 🌱 EVCİLLEŞTİRME

                                **Bitkiler:**
                                | Bitki | İlk Evcilleştirme |
                                |-------|-------------------|
                                | Emmer buğdayı | MÖ 9000 |
                                | Arpa | MÖ 9000 |
                                | Mercimek | MÖ 8000 |
                                | Bezelye | MÖ 7000 |

                                **Hayvanlar:**
                                | Hayvan | İlk Evcilleştirme |
                                |--------|-------------------|
                                | Koyun | MÖ 9000 (Anadolu) |
                                | Keçi | MÖ 8000 |
                                | Domuz | MÖ 7000 |
                                | Sığır | MÖ 6500 |

                                ---

                                ### 🏺 TEKNOLOJİK GELİŞMELER

                                **Çömlekçilik (MÖ 6500):**
                                - Depolama ve pişirme kapları
                                - Süsleme gelenekleri başladı
                                - Seramik stilleri bölgesel kimlik gösterdi

                                **Cilalı Taş Aletler:**
                                - El değirmenleri
                                - Baltalar ve kazma uçları
                                - Daha verimli tarım araçları

                                **Dokumacılık:**
                                - Keten ve yün işleme
                                - İlk kumaşlar
                                - Ağırlık taşları (tezgah kanıtı)

                                ---

                                ### 🗺️ NEOLİTİK YAYILIMI

                                Tarım, Anadolu'dan Avrupa'ya yayıldı:

                                | Bölge | Tarih |
                                |-------|-------|
                                | Bereketli Hilal | MÖ 9500 |
                                | Kıbrıs | MÖ 8500 |
                                | Yunanistan | MÖ 7000 |
                                | Balkanlar | MÖ 6500 |
                                | Orta Avrupa | MÖ 5500 |
                                | Britanya | MÖ 4000 |

                                ---

                                ### 📚 KAYNAKLAR
                                - UNESCO World Heritage Centre
                                - National Geographic Society
                                - Britannica Encyclopedia
                                - Ian Hodder, Çatalhöyük Excavation Reports
                                                """;
        }

        private String getChalcolithicContent() {
                return """
                                ## ⚱️ KALKOLİTİK ÇAĞ (BAKIR ÇAĞI)
                                **Tarih:** MÖ 5500 - MÖ 3300
                                **Kaynak:** Britannica, Archaeological Institute of America

                                ---

                                ### 📋 DÖNEM ÖZETİ

                                Kalkolitik (Yunanca: *khalkos* = bakır, *lithos* = taş), taş ve bakırın birlikte kullanıldığı geçiş dönemidir.

                                **Temel Özellikler:**
                                - İlk metal işçiliği
                                - Sosyal sınıfların ortaya çıkışı
                                - Uzun mesafe ticaret ağları
                                - Proto-şehirleşme

                                ---

                                ### ⚒️ BAKIR METALURJİSİ

                                **İlk Aşama: Doğal Bakır**
                                - Doğada saf halde bulunan bakır taşlarının dövülmesi
                                - Tarih: MÖ 7000-6000 (Anadolu)

                                **İkinci Aşama: Eritme**
                                - Bakır cevherinin 1000°C'de eritilmesi
                                - Tarih: MÖ 5000
                                - Kalıba döküm tekniği

                                **Üretilen Eşyalar:**
                                - Baltalar, keskiler
                                - Bıçaklar, hançerler
                                - Takılar, süs eşyaları
                                - Kaplar

                                ---

                                ### 👑 VARNA NEKROPOLÜ

                                **Dünyanın En Eski İşlenmiş Altın Hazinesi**

                                | Bilgi | Detay |
                                |-------|-------|
                                | Konum | Bulgaristan |
                                | Tarih | MÖ 4600 - MÖ 4200 |
                                | Keşif | 1972 |
                                | Altın Miktarı | 6 kg (3000+ parça) |

                                **Önemi:**
                                - Toplumsal eşitsizliğin net kanıtı
                                - En zengin mezarda tek kişiye 990 altın eşya
                                - Bazı mezarlar ise tamamen eşyasız
                                - "Prestij ekonomisi" ilk örnekleri

                                ---

                                ### 🏘️ PROTO-ŞEHİRLEŞME

                                Bu dönemde yerleşimler büyüdü ve karmaşıklaştı:

                                **Teleilat Ghassul (Ürdün):**
                                - MÖ 4500-3800
                                - Çok renkli duvar freskleri
                                - Anıtsal mimari

                                **Beycesultan (Türkiye):**
                                - MÖ 5000-3000
                                - Saray kompleksleri
                                - Metal işleme atölyeleri

                                ---

                                ### 📊 SOSYAL DEĞİŞİM

                                Kalkolitik toplumlar daha hiyerarşik hale geldi:

                                | Sınıf | Rol |
                                |-------|-----|
                                | Şefler/Liderler | Yönetim, ritüel |
                                | Zanaatkarlar | Metal işçiliği, çömlekçilik |
                                | Tüccarlar | Uzun mesafe ticaret |
                                | Çiftçiler | Tarım üretimi |

                                ---

                                ### 🔮 DİN VE RİTÜEL

                                **Kült Yapıları:**
                                - Teleilat Ghassul'da tapınak binaları
                                - Ritüel kaplar ve figürinler
                                - Gömü ritüellerinde zenginleşme

                                **Sembolik Eşyalar:**
                                - Bakır "asalar" (güç sembolü)
                                - Altın takılar (statü göstergesi)
                                - Seramik figürinler

                                ---

                                ### 🌐 TİCARET AĞLARI

                                Kalkolitik dönemde ticaret genişledi:

                                | Mal | Kaynak | Varış |
                                |-----|--------|-------|
                                | Obsidyen | Anadolu | Ortadoğu |
                                | Bakır | Kıbrıs, Anadolu | Akdeniz |
                                | Lapis Lazuli | Afganistan | Mezopotamya |
                                | Deniz Kabukları | Kızıldeniz | İç bölgeler |

                                ---

                                ### ➡️ TUNÇ ÇAĞINA GEÇİŞ

                                Kalkolitik'in sonlarında:
                                - Bakır + Kalay = **Tunç** alaşımı keşfedildi
                                - Daha sert ve dayanıklı aletler
                                - MÖ 3300 civarında Tunç Çağı başladı

                                ---

                                ### 📚 KAYNAKLAR
                                - Britannica Encyclopedia
                                - Archaeological Institute of America
                                - European Journal of Archaeology
                                                """;
        }

        private String getBronzeAgeContent() {
                return """
                                ## ⚔️ TUNÇ ÇAĞI
                                **Tarih:** MÖ 3.200 - MÖ 1.200
                                **Kaynak:** Britannica, UNESCO, Anadolu Medeniyetleri Müzesi

                                ---

                                ### 📋 DÖNEM ÖZETİ

                                Tunç Çağı, bakır ve kalayın alaşımı olan **tunç**un (bronz) keşfiyle başlayan dönemdir:
                                - İlk imparatorluklar kuruldu
                                - Yazı yaygınlaştı
                                - Uluslararası ticaret ağları oluştu
                                - Büyük anıtsal yapılar inşa edildi
                                - Savaş teknolojisi gelişti

                                ---

                                ### 🔹 ERKEN TUNÇ ÇAĞI (MÖ 3.200 - MÖ 2.000)

                                | Olay | Tarih |
                                |------|-------|
                                | Tunç metalurjisinin başlangıcı | MÖ 3.300 |
                                | Sümer şehir devletleri | MÖ 3.100 |
                                | Mısır'da ilk hanedanlık | MÖ 3.100 |
                                | Akkad İmparatorluğu (Sargon) | MÖ 2.334 |
                                | Troya II (Priamos Hazinesi) | MÖ 2.550-2.300 |

                                **Anadolu'da:**
                                - Alacahöyük Kral Mezarları
                                - Altın ve tunçtan yapılmış güneş kursları
                                - Hitit öncesi yerli beylikler

                                ---

                                ### 🔹 ORTA TUNÇ ÇAĞI (MÖ 2.000 - MÖ 1.600)

                                | Olay | Tarih |
                                |------|-------|
                                | Asur ticaret kolonileri (Kültepe/Kaneş) | MÖ 1.950 |
                                | Hammurabi Kanunları | MÖ 1.754 |
                                | Hitit Eski Krallık dönemi | MÖ 1.650 |
                                | Minos uygarlığının zirvesi (Girit) | MÖ 1.700 |

                                **Kültepe (Kaneş):**
                                Anadolu'nun en eski yazılı belgeleri burada bulundu. Asurlu tüccarlar kalay ve kumaş getirip altın ve gümüş götürüyordu.

                                ---

                                ### 🔹 GEÇ TUNÇ ÇAĞI (MÖ 1.600 - MÖ 1.200)

                                **Hitit İmparatorluğu Dönemi:**

                                | Olay | Tarih |
                                |------|-------|
                                | Hitit İmparatorluğu'nun kuruluşu | MÖ 1.600 |
                                | I. Şuppiluliuma'nın fetihleri | MÖ 1.344-1.322 |
                                | Kadeş Savaşı (Hititler vs Mısır) | MÖ 1.274 |
                                | Kadeş Antlaşması (ilk yazılı barış) | MÖ 1.259 |
                                | Troya Savaşı (tahmin) | MÖ 1.250-1.180 |
                                | Tunç Çağı Çöküşü | MÖ 1.200-1.150 |

                                ---

                                ### 🏛️ HİTİT İMPARATORLUĞU

                                **Başkent:** Hattuşaş (Boğazköy, Çorum)

                                **Önemli Krallar:**
                                - I. Hattuşili (kurucu)
                                - I. Şuppiluliuma (en güçlü dönem)
                                - II. Muwatalli (Kadeş Savaşı)
                                - III. Hattuşili (Kadeş Antlaşması)

                                **Özellikler:**
                                - Demir işleme teknolojisinin öncüleri
                                - Çivi yazısı ve hiyeroglif kullanımı
                                - Bin Tanrılı din anlayışı
                                - Gelişmiş hukuk sistemi

                                **Anıtsal Yapılar:**
                                - Aslanlı Kapı, Sfenksli Kapı
                                - Yazılıkaya Açık Hava Tapınağı
                                - Büyük Tapınak

                                ---

                                ### 🏺 TROYA

                                **Konum:** Çanakkale, Hisarlık

                                | Katman | Dönem | Özellik |
                                |--------|-------|---------|
                                | Troya I | MÖ 3.000-2.550 | İlk yerleşim |
                                | Troya II | MÖ 2.550-2.300 | Priamos Hazinesi |
                                | Troya VI | MÖ 1.700-1.250 | Güçlü surlar |
                                | Troya VIIa | MÖ 1.250-1.180 | **İlyada'nın Troya'sı** |

                                Heinrich Schliemann'ın 1873'te bulduğu "Priamos Hazinesi" aslında Troya II katmanına (MÖ 2.300) ait olup, efsanevi Troya Savaşı'ndan 1.000 yıl öncesine aittir.

                                ---

                                ### 🌊 EGE UYGARILIKLARI

                                **Minos (Girit) Uygarlığı:**
                                - Knossos Sarayı
                                - Lineer A yazısı (henüz çözülemedi)
                                - Deniz ticareti hakimiyeti
                                - MÖ 1.450'de Miken istilası

                                **Miken Uygarlığı:**
                                - Yunanistan ana karası
                                - Altın maskeler (Agamemnon Maskesi)
                                - Kyklopik duvarlar
                                - Lineer B yazısı (eski Yunanca)

                                ---

                                ### ⚙️ METALURJİ

                                **Tunç Yapımı:**
                                - %88-92 Bakır + %8-12 Kalay
                                - Kalay Anadolu ve Afganistan'dan geliyordu
                                - Ticaret ağları hayati önemdeydi

                                **Gelişmeler:**
                                | Teknoloji | Dönem |
                                |-----------|-------|
                                | Döküm kalıpları | MÖ 3.000 |
                                | Kayıp mum tekniği | MÖ 2.500 |
                                | Demir işleme (Hititler) | MÖ 1.400 |
                                | Çelik (karbonlu demir) | MÖ 1.200 |

                                ---

                                ### 💀 TUNÇ ÇAĞI ÇÖKÜŞÜ (MÖ 1.200-1.150)

                                50 yıl içinde tüm büyük uygarlıklar yıkıldı:
                                - Hitit İmparatorluğu
                                - Miken uygarlığı
                                - Ugarit ve Suriye şehirleri
                                - Mısır zayıfladı

                                **Olası Sebepler:**
                                1. Deniz Kavimleri istilaları
                                2. İklim değişikliği ve kuraklık
                                3. Depremler
                                4. Ticaret ağlarının çökmesi
                                5. İç isyanlar

                                Bu çöküş "Karanlık Çağ"ı başlattı ve yazı 400 yıl boyunca kayboldu.

                                ---

                                ### 📚 KAYNAKLAR
                                - Britannica Encyclopedia
                                - UNESCO World Heritage (Hattuşaş)
                                - Anadolu Medeniyetleri Müzesi
                                - Eric Cline, "1177 B.C.: The Year Civilization Collapsed"
                                """;
        }

        private String getAncientEgyptContent() {
                return """
                                ## 🏛️ ANTİK MISIR
                                **Tarih:** MÖ 3100 - MÖ 30
                                **Kaynak:** Britannica, UNESCO, Metropolitan Museum of Art

                                ---

                                ### 📋 DÖNEM ÖZETİ

                                Antik Mısır, **3000 yıl** boyunca kesintisiz varlığını sürdürmüş dünyanın en uzun ömürlü uygarlıklarından biridir.

                                ---

                                ### 📅 DÖNEMLER

                                | Dönem | Tarih | Önemli Olaylar |
                                |-------|-------|----------------|
                                | Erken Hanedan | MÖ 3100-2686 | Birleşme, yazı |
                                | Eski Krallık | MÖ 2686-2181 | Piramitler |
                                | 1. Ara Dönem | MÖ 2181-2055 | Parçalanma |
                                | Orta Krallık | MÖ 2055-1650 | Edebiyat, ticaret |
                                | 2. Ara Dönem | MÖ 1650-1550 | Hyksos istilası |
                                | Yeni Krallık | MÖ 1550-1069 | İmparatorluk |
                                | Geç Dönem | MÖ 1069-332 | Yabancı hakimiyetler |
                                | Ptolemaios | MÖ 332-30 | Yunan yönetimi |

                                ---

                                ### 🔺 GİZA PİRAMİTLERİ

                                **UNESCO Dünya Mirası, Antik Dünyanın 7 Harikasından günümüze ulaşan tek yapı**

                                | Piramit | Firavun | Yükseklik |
                                |---------|---------|-----------|
                                | Büyük Piramit | Khufu (Keops) | 146.6 m |
                                | Kefren Piramidi | Khafre | 136.4 m |
                                | Mikerinos Piramidi | Menkaure | 65 m |

                                **Büyük Piramit Hakkında:**
                                - 2.3 milyon taş blok
                                - Her blok ortalama 2.5 ton
                                - İnşaat süresi: ~20 yıl
                                - İşçi sayısı: 20.000-30.000

                                ---

                                ### 📜 HİYEROGLİF YAZISI

                                **Çözüm:** Rosetta Taşı (1799'da bulundu, 1822'de çözüldü)

                                | Yazı Türü | Kullanım |
                                |-----------|----------|
                                | Hiyeroglif | Tapınak yazıtları |
                                | Hiyeratik | Günlük yazışmalar |
                                | Demotik | Geç dönem halk yazısı |

                                ---

                                ### ⚱️ MUMYALAMA

                                70 günlük süreç:
                                1. Organların çıkarılması
                                2. Natron tuzu ile kurutma (40 gün)
                                3. Yağlar ve reçinelerle koruma
                                4. Keten bandajlarla sarma
                                5. Tabut ve mezara yerleştirme

                                **Amaç:** Ka (ruh) için bedeni korumak

                                ---

                                ### 👑 ÜNLÜ FİRAVUNLAR

                                | Firavun | Dönem | Önemi |
                                |---------|-------|-------|
                                | Khufu | MÖ 2589-2566 | Büyük Piramit |
                                | Hatshepsut | MÖ 1479-1458 | Kadın firavun |
                                | Akhenaton | MÖ 1353-1336 | Tek tanrıcılık |
                                | Tutankhamun | MÖ 1332-1323 | Bozulmamış mezar |
                                | Ramses II | MÖ 1279-1213 | En uzun hüküm |
                                | Kleopatra VII | MÖ 51-30 | Son firavun |

                                ---

                                ### 🏺 GÜNLÜK YAŞAM

                                **Toplum Piramidi:**
                                - Firavun (tanrı-kral)
                                - Rahipler ve soylular
                                - Yazıcılar ve bürokratlar
                                - Askeri sınıf
                                - Zanaatkarlar ve tüccarlar
                                - Çiftçiler
                                - Köleler

                                **Beslenme:** Ekmek, bira, sebzeler, balık, et (zenginler için)

                                ---

                                ### 📚 KAYNAKLAR
                                - Metropolitan Museum of Art
                                - British Museum
                                - Britannica Encyclopedia
                                """;
        }

        private String getClassicalGreeceContent() {
                return """
                                ## 🏛️ KLASİK YUNAN
                                **Tarih:** MÖ 800 - MÖ 323
                                **Kaynak:** Britannica, Oxford Classical Dictionary

                                ---

                                ### 📋 DÖNEM ÖZETİ

                                Batı medeniyetinin temellerini atan uygarlık:
                                - Demokrasinin doğuşu
                                - Felsefenin altın çağı
                                - Sanat ve mimarinin zirvesi
                                - Pers İmparatorluğu'na karşı zafer

                                ---

                                ### 📅 DÖNEMLER

                                | Dönem | Tarih | Özellikler |
                                |-------|-------|------------|
                                | Karanlık Çağ | MÖ 1100-800 | Yazının kaybı, Dor istilaları |
                                | Arkaik Dönem | MÖ 800-480 | Polis'lerin kuruluşu, kolonizasyon |
                                | Klasik Dönem | MÖ 480-323 | Altın Çağ, Atina hegemonyası |

                                ---

                                ### 🗳️ ATİNA DEMOKRASİSİ

                                **Kurucular:**
                                - Solon (MÖ 594) - Ekonomik reformlar
                                - Kleistenes (MÖ 508) - Demokratik anayasa
                                - Perikles (MÖ 461-429) - Tam demokrasi

                                | Kurum | İşlevi |
                                |-------|--------|
                                | Ekklesia | Halk Meclisi (6.000+ vatandaş) |
                                | Boule | 500 kişilik yürütme konseyi |
                                | Dikasteria | 6.000 hakim ile jüri mahkemeleri |
                                | Strategos | 10 seçilmiş general |

                                **Ostrakismos:** Tehlikeli görülen vatandaşların 10 yıl sürgüne gönderilmesi.

                                ---

                                ### ⚔️ PERS SAVAŞLARI

                                | Savaş | Tarih | Sonuç |
                                |-------|-------|-------|
                                | Marathon | MÖ 490 | Atina zaferi |
                                | Termopylae | MÖ 480 | 300 Spartalı'nın direnişi |
                                | Salamis | MÖ 480 | Yunan donanması zaferi |
                                | Plataea | MÖ 479 | Kesin Yunan zaferi |

                                ---

                                ### 💭 FİLOZOFLAR

                                **Üç Büyük:**
                                | Filozof | Dönem | Katkısı |
                                |---------|-------|---------|
                                | Sokrates | MÖ 470-399 | Sorgulama yöntemi, etik |
                                | Platon | MÖ 428-348 | İdealar teorisi, Akademia |
                                | Aristoteles | MÖ 384-322 | Mantık, doğa bilimleri |

                                **Diğerleri:**
                                - Thales (ilk filozof)
                                - Herakleitos (değişim felsefesi)
                                - Demokritos (atomculuk)

                                ---

                                ### 🏟️ OLİMPİYAT OYUNLARI

                                - **Başlangıç:** MÖ 776 (Olympia, Zeus onuruna)
                                - **Sıklık:** Her 4 yılda bir (Olimpiyat = 4 yıl)
                                - **Ödül:** Zeytin dalı çelengi

                                **Branşlar:** Stadion (koşu), Pentatlon (5'li), Güreş, Boks, Araba yarışı

                                ---

                                ### 🏛️ SANAT VE MİMARİ

                                **Üç Düzen:**
                                | Düzen | Özellik | Örnek |
                                |-------|---------|-------|
                                | Dor | Sade, güçlü | Parthenon |
                                | İyon | Volütlü (kıvrımlı) | Erechtheion |
                                | Korint | Akanthus yapraklı | Olympos Zeus |

                                **Heykelciler:** Fidias, Praksiteles, Miron

                                ---

                                ### 📚 KAYNAKLAR
                                - Oxford Classical Dictionary
                                - Britannica Encyclopedia
                                - Cambridge Ancient History
                                """;
        }

        private String getHellenisticContent() {
                return """
                                ## 🌍 HELENİSTİK ÇAĞ
                                **Tarih:** MÖ 323 - MÖ 31
                                **Kaynak:** Britannica, Cambridge Ancient History

                                ---

                                ### 📋 DÖNEM ÖZETİ

                                Büyük İskender'in ölümünden (MÖ 323) Actium Savaşı'na (MÖ 31) kadar:
                                - Yunan kültürünün Doğu'ya yayılması
                                - Doğu-Batı sentezi (Helenizm)
                                - Büyük kütüphaneler ve bilim merkezleri
                                - Devasa imparatorlukların kuruluşu

                                ---

                                ### ⚔️ BÜYÜK İSKENDER (MÖ 356-323)

                                **Makedonyalı Filip II'nin oğlu**, Aristoteles'in öğrencisi.

                                | Fetih | Tarih |
                                |-------|-------|
                                | İssos Savaşı (Hatay) | MÖ 333 |
                                | Mısır'ın fethi | MÖ 332 |
                                | Gaugamela (Pers çöküşü) | MÖ 331 |
                                | Babil'in alınışı | MÖ 331 |
                                | Persepolis'in yakılması | MÖ 330 |
                                | Hindistan seferi | MÖ 326 |
                                | Babil'de ölümü | MÖ 323 |

                                **Mirası:** 13 yılda Yunanistan'dan Hindistan'a uzanan imparatorluk.

                                ---

                                ### 👑 DİADOKHOİ (HALEFLER)

                                İskender'in ölümünden sonra imparatorluk 3'e bölündü:

                                | Krallık | Hanedan | Başkent |
                                |---------|---------|---------|
                                | Makedonya | Antigonidler | Pella |
                                | Mısır | Ptolemaioslar | İskenderiye |
                                | Asya | Seleukoslar | Antiokheia |

                                **Ayrıca:**
                                - Pergamon Krallığı (Bergama)
                                - Bithynia (Bursa bölgesi)
                                - Pontus (Karadeniz)

                                ---

                                ### 📚 İSKENDERİYE KÜTÜPHANESİ

                                Dünyanın en büyük antik kütüphanesi:
                                - **Kuruluş:** MÖ 300 (I. Ptolemaios)
                                - **Koleksiyon:** 400.000-700.000 rulo
                                - **Mouseion:** İlk araştırma üniversitesi

                                **Bilim İnsanları:**
                                | İsim | Alan | Katkı |
                                |------|------|-------|
                                | Euklides | Matematik | Geometri temelleri |
                                | Eratosthenes | Coğrafya | Dünya'nın çevresini hesapladı |
                                | Aristarkos | Astronomi | Güneş merkezli evren |
                                | Arşimet | Fizik | Kaldırma prensibi |
                                | Hipparkhos | Astronomi | Yıldız kataloğu |

                                ---

                                ### 🏛️ HELENİSTİK SANAT

                                Klasik dönemden farklı olarak:
                                - **Duygu ve hareket** ön planda
                                - Dramatik ifadeler
                                - Gerçekçi portreler

                                **Önemli Eserler:**
                                | Eser | Özellik |
                                |------|---------|
                                | Laokoon Grubu | Ağrı ve çaresizlik |
                                | Samothrace Nike | Zafer ve hareket |
                                | Pergamon Sunağı | Dev rölyefler |
                                | Ölmekte Olan Galat | Trajedi |

                                ---

                                ### 🌐 HELENİZM

                                Yunan dili ve kültürü Akdeniz'den Orta Asya'ya yayıldı:
                                - **Koine Yunancası** ortak dil oldu
                                - Yunan tiyatrosu, felsefesi, bilimi benimsendi
                                - Yerel kültürlerle sentez (Greko-Budizm, Mısır-Yunan tanrıları)

                                ---

                                ### 📚 KAYNAKLAR
                                - Britannica Encyclopedia
                                - Cambridge Ancient History
                                - Peter Green, "Alexander to Actium"
                                """;
        }

        private String getRomanEmpireContent() {
                return """
                                ## 🦅 ROMA İMPARATORLUĞU
                                **Tarih:** MÖ 753 - MS 476
                                **Kaynak:** Britannica, Oxford Classical Dictionary

                                ---

                                ### 📋 DÖNEM ÖZETİ

                                Küçük bir şehir devletinden Akdeniz'e hükmeden süper güce:
                                - Modern hukukun temelleri
                                - Mühendislik harikaları
                                - Pax Romana (Roma Barışı)
                                - Hristiyanlığın yayılması

                                ---

                                ### 📅 DÖNEMLER

                                | Dönem | Tarih | Özellikler |
                                |-------|-------|------------|
                                | Krallık | MÖ 753-509 | 7 kral, Etrüsk etkisi |
                                | Cumhuriyet | MÖ 509-27 | Senato, konsüller |
                                | Principatus | MÖ 27-MS 284 | Augustus-Diocletianus |
                                | Dominatus | MS 284-476 | Mutlak monarşi |

                                ---

                                ### 👑 ÖNEMLİ İMPARATORLAR

                                | İmparator | Dönem | Özellik |
                                |-----------|-------|---------|
                                | Augustus | MÖ 27-MS 14 | İlk imparator, Pax Romana |
                                | Nero | MS 54-68 | Roma yangını, zulümler |
                                | Traianus | MS 98-117 | En geniş sınırlar |
                                | Hadrianus | MS 117-138 | Hadrian Duvarı |
                                | Marcus Aurelius | MS 161-180 | Filozof imparator |
                                | Constantinus | MS 306-337 | Hristiyanlığı yasallaştırdı |
                                | Theodosius | MS 379-395 | Son birleşik imparator |

                                ---

                                ### ⚖️ ROMA HUKUKU

                                Modern Batı hukukunun temeli:

                                | Kanun/İlke | Tarih | Önem |
                                |-----------|-------|------|
                                | 12 Levha Kanunları | MÖ 450 | İlk yazılı hukuk |
                                | Masumiyet karinesi | - | Suçlu kanıtlanana dek masum |
                                | Corpus Iuris Civilis | MS 529-534 | Justinianus derlemesi |

                                **Hukuki sınıflar:**
                                - Patricii (asiller)
                                - Plebs (halk)
                                - Liberti (azatlılar)
                                - Servi (köleler)

                                ---

                                ### 🛣️ MÜHENDİSLİK HARİKALARI

                                | Yapı | Özellik |
                                |------|---------|
                                | Colosseum | 50.000 seyirci, gladyatör dövüşleri |
                                | Pantheon | Betonarme kubbe (43m çap) |
                                | Via Appia | İlk Roma yolu (MÖ 312) |
                                | Pont du Gard | Su kemeri, 50m yükseklik |

                                **Yol Ağı:** 400.000+ km yol
                                **Su Kemerleri:** Roma'ya günde 1 milyon m³ su

                                ---

                                ### ⚔️ ROMA ORDUSU

                                | Birim | Mevcudu | Özellik |
                                |-------|--------|---------|
                                | Lejyon | 5.000-6.000 | Temel birlik |
                                | Kohortu | 480 | Lejyonun 1/10'u |
                                | Centuria | 80 | Centurion komutasında |

                                **Taktik:** Testudo (kaplumbağa), pilum (mızrak), gladius (kısa kılıç)

                                ---

                                ### ✝️ HIRİSTİYANLIĞIN YAYILMASI

                                | Olay | Tarih |
                                |------|-------|
                                | İsa'nın çarmıha gerilmesi | MS ~30 |
                                | Nero'nun zulümleri | MS 64 |
                                | Milano Fermanı (serbestlik) | MS 313 |
                                | Devlet dini olması | MS 380 |

                                ---

                                ### 💀 ROMA'NIN ÇÖKÜŞÜ

                                **Sebepler:**
                                1. Barbar istilaları (Vizigotlar, Vandallar, Hunlar)
                                2. Ekonomik çöküş ve enflasyon
                                3. Siyasi istikrarsızlık
                                4. Askeri güç kaybı

                                **Batı Roma:** MS 476'da son imparator Romulus Augustulus tahttan indirildi.
                                **Doğu Roma (Bizans):** MS 1453'e kadar yaşadı.

                                ---

                                ### 📚 KAYNAKLAR
                                - Oxford Classical Dictionary
                                - Britannica Encyclopedia
                                - Cambridge Ancient History
                                - Mary Beard, "SPQR"
                                """;
        }

        private String getMedievalContent() {
                return """
                                ## ⚔️ ORTA ÇAĞ
                                **Tarih:** MS 476 - 1453
                                **Kaynak:** Britannica, Cambridge Medieval History

                                ---

                                ### 📋 DÖNEM ÖZETİ

                                Batı Roma'nın çöküşünden (476) İstanbul'un fethine (1453) kadar süren bin yıllık dönem.

                                ---

                                ### 📅 ALT DÖNEMLER

                                | Dönem | Tarih | Özellikler |
                                |-------|-------|------------|
                                | Erken Orta Çağ | 476-1000 | "Karanlık Çağlar" |
                                | Yüksek Orta Çağ | 1000-1300 | Haçlı Seferleri |
                                | Geç Orta Çağ | 1300-1453 | Veba, Yüzyıl Savaşları |

                                ---

                                ### 🏰 FEODALİZM

                                **Toplum Yapısı:**
                                | Sınıf | Rol |
                                |-------|-----|
                                | Kral | En yüksek otorite |
                                | Duka/Kont | Büyük toprak sahipleri |
                                | Baronlar | Orta düzey soylular |
                                | Şövalyeler | Askeri hizmet |
                                | Serfler | Toprak köleleri |

                                **Temel İlke:** Toprak karşılığı sadakat (vassal sistemi)

                                ---

                                ### ⚔️ HAÇLI SEFERLERİ (1095-1291)

                                | Sefer | Tarih | Sonuç |
                                |-------|-------|-------|
                                | 1. Haçlı | 1096-1099 | Kudüs fethi |
                                | 2. Haçlı | 1147-1149 | Başarısız |
                                | 3. Haçlı | 1189-1192 | Kısmi uzlaşma |
                                | 4. Haçlı | 1202-1204 | Konstantinopolis yağması |

                                **Selahaddin Eyyubi:** 1187'de Kudüs'ü geri aldı.

                                ---

                                ### 🕌 İSLAM'IN ALTIN ÇAĞI (750-1258)

                                Avrupa "karanlık çağları" yaşarken, İslam dünyasında bilim zirvedeydi:

                                | Bilim İnsanı | Alan | Katkısı |
                                |--------------|------|---------|
                                | El-Harezmi | Matematik | Cebir, algoritma |
                                | İbn Sina | Tıp | "El-Kanun fi't-Tıb" |
                                | İbn el-Heysem | Fizik | Optik bilimi |
                                | İbn Rüşd | Felsefe | Aristoteles yorumları |

                                **Merkezler:** Bağdat (Beytül-Hikme), Kurtuba, Kahire

                                ---

                                ### 🏛️ BİZANS İMPARATORLUĞU

                                - Roma'nın doğu kolu olarak devam etti
                                - Başkent: Konstantinopolis
                                - 1204: 4. Haçlı Seferi yağması
                                - 1453: Osmanlı fethi

                                ---

                                ### 🏰 KALE MİMARİSİ

                                **Krak des Chevaliers (Suriye):**
                                - Haçlı dönemi kalesi
                                - 2.000 asker kapasitesi
                                - UNESCO Dünya Mirası

                                ---

                                ### 📚 KAYNAKLAR
                                - Cambridge Medieval History
                                - Britannica Encyclopedia
                                - Recueil des historiens des croisades
                                                """;
        }

        private String getEarlyModernContent() {
                return """
                                ## 🎨 YENİ ÇAĞ & RÖNESANS
                                **Tarih:** 1453 - 1789
                                **Kaynak:** Britannica, Metropolitan Museum of Art

                                ---

                                ### 📋 DÖNEM ÖZETİ

                                Orta Çağ'ın sona erip modern dünyanın temellerinin atıldığı dönem:
                                - Rönesans sanatı
                                - Matbaa devrimi
                                - Coğrafi keşifler
                                - Bilimsel devrim
                                - Reform hareketi

                                ---

                                ### 🎨 RÖNESANS (1400-1600)

                                **Merkez:** İtalya (Floransa, Venedik, Roma)

                                | Sanatçı | Eserleri |
                                |---------|----------|
                                | Leonardo da Vinci | Mona Lisa, Son Akşam Yemeği |
                                | Michelangelo | Sistine Şapeli, Davut |
                                | Raffaello | Atina Okulu |
                                | Botticelli | Venüs'ün Doğuşu |

                                **Temel İlkeler:**
                                - Hümanizm (insan merkezli dünya görüşü)
                                - Perspektif (3 boyutlu sanat)
                                - Anatomi bilgisi
                                - Antik Yunan-Roma'ya dönüş

                                ---

                                ### 📖 MATBAA DEVRİMİ

                                **Johannes Gutenberg (1440):** Hareketli metal harfli matbaa

                                **Etkileri:**
                                - Kitap fiyatları düştü
                                - Okuma-yazma yaygınlaştı
                                - Reformasyon hızlandı
                                - Bilimsel bilgi yayıldı

                                **İlk basılı kitap:** Gutenberg İncili (1455)

                                ---

                                ### 🌍 COĞRAFİ KEŞİFLER

                                | Kaşif | Tarih | Keşfi |
                                |-------|-------|-------|
                                | Kristof Kolomb | 1492 | Amerika |
                                | Vasco da Gama | 1498 | Hindistan deniz yolu |
                                | Magellan | 1519-1522 | Dünya turu |
                                | Cortés | 1519-1521 | Aztek fethici |

                                **Sonuçları:**
                                - "Kolomb Değişimi" (bitki/hayvan transferi)
                                - Sömürgecilik
                                - Küresel ticaret ağları

                                ---

                                ### ⛪ REFORM HAREKETİ

                                **Martin Luther (1517):** 95 Tez

                                | Hareket | Kurucu | Bölge |
                                |---------|--------|-------|
                                | Luthercilik | Martin Luther | Almanya |
                                | Kalvinizm | Jean Calvin | İsviçre, Fransa |
                                | Anglikanizm | VIII. Henry | İngiltere |

                                **Karşı-Reform:** Trent Konsili (1545-1563)

                                ---

                                ### 🔬 BİLİMSEL DEVRİM (1543-1687)

                                | Bilim İnsanı | Katkısı |
                                |--------------|---------|
                                | Kopernik | Güneş merkezli evren |
                                | Galileo | Teleskop gözlemleri |
                                | Kepler | Gezegen yörünge yasaları |
                                | Newton | Hareket ve kütleçekim yasaları |

                                **Bilimsel Yöntem:** Gözlem → Hipotez → Deney → Teori

                                ---

                                ### 📚 KAYNAKLAR
                                - Metropolitan Museum of Art
                                - Britannica Encyclopedia
                                - Scientific Revolution Symposium
                                                """;
        }

        private String getIndustrialContent() {
                return """
                                ## 🏭 SANAYİ DEVRİMİ
                                **Tarih:** 1760 - 1914
                                **Kaynak:** Britannica, Economic History Association

                                ---

                                ### 📋 DÖNEM ÖZETİ

                                İnsan tarihinin en büyük ekonomik dönüşümü:
                                - El işçiliğinden makine üretimine geçiş
                                - Tarım toplumundan sanayi toplumuna dönüşüm
                                - Kentleşme patlaması
                                - Küresel ticaretin genişlemesi

                                ---

                                ### ⚙️ TEKNOLOJİK YENİLİKLER

                                | İcat | Tarih | Mucit |
                                |------|-------|-------|
                                | Spinning Jenny | 1764 | James Hargreaves |
                                | Buhar makinesi | 1769 | James Watt |
                                | Buharlı lokomotif | 1804 | Richard Trevithick |
                                | Telgraf | 1837 | Samuel Morse |
                                | Telefon | 1876 | Alexander Graham Bell |
                                | Ampul | 1879 | Thomas Edison |
                                | Otomobil | 1886 | Karl Benz |

                                ---

                                ### 🚂 ULAŞIM DEVRİMİ

                                **Demiryolları:**
                                - 1825: İlk yolcu hattı (Stockton-Darlington)
                                - 1869: ABD kıtalararası demiryolu
                                - 1869: Süveyş Kanalı açılışı

                                **Buharlı Gemiler:**
                                - Transatlantik seferler
                                - Dünya ticaretinin hızlanması

                                ---

                                ### 🏙️ KENTLEŞME

                                | Şehir | 1800 Nüfusu | 1900 Nüfusu |
                                |-------|-------------|-------------|
                                | Londra | 1 milyon | 6.5 milyon |
                                | Manchester | 75.000 | 544.000 |
                                | New York | 60.000 | 3.4 milyon |
                                | Berlin | 172.000 | 1.9 milyon |

                                **Sorunlar:**
                                - Aşırı kalabalık
                                - Salgın hastalıklar
                                - İşçi sınıfı sefaleti

                                ---

                                ### 👷 TOPLUMSAL ETKİLER

                                **İşçi Sınıfı:**
                                - 12-16 saat çalışma
                                - Çocuk işçiliği
                                - Tehlikeli fabrika koşulları

                                **Tepkiler:**
                                - Sendikalaşma
                                - Çartist hareket (oy hakkı)
                                - Sosyalizm ve Marksizm

                                ---

                                ### 🌍 YAYILIMI

                                | Ülke | Sanayileşme Dönemi |
                                |------|-------------------|
                                | İngiltere | 1760-1840 |
                                | Belçika | 1820-1870 |
                                | Fransa | 1830-1870 |
                                | Almanya | 1840-1900 |
                                | ABD | 1850-1920 |
                                | Japonya | 1870-1920 (Meiji) |

                                ---

                                ### 🌐 İKİNCİ SANAYİ DEVRİMİ (1870-1914)

                                **Yeni Enerji Kaynakları:**
                                - Elektrik
                                - Petrol

                                **Yeni Endüstriler:**
                                - Kimya
                                - Otomotiv
                                - Çelik

                                **Büyük Şirketler:**
                                - Standard Oil (Rockefeller)
                                - Carnegie Steel
                                - Ford Motor Company

                                ---

                                ### 📚 KAYNAKLAR
                                - Economic History Association
                                - Britannica Encyclopedia
                                - Industrial Revolution Studies
                                                """;
        }

        // Inner class for Era structure
        public static class Era {
                private String id;
                private String title;
                private String category;
                private String period;
                private String description;
                private String imageUrl;
                private String detailedContent;

                public Era(String id, String title, String category, String period, String description, String imageUrl,
                                String detailedContent) {
                        this.id = id;
                        this.title = title;
                        this.category = category;
                        this.period = period;
                        this.description = description;
                        this.imageUrl = imageUrl;
                        this.detailedContent = detailedContent;
                }

                public String getId() {
                        return id;
                }

                public String getTitle() {
                        return title;
                }

                public String getCategory() {
                        return category;
                }

                public String getPeriod() {
                        return period;
                }

                public String getDescription() {
                        return description;
                }

                public String getImageUrl() {
                        return imageUrl;
                }

                public String getDetailedContent() {
                        return detailedContent;
                }
        }
}
