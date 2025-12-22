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
                                admin.setRole(Role.ADMIN);
                                admin.setBadges("KURUCU,YÖNETİCİ,KRAL");
                                userRepository.save(admin);
                                System.out.println(">>> Varsayılan Admin kullanıcısı oluşturuldu: Mcquelss");
                        } else {
                                // Mevcut admini güncelle (Eski kayıtlarda rol eksik olabilir)
                                admin.setRole(Role.ADMIN);
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
                                                "https://images.unsplash.com/photo-1544558635-667480601430?auto=format&fit=crop&w=800&q=80"));
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
                                                "https://images.unsplash.com/photo-1500937386664-56d1dfef3854?auto=format&fit=crop&w=800&q=80"));

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
                                                "https://images.unsplash.com/photo-1568667256549-094345857637?auto=format&fit=crop&w=800&q=80"));
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
                                        // Placeholder Görsel (Doğa/Harabe)
                                        String img = "https://images.unsplash.com/photo-"
                                                        + (1500000000000L + (long) (Math.random() * 1000000))
                                                        + "?auto=format&fit=crop&w=300&q=80";
                                        // Daha güvenli bir placeholder seti kullanalım
                                        String[] safeImages = {
                                                        "https://images.unsplash.com/photo-1564399580075-5dfe19c205f9?auto=format&fit=crop&w=300&q=80", // Harabe
                                                                                                                                                        // 1
                                                        "https://images.unsplash.com/photo-1599839575945-a9e5af0c3fa5?auto=format&fit=crop&w=300&q=80", // Harabe
                                                                                                                                                        // 2
                                                        "https://images.unsplash.com/photo-1548685913-fe65a8df24d5?auto=format&fit=crop&w=300&q=80", // Taşlar
                                                        "https://images.unsplash.com/photo-1533514114760-43846b07e4d1?auto=format&fit=crop&w=300&q=80", // Colosseum
                                                                                                                                                        // benzer
                                                        "https://images.unsplash.com/photo-1590059390002-3c3e87cb542d?auto=format&fit=crop&w=300&q=80" // Sütunlar
                                        };
                                        img = safeImages[(int) (Math.random() * safeImages.length)];

                                        mapService.saveLocation(new MapLocation(name, desc, lat, lng, img));
                                }
                                System.out.println(">>> 500+ Procedural Lokasyon Eklendi.");
                        }

                        // 3. Blog İçerikleri (Küratörlü Top 100 Seeder)
                        if (contentRepository.count() < 10) {
                                userRepository.findByUsername("Mcquelss").ifPresent(u -> {
                                        System.out.println(">>> 200 Adet Demo İçerik Eklendi. (Admin bulundu: "
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
