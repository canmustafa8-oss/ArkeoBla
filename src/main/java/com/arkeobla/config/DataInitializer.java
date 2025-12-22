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
        public CommandLineRunner initData(UserRepository userRepository, MapService mapService,
                        com.arkeobla.repository.ContentRepository contentRepository) {
                return args -> {
                        // 1. Admin Kullanıcısı (Mcquelss)
                        com.arkeobla.model.User admin = userRepository.findByUsername("Mcquelss").orElse(null);
                        if (admin == null) {
                                admin = new User();
                                admin.setUsername("Mcquelss");
                                admin.setPassword("Mcan1346.");
                                admin.setFirstName("Mustafa Can");
                                admin.setLastName("Yılmaz");
                                admin.setBirthDate(java.time.LocalDate.of(1990, 1, 1));
                                admin.setRole(Role.ADMIN);
                                admin.setBadges("KURUCU,YÖNETİCİ");
                                admin.setEnabled(true);
                                userRepository.save(admin);
                                System.out.println(">>> Varsayılan Admin kullanıcısı oluşturuldu: Mcquelss");
                        } else {
                                // Mevcut admini güncelle
                                admin.setRole(Role.ADMIN);
                                if (admin.getFirstName() == null)
                                        admin.setFirstName("Mustafa Can");
                                if (admin.getLastName() == null)
                                        admin.setLastName("Yılmaz");
                                if (admin.getBirthDate() == null)
                                        admin.setBirthDate(java.time.LocalDate.of(1990, 1, 1));
                                admin.setEnabled(true);
                                userRepository.save(admin);
                                System.out.println(">>> Admin yetkileri güncellendi: Mcquelss");
                        }

                        // 2. Harita Verileri (Procedural Generator - 500+ Nokta)
                        if (mapService.getAllLocations().size() < 20) {
                                System.out.println(">>> Harita Verileri Oluşturuluyor (500+ Nokta)...");

                                // Önce Sabit Gerçek Veriler (Kaliteyi korumak için)
                                mapService.saveLocation(new MapLocation("Göbeklitepe",
                                                "Tarihin Sıfır Noktası. Şanlıurfa. Dünyanın en eski tapınağı.",
                                                37.223056, 38.9225,
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/G%C3%B6bekli_Tepe%2C_Urfa.jpg/300px-G%C3%B6bekli_Tepe%2C_Urfa.jpg"));
                                mapService.saveLocation(new MapLocation("Efes Antik Kenti",
                                                "İyonya'nın başkenti, Artemis Tapınağı. İzmir.", 37.9422, 27.3631,
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Ephesus_Celsus_Library.jpg/300px-Ephesus_Celsus_Library.jpg"));
                                mapService.saveLocation(new MapLocation("Giza Piramitleri",
                                                "Antik dünyanın 7 harikasından biri. Kahire, Mısır.", 29.9792, 31.1342,
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/All_Gizah_Pyramids.jpg/300px-All_Gizah_Pyramids.jpg"));
                                // ... Diğer ana noktalar eklenebilir veya procedural yapıya bırakılabilir

                                // PALEOLİTİK & NEOLİTİK (TAŞ DEVRİ)
                                mapService.saveLocation(new MapLocation(
                                                "Ateşin Kontrolü",
                                                "İnsanlık tarihinin en büyük devrimi: Ateşin evcilleştirilmesi.\n\n" +
                                                                "İnsanlık tarihindeki belki de en kritik dönüm noktası, ateşin bilinçli olarak kontrol altına alınmasıdır. Yaklaşık 700.000 ila 1 milyon yıl önce, Homo erectus atalarımız, doğada yıldırım düşmesi gibi olaylarla oluşan ateşi sadece kullanmakla kalmayıp, onu taşımayı ve yeniden yakmayı öğrendiler.\n\n"
                                                                +
                                                                "Ateşin kontrolü, insanın hayatta kalma mücadelesinde devrim yarattı. İlk olarak, yırtıcı hayvanlara karşı etkili bir koruma kalkanı sağladı; gecenin karanlığında parlayan bir ateş, en vahşi hayvanları bile uzak tutmaya yetiyordu. İkinci olarak, ısınma ihtiyacını karşılayarak insanların daha soğuk iklimlere göç etmesine ve oralarda yerleşmesine olanak tanıdı.\n\n"
                                                                +
                                                                "Ancak en önemli etkisi beslenme üzerindedir. Ateş, besinleri pişirerek tüketmeyi mümkün kıldı. Pişmiş et ve bitkiler, sindirimi çok daha kolay hale getirdi ve vücudun harcadığı sindirim enerjisini azalttı. Bu enerji tasarrufu, insan beyninin büyümesine ve gelişmesine doğrudan katkıda bulundu. Ayrıca, ateş başında toplanan topluluklar, hikayeler anlatarak ve deneyimlerini paylaşarak dilin ve sosyal yapıların gelişmesini sağladı.",
                                                34.0, 35.0, // Placeholder coordinates, as actual coordinates are not
                                                            // provided in the snippet
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Gesher_Benot_Ya%27aqov_-_reconstruction_of_the_780%2C000_year_old_site.jpg/800px-Gesher_Benot_Ya%27aqov_-_reconstruction_of_the_780%2C000_year_old_site.jpg"));
                                mapService.saveLocation(new MapLocation(
                                                "Mağara Sanatı: Lascaux",
                                                "İnsanın ilk sanatsal ifadeleri ve büyüleyici duvar resimleri.\n\n" +
                                                                "Güneybatı Fransa'da bulunan Lascaux Mağarası, Üst Paleolitik döneme ait en etkileyici ve karmaşık mağara resimlerine ev sahipliği yapmasıyla 'Tarihöncesinin Sistine Şapeli' olarak anılır. MÖ 17.000 civarına tarihlenen bu mağarada, yaklaşık 600 duvar resmi ve 1500'den fazla kazıma figür bulunur.\n\n"
                                                                +
                                                                "Mağaranın duvarlarını süsleyen devasa boğalar, atlar, geyikler ve bizonlar, o dönem insanının sadece sanatsal yeteneğini değil, aynı zamanda doğayla kurduğu derin bağı da gösterir. Resimlerde kullanılan boyalar, demir oksit (kırmızı), manganez (siyah) ve diğer doğal minerallerden elde edilmiştir. Sanatçıların perspektif tekniklerini kullanması ve hayvanların hareketlerini bu denli canlı tasvir etmesi, modern sanatçıları bile hayrete düşürmektedir.\n\n"
                                                                +
                                                                "Antropologlar, bu resimlerin sadece estetik amaçlı yapılmadığını savunuyor. Mağaranın derin ve karanlık galerilerinde bulunan bu çizimler, muhtemelen av büyüsü, erginlenme törenleri veya şamanik ritüeller için kullanılıyordu. Lascaux, insanın soyut düşünme yeteneğinin ve sembolik dünyasının ne kadar eskiye dayandığının en somut kanıtıdır.",
                                                45.0, 1.0, // Placeholder coordinates
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Lascaux_painting.jpg/800px-Lascaux_painting.jpg"));
                                mapService.saveLocation(new MapLocation(
                                                "Tarıma Geçiş (Neolitik Devrim)",
                                                "Avcı-toplayıcılıktan yerleşik hayata geçişin hikayesi.\n\n" +
                                                                "Yaklaşık 12.000 yıl önce, Bereketli Hilal olarak bilinen Mezopotamya ve Anadolu topraklarında insanlık tarihinin en köklü değişimi başladı: Neolitik Devrim. Binlerce yıl boyunca avcı-toplayıcı olarak yaşayan insanlar, buğday ve arpa gibi yabani tahılları evcilleştirmeyi ve koyun, keçi gibi hayvanları beslemeyi öğrendiler.\n\n"
                                                                +
                                                                "Tarımın başlaması, yerleşik hayata geçişi zorunlu kıldı. İnsanlar artık mevsimlik kamplarda değil, kalıcı köylerde yaşamaya başladılar. Çatalhöyük ve Göbeklitepe gibi yerleşimler, bu dönemin en çarpıcı örnekleridir. Yerleşik hayat, nüfusun hızla artmasına, gıda fazlasının (artı ürün) depolanmasına ve toplumsal iş bölümünün ortaya çıkmasına neden oldu.\n\n"
                                                                +
                                                                "Ancak bu devrimin bedelleri de vardı. Tek tip beslenme ve kalabalık yaşam, salgın hastalıkların artmasına yol açtı. Ayrıca 'mülkiyet' kavramının doğuşu, insanlar ve topluluklar arasındaki ilk savaşların da fitilini ateşledi. Tarım Devrimi, modern medeniyetin temelini atan, geri dönüşü olmayan bir adımdı.",
                                                37.0, 35.0, // Placeholder coordinates
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Fertile_Crescent_map.png/800px-Fertile_Crescent_map.png"));

                                // ANTİK MISIR
                                mapService.saveLocation(new MapLocation(
                                                "Büyük Giza Piramidi",
                                                "Firavun Khufu'nun ölümsüzlük anıtı ve antik dünyanın harikası.\n\n" +
                                                                "Kahire yakınlarındaki Giza platosunda yükselen Büyük Piramit, Antik Dünyanın Yedi Harikası'ndan günümüze ulaşan tek yapıdır. 4. Hanedan firavunu Khufu (Keops) için MÖ 2560 civarında inşa edilen bu devasa anıt, yaklaşık 146 metre yüksekliğiyle 3800 yıl boyunca dünyanın en yüksek insan yapısı olma unvanını korumuştur.\n\n"
                                                                +
                                                                "Piramidin inşası, bugün bile mühendislik, matematik ve lojistik açısından bir mucize olarak kabul edilir. Yapıda yaklaşık 2.3 milyon kireçtaşı ve granit blok kullanılmıştır ve bu blokların her biri ortalama 2.5 ton ağırlığındadır. Bazı granit bloklar ise 80 tona kadar çıkmaktadır ve 800 km uzaklıktaki Asvan'dan getirilmiştir.\n\n"
                                                                +
                                                                "Uzun yıllar boyunca piramitlerin köleler tarafından yapıldığı düşünülse de, son arkeolojik bulgular bu tezi çürütmüştür. Piramitlerin yakınında bulunan işçi köyleri, mezarlar ve beslenme kayıtları, inşaatta çalışanların maaşlı, iyi beslenen ve saygı gören Mısırlı zanaatkarlar ve işçiler olduğunu göstermektedir. Bu yapı, sadece bir mezar değil, Mısır devletinin gücünü ve organizasyon yeteneğini simgeleyen bir projedir.",
                                                29.9792, 31.1342,
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Kheops-Pyramid.jpg/800px-Kheops-Pyramid.jpg"));
                                mapService.saveLocation(new MapLocation(
                                                "Hiyerogliflerin Çözülmesi",
                                                "Rosetta Taşı sayesinde Mısır'ın sessiz dili yeniden konuştu.\n\n" +
                                                                "Antik Mısır medeniyeti çöktükten sonra, hiyeroglif yazısının bilgisi tamamen kaybolmuştu. Tapınak duvarlarındaki yazılar yüzyıllar boyunca sadece 'büyülü resimler' olarak görüldü. Ta ki 1799 yılında, Napolyon'un Mısır seferi sırasında Fransız askerleri Reşid (Rosetta) kasabasında siyah bazalt bir taş parçası bulana kadar.\n\n"
                                                                +
                                                                "Rosetta Taşı'nın üzerinde aynı metin üç farklı yazıyla kazınmıştı: En üstte Hiyeroglif (rahiplerin dili), ortada Demotik (halkın dili) ve en altta Antik Yunanca (yönetici sınıfın dili). Yunanca bilinen bir dil olduğu için, bu taş Mısır yazısının şifresini kırmak için mükemmel bir anahtardı.\n\n"
                                                                +
                                                                "Fransız dilbilimci Jean-François Champollion, yıllar süren hummalı bir çalışmanın ardından 1822'de şifreyi çözmeyi başardı. Champollion, hiyerogliflerin sadece sembolik değil, aynı zamanda fonetik (ses değeri olan) karakterler olduğunu keşfetti. Bu keşif, Mısır tarihinin kapılarını sonuna kadar açtı ve binlerce yıllık sessizliği bozarak firavunların, şairlerin ve katiplerin sesini günümüze taşıdı.",
                                                31.4000, 30.4167, // Placeholder coordinates for Rosetta
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Rosetta_Stone.JPG/800px-Rosetta_Stone.JPG"));
                                mapService.saveLocation(new MapLocation(
                                                "Tutankhamun'un Hazineleri",
                                                "Howard Carter'ın 1922'de keşfettiği el değmemiş mezar.\n\n" +
                                                                "4 Kasım 1922'de İngiliz arkeolog Howard Carter, Krallar Vadisi'nde o güne kadar fark edilmemiş bir basamak buldu. Bu, 3000 yıldır kimsenin girmediği, çocuk firavun Tutankhamun'un mezarıydı. Mezarın mührü bozulmamıştı, yani antik çağın mezar hırsızları burayı teğet geçmişti.\n\n"
                                                                +
                                                                "Carter mezar odasına küçük bir delik açıp içeriye mum ışığıyla baktığında, gördüğü manzara karşısında büyülenmişti. Lord Carnarvon'un 'Bir şey görüyor musun?' sorusuna verdiği cevap efsaneleşmiştir: 'Evet, harika şeyler!' İçeride altın kaplama tahtlar, savaş arabaları, heykeller ve mücevherlerle dolu bir hazine odası vardı.\n\n"
                                                                +
                                                                "En büyük keşif ise lahit odasındaydı. İç içe geçmiş üç tabutun en içindekisi saf altındandı ve firavunun mumyası, bugün Mısır'ın sembolü haline gelen 11 kilogramlık ikonik altın ölüm maskesiyle kaplıydı. Tutankhamun aslında önemsiz bir firavundu ve genç yaşta ölmüştü; ancak mezarının bozulmadan bulunması onu modern dünyada en ünlü firavun yaptı.",
                                                25.7400, 32.6000, // Placeholder coordinates for Valley of the Kings
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Tutanchamun_Maske.jpg/800px-Tutanchamun_Maske.jpg"));

                                // ANTİK YUNAN & ROMA
                                mapService.saveLocation(new MapLocation(
                                                "Demokrasinin Doğuşu",
                                                "Atina'da Kleisthenes reformları ve halkın yönetimi.\n\n" +
                                                                "MÖ 5. yüzyılda Atina'da, dünya siyaset tarihini kökten değiştirecek bir deney başladı: Demokrasi (Demos: Halk, Kratos: İktidar). Atinalı devlet adamı Kleisthenes'in reformlarıyla başlayan bu süreç, kararların krallar veya tiranlar tarafından değil, vatandaşlar meclisi (Ekklesia) tarafından alınmasını sağlıyordu.\n\n"
                                                                +
                                                                "Atina demokrasisi, bugünkü temsili demokrasilerden farklı olarak 'doğrudan demokrasi' idi. Oy hakkı olan vatandaşlar (sadece özgür, Atina doğumlu erkekler) Agora'da veya Pnyx tepesinde toplanır, yasaları tartışır ve el kaldırarak oylardı. Mahkemelerde jüriler yine kura ile seçilen vatandaşlardan oluşurdu.\n\n"
                                                                +
                                                                "Elbette bu sistem kusursuz değildi; kadınlar, köleler ve yabancılar (metekler) sistemin tamamen dışındaydı. Yine de, sıradan insanların devlet yönetiminde söz sahibi olabileceği fikri, insanlık tarihi için devrim niteliğindeydi ve Batı medeniyetinin siyasi temelini oluşturdu.",
                                                37.9715, 23.7257, // Placeholder coordinates for Athens Acropolis
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Acropolis_from_Philopappos_Hill.jpg/800px-Acropolis_from_Philopappos_Hill.jpg"));
                                mapService.saveLocation(new MapLocation(
                                                "İskenderiye Kütüphanesi",
                                                "Antik dünyanın en büyük bilgi merkezi ve trajik yok oluşu.\n\n" +
                                                                "Büyük İskender'in generallerinden Ptolemaios tarafından Mısır'da kurulan İskenderiye Kütüphanesi, antik dünyanın en büyük ve en prestijli bilim merkeziydi. Amacı basitti ama iddialıydı: Dünyadaki tüm bilgiyi tek bir çatı altında toplamak.\n\n"
                                                                +
                                                                "Kütüphane, sadece bir kitap deposu değil, aynı zamanda bir araştırma enstitüsüydü (Museion). Arşimet, Öklid, Eratosthenes gibi dahi beyinler burada çalıştı, ders verdi ve keşifler yaptı. Kütüphane yasalarına göre, İskenderiye limanına giren her gemi aranır, eğer kitap bulunursa el konulur, kopyası çıkarılıp gemiye verilir, aslı kütüphanede kalırdı. Bu sayede 700.000'den fazla papirüs rulosunun toplandığı tahmin edilmektedir.\n\n"
                                                                +
                                                                "Bu muazzam bilgi hazinesinin yok oluşu, insanlık tarihinin en büyük trajedilerinden biridir. Kütüphane tek bir günde yanmadı; Jül Sezar'ın kuşatması, Hristiyan fanatiklerin saldırıları ve son olarak İslami fetihler sırasındaki ihmallerle yüzyıllar içinde yok oldu. Eğer İskenderiye Kütüphanesi günümüze ulaşabilseydi, bugün bilim ve teknolojide çok daha ileri bir noktada olabilirdik.",
                                                31.2000, 29.9167, // Placeholder coordinates for Alexandria
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/30/Ancientlibraryalex.jpg/800px-Ancientlibraryalex.jpg"));
                                mapService.saveLocation(new MapLocation(
                                                "Roma Yolları ve Mühendisliği",
                                                "'Bütün yollar Roma'ya çıkar' sözünün arkasındaki mühendislik.\n\n" +
                                                                "Roma İmparatorluğu'nun başarısının sırrı sadece lejyonları değil, aynı zamanda mühendisleriydi. Romalılar, imparatorluğun en uzak köşelerini başkente bağlayan 400.000 kilometrelik devasa bir yol ağı inşa ettiler. Bunun 80.000 kilometresi taş döşeli, her türlü hava koşuluna dayanıklı yollardı.\n\n"
                                                                +
                                                                "Roma yolları (Via), askeri birliklerin hızla hareket etmesi için tasarlanmıştı, ancak ticaretin ve haberleşmenin de can damarı oldu. Mühendisler, yolları mümkün olduğunca düz yapıyor, nehirleri kemerli köprülerle, dağları ise tünellerle aşıyorlardı. Yol yapımında kullanılan çok katmanlı teknik o kadar ileriydi ki, Appia Yolu gibi bazı Roma yolları 2000 yıl sonra bugün bile hala kullanılmaktadır.\n\n"
                                                                +
                                                                "Sadece yollar değil; su kemerleri (aküduktler), hamamlar, kanalizasyon sistemleri (Cloaca Maxima) ve betonu (Opus Caementicium) icat etmeleri, Roma mühendisliğinin zirvesini gösterir. Özellikle volkanik kül kullanarak yaptıkları beton, bugün kullandığımız modern betondan bile daha uzun ömürlüdür.",
                                                41.8500, 12.5000, // Placeholder coordinates for Rome
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/Appian_Way.jpg/800px-Appian_Way.jpg"));
                                mapService.saveLocation(new MapLocation(
                                                "Kolezyum'da Gladyatör Oyunları",
                                                "Roma halkının eğlence anlayışı: Ekmek ve Sirk.\n\n" +
                                                                "MS 80 yılında İmparator Titus tarafından açılışı yapılan Flavianus Amfitiyatrosu, ya da bilinen adıyla Kolezyum, Roma'nın en büyük eğlence merkeziydi. 50.000 izleyici kapasiteli bu devasa yapı, her sınıf vatandaşın (kadınlar ve köleler en üstkatlarda olmak üzere) bir araya geldiği nadir yerlerdendi.\n\n"
                                                                +
                                                                "Burada düzenlenen oyunlar (Ludi), Roma'nın gücünü ve imparatorun cömertliğini sergileme aracıydı. Gladyatör dövüşleri en popüler gösteriydi; savaş esirleri, suçlular veya profesyonel savaşçılar ölümüne dövüşürdü. Ayrıca, egzotik hayvanların (aslan, fil, gergedan) avlandığı 'Venatio' gösterileri de yapılırdı. Hatta alanın suyla doldurulup küçük gemilerle deniz savaşlarının (Naumachia) canlandırıldığı bile söylenir.\n\n"
                                                                +
                                                                "Kolezyum'un mimarisi de en az oyunlar kadar etkileyiciydi. 80 giriş kapısı sayesinde on binlerce insan dakikalar içinde binayı boşaltabiliyordu. Arenanın altında ise gladyatörlerin ve hayvanların bekletildiği, asansör sistemleriyle sahneye çıkarıldığı karmaşık bir tünel ağı (Hypogeum) bulunuyordu.",
                                                41.8902, 12.4922,
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Colosseo_2020.jpg/800px-Colosseo_2020.jpg"));

                                // ORTA ÇAĞ & RÖNESANS
                                mapService.saveLocation(new MapLocation(
                                                "Matbaanın İcadı",
                                                "Gutenberg'in devrimi ve bilginin demokratikleşmesi.\n\n" +
                                                                "1440 yılına kadar kitaplar, rahipler tarafından elle, aylar hatta yıllar süren çabalarla kopyalanırdı. Bu nedenle kitaplar inanılmaz derecede pahalıydı ve sadece çok zenginlerin veya kilisenin erişimi vardı. Alman kuyumcu Johannes Gutenberg'in 'hareketli metal harflerle baskı' tekniğini geliştirmesi, bu durumu kökünden değiştirdi.\n\n"
                                                                +
                                                                "Gutenberg'in icadı sayesinde, eskiden bir yılda yazılan bir kitap, artık bir günde yüzlerce kopya basılabiliyordu. Basılan ilk büyük eser olan Gutenberg İncili, kalitesiyle el yazması kitapları aratmıyordu. Kitap fiyatlarının düşmesi, bilginin hızla yayılmasını sağladı.\n\n"
                                                                +
                                                                "Matbaa, Avrupa'da Rönesans'ın, Reform hareketlerinin ve Bilimsel Devrim'in en büyük hızlandırıcısı oldu. Bilim insanları keşiflerini, filozoflar fikirlerini geniş kitlelere ulaştırabildi. Martin Luther'in 95 Tezi'nin hızla yayılması ve Katolik Kilisesi'nin otoritesinin sarsılması, büyük ölçüde matbaanın gücü sayesinde mümkün oldu.",
                                                50.0000, 8.2711, // Placeholder coordinates for Mainz, Germany
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Gutenberg_Bible_B42_Genesis.jpg/800px-Gutenberg_Bible_B42_Genesis.jpg"));
                                mapService.saveLocation(new MapLocation(
                                                "Leonardo da Vinci'nin Defterleri",
                                                "Sanat ve bilimi birleştiren evrensel bir deha.\n\n" +
                                                                "Dünya Leonardo da Vinci'yi en çok 'Mona Lisa' ve 'Son Akşam Yemeği' tablolarıyla tanısa da, o aslında gelmiş geçmiş en meraklı zihinlerden biriydi. Onun dehasının gerçek kanıtı, yaşamı boyunca tuttuğu ve sayısı 13.000 sayfayı bulan not defterleridir.\n\n"
                                                                +
                                                                "Da Vinci, bu defterlere 'ayna yazısı' (tersten yazı) tekniğiyle, aklına gelen her şeyi çizdi ve yazdı. Anatomi çalışmaları için kadavraları inceledi, insan vücudunun kas, kemik ve damar yapısını şaşırtıcı bir doğrulukla çizdi. Mühendislik alanında ise helikopter, tank, dalgıç kıyafeti, makineli tüfek ve açılır-kapanır köprü gibi, kendi zamanının yüzlerce yıl ötesinde makineler tasarladı.\n\n"
                                                                +
                                                                "Botanik, jeoloji, hidrolik, optik... Leonardo'nun ilgilenmediği neredeyse hiçbir alan yoktu. Akıntılı suda taşların nasıl aşındığından, kuşların kanat hareketlerine kadar doğayı bir bilim insanı titizliğiyle gözlemledi. Onun defterleri, 'Rönesans İnsanı' (Homo Universalis) kavramının, yani çok yönlü insanın en mükemmel örneğidir.",
                                                43.7696, 11.2558, // Placeholder coordinates for Florence, Italy
                                                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg/800px-Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg"));

                                // Procedural Generator
                                String[] prefixes = { "Antik", "Kayıp", "Gizemli", "Kral", "Kutsal", "Yıkık", "Büyük",
                                                "Eski", "Tapınak", "Saray" };
                                String[] suffixes = { "Harabeleri", "Tümülüsü", "Tapınağı", "Gözetleme Kulesi",
                                                "Sarayı", "Nekropolü", "Sunağı", "Agorası", "Limanı", "Höyüğü" };
                                String[] names = { "Midas", "Hektor", "Ramses", "Attila", "Sezar", "Artemis", "Zeus",
                                                "Athena", "Osiris", "Anubis", "Gilgamış", "Enkidu", "Hammurabi",
                                                "Hattuşiling", "Priamos" };

                                String[] findings = { "Altın sikkeler", "Seramik parçaları", "Savaş aletleri",
                                                "Kraliyet mührü", "Tabletler", "Heykel parçaları", "Kemik kalıntıları",
                                                "Tılsımlar" };

                                // Koordinat Sınırları (Yaklaşık)
                                RegionBounds[] regions = {
                                                new RegionBounds(36.0, 42.0, 26.0, 45.0, "Anadolu"), // Türkiye
                                                new RegionBounds(22.0, 31.0, 25.0, 35.0, "Mısır Çölleri"), // Mısır
                                                new RegionBounds(35.0, 41.0, 20.0, 28.0, "Yunan Yarımadası"), // Yunanistan
                                                new RegionBounds(37.0, 46.0, 10.0, 18.0, "İtalya Yarımadası"), // İtalya
                                                new RegionBounds(30.0, 37.0, 40.0, 48.0, "Mezopotamya") // Irak/Suriye
                                };

                                for (int i = 0; i < 500; i++) {
                                        // Rastgele Bölge Seç
                                        RegionBounds region = regions[(int) (Math.random() * regions.length)];

                                        // Rastgele Koordinat
                                        double lat = region.minLat + (Math.random() * (region.maxLat - region.minLat));
                                        double lng = region.minLng + (Math.random() * (region.maxLng - region.minLng));

                                        // Rastgele İsim
                                        String name = prefixes[(int) (Math.random() * prefixes.length)] + " " +
                                                        names[(int) (Math.random() * names.length)] + " " +
                                                        suffixes[(int) (Math.random() * suffixes.length)];

                                        // Detaylı Rastgele Açıklama
                                        String finding = findings[(int) (Math.random() * findings.length)];
                                        int year = 1000 + (int) (Math.random() * 4000); // MÖ

                                        String desc = "Bu bölgede yapılan " + (2020 - (int) (Math.random() * 20))
                                                        + " yılındaki kazılarda, " +
                                                        region.name + " medeniyetine ait önemli izlere rastlanmıştır. "
                                                        +
                                                        "Arkeologlar burada " + finding + " bulmuştur. " +
                                                        "Karbon testlerine göre bu yerleşim yeri MÖ " + year
                                                        + " civarına tarihlenmektedir. " +
                                                        "Bölgenin ticari ve dini açıdan önemli bir merkez olduğu düşünülmektedir. "
                                                        +
                                                        "Kazı çalışmaları hala devam etmektedir ve her geçen gün yeni sırlar gün yüzüne çıkmaktadır.\n\n"
                                                        +
                                                        "Elde edilen bulgular yerel müzelerde sergilenmektedir.";
                                        // Placeholder Görsel - Güvenilir Wikipedia Görselleri
                                        String[] safeImages = {
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/G%C3%B6bekli_Tepe%2C_Urfa.jpg/300px-G%C3%B6bekli_Tepe%2C_Urfa.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Ephesus_Celsus_Library.jpg/300px-Ephesus_Celsus_Library.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/All_Gizah_Pyramids.jpg/300px-All_Gizah_Pyramids.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Colosseo_2020.jpg/300px-Colosseo_2020.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Acropolis_from_Philopappos_Hill.jpg/300px-Acropolis_from_Philopappos_Hill.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/Appian_Way.jpg/300px-Appian_Way.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Tutanchamun_Maske.jpg/300px-Tutanchamun_Maske.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Rosetta_Stone.JPG/300px-Rosetta_Stone.JPG",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Lascaux_painting.jpg/300px-Lascaux_painting.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Gutenberg_Bible_B42_Genesis.jpg/300px-Gutenberg_Bible_B42_Genesis.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/Pergamonmuseum_Babylon_Ischtar-Tor.jpg/300px-Pergamonmuseum_Babylon_Ischtar-Tor.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Persepolis_T_Chipiez.jpg/300px-Persepolis_T_Chipiez.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Sphinx_partially_excavated2.jpg/300px-Sphinx_partially_excavated2.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Pompeii-Forum.jpg/300px-Pompeii-Forum.jpg",
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Big_Temple%2C_Thanjavur.jpg/300px-Big_Temple%2C_Thanjavur.jpg"
                                        };
                                        String img = safeImages[(int) (Math.random() * safeImages.length)];

                                        mapService.saveLocation(new MapLocation(name, desc, lat, lng, img));
                                }
                                System.out.println(">>> 500+ Procedural Lokasyon Eklendi.");
                        }

                        // 3. Blog İçerikleri (Küratörlü Detaylı Makaleler)
                        if (contentRepository.count() < 10) {
                                userRepository.findByUsername("Mcquelss").ifPresent(u -> {
                                        // Makale 1: Göbeklitepe
                                        com.arkeobla.model.Content c1 = new com.arkeobla.model.Content();
                                        c1.setTitle("Göbeklitepe: Tarihin Sıfır Noktası");
                                        c1.setCategory("Arkeoloji");
                                        c1.setEra("Neolitik Çağ");
                                        c1.setHistoricalDate("MÖ 9600 - MÖ 8200");
                                        c1.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/G%C3%B6bekli_Tepe%2C_Urfa.jpg/1200px-G%C3%B6bekli_Tepe%2C_Urfa.jpg");
                                        c1.setSummary("Şanlıurfa'da keşfedilen dünyanın en eski tapınağı, insanlık tarihini yeniden yazdı.");
                                        c1.setBody("Göbeklitepe, Şanlıurfa'nın yaklaşık 18 km kuzeydoğusunda, deniz seviyesinden 760 metre yükseklikte yer alan devasa bir arkeolojik alandır. T şeklindeki devasa taş dikilitlerden oluşan bu yapılar kompleksi, henüz tarımı bile keşfetmemiş avcı-toplayıcı topluluklar tarafından inşa edilmiştir.\n\n"
                                                        +
                                                        "Alman arkeolog Klaus Schmidt bu siteyi 1994'te dünyaya tanıttı ve ölümüne kadar kazı çalışmalarını yönetti. Radyokarbon tarihleme sonuçlarına göre yapılar MÖ 9600-8200 yılları arasına, yani günümüzden yaklaşık 12.000 yıl öncesine tarihleniyor. Bu, Mısır piramitlerinden 7.000, Stonehenge'den 6.000 yıl daha eskidir.\n\n"
                                                        +
                                                        "Güneydoğu Anadolu'da, Bereketli Hilal'in tam kalbinde yer alan Göbeklitepe, insanlığın ilk tarıma geçtiği bölgede bulunmaktadır. Göbeklitepe, 'önce tapınak, sonra şehir' tezini ortaya koyarak arkeoloji dünyasını sarstı. Geleneksel görüşe göre insanlar önce yerleşik hayata geçer, sonra dini yapılar inşa ederdi. Göbeklitepe bunun tersini kanıtladı.\n\n"
                                                        +
                                                        "10-15 ton ağırlığındaki kireçtaşı bloklar, taş aletlerle yontulup yaklaşık 500 metre uzaklıktaki taş ocağından taşındı. Metal aletler, tekerlek veya hayvan gücü kullanılmadan, sırf insan emeğiyle yapıldı.\n\n"
                                                        +
                                                        "Neolitik Çağ, insanlığın en radikal dönüşümünü yaşadığı dönemdir. Buzul Çağı'nın sona ermesiyle iklim yumuşamış, yabani tahıllar bollaşmış, insanlar yavaş yavaş avcılıktan tarıma geçmeye başlamıştı. Göbeklitepe'yi inşa edenler bu geçişin tam eşiğindeydi.");
                                        c1.setAuthor(u);
                                        c1.setCreatedAt(java.time.LocalDateTime.now().minusDays(1));
                                        contentRepository.save(c1);

                                        // Makale 2: Piramitler
                                        com.arkeobla.model.Content c2 = new com.arkeobla.model.Content();
                                        c2.setTitle("Büyük Giza Piramidi: Firavunların Ölümsüzlük Arayışı");
                                        c2.setCategory("Tarih");
                                        c2.setEra("Antik Mısır");
                                        c2.setHistoricalDate("MÖ 2560");
                                        c2.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Kheops-Pyramid.jpg/1200px-Kheops-Piramit.jpg");
                                        c2.setSummary("Antik dünyanın yedi harikasından günümüze ulaşan tek yapı: Keops Piramidi.");
                                        c2.setBody("Giza Platosu'ndaki üç büyük piramidin en büyüğü ve en eskisi olan Keops Piramidi, orijinal yüksekliği 146.6 metre olup 3.800 yıl boyunca dünyanın en yüksek yapısı olarak kalmıştır.\n\n"
                                                        +
                                                        "4. Hanedan firavunu Khufu (Yunanca: Keops) için inşa edilen bu devasa yapı, Mimar Hemiunu'nun projesi olduğu düşünülmektedir. MÖ 2560 civarında yaklaşık 20 yılda tamamlandı.\n\n"
                                                        +
                                                        "Kahire'nin hemen güneybatısında, Giza Platosu üzerinde yer alan piramit, Nil Nehri'nin batı kıyısında konumlandırılmıştır. Mısırlılar için batı 'ölüler diyarı'nı simgeliyordu.\n\n"
                                                        +
                                                        "Piramit sadece bir mezar değil, firavunun ölümsüzlüğe ulaşması için kozmik bir makineydi. İç odalar ve koridorlar, firavunun ruhunun yıldızlara yükselmesini sağlayacak şekilde tasarlandı.\n\n"
                                                        +
                                                        "2.3 milyon kireçtaşı ve granit blok kullanılarak inşa edildi. Bloklar ortalama 2.5 ton, bazıları 80 tona kadar çıkıyor. Rampa sistemleri, kaldıraçlar ve devasa işgücüyle taşındı. Araştırmalar, işçilerin köle değil maaşlı çalışanlar olduğunu göstermektedir.");
                                        c2.setAuthor(u);
                                        c2.setCreatedAt(java.time.LocalDateTime.now().minusDays(2));
                                        contentRepository.save(c2);

                                        // Makale 3: Roma Yolları
                                        com.arkeobla.model.Content c3 = new com.arkeobla.model.Content();
                                        c3.setTitle("Roma Yolları: İmparatorluğu Birleştiren Damarlar");
                                        c3.setCategory("Tarih");
                                        c3.setEra("Roma İmparatorluğu");
                                        c3.setHistoricalDate("MÖ 312 - MS 400");
                                        c3.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/Appian_Way.jpg/1200px-Appian_Way.jpg");
                                        c3.setSummary("'Bütün yollar Roma'ya çıkar' sözünün arkasındaki mühendislik harikası.");
                                        c3.setBody("Roma İmparatorluğu'nun inşa ettiği yol ağı toplam 400.000 km'yi buluyordu. Bunun 80.000 km'si taş döşeli ana yollardır.\\n\\n"
                                                        +
                                                        "Roma devleti tarafından planlı bir şekilde inşa edilen bu yollar, imparatorluğun can damarlarıydı. İlk büyük yol olan Via Appia, Censor Appius Claudius Caecus tarafından MÖ 312'de başlatıldı.\\n\\n"
                                                        +
                                                        "700 yıl boyunca sürekli genişletilen yol ağı, İspanya'dan Mezopotamya'ya, Britanya'dan Kuzey Afrika'ya tüm imparatorluk topraklarını kapsıyordu.\\n\\n"
                                                        +
                                                        "Çok katmanlı mühendislik kullanılarak yapılan bu yolların bazıları 2000 yıl sonra bugün bile kullanılmaktadır.");
                                        c3.setAuthor(u);
                                        c3.setCreatedAt(java.time.LocalDateTime.now().minusDays(3));
                                        contentRepository.save(c3);

                                        // Makale 4: Truva Savaşı
                                        com.arkeobla.model.Content c4 = new com.arkeobla.model.Content();
                                        c4.setTitle("Truva Savaşı: Efsane mi Gerçek mi?");
                                        c4.setCategory("Mitoloji");
                                        c4.setEra("Tunç Çağı");
                                        c4.setHistoricalDate("MÖ 1200 civarı");
                                        c4.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Procession_of_the_Trojan_Horse_into_Troy.jpg/1200px-Procession_of_the_Trojan_Horse_into_Troy.jpg");
                                        c4.setSummary("Homeros'un İlyada destanındaki savaşın arkeolojik kanıtları.");
                                        c4.setBody("NE? Yunan mitolojisine göre, Akhaların (Yunanlıların) Truva şehrini 10 yıl kuşatması ve tahta at hilesiyle ele geçirmesi.\n\n"
                                                        +
                                                        "KİM? Mitolojiye göre Spartalı Helen'in kaçırılması savaşı başlattı. Akhilleus, Hektor, Odysseus gibi kahramanlar öne çıkar.\n\n"
                                                        +
                                                        "NE ZAMAN? Geleneksel olarak MÖ 1194-1184 olarak tarihlenir. Arkeolojik olarak Truva VIIa tabakası (MÖ 1190-1180) savaş izleri taşır.\n\n"
                                                        +
                                                        "NEREDE? Çanakkale'nin güneybatısında, Hisarlık Höyüğü. Alman arkeolog Heinrich Schliemann 1871'de kazılara başladı.\n\n"
                                                        +
                                                        "NEDEN ÖNEMLİ? Efsanenin gerçek bir tarihi çekirdeği olduğu artık kabul görüyor. Tunç Çağı'nın sonundaki büyük çöküş dönemine denk geliyor.\n\n"
                                                        +
                                                        "DÖNEM HİKAYESİ: MÖ 1200 civarında Doğu Akdeniz'i sarsan 'Tunç Çağı Çöküşü' yaşandı. Hitit İmparatorluğu yıkıldı, Miken uygarlığı sona erdi, 'Deniz Kavimleri' kıyıları kasıp kavurdu. Truva muhtemelen bu kaotik dönemin kurbanlarından biriydi.");
                                        c4.setAuthor(u);
                                        c4.setCreatedAt(java.time.LocalDateTime.now().minusDays(4));
                                        contentRepository.save(c4);

                                        // Makale 5: İskenderiye Kütüphanesi
                                        com.arkeobla.model.Content c5 = new com.arkeobla.model.Content();
                                        c5.setTitle("İskenderiye Kütüphanesi: Kaybolan Bilgi Hazinesi");
                                        c5.setCategory("Tarih");
                                        c5.setEra("Antik Yunan");
                                        c5.setHistoricalDate("MÖ 3. yüzyıl - MS 391");
                                        c5.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Ancientlibraryalex.jpg/1200px-Ancientlibraryalex.jpg");
                                        c5.setSummary("Antik dünyanın en büyük bilgi merkezi ve trajik yok oluşunun hikayesi.");
                                        c5.setBody("NE? İskenderiye'de kurulan, amacı dünyadaki tüm bilgiyi toplamak olan devasa kütüphane ve araştırma merkezi (Museion).\n\n"
                                                        +
                                                        "KİM? Ptolemaios I Soter tarafından kuruldu, ardılları tarafından genişletildi. Arşimet, Öklid, Eratosthenes gibi dahiler burada çalıştı.\n\n"
                                                        +
                                                        "NE ZAMAN? MÖ 3. yüzyılda kuruldu, MS 391'de Pagan tapınaklarının kapatılmasıyla son kalıntıları yok edildi.\n\n"
                                                        +
                                                        "NEREDE? Mısır'ın kuzey kıyısındaki liman kenti İskenderiye'de.\n\n"
                                                        +
                                                        "NEDEN ÖNEMLİ? Yaklaşık 700.000 papirüs rulosu barındırıyordu. Antik dünyanın bütün birikimi burada korunuyordu. Yok olması insanlık için büyük kayıptır.\n\n"
                                                        +
                                                        "NASIL YOK OLDU? Tek bir günde yanmadı. Jül Sezar'ın kuşatması (MÖ 48), Hristiyan saldırıları (MS 391) ve ihmal yüzyıllar içinde sona erdirdi.\n\n"
                                                        +
                                                        "DÖNEM HİKAYESİ: Helenistik Çağ, Büyük İskender'in fetihlerinden sonra Yunan kültürünün Doğu'ya yayıldığı dönemdir. Ptolemaioslar Mısır'ı yönetirken, İskenderiye antik dünyanın en kozmopolit şehri ve bilim başkenti oldu.");
                                        c5.setAuthor(u);
                                        c5.setCreatedAt(java.time.LocalDateTime.now().minusDays(5));
                                        contentRepository.save(c5);

                                        // Makale 6: Demokrasinin Doğuşu
                                        com.arkeobla.model.Content c6 = new com.arkeobla.model.Content();
                                        c6.setTitle("Atina Demokrasisi: Halkın İlk Sesi");
                                        c6.setCategory("Tarih");
                                        c6.setEra("Antik Yunan");
                                        c6.setHistoricalDate("MÖ 508/507");
                                        c6.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Acropolis_from_Philopappos_Hill.jpg/1200px-Acropolis_from_Philopappos_Hill.jpg");
                                        c6.setSummary("Kleisthenes reformlarıyla başlayan, sıradan vatandaşın devlet yönetimine katıldığı sistem.");
                                        c6.setBody("NE? 'Demos' (halk) ve 'Kratos' (iktidar) kelimelerinden türeyen, vatandaşların doğrudan yönetimde söz sahibi olduğu sistem.\n\n"
                                                        +
                                                        "KİM? Atinalı devlet adamı Kleisthenes, MÖ 508/507'de köklü reformlar yaparak sistemi kurdu. Perikles döneminde (MÖ 5. yy ortası) altın çağını yaşadı.\n\n"
                                                        +
                                                        "NE ZAMAN? Yaklaşık 200 yıl sürdü. MÖ 322'de Makedonya hakimiyetiyle sona erdi.\n\n"
                                                        +
                                                        "NEREDE? Atina şehir devleti ve çevresindeki Attika bölgesi.\n\n"
                                                        +
                                                        "NASIL İŞLİYORDU? Ekklesia (Halk Meclisi) yasaları tartışır ve oylardı. Jüri üyeleri kura ile seçilirdi. 'Ostrakismos' ile tehlikeli görülen kişiler 10 yıllığına sürgün edilebilirdi.\n\n"
                                                        +
                                                        "SINIRLAMALARI: Kadınlar, köleler ve yabancılar (metekler) oy kullanamazdı. Nüfusun sadece %10-20'si 'vatandaş' sayılıyordu.\n\n"
                                                        +
                                                        "DÖNEM HİKAYESİ: MÖ 5. yüzyıl Atinası'nda sanat, felsefe ve siyaset bir arada çiçek açtı. Tragedya yazarları, Sokrates, Parthenon hepsi bu dönemin ürünüdür. Pers Savaşları'ndaki zafer Atina'ya büyük prestij ve güç kazandırmıştı.");
                                        c6.setAuthor(u);
                                        c6.setCreatedAt(java.time.LocalDateTime.now().minusDays(6));
                                        contentRepository.save(c6);

                                        // Makale 7: Sümer Tabletleri
                                        com.arkeobla.model.Content c7 = new com.arkeobla.model.Content();
                                        c7.setTitle("Sümer Çivi Yazısı: İnsanlığın İlk Sözleri");
                                        c7.setCategory("Arkeoloji");
                                        c7.setEra("Erken Tunç Çağı");
                                        c7.setHistoricalDate("MÖ 3400 - MÖ 2000");
                                        c7.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Cuneiform_script2.jpg/1200px-Cuneiform_script2.jpg");
                                        c7.setSummary("Mezopotamya'da icat edilen dünyanın bilinen en eski yazı sistemi.");
                                        c7.setBody("NE? Kamış kalemlerle ıslak kil tabletlere basılan çivi (kama) şeklindeki işaretlerden oluşan yazı sistemi.\n\n"
                                                        +
                                                        "KİM? Sümerler tarafından icat edildi. Sonra Akadlar, Babilliler, Asurlular, Hititler ve Persler tarafından benimsendi.\n\n"
                                                        +
                                                        "NE ZAMAN? MÖ 3400 civarında ortaya çıktı. 3000 yıldan fazla kullanıldı, MS 1. yüzyılda tamamen terk edildi.\n\n"
                                                        +
                                                        "NEREDE? Güney Irak'taki Uruk şehrinde başladı, tüm Yakın Doğu'ya yayıldı.\n\n"
                                                        +
                                                        "NEDEN İCAT EDİLDİ? İlk başta tapınak ekonomisini yönetmek için: Tahıl stokları, hayvan sayıları, işçi ücretleri kayıt altına alınıyordu.\n\n"
                                                        +
                                                        "NASIL ÇÖZÜLDÜ? 19. yüzyılda Henry Rawlinson, Behistun Yazıtı'ndaki üç dilli metni çözerek çivi yazısının kapısını açtı.\n\n"
                                                        +
                                                        "DÖNEM HİKAYESİ: Sümerler ilklerin uygarlığıdır: İlk şehirler, ilk yazı, ilk yasalar, ilk edebiyat (Gılgamış Destanı). Dicle ve Fırat nehirleri arasındaki verimli topraklarda doğan bu uygarlık, insanlık tarihinin temellerini attı.");
                                        c7.setAuthor(u);
                                        c7.setCreatedAt(java.time.LocalDateTime.now().minusDays(7));
                                        contentRepository.save(c7);

                                        // Makale 8: Tutankhamun
                                        com.arkeobla.model.Content c8 = new com.arkeobla.model.Content();
                                        c8.setTitle("Tutankhamun'un Hazineleri: Altın Maskenin Ardındaki Çocuk Kral");
                                        c8.setCategory("Arkeoloji");
                                        c8.setEra("Antik Mısır");
                                        c8.setHistoricalDate("MÖ 1332 - MÖ 1323");
                                        c8.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Tutanchamun_Maske.jpg/1200px-Tutanchamun_Maske.jpg");
                                        c8.setSummary("Howard Carter'ın 1922'de bulduğu el değmemiş mezar ve ikonik altın maske.");
                                        c8.setBody("NE? Yeni Krallık dönemi firavunu Tutankhamun'un Krallar Vadisi'ndeki mezarı (KV62). Antik çağda yağmalanmadan kalan nadir mezarlardan biri.\n\n"
                                                        +
                                                        "KİM? Tutankhamun yaklaşık 9 yaşında tahta çıktı, 18-19 yaşında öldü. Babası 'deli firavun' Akhenaton olabilir.\n\n"
                                                        +
                                                        "NE ZAMAN? Mezar 4 Kasım 1922'de İngiliz arkeolog Howard Carter tarafından keşfedildi.\n\n"
                                                        +
                                                        "NEREDE? Mısır'ın güneyinde, Luksor yakınlarındaki Krallar Vadisi.\n\n"
                                                        +
                                                        "NELER BULUNDU? 5.000'den fazla eser: Altın tabut, 11 kg ağırlığındaki altın maske, savaş arabaları, tahtlar, mücevherler, hatta bir trompet bile.\n\n"
                                                        +
                                                        "FİRAVUN LANETİ: Keşiften sonra Lord Carnarvon'un ölümü 'lanet' söylentilerini başlattı. Gerçekte muhtemelen enfeksiyon nedeniyle öldü.\n\n"
                                                        +
                                                        "DÖNEM HİKAYESİ: Tutankhamun, babasının dini devrimini (tek tanrı Aten) geri alıp geleneksel Mısır dinine dönüşü simgeler. Kısa hükümdarlığına rağmen mezarındaki hazineler onu tarihin en ünlü firavunu yaptı.");
                                        c8.setAuthor(u);
                                        c8.setCreatedAt(java.time.LocalDateTime.now().minusDays(8));
                                        contentRepository.save(c8);

                                        // Makale 9: Kolezyum
                                        com.arkeobla.model.Content c9 = new com.arkeobla.model.Content();
                                        c9.setTitle("Kolezyum: Gladyatörlerin Kan Arenası");
                                        c9.setCategory("Tarih");
                                        c9.setEra("Roma İmparatorluğu");
                                        c9.setHistoricalDate("MS 70 - MS 80");
                                        c9.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Colosseo_2020.jpg/1200px-Colosseo_2020.jpg");
                                        c9.setSummary("Roma'nın 50.000 kişilik devasa amfitiyatrosunda gladyatör oyunları ve vahşet gösteri olmuştu.");
                                        c9.setBody("NE? Resmi adı Flavianus Amfitiyatrosu olan, 50.000 seyirci kapasiteli dev arena. Romalıların 'Ekmek ve Sirk' politikasının simgesi.\n\n"
                                                        +
                                                        "KİM? İmparator Vespasianus tarafından başlatıldı, oğlu Titus döneminde MS 80'de açıldı.\n\n"
                                                        +
                                                        "NE ZAMAN? MS 80'den itibaren yaklaşık 400 yıl boyunca aktif olarak kullanıldı.\n\n"
                                                        +
                                                        "NEREDE? Roma'nın tam kalbinde, Forum Romanum'un hemen doğusunda.\n\n"
                                                        +
                                                        "NELER OLUYORDU? Gladyatör dövüşleri (Munera), hayvan avları (Venatio), idam infazları, hatta su doldurulup deniz savaşları (Naumachia) bile canlandırılıyordu.\n\n"
                                                        +
                                                        "MİMARİ DEHASI: 80 giriş kapısı sayesinde on binlerce kişi dakikalar içinde tahliye edilebiliyordu. Arenanın altındaki Hypogeum tunellerinde gladyatörler ve hayvanlar asansörlerle sahneye çıkarılırdı.\n\n"
                                                        +
                                                        "DÖNEM HİKAYESİ: Flavius hanedanı dönemi, İç Savaş'ın ardından Roma'da istikrarın yeniden kurulduğu zamandı. Kolezyum, imparatorların halka cömertliklerini ve Roma'nın gücünü gösterdiği bir propaganda aracıydı.");
                                        c9.setAuthor(u);
                                        c9.setCreatedAt(java.time.LocalDateTime.now().minusDays(9));
                                        contentRepository.save(c9);

                                        // Makale 10: Matbaa
                                        com.arkeobla.model.Content c10 = new com.arkeobla.model.Content();
                                        c10.setTitle("Gutenberg'in Matbaası: Bilginin Demokratikleşmesi");
                                        c10.setCategory("Keşif");
                                        c10.setEra("Rönesans");
                                        c10.setHistoricalDate("1440");
                                        c10.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Gutenberg_Bible_B42_Genesis.jpg/1200px-Gutenberg_Bible_B42_Genesis.jpg");
                                        c10.setSummary("Hareketli metal harfli baskı tekniği, Avrupa'yı ve dünyayı sonsuza dek değiştirdi.");
                                        c10.setBody("NE? Değiştirilebilir metal harflerle mekanik baskı yapan sistem. Bir kitap artık elle kopyalanmak yerine yüzlerce kopya basılabiliyordu.\n\n"
                                                        +
                                                        "KİM? Alman kuyumcu Johannes Gutenberg, Mainz şehrinde. Çin ve Kore'de daha önce benzer teknikler vardı, ama Avrupa'da Gutenberg sistematize etti.\n\n"
                                                        +
                                                        "NE ZAMAN? 1440 civarında geliştirildi. İlk büyük eser 1455'teki Gutenberg İncili'dir.\n\n"
                                                        +
                                                        "NEREDE? Almanya'nın Mainz şehri Avrupa matbaacılığının beşiği oldu.\n\n"
                                                        +
                                                        "NEDEN DEVRİMCİ? Kitap fiyatları düştü, okur-yazarlık arttı, fikirler hızla yayıldı. Rönesans, Reform, Bilimsel Devrim hepsi matbaanın hızlandırdığı süreçlerdir.\n\n"
                                                        +
                                                        "ETKİLERİ: Martin Luther'in 95 Tezi haftalar içinde Avrupa'ya yayıldı. Bilim insanları keşiflerini anında paylaşabildi. Standart diller ve ulusal kimlikler şekillendi.\n\n"
                                                        +
                                                        "DÖNEM HİKAYESİ: 15. yüzyıl, Orta Çağ'dan Modern Çağ'a geçişin yaşandığı dönemdir. Konstantinopolis düştü, Amerika keşfedildi, Rönesans sanatı zirveye ulaştı. Matbaa bu dönüşümün en güçlü katalizörlerinden biriydi.");
                                        c10.setAuthor(u);
                                        c10.setCreatedAt(java.time.LocalDateTime.now().minusDays(10));
                                        contentRepository.save(c10);

                                        System.out.println(">>> 10 Adet Detaylı Makale Eklendi. (Admin: "
                                                        + u.getUsername() + ")");
                                });
                        }
                };
        }

        private static class RegionBounds {
                double minLat, maxLat, minLng, maxLng;
                String name;

                public RegionBounds(double minLat, double maxLat, double minLng, double maxLng, String name) {
                        this.minLat = minLat;
                        this.maxLat = maxLat;
                        this.minLng = minLng;
                        this.maxLng = maxLng;
                        this.name = name;
                }
        }
}
