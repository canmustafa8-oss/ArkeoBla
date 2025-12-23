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
        String modelBust = "https://raw.githubusercontent.com/mrdoob/three.js/dev/examples/models/gltf/Nefertiti/Nefertiti.glb";
        String modelStone = "https://raw.githubusercontent.com/alexbfree/rosetta-stone/main/rosetta_stone.glb";
        String modelHelmet = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/DamagedHelmet/glTF-Binary/DamagedHelmet.glb";
        String modelStatue = "https://raw.githubusercontent.com/google/model-viewer/master/packages/shared-assets/models/glTF-Sample-Models/2.0/Relaxation/glTF-Binary/Relaxation.glb"; // Temsili
                                                                                                                                                                                        // Heykel
        String modelPottery = "https://raw.githubusercontent.com/google/model-viewer/master/packages/shared-assets/models/glTF-Sample-Models/2.0/SheenChair/glTF-Binary/SheenChair.glb"; // Temsili
                                                                                                                                                                                         // (Ahşap/Sandalye
                                                                                                                                                                                         // yerine)
                                                                                                                                                                                         // -
                                                                                                                                                                                         // Şimdilik
                                                                                                                                                                                         // Bust
                                                                                                                                                                                         // kullanalım

        // Model atamaları: Büstler için Nefertiti, Taş/Yazıtlar için Rosetta, Savaş
        // aletleri için Helmet

        // 1. Nefertiti Büstü
        artifacts.add(new Artifact("Nefertiti Büstü", "Mısır Kraliçesi Nefertiti'nin ikonik kireçtaşı büstü. MÖ 1345.",
                modelBust, ""));
        // 2. Rosetta Taşı
        artifacts.add(new Artifact("Rosetta Taşı", "Hiyerogliflerin çözülmesini sağlayan efsanevi taş yazıt.",
                modelStone, ""));
        // 3. Büyük İskender Büstü
        artifacts.add(new Artifact("Büyük İskender", "Makedonya Kralı Büyük İskender'in mermer büstü tasviri.",
                modelBust, ""));
        // 4. Hammurabi Kanunları
        artifacts.add(new Artifact("Hammurabi Kanunları",
                "Babil Kralı Hammurabi'nin yasalarını içeren devasa bazalt stel.", modelStone, ""));
        // 5. Sparta Miğferi
        artifacts.add(new Artifact("Sparta Savaş Miğferi",
                "Antik Yunan dönemine ait, savaşlarda kullanılan bronz miğfer.", modelHelmet, ""));
        // 6. Göbeklitepe T Sütunu
        artifacts.add(new Artifact("Göbeklitepe Dikilitaşı",
                "Tarihin sıfır noktası Göbeklitepe'den sembollerle bezeli T sütunu.", modelStone, ""));
        // 7. Tutankhamun Maskesi
        artifacts.add(new Artifact("Tutankhamun Maskesi",
                "Genç firavunun som altından yapılmış, dünyaca ünlü ölüm maskesi.", modelBust, ""));
        // 8. Zeus Heykeli
        artifacts.add(new Artifact("Zeus Heykeli", "Olimposlu Zeus'un tasviri. Antik Dünyanın Yedi Harikasından biri.",
                modelStatue, ""));
        // 9. Artemis Tapınağı Sütunu
        artifacts.add(new Artifact("Efes Artemis Sütunu",
                "Efes antik kentinden günümüze kalan nadir mermer sütunlardan.", modelStone, ""));
        // 10. Orhun Yazıtları
        artifacts.add(new Artifact("Orhun Abideleri", "Türk tarihinin en eski yazılı belgeleri olan Bengü Taşlar.",
                modelStone, ""));

        // 11-20
        artifacts.add(new Artifact("Roma İmparatoru Büstü",
                "Roma İmparatorluğu'nun kudretli liderini tasvir eden mermer büst.", modelBust, ""));
        artifacts.add(
                new Artifact("Hitit Güneş Kursu", "Hitit uygarlığının sembolü olan törensel disk.", modelHelmet, ""));
        artifacts.add(new Artifact("Sümer Kil Tableti",
                "Çivi yazısıyla yazılmış, ticari kayıtları içeren antik kil tablet.", modelStone, ""));
        artifacts.add(new Artifact("Asur Savaşçısı", "Asur ordusunun acımasız savaşçılarını gösteren kabartma detayı.",
                modelStatue, ""));
        artifacts.add(new Artifact("Viking Miğferi", "Kuzeyin savaşçılarına ait demir miğfer (boynuzsuz!).",
                modelHelmet, ""));
        artifacts.add(new Artifact("Maya Takvimi",
                "Mezoamerika uygarlıklarının zamanı ölçmek için kullandığı karmaşık taş disk.", modelStone, ""));
        artifacts.add(
                new Artifact("Aztek Güneş Taşı", "Aztek kozmolojisini anlatan devasa bazalt monolit.", modelStone, ""));
        artifacts.add(new Artifact("Çin Terracotta Askeri",
                "İlk Çin İmparatoru'nun mezarını koruyan kilden asker heykeli.", modelStatue, ""));
        artifacts.add(
                new Artifact("Miken Agamemnon Maskesi", "Miken uygarlığına ait altın cenaze maskesi.", modelBust, ""));
        artifacts.add(new Artifact("Gılgamış Destanı Tableti", "İnsanlık tarihinin bilinen en eski edebi eseri.",
                modelStone, ""));

        // 21-30
        artifacts.add(new Artifact("Afrodit Heykeli", "Güzellik ve aşk tanrıçası Afrodit'in (Venüs) zarif heykeli.",
                modelStatue, ""));
        artifacts.add(new Artifact("Disk Atan Atlet (Discobolus)",
                "Antik Yunan sporcusunun idealize edilmiş vücut oranlarını gösteren heykel.", modelStatue, ""));
        artifacts.add(new Artifact("Samuray Zırhı", "Feodal Japonya'nın elit savaşçılarına ait korkutucu zırh takımı.",
                modelHelmet, ""));
        artifacts.add(
                new Artifact("Moai Heykeli", "Paskalya Adası'ndaki gizemli dev taş heykellerden biri.", modelBust, ""));
        artifacts.add(new Artifact("Olmec Dev Başı", "Mezoamerika'nın en eski uygarlığına ait devasa bazalt kafa.",
                modelBust, ""));
        artifacts.add(new Artifact("Kadeş Antlaşması", "Tarihin kaydedilmiş ilk barış antlaşması (Hitit-Mısır).",
                modelStone, ""));
        artifacts.add(new Artifact("Celsus Kütüphanesi Cephesi",
                "Efes Antik Kenti'nin sembolü olan kütüphanenin mimari parçası.", modelStone, ""));
        artifacts
                .add(new Artifact("Troya Hazineleri", "Priam'ın hazinesinden altın takılar ve kaplar.", modelBust, ""));
        artifacts.add(new Artifact("İştar Kapısı Aslanı",
                "Babil'in mavi sırlı tuğlaları üzerindeki yürüyen aslan kabartması.", modelStone, ""));
        artifacts.add(new Artifact("Ayasofya Mozaiği", "Bizans dönemine ait eşsiz altın ve cam mozaik parçası.",
                modelStone, ""));

        // 31-40
        artifacts.add(new Artifact("Düşünen Adam (Rodin)", "Modern heykel sanatının öncüsü (Antik değil ama ikonik).",
                modelStatue, ""));
        artifacts.add(
                new Artifact("Davut Heykeli (Kafa)", "Michelangelo'nun şaheseri Davut'un baş kısmı.", modelBust, ""));
        artifacts.add(new Artifact("Nemrut Dağı Heykeli",
                "Kommagene Krallığı'na ait devasa tanrı heykellerinden bir baş.", modelBust, ""));
        artifacts.add(
                new Artifact("Urartu Kazanı", "Urartu maden işçiliğinin şaheseri olan bronz kazan.", modelHelmet, ""));
        artifacts.add(new Artifact("Likya Lahdi", "Likya uygarlığına özgü kaya mezarı mimarisi.", modelStone, ""));
        artifacts.add(
                new Artifact("Semazen Heykelciği", "Mevlevi kültürünü yansıtan tasavvufi figür.", modelStatue, ""));
        artifacts.add(new Artifact("Topkapı Hançeri", "Osmanlı mücevher sanatının nadide örneği.", modelHelmet, ""));
        artifacts.add(
                new Artifact("Kaşıkçı Elması", "Dünyanın en tanınmış ve değerli elmaslarından biri.", modelStone, ""));
        artifacts.add(new Artifact("Piri Reis Haritası Parçası",
                "Dünyayı gösteren eşsiz haritanın bir kesiti (Taş üzerine baskı).", modelStone, ""));
        artifacts.add(
                new Artifact("Karun Hazineleri Broş", "Lidya dönemine ait kanatlı denizatı broşu.", modelBust, ""));

        // 41-50+
        artifacts.add(new Artifact("Venedik Tacı", "Orta Çağ Avrupa'sının güç sembolü.", modelHelmet, ""));
        artifacts.add(new Artifact("Aziz Petrus'un Anahtarları", "Vatikan sembolü, cennetin anahtarları rölyefi.",
                modelStone, ""));
        artifacts.add(
                new Artifact("Machu Picchu Taşı", "İnka mimarisinin kusursuz taş işçiliği örneği.", modelStone, ""));
        artifacts.add(new Artifact("Petra Sütun Başlığı", "Nebati uygarlığının kayaya oyulmuş sanatının detayı.",
                modelStone, ""));
        artifacts.add(new Artifact("Tac Mahal Mermeri", "Babür İmparatorluğu'nun aşk anıtından işlemeli mermer.",
                modelStone, ""));
        artifacts
                .add(new Artifact("Kızılderili Totemi", "Kuzey Amerika yerlilerinin ruhani sembolü.", modelStatue, ""));
        artifacts.add(new Artifact("Aborjin Bumerangı", "Avustralya yerlilerinin ikonik av aleti.", modelStone, ""));
        artifacts.add(new Artifact("Eskimo (İnuit) Gözlüğü", "Kardan korunmak için kemikten yapılmış antik gözlük.",
                modelHelmet, ""));
        artifacts.add(new Artifact("Vaso François", "Antik Yunan çömlekçiliğinin başyapıtı (Kleitias Vazosu).",
                modelBust, ""));
        artifacts.add(
                new Artifact("Altamira Mağara Resmi", "Taş üzerine işlenmiş bizon çizimi replikası.", modelStone, ""));
        artifacts.add(new Artifact("Veni Vidi Vici Tableti", "Jül Sezar'ın ünlü sözünün kazındığı mermer levha.",
                modelStone, ""));

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
