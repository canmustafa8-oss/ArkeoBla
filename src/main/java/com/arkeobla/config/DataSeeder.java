package com.arkeobla.config;

import com.arkeobla.model.Content;
import com.arkeobla.model.Role;
import com.arkeobla.model.User;
import com.arkeobla.repository.ContentRepository;
import com.arkeobla.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ContentRepository contentRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Admin User
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@arkeobla.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                admin.setEnabled(true);
                admin.setFirstName("Arkeo");
                admin.setLastName("Admin");
                admin.setTotalScore(1000);
                admin.setBadges("KURUCU,BİLGE");
                userRepository.save(admin);
            }

            User admin = userRepository.findByUsername("admin").get();

            // Content Seeding (12 High Quality Turkish Archaeology Articles)
            if (contentRepository.count() == 0) {
                createContent(contentRepository, admin,
                        "Göbeklitepe: Tarihin Sıfır Noktası",
                        "Şanlıurfa yakınlarındaki Göbeklitepe, MÖ 9600 yıllarına tarihlenen ve dünyanın bilinen en eski tapınak kompleksidir. Neolitik dönemde avcı-toplayıcı topluluklar tarafından inşa edilen bu yapı, yerleşik hayata geçiş ve dinler tarihi hakkındaki teorileri kökünden değiştirmiştir. T biçimindeki devasa sütunlar üzerindeki hayvan kabartmaları (akrep, tilki, yılan vb.) o dönemin sanat anlayışını ve inanç dünyasını yansıtır. UNESCO Dünya Mirası Listesi'nde yer almaktadır.",
                        "Neolitik Çağ", "MÖ 9600", "Arkeolojik Alan",
                        "https://upload.wikimedia.org/wikipedia/commons/1/10/G%C3%B6bekli_Tepe%2C_Urfa.jpg");

                createContent(contentRepository, admin,
                        "Efes Antik Kenti ve Artemis Tapınağı",
                        "İzmir'in Selçuk ilçesinde bulunan Efes, antik dünyanın en önemli metropollerinden biriydi. Hem liman kenti olması hem de Hac merkezi olması nedeniyle Roma döneminde 250.000 nüfusa ulaşmıştır. Celsus Kütüphanesi, Büyük Tiyatro ve Yamaç Evler en dikkat çekici yapılardır. Ayrıca Dünyanın Yedi Harikası'ndan biri olan Artemis Tapınağı da buradaydı, ancak günümüze sadece tek bir sütunu kalmıştır.",
                        "Roma İmparatorluğu", "MÖ 6000 - MS 15. yy", "Antik Kent",
                        "https://upload.wikimedia.org/wikipedia/commons/8/87/Celsus_Library_Ephesus_Turkey.jpg");

                createContent(contentRepository, admin,
                        "Hattuşaş: Bin Tanrılı Şehir",
                        "Çorum'un Boğazkale ilçesinde bulunan Hattuşaş, Hitit İmparatorluğu'nun başkentiydi. MÖ 17. yüzyıldan itibaren Anadolu'ya hükmeden Hititlerin yönetim merkezi olan kent, anıtsal kapıları (Aslanlı Kapı, Kral Kapısı, Sfenksli Kapı) ve 6 kilometreyi bulan surlarıyla ünlüdür. Kadeş Antlaşması'nın kil tabletleri burada bulunmuştur. UNESCO koruması altındadır.",
                        "Tunç Çağı", "MÖ 1650", "Başkent",
                        "https://upload.wikimedia.org/wikipedia/commons/9/90/Lion_Gate_Hattusa.jpg");

                createContent(contentRepository, admin,
                        "Çatalhöyük: İlk Şehir Deneyimi",
                        "Konya Ovası'nda yer alan Çatalhöyük, MÖ 7400 yıllarına tarihlenen dünyanın en iyi korunmuş Neolitik yerleşimlerinden biridir. Sokakların olmadığı, evlere çatılardan girilen bu yerleşimde, duvar resimleri ve boğa başı rölyefleri dikkat çeker. Ana Tanrıça kültünün izleri ve ilk manzara resmi olduğu düşünülen duvar çizimleri burada bulunmuştur.",
                        "Neolitik Çağ", "MÖ 7400", "Höyük",
                        "https://upload.wikimedia.org/wikipedia/commons/a/a2/Catalh%C3%BCy%C3%BCk_restauriertes_Haus_innen.jpg");

                createContent(contentRepository, admin,
                        "Nemrut Dağı: Tanrıların Tahtı",
                        "Adıyaman'ın Kahta ilçesindeki Nemrut Dağı zirvesinde (2150m), Kommagene Kralı I. Antiochos'un kendisine yaptırdığı anıt mezar ve devasa heykeller bulunur. MÖ 1. yüzyıla ait bu heykeller (Zeus, Apollon, Herakles) hem Yunan hem Pers sanatının izlerini taşır. Doğu ve Batı teraslarında güneşin doğuşu ve batışı eşsiz bir manzara sunar.",
                        "Helenistik Dönem", "MÖ 62", "Anıt Mezar",
                        "https://upload.wikimedia.org/wikipedia/commons/5/5a/Nemrut.jpg");

                createContent(contentRepository, admin,
                        "Zeugma ve Çingene Kızı",
                        "Gaziantep'in Nizip ilçesinde Fırat Nehri kıyısında kurulan Zeugma, Roma döneminin zengin villaları ve taban mozaikleriyle ünlüdür. Dünyaca ünlü 'Çingene Kızı' mozaiği burada bulunmuştur. Savaş Tanrısı Mars'ın bronz heykeli ve villaların zeminlerini süsleyen mitolojik sahneler, dönemin sanat anlayışının zirvesini temsil eder.",
                        "Roma Dönemi", "MÖ 300", "Mozaik Kenti",
                        "https://upload.wikimedia.org/wikipedia/commons/0/01/Zeugma_Mosaic_Museum_Gipsy_Girl.jpg");

                createContent(contentRepository, admin,
                        "Troya: Efsanelerin Kenti",
                        "Çanakkale'de bulunan Troya, Homeros'un İlyada destanına konu olan Troya Savaşı'nın geçtiği yerdir. 9 farklı katmandan oluşan şehir, 3000 yıllık bir tarihi kesintisiz sunar. Heinrich Schliemann tarafından 19. yüzyılda yapılan kazılarda 'Priam'ın Hazinesi' denen altın eserler bulunmuştur. Efsanevi Tahta At'ın hikayesi tüm dünyada bilinir.",
                        "Tunç Çağı", "MÖ 3000", "Efsanevi Kent",
                        "https://upload.wikimedia.org/wikipedia/commons/a/a0/Troy_walls.jpg");

                createContent(contentRepository, admin,
                        "Patara: Likya'nın Başkenti",
                        "Antalya'nın Kaş ilçesinde bulunan Patara, Likya Birliği'nin başkentiydi. Dünyanın ilk demokratik meclis binası (Bouleuterion) burada restore edilmiştir. Ayrıca Noel Baba olarak bilinen Aziz Nikolaos'un doğum yeridir. 12 km'lik kumsalı ve antik deniz feneri ile hem doğal hem tarihi bir hazinedir.",
                        "Antik Çağ", "MÖ 8. yy", "Başkent",
                        "https://upload.wikimedia.org/wikipedia/commons/1/1e/Patara_Arch_of_Mettius_Modestus.jpg");

                createContent(contentRepository, admin,
                        "Afrodisias: Mermerin Merkezi",
                        "Aydın'ın Karacasu ilçesindeki Afrodisias, aşk ve güzellik tanrıçası Afrodit'e adanmıştır. Antik dünyanın en ünlü heykeltıraşlık okulu buradaydı. 30.000 kişilik stadyumu, antik dünyanın en iyi korunmuş stadyumlarından biridir. Tetrapylon kapısı ve Afrodit Tapınağı görülmeye değerdir.",
                        "Roma Dönemi", "MÖ 5. yy", "Sanat Merkezi",
                        "https://upload.wikimedia.org/wikipedia/commons/1/12/Tetrapylon_Aphrodisias_Turkey.jpg");

                createContent(contentRepository, admin,
                        "Myra ve Kaya Mezarları",
                        "Antalya'nın Demre ilçesindeki Myra, Likya uygarlığının en önemli 6 şehrinden biriydi. Dik kayalara oyulmuş ev tipi mezarları ve hemen yanındaki Roma tiyatrosu ile çarpıcı bir görünüme sahiptir. Aziz Nikolaos (Noel Baba) burada piskoposluk yapmış ve burada ölmüştür.",
                        "Klasik Dönem", "MÖ 5. yy", "Nekropol",
                        "https://upload.wikimedia.org/wikipedia/commons/d/d4/Myra_rock_graves.jpg");

                createContent(contentRepository, admin,
                        "Gordion ve Midas'ın Kulakları",
                        "Ankara Polatlı'da bulunan Gordion, Frigya Krallığı'nın başkentidir. Efsanevi Kral Midas'ın Büyük Tümülüsü (mezar tepesi) burada yer alır. Midas'ın dokunduğu her şeyi altına çevirmesi ve eşek kulaklı olması efsaneleri burayla özdeşleşmiştir. Dünyanın en eski ahşap mezar odası buradadır (MÖ 740).",
                        "Demir Çağı", "MÖ 12. yy", "Frigya Başkenti",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6f/Midas_Tomb_Gordion.jpg");

                createContent(contentRepository, admin,
                        "Perge: Sütunlu Cadde",
                        "Antalya'nın 18 km doğusunda, Pamphylia bölgesinin başkenti. Aziz Paul'ün ziyaret ettiği kutsal bir şehirdir. Kestros Çeşmesi, Helenistik kuleler ve kilometrelerce uzanan sütunlu caddeleriyle Roma şehir planlamasının en güzel örneğidir.",
                        "Helenistik/Roma", "MÖ 1200", "Antik Kent",
                        "https://upload.wikimedia.org/wikipedia/commons/1/15/Perge_Antalya.jpg");
            }
        };
    }

    private void createContent(ContentRepository repo, User author, String title, String body, String era, String date,
            String category, String img) {
        Content c = new Content();
        c.setTitle(title);
        c.setBody(body);
        c.setSummary(body.length() > 150 ? body.substring(0, 147) + "..." : body);
        c.setEra(era);
        c.setHistoricalDate(date);
        c.setCategory(category);
        c.setImageUrl(img);
        c.setAuthor(author);
        c.setCreatedAt(java.time.LocalDateTime.now());
        repo.save(c);
    }
}
