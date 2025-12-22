package com.arkeobla.config;

import com.arkeobla.model.Role;
import com.arkeobla.model.User;
import com.arkeobla.repository.UserRepository;
import com.arkeobla.service.MapService;
import com.arkeobla.model.MapLocation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, MapService mapService) {
        return args -> {
            // 1. Admin Kullanıcısı
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin123");
                admin.setRole(Role.ADMIN);
                admin.setBadges("KURUCU,YÖNETİCİ");
                userRepository.save(admin);
                System.out.println(">>> Varsayılan Admin kullanıcısı oluşturuldu: admin / admin123");
            }

            // 2. Harita Verileri (Eğer boşsa doldur)
            if (mapService.getAllLocations().isEmpty()) {
                // TÜRKİYE
                mapService.saveLocation(new MapLocation("Göbeklitepe", "Tarihin Sıfır Noktası. Şanlıurfa.", 37.223056,
                        38.9225,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/G%C3%B6bekli_Tepe%2C_Urfa.jpg/300px-G%C3%B6bekli_Tepe%2C_Urfa.jpg"));
                mapService.saveLocation(new MapLocation("Efes Antik Kenti",
                        "İyonya'nın başkenti, Artemis Tapınağı. İzmir.", 37.9422, 27.3631,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Ephesus_Celsus_Library.jpg/300px-Ephesus_Celsus_Library.jpg"));
                mapService.saveLocation(new MapLocation("Troya", "Homeros'un İlyada'sındaki efsanevi şehir. Çanakkale.",
                        39.9575, 26.2389,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Trojan_Horse_Replica.jpg/300px-Trojan_Horse_Replica.jpg"));
                mapService.saveLocation(new MapLocation("Hattuşa", "Hitit İmparatorluğu'nun başkenti. Çorum.", 40.0197,
                        34.6152,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Lion_Gate_Hattusa.jpg/300px-Lion_Gate_Hattusa.jpg"));
                mapService.saveLocation(new MapLocation("Nemrut Dağı", "Tanrılarla kralların buluştuğu yer. Adıyaman.",
                        37.9806, 38.7408,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cf/Heads_of_statues_at_Mount_Nemrut.jpg/300px-Heads_of_statues_at_Mount_Nemrut.jpg"));
                mapService.saveLocation(new MapLocation("Çatalhöyük", "En eski yerleşim yerlerinden biri. Konya.",
                        37.6664, 32.8225,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Catal_H%C3%BCy%C3%BCk_restoration.jpg/300px-Catal_H%C3%BCy%C3%BCk_restoration.jpg"));
                mapService.saveLocation(new MapLocation("Aspendos", "En iyi korunmuş antik tiyatro. Antalya.", 36.9392,
                        31.1717,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Aspendos_Amphitheatre.jpg/300px-Aspendos_Amphitheatre.jpg"));
                mapService.saveLocation(new MapLocation("Pergamon", "Parşömen kağıdının doğduğu yer. Bergama, İzmir.",
                        39.1325, 27.1843,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Pergamon_Theater_2017.jpg/300px-Pergamon_Theater_2017.jpg"));

                // DÜNYA
                mapService.saveLocation(new MapLocation("Giza Piramitleri",
                        "Antik dünyanın 7 harikasından biri. Kahire, Mısır.", 29.9792, 31.1342,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/All_Gizah_Pyramids.jpg/300px-All_Gizah_Pyramids.jpg"));
                mapService.saveLocation(new MapLocation("Stonehenge", "Gizemli neolitik taş çemberi. İngiltere.",
                        51.1788, -1.8262,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Stonehenge2007_07_30.jpg/300px-Stonehenge2007_07_30.jpg"));
                mapService.saveLocation(new MapLocation("Machu Picchu", "İnka'ların kayıp şehri. Peru.", -13.1631,
                        -72.5450,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Machu_Picchu%2C_Peru.jpg/300px-Machu_Picchu%2C_Peru.jpg"));
                mapService.saveLocation(new MapLocation("Petra", "Gül kırmızısı şehir. Ürdün.", 30.3285, 35.4444,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Treasury_petra_crop.jpeg/300px-Treasury_petra_crop.jpeg"));
                mapService.saveLocation(new MapLocation("Kolezyum", "Gladyatörlerin arenası. Roma, İtalya.", 41.8902,
                        12.4922,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Colosseo_2020.jpg/300px-Colosseo_2020.jpg"));
                mapService.saveLocation(new MapLocation("Akropolis", "Atina'nın kalbi. Yunanistan.", 37.9715, 23.7257,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Acropolis_from_Philopappos_Hill.jpg/300px-Acropolis_from_Philopappos_Hill.jpg"));
                mapService.saveLocation(new MapLocation("Çin Seddi", "Dünyanın en uzun savunma duvarı. Çin.", 40.4319,
                        116.5704,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/The_Great_Wall_of_China_at_Jinshanling-edit.jpg/300px-The_Great_Wall_of_China_at_Jinshanling-edit.jpg"));

                // MAĞARALAR VE KAZI ALANLARI
                mapService.saveLocation(new MapLocation("İnönü Mağarası",
                        "Zonguldak/Ereğli. Karadeniz'in tarih öncesi hafızası (MÖ 4500).", 41.2500, 31.4500,
                        "https://im.haberturk.com/2019/08/19/ver1566213751/2513904_810x458.jpg"));
                mapService.saveLocation(new MapLocation("Lascaux Mağarası",
                        "Paleolitik duvar resimleriyle ünlü 'Tarihöncesi Sistine Şapeli'. Fransa.", 45.0536, 1.1708,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Lascaux_painting.jpg/300px-Lascaux_painting.jpg"));
                mapService.saveLocation(new MapLocation("Altamira Mağarası",
                        "Üst Paleolitik döneme ait polikrom çizimler. İspanya.", 43.3775, -4.1200,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Bison_Altamira.jpg/300px-Bison_Altamira.jpg"));
                mapService.saveLocation(new MapLocation("Chauvet Mağarası",
                        "Dünyanın en eski ve en iyi korunmuş mağara resimleri. Fransa.", 44.3876, 4.4141,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Chauvet%2C_chevaux.jpg/300px-Chauvet%2C_chevaux.jpg"));
                mapService.saveLocation(new MapLocation("Karain Mağarası",
                        "Anadolu'nın en eski insan yerleşimlerinden biri. Antalya.", 37.0778, 30.5706,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Karain_Cave_entrance.jpg/300px-Karain_Cave_entrance.jpg"));
                mapService.saveLocation(new MapLocation("Yarımburgaz Mağarası",
                        "İstanbul'un en eski yerleşim yeri. Başakşehir.", 41.0764, 28.7397,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Yarimburgaz_Cave_interior.jpg/300px-Yarimburgaz_Cave_interior.jpg"));
                mapService.saveLocation(new MapLocation("Cueva de las Manos", "Eller Mağarası. MÖ 7300. Arjantin.",
                        -47.1575, -70.6558,
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Santa_Cruz_Cueva_Manos_P2140069b.jpg/300px-Santa_Cruz_Cueva_Manos_P2140069b.jpg"));

                System.out.println(">>> Genişletilmiş Harita Verileri (TR & Dünya + Mağaralar) eklendi.");
            }
        };
    }
}
