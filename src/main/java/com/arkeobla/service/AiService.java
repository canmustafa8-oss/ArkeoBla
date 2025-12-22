package com.arkeobla.service;

import org.springframework.stereotype.Service;
import java.util.Locale;

@Service
public class AiService {

    private final java.util.List<com.arkeobla.model.QuizQuestion> knowledgeBase = new java.util.ArrayList<>();
    private final com.arkeobla.repository.AiLogRepository aiLogRepository;

    public AiService(com.arkeobla.repository.AiLogRepository aiLogRepository) {
        this.aiLogRepository = aiLogRepository;
    }

    @jakarta.annotation.PostConstruct
    public void init() {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            java.io.InputStream inputStream = getClass().getResourceAsStream("/questions.json");
            if (inputStream == null) {
                System.out.println("⚠️ AI Asistan: questions.json bulunamadı!");
                return;
            }
            java.util.List<com.arkeobla.model.QuizQuestion> questions = mapper.readValue(inputStream,
                    new com.fasterxml.jackson.core.type.TypeReference<java.util.List<com.arkeobla.model.QuizQuestion>>() {
                    });
            knowledgeBase.addAll(questions);
            System.out.println("✅ AI Asistan: " + knowledgeBase.size() + " bilgi maddesi yüklendi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAnswer(String question) {
        String q = question.toLowerCase(Locale.forLanguageTag("tr"));

        // 1. ADIM: Bilgi Bankasında (questions.json) Arama Yap
        com.arkeobla.model.QuizQuestion bestMatch = null;
        double highestScore = 0.0;

        for (com.arkeobla.model.QuizQuestion item : knowledgeBase) {
            double score = calculateSimilarity(q, item.getText().toLowerCase(Locale.forLanguageTag("tr")));
            if (score > highestScore) {
                highestScore = score;
                bestMatch = item;
            }
        }

        String finalAnswer;

        // Eşik değer (%40 benzerlik)
        if (highestScore > 0.4 && bestMatch != null) {
            finalAnswer = "📖 <strong>Bilgi Bankamda Buldum:</strong><br>" +
                    "<em>" + bestMatch.getText() + "</em><br><br>" +
                    "✅ <strong>Cevap:</strong> " + bestMatch.getCorrectAnswer();
        } else if (q.contains("arkeoloji")) {
            finalAnswer = "Arkeoloji, insanlık tarihini maddi kalıntılar üzerinden inceleyen bilim dalıdır. Kazılarla geçmişi aydınlatır.";
        } else if (q.contains("göbeklitepe") || q.contains("gobeklitepe")) {
            finalAnswer = "Göbeklitepe, Şanlıurfa'da bulunan ve dünyanın bilinen en eski tapınak merkezi kabul edilen yapıdır (M.Ö. 9600). Tarihin sıfır noktası olarak anılır.";
        } else if (q.contains("piramit") || q.contains("mısır")) {
            finalAnswer = "Mısır Piramitleri, firavunların mezarı olarak inşa edilmiştir. En büyüğü Keops Piramidi'dir ve Dünyanın Yedi Harikası'ndan biridir.";
        } else if (q.contains("roma")) {
            finalAnswer = "Roma İmparatorluğu, Akdeniz havzasına hükmeden antik çağın en büyük medeniyetlerinden biridir.";
        } else if (q.contains("hitit") || q.contains("hattuşa")) {
            finalAnswer = "Hititler, Anadolu'nun ilk büyük imparatorluğudur. Başkentleri Çorum'daki Hattuşa'dır.";
        } else if (q.contains("tarih")) {
            finalAnswer = "Tarih, geçmişteki olayları neden-sonuç ilişkisi içinde inceleyen bilimdir.";
        } else if (q.contains("merhaba") || q.contains("selam")) {
            finalAnswer = "Merhaba! Ben ArkeoBla Asistanı. Tarih, arkeoloji ve sanat hakkında geniş bir bilgi arşivine sahibim. Sorunuzu bekliyorum!";
        } else if (q.contains("kimsin")) {
            finalAnswer = "Ben ArkeoBla yapay zeka asistanıyım. Yaklaşık " + knowledgeBase.size()
                    + " konuda bilgi sahibiyim.";
        } else {
            // Bulunamadı
            finalAnswer = "Bu konuda veritabanımda kesin bir bilgi bulamadım. Ama senin için Google'da arayabilirim: " +
                    "<a href='https://www.google.com/search?q=" + question.replace(" ", "+")
                    + "' target='_blank' style='color:blue; text-decoration:underline;'>'" + question
                    + "' için Google'da Ara</a>";
        }

        // Log to DB
        aiLogRepository.save(new com.arkeobla.model.AiLog(question, finalAnswer));

        return finalAnswer;
    }

    // Basit Kelime Benzerliği Algoritması (Jaccard Benzeri)
    private double calculateSimilarity(String userQuery, String dbText) {
        String[] queryWords = userQuery.split("\\s+");
        String[] dbWords = dbText.split("\\s+");

        int matchCount = 0;
        for (String qWord : queryWords) {
            if (qWord.length() < 3)
                continue; // "ve", "ile" gibi bağlaçları takılma
            for (String dbWord : dbWords) {
                if (dbWord.contains(qWord) || qWord.contains(dbWord)) {
                    matchCount++;
                    break;
                }
            }
        }

        // Skor = Eşleşen Kelime Sayısı / Sorgudaki Kelime Sayısı
        // (Sorgunun ne kadarının karşılandığına bakıyoruz)
        return (double) matchCount / queryWords.length;
    }
}
