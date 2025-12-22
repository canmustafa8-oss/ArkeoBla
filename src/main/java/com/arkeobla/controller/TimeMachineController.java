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
                // Tarih Öncesi Çağlar - ÇOK DETAYLI
                Era prehistoric = new Era("prehistoric", "Tarih Öncesi Çağlar", "MÖ 2.5 Milyon - MÖ 3200",
                                "İnsanlığın doğuşu, avcı-toplayıcı yaşam ve ilk aletlerin icadı.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Lascaux_II.jpg/600px-Lascaux_II.jpg");
                prehistoric.setDetailedContent(getPrehistoricContent());
                eraMap.put("prehistoric", prehistoric);

                // Antik Mısır
                Era egypt = new Era("ancient-egypt", "Antik Mısır", "MÖ 3100 - MÖ 30",
                                "Piramitler, firavunlar ve Nil'in bereketiyle yükselen bir medeniyet.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/All_Gizah_Pyramids.jpg/600px-All_Gizah_Pyramids.jpg");
                egypt.setDetailedContent(getAncientEgyptContent());
                eraMap.put("ancient-egypt", egypt);

                // Antik Yunan & Roma
                Era greece = new Era("ancient-greece", "Antik Yunan & Roma", "MÖ 800 - MS 476",
                                "Felsefe, demokrasi, hukuk ve muazzam mimari eserlerin altın çağı.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/The_Parthenon_in_Athens.jpg/600px-The_Parthenon_in_Athens.jpg");
                greece.setDetailedContent(getAncientGreeceRomeContent());
                eraMap.put("ancient-greece", greece);

                // Orta Çağ
                Era medieval = new Era("medieval", "Orta Çağ", "MS 476 - MS 1453",
                                "Şovalyeler, kaleler, feodalizm ve İslam'ın Altın Çağı.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Krak_des_Chevaliers_landscape.jpg/600px-Krak_des_Chevaliers_landscape.jpg");
                medieval.setDetailedContent(getMedievalContent());
                eraMap.put("medieval", medieval);

                // Yeni Çağ & Rönesans
                Era earlyModern = new Era("early-modern", "Yeni Çağ & Rönesans", "1453 - 1789",
                                "Sanatın yeniden doğuşu, coğrafi keşifler ve bilimsel devrim.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Da_Vinci_Vitruve_Luc_Viatour.jpg/450px-Da_Vinci_Vitruve_Luc_Viatour.jpg");
                earlyModern.setDetailedContent(getEarlyModernContent());
                eraMap.put("early-modern", earlyModern);

                // Sanayi Devrimi
                Era industrial = new Era("industrial", "Sanayi Devrimi", "1760 - 1900",
                                "Buharlı makineler, fabrikalar ve modern dünyanın temellerinin atılması.",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Opening_of_the_Stockton_and_Darlington_Railway.jpg/600px-Opening_of_the_Stockton_and_Darlington_Railway.jpg");
                industrial.setDetailedContent(getIndustrialContent());
                eraMap.put("industrial", industrial);
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

        private String getPrehistoricContent() {
                return """
                                ## 🦴 TARİH ÖNCESİ ÇAĞLAR: İNSANLIĞIN ŞAFAĞI

                                ### GİRİŞ
                                Tarih öncesi çağlar, yazının icadından (yaklaşık MÖ 3200) önceki tüm insan tarihini kapsar. Bu dönem, insanlığın en uzun ve en dramatik dönüşümlerini içerir: İlk ataların Afrika'da ortaya çıkışından, tarımın icadına ve ilk şehirlerin kurulmasına kadar uzanan milyonlarca yıllık bir serüven.

                                ---

                                ## 📅 DÖNEM SINIFLANDIRMASI

                                ### 1. PALEOLİTİK ÇAĞ (ESKİ TAŞ ÇAĞI)
                                **Süre:** MÖ 2.5 Milyon - MÖ 10.000

                                Paleolitik Çağ, insan tarihinin en uzun dönemidir. Bu dönemde insanlar:
                                - Avcı-toplayıcı yaşam sürdü
                                - İlk taş aletleri geliştirdi
                                - Ateşi kontrol etmeyi öğrendi
                                - Mağara resimleri yaptı
                                - Dil ve iletişim becerilerini geliştirdi

                                #### Alt Dönemler:

                                **Alt Paleolitik (MÖ 2.5 Milyon - MÖ 300.000)**
                                - Homo habilis ve Homo erectus türleri
                                - Oldowan ve Acheulean alet gelenekleri
                                - Ateşin ilk kullanımı (MÖ 1.5 milyon civarı)
                                - Afrika'dan ilk göçler

                                **Orta Paleolitik (MÖ 300.000 - MÖ 30.000)**
                                - Homo neanderthalensis ve erken Homo sapiens
                                - Mousterian alet geleneği
                                - Karmaşık av stratejileri
                                - İlk gömü ritüelleri
                                - Sembolik düşüncenin başlangıcı

                                **Üst Paleolitik (MÖ 30.000 - MÖ 10.000)**
                                - Modern Homo sapiens'in hakimiyeti
                                - Sanat patlaması: Mağara resimleri, heykelcikler
                                - Kemik, boynuz ve fildişi aletler
                                - İlk müzik aletleri
                                - Karmaşık toplumsal yapılar

                                ---

                                ### 2. MEZOLİTİK ÇAĞ (ORTA TAŞ ÇAĞI)
                                **Süre:** MÖ 10.000 - MÖ 8.000

                                Buzul Çağı'nın sona ermesiyle birlikte:
                                - İklim ısındı ve ormanlar genişledi
                                - Mikrolit (küçük taş alet) teknolojisi
                                - Balıkçılık ve deniz kaynakları
                                - Yarı-yerleşik topluluklar
                                - Evcilleştirmenin ilk adımları

                                ---

                                ### 3. NEOLİTİK ÇAĞ (YENİ TAŞ ÇAĞI)
                                **Süre:** MÖ 8.000 - MÖ 3.200

                                İnsanlık tarihinin en büyük dönüşümü: **Neolitik Devrim**

                                #### Tarımın İcadı
                                - Buğday, arpa, mercimek gibi tahılların evcilleştirilmesi
                                - Koyun, keçi, sığır ve domuzun evcilleştirilmesi
                                - Bereketli Hilal bölgesi: Güneydoğu Anadolu, Suriye, Irak
                                - Nüfus patlaması

                                #### İlk Köyler ve Kasabalar
                                - **Çatalhöyük (MÖ 7500-5700):** Dünyanın ilk "şehirlerinden" biri, Konya
                                - **Göbeklitepe (MÖ 9600-8200):** Dünyanın en eski tapınağı, Şanlıurfa
                                - **Jericho (MÖ 9000):** En eski surlu yerleşimlerden biri

                                #### Teknolojik Gelişmeler
                                - Cilalı taş aletler
                                - Çömlekçilik
                                - Dokumacılık
                                - İlkel metallürji (bakır)

                                ---

                                ## 🦣 İNSAN EVRİMİNİN MİHENK TAŞLARI

                                ### Australopithecus (MÖ 4 Milyon - MÖ 2 Milyon)
                                - Afrika'da yaşadı
                                - İki ayak üzerinde yürüyen ilk primatlar
                                - Küçük beyin (450-530 cm³)
                                - Ünlü fosil: "Lucy" (Etiyopya, 3.2 milyon yıl)

                                ### Homo habilis (MÖ 2.5 Milyon - MÖ 1.5 Milyon)
                                - "Becerikli İnsan"
                                - İlk alet yapımcısı
                                - Beyin: 600-750 cm³
                                - Oldowan aletleri

                                ### Homo erectus (MÖ 1.9 Milyon - MÖ 110.000)
                                - "Dik Duran İnsan"
                                - Afrika'dan çıkan ilk tür
                                - Ateşi kullandı
                                - Acheulean el baltaları
                                - Beyin: 850-1100 cm³

                                ### Homo neanderthalensis (MÖ 400.000 - MÖ 40.000)
                                - Avrupa ve Batı Asya
                                - Güçlü yapı, soğuğa adaptasyon
                                - Ölülerini gömdü
                                - Takı ve süs eşyaları
                                - Modern insanlarla melezleşti

                                ### Homo sapiens (MÖ 300.000 - Günümüz)
                                - "Akıllı İnsan"
                                - Afrika'da ortaya çıktı
                                - MÖ 70.000'de Afrika'dan göç
                                - Sembolik düşünce, sanat, din
                                - Tüm dünyaya yayıldı

                                ---

                                ## 🎨 PALEOLİTİK SANATI

                                ### Mağara Resimleri

                                **Lascaux Mağarası (Fransa, MÖ 17.000)**
                                - "Tarih Öncesi Sistine Şapeli"
                                - 600'den fazla hayvan figürü
                                - Atlar, bizonlar, geyikler
                                - Doğal pigmentler: Oker, manganez, kömür

                                **Altamira Mağarası (İspanya, MÖ 36.000-15.000)**
                                - İlk keşfedilen mağara sanatı
                                - Çok renkli bizon resimleri
                                - Tavan boyamaları

                                **Chauvet Mağarası (Fransa, MÖ 36.000)**
                                - En eski bilinen mağara sanatı
                                - Aslanlar, gergedanlar, ayılar
                                - Olağanüstü realizm

                                ### Heykelcikler

                                **Venüs Figürinleri**
                                - Avrupa genelinde bulunan kadın heykelcikleri
                                - MÖ 40.000 - MÖ 10.000
                                - Abartılı kadınsı özellikler
                                - Bereket sembolü?

                                **Aslan-İnsan (Löwenmensch)**
                                - Almanya, MÖ 40.000
                                - Bilinen en eski hayvan-insan karışımı heykel
                                - Mamut dişinden oyulmuş

                                ---

                                ## 🔥 ATEŞİN ÖNEMİ

                                Ateşin kontrol edilmesi insanlık için devrim niteliğindeydi:

                                1. **Beslenme:** Pişirme ile daha çok kalori ve besin
                                2. **Güvenlik:** Yırtıcılardan korunma
                                3. **Isınma:** Soğuk iklimlere yayılma
                                4. **Sosyalleşme:** Ateş etrafında topluluk bağları
                                5. **Teknoloji:** Alet yapımında ısı kullanımı

                                İlk ateş kullanımı kanıtları:
                                - Wonderwerk Mağarası (Güney Afrika): MÖ 1 milyon
                                - Gesher Benot Ya'akov (İsrail): MÖ 790.000

                                ---

                                ## 🗺️ BÜYÜK GÖÇ: AFRİKA'DAN DÜNYAYA

                                ### İlk Göç Dalgası (Homo erectus)
                                - MÖ 1.8 milyon
                                - Asya ve Avrupa'ya yayılım
                                - Java Adamı, Pekin Adamı

                                ### İkinci Göç Dalgası (Homo sapiens)
                                - MÖ 70.000 - MÖ 60.000
                                - Afrika'dan Arap Yarımadası'na
                                - MÖ 50.000: Avustralya'ya ulaşım
                                - MÖ 45.000: Avrupa'ya varış
                                - MÖ 15.000: Amerika kıtasına geçiş (Bering Boğazı)

                                ---

                                ## 🏠 GÖBEKLİTEPE: TARİHİN SIFIR NOKTASI

                                Şanlıurfa'daki Göbeklitepe, tarih öncesi anlayışımızı kökten değiştirdi.

                                **Önemli Bilgiler:**
                                - Tarih: MÖ 9600 - MÖ 8200
                                - Piramitlerden 7.000, Stonehenge'den 6.000 yıl daha eski
                                - Dev T-şeklinde dikilitaşlar (10-15 ton)
                                - Karmaşık kabartmalar: Aslanlar, boğalar, yılanlar, akbabalar

                                **Devrimsel Önemi:**
                                Geleneksel görüş: İnsanlar önce tarıma geçti, sonra tapınak yaptı.
                                Göbeklitepe: Din ve ritüel, uygarlığın öncüsü olabilir.

                                ---

                                ## 🌾 ÇATALHÖYÜK: İLK "KENT"

                                Konya'daki Çatalhöyük, Neolitik dönemin en iyi korunmuş yerleşimlerinden biridir.

                                **Özellikler:**
                                - MÖ 7500 - MÖ 5700
                                - 8.000'e varan nüfus
                                - Bitişik evler, sokak yok
                                - Evlere çatıdan giriş
                                - Ev içi gömüler
                                - Duvar resimleri ve kabartmalar

                                **Sosyal Yapı:**
                                - Görünür eşitsizlik yok
                                - Kadın ve erkek eşit gömüler
                                - Topluluk odaklı yaşam

                                ---

                                ## 🧪 NEOLİTİK TEKNOLOJİ

                                ### Tarım Aletleri
                                - Oraklar (obsidyen ve çakmaktaşı)
                                - Değirmen taşları
                                - Kazma ve çapalar

                                ### Çömlekçilik
                                - MÖ 6500 civarında yaygınlaşma
                                - Depolama ve pişirme
                                - Süsleme gelenekleri

                                ### Dokumacılık
                                - Keten ve yün
                                - İğne ve ağırlıklar
                                - Giysi ve çadır yapımı

                                ### Metallürji
                                - İlk bakır işleme: MÖ 7000
                                - Doğal bakır dövme
                                - Eritme teknikleri gelişiyor

                                ---

                                ## 🌍 DÜNYADA TARİH ÖNCESİ DÖNEM

                                ### Avrupa
                                - Megalitik yapılar (Stonehenge, Carnac)
                                - Bandkeramik kültürü
                                - Bronz Çağı'na geçiş

                                ### Ortadoğu (Bereketli Hilal)
                                - Tarımın beşiği
                                - İlk şehir-devletler
                                - Sümer uygarlığına geçiş

                                ### Uzak Doğu
                                - Çin: Pirinç tarımı (MÖ 6000)
                                - Japonya: Jomon kültürü (çömlekçilik)
                                - Hindistan: İndus Vadisi'ne giden yol

                                ### Afrika
                                - Nil Vadisi tarımı
                                - Sahra'nın yeşil dönemi
                                - Kaya sanatı

                                ### Amerika
                                - Clovis kültürü (MÖ 13.000)
                                - Mamut avcıları
                                - Tarıma geçiş (mısır, fasulye)

                                ---

                                ## 📊 ZAMAN ÇİZELGESİ

                                | Tarih | Olay |
                                |-------|------|
                                | MÖ 2.5 Milyon | İlk taş aletler |
                                | MÖ 1.8 Milyon | Homo erectus Afrika'dan çıkış |
                                | MÖ 1.5 Milyon | Ateşin kontrolü |
                                | MÖ 300.000 | Homo sapiens ortaya çıkışı |
                                | MÖ 100.000 | Sembolik davranışlar |
                                | MÖ 70.000 | Afrika'dan büyük göç |
                                | MÖ 45.000 | Avrupa'da modern insan |
                                | MÖ 40.000 | Mağara sanatı başlangıcı |
                                | MÖ 15.000 | Amerika'ya geçiş |
                                | MÖ 10.000 | Buzul Çağı sonu |
                                | MÖ 9600 | Göbeklitepe |
                                | MÖ 8000 | Tarımın başlangıcı |
                                | MÖ 7500 | Çatalhöyük |
                                | MÖ 6500 | Çömlekçilik yaygınlaşması |
                                | MÖ 5000 | Bakır Çağı |
                                | MÖ 3200 | Yazının icadı (Sümer) |

                                ---

                                ## 🎯 SONUÇ

                                Tarih öncesi çağlar, insanlığın "tarihe" geçişinin temellerini attı. Milyonlarca yıl süren bu dönemde:

                                - Basit taş aletlerden karmaşık teknolojilere geçildi
                                - Avcı-toplayıcı yaşamdan tarım toplumuna dönüşüldü
                                - Küçük göçer gruplardan yerleşik köy ve kasabalara geçildi
                                - Sanat, din ve sembolik düşünce doğdu
                                - İnsanlık tüm dünyaya yayıldı

                                Yazının icadıyla birlikte (MÖ 3200 civarı), "tarih öncesi" sona erdi ve kayıtlı tarih başladı. Ancak bu dönemin mirası, modern insanlığın genetiğinden kültürüne kadar her yerde yaşamaya devam ediyor.
                                """;
        }

        private String getAncientEgyptContent() {
                return """
                                ## 🏛️ ANTİK MISIR: NİL'İN HEDİYESİ

                                ### GİRİŞ
                                Antik Mısır, tarihin en uzun süren ve en etkileyici uygarlıklarından biridir. Yaklaşık 3000 yıl boyunca, firavunlar Nil Nehri'nin verimli vadisinde bir imparatorluk yönetti.

                                ---

                                ## 📅 MISIR TARİHİNİN DÖNEMLERİ

                                ### 1. Erken Hanedan Dönemi (MÖ 3150-2686)
                                - Kral Menes tarafından Yukarı ve Aşağı Mısır'ın birleştirilmesi
                                - Hiyeroglif yazısının gelişimi
                                - Memphis'in başkent oluşu

                                ### 2. Eski Krallık (MÖ 2686-2181) - "Piramit Çağı"
                                - Büyük Piramitler inşa edildi
                                - Merkezi idari sistem
                                - Firavun tanrı-kral olarak görüldü
                                - Sosyal piramit: Firavun, rahipler, bürokratlar, işçiler

                                ### 3. Birinci Ara Dönem (MÖ 2181-2055)
                                - Merkezi otoritenin çöküşü
                                - Bölgesel yöneticilerin (nomarch) güçlenmesi
                                - Kıtlık ve kaos

                                ### 4. Orta Krallık (MÖ 2055-1650)
                                - Yeniden birleşme
                                - Edebiyat ve sanatın altın çağı
                                - Nubya'ya genişleme
                                - Ticaret ağlarının büyümesi

                                ### 5. İkinci Ara Dönem (MÖ 1650-1550)
                                - Hyksos istilası
                                - At ve savaş arabasının tanıtılması
                                - Kuzeyde yabancı hakimiyet

                                ### 6. Yeni Krallık (MÖ 1550-1069) - "İmparatorluk Çağı"
                                - En güçlü dönem
                                - Suriye, Filistin, Nubya'ya genişleme
                                - Tutankhamun, Ramses II, Hatshepsut
                                - Lüksordaki tapınaklar
                                - Krallar Vadisi gömüleri

                                ### 7. Üçüncü Ara Dönem ve Geç Dönem (MÖ 1069-332)
                                - Libyalı ve Nubiyalı hanedanlar
                                - Asur ve Pers istilaları
                                - Kültürel devam ama siyasi zayıflama

                                ### 8. Ptolemaios Dönemi (MÖ 332-30)
                                - Büyük İskender'in fethi
                                - Yunan-Makedon yönetimi
                                - Kleopatra VII - Son firavun
                                - MÖ 30'da Roma'ya ilhak

                                ---

                                ## 🔺 PİRAMİTLER

                                ### Giza Piramitleri

                                **Keops Piramidi (Büyük Piramit)**
                                - Yükseklik: 146.6 metre (orijinal)
                                - İnşaat süresi: ~20 yıl
                                - 2.3 milyon taş blok
                                - Antik dünyanın yedi harikasından günümüze ulaşan tek yapı

                                **Kefren Piramidi**
                                - Biraz daha kısa ama daha yüksek bir platoda
                                - Üstündeki kaplama taşları kısmen korunmuş

                                **Mikerinos Piramidi**
                                - En küçük Giza piramidi
                                - Granit kaplaması

                                **Büyük Sfenks**
                                - 73 metre uzunluk, 20 metre yükseklik
                                - Aslan gövdesi, insan başı
                                - Muhtemelen Kefren'i temsil ediyor

                                ---

                                ## 📜 HİYEROGLİF YAZISI

                                **Özellikleri:**
                                - 700'den fazla işaret
                                - Resim yazısı + fonetik elemanlar
                                - Üç biçim: Hiyeroglif, hiyeratik, demotik

                                **Çözülmesi:**
                                - Rosetta Taşı (MÖ 196)
                                - Jean-François Champollion (1822)
                                - Yunanca, demotik ve hiyeroglif karşılaştırması

                                ---

                                ## ⚱️ MUMYALAMA VE AHİRET İNANCI

                                Mısırlılar, ölümden sonra yaşama inanıyordu. Mumyalama süreci:

                                1. Organların çıkarılması (beyin burundan, iç organlar gövdeden)
                                2. Natron tuzu ile kurutma (40 gün)
                                3. Yağ ve reçinelerle sarma
                                4. Keten bantlarla sarma
                                5. Tabut ve mezar odası

                                **Ölüler Kitabı:** Ahirette yolculuk rehberi

                                ---

                                ## 👑 ÜNLÜ FİRAVUNLAR

                                **Khufu (Keops):** Büyük Piramit'in sahibi
                                **Hatshepsut:** Kadın firavun, barışçıl yönetim
                                **Tutankhamun:** Altın maskesiyle ünlü çocuk kral
                                **Akhenaton:** Tek tanrılı Aten dinini kurdu
                                **Ramses II:** En uzun süre hüküm süren firavun, Mısır'ın en büyük yapıları

                                ---

                                ## 🌿 GÜNLÜK YAŞAM

                                **Toplum Katmanları:**
                                - Firavun ve kraliyet ailesi
                                - Rahipler ve soylular
                                - Yazıcılar ve zanaatkarlar
                                - Çiftçiler ve işçiler
                                - Köleler

                                **Beslenme:** Ekmek, bira, sebzeler, balık, et (zenginler için)
                                **Giyim:** Keten elbiseler, takılar, peruklar
                                **Eğlence:** Senet oyunu, müzik, dans, av

                                ---

                                ## SONUÇ

                                Antik Mısır, piramitleri, mumyaları ve hiyeroglifleriyle tarih boyunca insanlığı büyülemeye devam ediyor. 3000 yıllık bu uygarlık, modern dünyaya astronomi, tıp, mimari ve yönetim alanlarında kalıcı miraslar bıraktı.
                                """;
        }

        private String getAncientGreeceRomeContent() {
                return """
                                ## 🏛️ ANTİK YUNAN VE ROMA

                                ### GİRİŞ
                                Antik Yunan ve Roma uygarlıkları, Batı medeniyetinin temellerini attı. Demokrasi, felsefe, hukuk ve mimari bu dönemde zirveye ulaştı.

                                ---

                                ## ⚡ ANTİK YUNAN (MÖ 800 - MÖ 146)

                                ### Dönemler

                                **Arkaik Dönem (MÖ 800-480)**
                                - Şehir-devletlerin (polis) doğuşu
                                - Yunan alfabesinin gelişimi
                                - Olimpiyat Oyunları'nın başlangıcı (MÖ 776)
                                - Kolonizasyon hareketi

                                **Klasik Dönem (MÖ 480-323)**
                                - Altın Çağ
                                - Pers Savaşları zaferler
                                - Atina demokrasisi
                                - Parthenon'un inşası
                                - Büyük filozoflar: Sokrates, Platon, Aristoteles

                                **Helenistik Dönem (MÖ 323-146)**
                                - Büyük İskender'in fetihleri
                                - Yunan kültürünün Doğu'ya yayılması
                                - İskenderiye'nin yükselişi

                                ### Atina Demokrasisi
                                - Ekklesia (Halk Meclisi)
                                - Boule (500 kişilik konsey)
                                - Kura ile seçim
                                - Ostrakismos (sürgün oylaması)

                                ### Yunan Felsefesi
                                **Sokrates:** "Bildiğim tek şey, hiçbir şey bilmediğimdir"
                                **Platon:** İdealar teorisi, Devlet
                                **Aristoteles:** Mantık, bilim, etik

                                ### Yunan Sanatı
                                - Parthenon ve Akropolis
                                - İnsan vücudunun idealize tasviri
                                - Tragedya ve komedya: Aiskhylos, Sofokles, Euripides

                                ---

                                ## 🦅 ROMA İMPARATORLUĞU (MÖ 753 - MS 476)

                                ### Dönemler

                                **Krallık (MÖ 753-509)**
                                - Roma'nın efsanevi kuruluşu (Romulus ve Remus)
                                - Etrüsk etkisi

                                **Cumhuriyet (MÖ 509-27)**
                                - Senato yönetimi
                                - Kartaca Savaşları
                                - Akdeniz'e hakimiyet
                                - Jül Sezar ve iç savaşlar

                                **İmparatorluk (MÖ 27 - MS 476)**
                                - Augustus ile başlangıç
                                - Pax Romana (Roma Barışı)
                                - En geniş sınırlar: Britanya'dan Mezopotamya'ya
                                - Hristiyanlığın yükselişi
                                - 395'te Doğu-Batı bölünmesi
                                - 476'da Batı Roma'nın çöküşü

                                ### Roma Hukuku
                                - 12 Levha Kanunları
                                - Dünya hukukunun temeli
                                - "Suç ispat edilene kadar masumsun" ilkesi

                                ### Roma Mühendisliği
                                - Su kemerleri (Aquaeductus)
                                - Roma yolları (80.000 km taş döşeli)
                                - Kolezyum (50.000 seyirci)
                                - Pantheon (beton kubbe)

                                ### Roma Ordusu
                                - Lejyonlar (5.000 asker)
                                - Disiplin ve organizasyon
                                - Taktik üstünlük

                                ---

                                ## SONUÇ

                                Yunan demokrasisi, felsefesi ve sanatı; Roma hukuku, mühendisliği ve devlet yönetimi modern dünyanın temellerini oluşturur. Bu iki uygarlığın mirası binlerce yıl boyunca yaşamaya devam ediyor.
                                """;
        }

        private String getMedievalContent() {
                return """
                                ## ⚔️ ORTA ÇAĞ (MS 476 - 1453)

                                ### GİRİŞ
                                Orta Çağ, Roma İmparatorluğu'nun çöküşünden Konstantinopolis'in fethine kadar süren bin yıllık dönemdir. Avrupa'da feodalizm, İslam dünyasında Altın Çağ yaşandı.

                                ---

                                ## 🏰 FEODALİZM

                                ### Toplum Yapısı
                                - Kral/İmparator
                                - Derebeyler (Lord)
                                - Şövalyeler
                                - Serfler (Toprak köleleri)

                                ### Şövalyelik
                                - Zırhlı süvari savaşçılar
                                - Şeref yasası
                                - Haçlı Seferleri'nde önemli rol

                                ---

                                ## 🕌 İSLAM'IN ALTIN ÇAĞI (MS 750-1258)

                                ### Bilim ve Kültür
                                - Cebir (el-Harezmi)
                                - Tıp (İbn Sina)
                                - Optik (İbn el-Heysem)
                                - Felsefe (İbn Rüşd)

                                ### Mekanlar
                                - Bağdat (Beytül-Hikme)
                                - Kurtuba (Cordoba)
                                - Kahire

                                ---

                                ## ✝️ HAÇLI SEFERLERİ (1096-1291)

                                - 1. Haçlı Seferi: Kudüs'ün fethi (1099)
                                - Selahaddin Eyyubi'nin Kudüs'ü geri alması (1187)
                                - Toplam 8 büyük sefer
                                - Doğu-Batı kültür alışverişi

                                ---

                                ## 🏛️ BİZANS İMPARATORLUĞU

                                - Roma'nın Doğu kolu
                                - Konstantinopolis başkent
                                - Ortodoks Hristiyanlığın merkezi
                                - 1204: 4. Haçlı Seferi yağması
                                - 1453: Osmanlı fethi

                                ---

                                ## SONUÇ

                                Orta Çağ, genellikle "karanlık çağ" olarak adlandırılsa da, bu dönemde üniversiteler kuruldu, gotik katedraller inşa edildi ve İslam dünyasında bilim altın çağını yaşadı.
                                """;
        }

        private String getEarlyModernContent() {
                return """
                                ## 🎨 YENİ ÇAĞ VE RÖNESANS (1453-1789)

                                ### GİRİŞ
                                Rönesans ("yeniden doğuş"), Antik Yunan ve Roma kültürünün yeniden keşfedildiği, sanat, bilim ve düşüncenin devrimci bir şekilde değiştiği dönemdir.

                                ---

                                ## 🖼️ RÖNESANS SANATI

                                ### Büyük Ustalar
                                **Leonardo da Vinci:** Mona Lisa, Son Akşam Yemeği
                                **Michelangelo:** Sistine Şapeli, Davut heykeli
                                **Raphael:** Atina Okulu

                                ### Yenilikler
                                - Perspektif
                                - Anatomi bilgisi
                                - Yağlı boya tekniği
                                - Bireysellik vurgusu

                                ---

                                ## 🌍 COĞRAFİ KEŞİFLER

                                - 1492: Kolomb Amerika'ya ulaşır
                                - 1498: Vasco da Gama Hindistan'a
                                - 1519-1522: Magellan'ın dünya turu
                                - Sömürgecilik çağı

                                ---

                                ## 📚 MATBAAİNCİ DEVRİM

                                - Johannes Gutenberg (1440)
                                - Bilginin demokratikleşmesi
                                - Reform hareketini hızlandırdı
                                - Kitap fiyatlarında düşüş

                                ---

                                ## ✝️ REFORM HAREKETİ

                                - Martin Luther'un 95 Tezi (1517)
                                - Protestanlığın doğuşu
                                - Din Savaşları
                                - Karşı-Reform

                                ---

                                ## 🔬 BİLİMSEL DEVRİM

                                **Kopernik:** Güneş merkezli evren
                                **Galileo:** Teleskop gözlemleri
                                **Newton:** Hareket yasaları, kütleçekim
                                **Kepler:** Gezegen yörüngeleri

                                ---

                                ## SONUÇ

                                Rönesans ve Yeni Çağ, modern dünyanın temellerini attı. Sanat, bilim, keşif ve düşünce alanlarındaki devrimler, Aydınlanma Çağı'na zemin hazırladı.
                                """;
        }

        private String getIndustrialContent() {
                return """
                                ## 🏭 SANAYİ DEVRİMİ (1760-1900)

                                ### GİRİŞ
                                Sanayi Devrimi, insan tarihinin en büyük dönüşümlerinden biridir. Tarım toplumundan sanayi toplumuna geçiş, modern dünyanın temellerini attı.

                                ---

                                ## ⚙️ TEKNOLOJİK YENİLİKLER

                                ### Buhar Makinesi
                                - James Watt (1769)
                                - Fabrikaların güç kaynağı
                                - Ulaşımda devrim

                                ### Tekstil Makineleri
                                - Spinning Jenny
                                - Su çerçevesi
                                - Pamuk işlemede patlama

                                ### Demir ve Çelik
                                - Kok kömürü kullanımı
                                - Bessemer prosesi
                                - Köprüler, demiryolları

                                ---

                                ## 🚂 ULAŞIM DEVRİMİ

                                - 1804: İlk buharlı lokomotif
                                - 1825: İlk yolcu treni (Stockton-Darlington)
                                - 1869: Transatlantik kablo
                                - 1869: Süveyş Kanalı

                                ---

                                ## 🏙️ KENTLEŞME

                                - Kırdan kente göç
                                - Fabrika kentleri (Manchester, Birmingham)
                                - Konut sıkıntısı
                                - Halk sağlığı sorunları

                                ---

                                ## 👷 TOPLUMSAL DEĞİŞİM

                                ### İşçi Sınıfı
                                - Uzun çalışma saatleri
                                - Çocuk işçiliği
                                - Sendikalaşma hareketleri
                                - Sosyalizmin doğuşu

                                ### Orta Sınıfın Yükselişi
                                - Fabrika sahipleri
                                - Profesyoneller
                                - Tüketim kültürü

                                ---

                                ## 🌍 YAYILMASI

                                - İngiltere'den Avrupa'ya
                                - ABD'de hızlı gelişme
                                - Japonya Meiji Restorasyonu
                                - Küresel ticaret ağları

                                ---

                                ## SONUÇ

                                Sanayi Devrimi, insanlığın üretim kapasitesini katlanarak artırdı. Ancak beraberinde çevre sorunları, toplumsal eşitsizlik ve emperyal genişleme de getirdi. Bu dönemin mirası, günümüz dünyasını şekillendirmeye devam ediyor.
                                """;
        }

        // ========== ERA SINIFI ==========

        public static class Era {
                public String id;
                public String title;
                public String period;
                public String description;
                public String imageUrl;
                public String detailedContent;

                public Era(String id, String title, String period, String description, String imageUrl) {
                        this.id = id;
                        this.title = title;
                        this.period = period;
                        this.description = description;
                        this.imageUrl = imageUrl;
                }

                public void setDetailedContent(String content) {
                        this.detailedContent = content;
                }

                public String getDetailedContent() {
                        return detailedContent;
                }

                // Getters for Thymeleaf
                public String getId() {
                        return id;
                }

                public String getTitle() {
                        return title;
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
        }
}
