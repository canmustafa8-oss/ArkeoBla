package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class TimeMachineController {

        private final Map<String, Era> eraMap = new LinkedHashMap<>();

        public TimeMachineController() {
                // --- TARİH ÖNCESİ ÇAĞLAR ---
                addEra(new Era("paleolithic", "Paleolitik Çağ", "Tarih Öncesi", "MÖ 2.5 Milyon - MÖ 10.000",
                                "İnsanlığın şafağı, ilk ateş, mağara sanatı ve avcı-toplayıcı yaşam.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Lascaux_II.jpg/800px-Lascaux_II.jpg",
                                getPaleolithicContent()));

                addEra(new Era("mesolithic", "Mezolitik Çağ", "Tarih Öncesi", "MÖ 10.000 - MÖ 8.000",
                                "Buzulların erimesi, mikrolit teknolojisi ve yerleşik hayata geçişin ilk izleri.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Mesolithic_House_Reconstruction_-_Irish_National_Heritage_Park.jpg/800px-Mesolithic_House_Reconstruction_-_Irish_National_Heritage_Park.jpg",
                                getMesolithicContent()));

                addEra(new Era("neolithic", "Neolitik Çağ", "Tarih Öncesi", "MÖ 8.000 - MÖ 5.500",
                                "Tarım devrimi, Göbeklitepe, Çatalhöyük ve ilk köylerin kuruluşu.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/G%C3%B6bekli_Tepe%2C_Urfa.jpg/800px-G%C3%B6bekli_Tepe%2C_Urfa.jpg",
                                getNeolithicContent()));

                addEra(new Era("chalcolithic", "Kalkolitik Çağ", "Tarih Öncesi", "MÖ 5.500 - MÖ 3.000",
                                "Bakırın keşfi, madenciliğin doğuşu ve şehirleşmeye giden yol.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Varna_Gold_Treasures.jpg/800px-Varna_Gold_Treasures.jpg",
                                getChalcolithicContent()));

                // --- ANTİK ÇAĞ ---
                addEra(new Era("ancient-egypt", "Antik Mısır", "Antik Çağ", "MÖ 3100 - MÖ 30",
                                "Piramitler, firavunlar ve Nil'in bereketiyle yükselen bir medeniyet.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/All_Gizah_Pyramids.jpg/800px-All_Gizah_Pyramids.jpg",
                                getAncientEgyptContent()));

                addEra(new Era("ancient-greece", "Antik Yunan & Roma", "Antik Çağ", "MÖ 800 - MS 476",
                                "Felsefe, demokrasi, hukuk ve muazzam mimari eserlerin altın çağı.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/The_Parthenon_in_Athens.jpg/800px-The_Parthenon_in_Athens.jpg",
                                getAncientGreeceRomeContent()));

                // --- ORTA ÇAĞ ---
                addEra(new Era("medieval", "Orta Çağ", "Orta Çağ", "MS 476 - MS 1453",
                                "Şovalyeler, kaleler, feodalizm ve İslam'ın Altın Çağı.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Krak_des_Chevaliers_landscape.jpg/800px-Krak_des_Chevaliers_landscape.jpg",
                                getMedievalContent()));

                // --- YENİ & YAKIN ÇAĞ ---
                addEra(new Era("early-modern", "Yeni Çağ & Rönesans", "Yeni Çağ", "1453 - 1789",
                                "Sanatın yeniden doğuşu, coğrafi keşifler ve bilimsel devrim.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Da_Vinci_Vitruve_Luc_Viatour.jpg/800px-Da_Vinci_Vitruve_Luc_Viatour.jpg",
                                getEarlyModernContent()));

                addEra(new Era("industrial", "Sanayi Devrimi", "Yakın Çağ", "1760 - 1900",
                                "Buharlı makineler, fabrikalar ve modern dünyanın temellerinin atılması.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Opening_of_the_Stockton_and_Darlington_Railway.jpg/800px-Opening_of_the_Stockton_and_Darlington_Railway.jpg",
                                getIndustrialContent()));
        }

        private void addEra(Era era) {
                eraMap.put(era.getId(), era);
        }

        @GetMapping("/time-machine")
        public String timeMachine(@RequestParam(required = false) String era, Model model) {
                if (era != null && eraMap.containsKey(era)) {
                        model.addAttribute("selectedEra", eraMap.get(era));
                }
                model.addAttribute("eras", eraMap.values());
                return "time-machine";
        }

        @GetMapping("/time-machine/era/{id}")
        public String eraDetail(@PathVariable String id, Model model) {
                Era era = eraMap.get(id);
                if (era == null) {
                        return "redirect:/time-machine";
                }
                model.addAttribute("era", era);
                return "era-detail";
        }

        // ========== İÇERİK METOTLARI ==========

        private String getPaleolithicContent() {
                return """
                                ## 🦴 PALEOLİTİK ÇAĞ: İNSANLIĞIN EN UZUN YOLCULUĞU

                                ### GİRİŞ
                                Paleolitik Çağ (Eski Taş Çağı), insanlık tarihinin %99'unu kapsayan, ilk taştan aletlerin yapımından son buzul çağının bitimine kadar süren devasa bir zaman dilimidir.

                                ![Mağara Sanatı](https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Chauvet%2C_chevaux.jpg/800px-Chauvet%2C_chevaux.jpg)
                                *(Chauvet Mağarası'ndaki 30.000 yıllık at çizimleri, sanatın doğuşunu simgeler)*

                                ---

                                ## 🔥 ATEŞİN EVCİLLEŞTİRİLMESİ
                                İnsanlık tarihindeki en büyük teknolojik sıçramalardan biri ateşin kontrol altına alınmasıdır.
                                - **Isınma:** Soğuk iklimlerde hayatta kalmayı mümkün kıldı.
                                - **Korunma:** Yırtıcı hayvanları kamplardan uzak tuttu.
                                - **Beslenme:** Pişirilen etin sindirimi kolaylaştı, bu da beyin gelişimine enerji sağladı.
                                - **Sosyalleşme:** Ateş başı sohbetleri, dilin ve hikaye anlatıcılığının gelişmesini sağladı.

                                ---

                                ## 🔨 ALET TEKNOLOJİSİ
                                İnsanları diğer canlılardan ayıran en önemli özellik alet yapımıdır.

                                ![Taş Aletler](https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Handaxe_of_Sidi_Abderrahman.jpg/600px-Handaxe_of_Sidi_Abderrahman.jpg)
                                *(Acheulean tarzı bir el baltası - Dönemin "İsviçre Çakısı")*

                                - **Oldowan Kültürü:** Basit yongalar ve keskin taşlar.
                                - **Acheulean Kültürü:** Simetrik el baltaları (Gözyaşı damlası şeklinde).
                                - **Mousterian Kültürü:** Neandertallerin kullandığı daha karmaşık aletler.

                                ---

                                ## 🎨 SANATIN DOĞUŞU: MAĞARA RESİMLERİ VE VENÜSLER
                                İnsanlar sadece hayatta kalmaya çalışmadılar, aynı zamanda dünyayı anlamlandırmaya çalıştılar.

                                ### Venüs Figürinleri
                                Avrupa'nın dört bir yanında bulunan, abartılı kadınsı hatlara sahip küçük heykelcikler.
                                ![Venüs Heykelciği](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Venus_von_Willendorf_01.jpg/400px-Venus_von_Willendorf_01.jpg)
                                *(Willendorf Venüsü - Avusturya, MÖ 25.000 (Bereket Sembolü))*

                                """;
        }

        private String getMesolithicContent() {
                return """
                                ## 🌊 MEZOLİTİK ÇAĞ: DEĞİŞİM VE ADAPTASYON

                                ### GİRİŞ
                                Buzul Çağı'nın sona ermesiyle (MÖ 10.000 civarı) dünya ısındı, deniz seviyeleri yükseldi ve flora/fauna kökten değişti. Mamutlar yok oldu, ormanlar genişledi.

                                ![Mezolitik Yaşam](https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Mesolithic_House_Reconstruction_-_Irish_National_Heritage_Park.jpg/800px-Mesolithic_House_Reconstruction_-_Irish_National_Heritage_Park.jpg)
                                *(İrlanda'da bir Mezolitik dönem kulübesi rekonstrüksiyonu)*

                                ---

                                ## 🗡️ MİKROLİTLER: KÜÇÜK TAŞLAR, BÜYÜK ETKİ
                                Bu dönemin en belirgin teknolojisi **Mikrolit**lerdir.
                                - Çok küçük, geometrik (üçgen, yamuk) çakmaktaşı parçalarıdır.
                                - Ahşap veya kemik saplara dizilerek mızrak ucu, ok ucu veya orak olarak kullanılırdı.
                                - Bu modüler sistem, sap kırılmadan sadece ucun değiştirilmesine olanak tanıyordu.

                                ---

                                ## 🐕 KÖPEĞİN EVCİLLEŞTİRİLMESİ
                                İnsanlık tarihindeki ilk evcilleştirme olayı bu dönemde gerçekleşti. Kurtlar, av partneri ve kamp koruyucusu olarak insan yaşamına girdi.
                                """;
        }

        private String getNeolithicContent() {
                return """
                                ## 🌾 NEOLİTİK ÇAĞ: TARIM DEVRİMİ

                                ### GİRİŞ
                                İnsanlık tarihinin en büyük kırılma noktası: Avcı-toplayıcılıktan, üretici (tarımcı) yaşama geçiş. Bu devrim, nüfus patlamasına ve ilk medeniyetlerin doğuşuna yol açtı.

                                ---

                                ## 🏛️ GÖBEKLİTEPE: TARİH YENİDEN YAZILIYOR
                                Şanlıurfa'da bulunan bu tapınak kompleksi, "Önce yerleşik hayat, sonra tapınak" tezini çürüttü. İnsanlar henüz avcıyken bu devasa taşları diktiler.

                                ![Göbeklitepe](https://upload.wikimedia.org/wikipedia/commons/thumb/1/10/G%C3%B6bekli_Tepe%2C_Urfa.jpg/800px-G%C3%B6bekli_Tepe%2C_Urfa.jpg)
                                *(T-Biçimindeki Sütunlar, MÖ 9600)*

                                > [!IMPORTANT]
                                > Göbeklitepe, Mısır Piramitlerinden 7.000, Stonehenge'den 6.000 yıl daha yaşlıdır.

                                ---

                                ## 🏠 ÇATALHÖYÜK: SOKAKSIZ ŞEHİR
                                Konya Ovası'ndaki bu yerleşim, 8.000 kişilik nüfusuyla döneminin metropolüydü.
                                - Sokak yoktu, evler bitişik nizam yapılmıştı.
                                - Evlere çatılardaki deliklerden girilirdi.
                                - Ölüler evlerin tabanına, uyuma platformlarının altına gömülürdü.

                                ![Çatalhöyük](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Catalh%C3%BCy%C3%BCk_restauriertes_Haus_innen.jpg/800px-Catalh%C3%BCy%C3%BCk_restauriertes_Haus_innen.jpg)
                                *(Çatalhöyük ev içi rekonstrüksiyonu)*

                                ---

                                ## 🏺 ÇÖMLEKÇİLİK VE DOKUMA
                                Artık insanlar yerleşik olduğu için ağır kap kacakları taşıma derdi yoktu. Kili pişirerek kaplar yaptılar, yünü eğirerek kumaş dokudular.
                                """;
        }

        private String getChalcolithicContent() {
                return """
                                ## ⚱️ KALKOLİTİK ÇAĞ: MADENİN KEŞFİ

                                ### GİRİŞ
                                Taş Çağı ile Maden Çağı arasındaki geçiş dönemidir. İnsanlar ilk kez taşı değil, bir madeni (Bakır) ısıtıp şekillendirmeyi öğrendi. (Chalkos: Bakır, Lithos: Taş).

                                ![Varna Altınları](https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Varna_Gold_Treasures.jpg/800px-Varna_Gold_Treasures.jpg)
                                *(Varna Nekropolü'nde bulunan dünyanın en eski işlenmiş altın hazinesi - MÖ 4600)*

                                ---

                                ## 💎 SOSYAL SINIFLARIN DOĞUŞU
                                Madenin işlenmesi uzmanlık gerektiriyordu. Bu durum toplumda iş bölümünü ve hiyerarşiyi doğurdu:
                                - Madenciler ve demirciler (Zanaatkarlar)
                                - Bu ürünleri pazarlayan tüccarlar
                                - Zenginliği koruyan askerler
                                - Yönetici elit sınıf

                                Kalkolitik çağın sonlarına doğru **Tunç** (Bakır + Kalay alaşımı) keşfedilecek ve Tunç Çağı başlayacaktı.
                                """;
        }

        private String getAncientEgyptContent() {
                return """
                                ## 🏛️ ANTİK MISIR: NİL'İN HEDİYESİ

                                ### GİRİŞ
                                Antik Mısır, çöllerin ortasında Nil Nehri'nin yarattığı bir vaha medeniyetidir. 3000 yıl boyunca istikrarını korumuştur.

                                ![Giza Piramitleri](https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/All_Gizah_Pyramids.jpg/800px-All_Gizah_Pyramids.jpg)
                                *(Giza Platosu'ndaki devasa anıt mezarlar)*

                                ---

                                ## 👑 FİRAVUNLAR VE TANRILAR
                                Firavun, sadece bir kral değil, yaşayan bir tanrı (Horus'un yeryüzündeki gölgesi) olarak kabul edilirdi.

                                ### Tutankhamun'un Maskesi
                                Mısır sanatının en ikonik eseri. Genç yaşta ölen firavunun mezarı 1922'de hiç bozulmadan bulundu.
                                ![Tutankhamun](https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Tutanchamun_Maske.jpg/400px-Tutanchamun_Maske.jpg)

                                ---

                                ## 📜 HİYEROGLİF VE YAZI
                                Mısırlılar tapınak duvarlarına kazıdıkları "Kutsal Yazılar" ile tarihlerini kaydettiler. Bu yazıların sırrı, 1799'da bulunan **Rosetta Taşı** sayesinde çözüldü.

                                ![Rosetta Taşı](https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Rosetta_Stone.JPG/400px-Rosetta_Stone.JPG)
                                """;
        }

        private String getAncientGreeceRomeContent() {
                return """
                                ## 🏛️ ANTİK YUNAN VE ROMA

                                ### ANTİK YUNAN: DEMOKRASİNİN BEŞİĞİ
                                Atina, dünya tarihinde demokrasinin ilk uygulandığı yerdir (her ne kadar sadece özgür erkekler için olsa da).

                                ![Parthenon](https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/The_Parthenon_in_Athens.jpg/800px-The_Parthenon_in_Athens.jpg)
                                *(Atina Akropolisi'ndeki Parthenon Tapınağı - Tanrıça Athena'ya adanmıştır)*

                                ---

                                ### ROMA İMPARATORLUĞU: MÜHENDİSLİK DEVLERİ
                                Romalılar pragmatikti. Yollar, köprüler, su kemerleri ve devasa arenalar inşa ettiler.

                                ![Kolezyum](https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Colosseum_in_Rome-April_2007-1-_copie_2B.jpg/800px-Colosseum_in_Rome-April_2007-1-_copie_2B.jpg)
                                *(Roma Kolezyumu - 50.000 kişi kapasiteli gladyatör arenası)*
                                """;
        }

        private String getMedievalContent() {
                return """
                                ## ⚔️ ORTA ÇAĞ: ŞOVALYELER VE KATEDRALLER

                                Batı Roma'nın çöküşüyle (MS 476) başlayan ve İstanbul'un Fethiyle (1453) biten dönem.

                                ### FEODALİZM VE ŞATOLAR
                                Güvenliğin kalmadığı Avrupa'da insanlar kalın duvarlı şatolara sığındı. Lordlar toprak karşılığı şövalyelerden sadakat aldı.

                                ![Krak des Chevaliers](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Krak_des_Chevaliers_landscape.jpg/800px-Krak_des_Chevaliers_landscape.jpg)
                                *(Haçlı Seferleri döneminden kalma, dünyanın en iyi korunmuş kalesi)*

                                ---

                                ### İSLAM'IN ALTIN ÇAĞI
                                Avrupa karanlık çağları yaşarken, Bağdat, Şam ve Kurtuba'da bilim, tıp ve felsefe zirve yapıyordu.
                                """;
        }

        private String getEarlyModernContent() {
                return """
                                ## 🎨 RÖNESANS: YENİDEN DOĞUŞ

                                Orta Çağ'ın dogmatik düşüncesinin yerini, insanı merkeze alan (Hümanizm) ve aklı yücelten bir dönem aldı.

                                ![Mona Lisa](https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg/400px-Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg)
                                *(Leonardo da Vinci'nin başyapıtı)*

                                ### COĞRAFİ KEŞİFLER
                                Pusulanın gelişmesi ve cesur denizciler sayesinde dünyanın haritası değişti. Amerika'nın keşfi, küresel ticaret ağlarını başlattı.
                                """;
        }

        private String getIndustrialContent() {
                return """
                                ## 🏭 SANAYİ DEVRİMİ: MAKİNE ÇAĞI

                                18. yüzyılda İngiltere'de buhar makinesinin icadıyla dünya bir daha asla eskisi gibi olmadı.

                                ![Buharlı Tren](https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Opening_of_the_Stockton_and_Darlington_Railway.jpg/800px-Opening_of_the_Stockton_and_Darlington_Railway.jpg)
                                *(İlk demiryolları, mesafeleri kısalttı ve dünyayı küçülttü)*

                                - **Seri Üretim:** Fabrikalar el emeğinin yerini aldı.
                                - **Kentleşme:** Köylerden şehirlere büyük göçler yaşandı.
                                - **Elektrik:** Ampulün icadı geceyi gündüze çevirdi.
                                """;
        }

        // Inner class for Era structure
        public static class Era {
                private String id;
                private String title;
                private String category; // Yeni Alan
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
