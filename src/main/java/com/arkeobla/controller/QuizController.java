package com.arkeobla.controller;

import com.arkeobla.model.QuizCategory;
import com.arkeobla.model.QuizQuestion;
import com.arkeobla.model.User;
import com.arkeobla.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class QuizController {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    // Tüm soruları hafızada tutacağız
    private List<QuizQuestion> allQuestions = new ArrayList<>();

    public QuizController(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void loadQuestions() {
        try {
            ClassPathResource resource = new ClassPathResource("questions.json");
            if (!resource.exists()) {
                System.err.println("ERROR: questions.json file NOT FOUND in classpath!");
                // Try alternative path
                resource = new ClassPathResource("/questions.json");
            }

            InputStream inputStream = resource.getInputStream();
            allQuestions = objectMapper.readValue(inputStream, new TypeReference<List<QuizQuestion>>() {
            });
            System.out.println("✅ Quiz soruları başarıyla yüklendi: " + allQuestions.size() + " adet.");

            // Debug: Print category counts
            System.out.println("Kategori dağılımı:");
            allQuestions.stream()
                    .collect(java.util.stream.Collectors.groupingBy(QuizQuestion::getCategory,
                            java.util.stream.Collectors.counting()))
                    .forEach((category, count) -> System.out.println("  " + category + ": " + count + " soru"));

        } catch (IOException e) {
            System.err.println("❌ HATA: Sorular yüklenirken hata oluştu!");
            System.err.println("Hata detayı: " + e.getMessage());
            e.printStackTrace();
            allQuestions = new ArrayList<>();
        }
    }

    // Seçim Sayfası veya Soru Sayfası
    @GetMapping("/quiz")
    public String showQuiz(@RequestParam(required = false) String category, Model model) {
        if (category == null || category.isEmpty()) {
            return "quiz-select"; // Kategori seçme ekranı
        }

        try {
            List<QuizQuestion> filteredQuestions = new ArrayList<>();
            QuizCategory selectedCat = QuizCategory.valueOf(category.toUpperCase());

            if (selectedCat == QuizCategory.KARISIK) {
                filteredQuestions.addAll(allQuestions);
            } else {
                filteredQuestions = allQuestions.stream()
                        .filter(q -> q.getCategory() == selectedCat)
                        .collect(Collectors.toList());
            }

            // --- RASTGELE SEÇİM MANTIĞI ---
            Collections.shuffle(filteredQuestions);

            // İlk 5 soruyu al
            int questionLimit = 5;
            List<QuizQuestion> randomQuestions = filteredQuestions.isEmpty()
                    ? new ArrayList<>()
                    : filteredQuestions.subList(0, Math.min(filteredQuestions.size(), questionLimit));

            model.addAttribute("questions", randomQuestions);
            model.addAttribute("selectedCategory", selectedCat.label);
            return "quiz";
        } catch (IllegalArgumentException e) {
            // Geçersiz kategori - seçim sayfasına yönlendir
            return "redirect:/quiz";
        }
    }

    @PostMapping("/quiz/submit")
    public String submitQuiz(@RequestParam Map<String, String> allParams,
            @AuthenticationPrincipal UserDetails currentUser,
            Model model) {

        int score = 0;
        int totalQuestions = 0; // Dinamik hesapla

        // Formdan gelen ID'lere göre kontrol et
        for (String key : allParams.keySet()) {
            if (key.startsWith("q")) {
                int qId = Integer.parseInt(key.substring(1));
                String userAnswer = allParams.get(key);

                // Soruyu bul
                QuizQuestion q = allQuestions.stream().filter(que -> que.getId() == qId).findFirst().orElse(null);
                if (q != null) {
                    totalQuestions++;
                    if (q.getCorrectAnswer().equals(userAnswer)) {
                        score++;
                    }
                }
            }
        }

        if (totalQuestions == 0)
            totalQuestions = 1; // Divide by zero koruması

        int percentage = (score * 100) / totalQuestions;
        String badgeMessage = null;

        if (percentage >= 80 && currentUser != null) {
            User user = userRepository.findByUsername(currentUser.getUsername()).orElse(null);
            if (user != null) {
                // İstatistikleri Güncelle
                user.setCorrectAnswers(user.getCorrectAnswers() + score);
                user.setWrongAnswers(user.getWrongAnswers() + (totalQuestions - score));
                user.setTotalScore(user.getTotalScore() + (score * 10)); // Her doğru 10 puan

                // Rozet Kontrolü
                String currentBadges = user.getBadges() == null ? "" : user.getBadges();
                if (!currentBadges.contains("BİLGE_KAŞİF")) {
                    user.setBadges(currentBadges.isEmpty() ? "BİLGE_KAŞİF" : currentBadges + ",BİLGE_KAŞİF");
                    badgeMessage = "TEBRİKLER! 'BİLGE KAŞİF' ROZETİNİ KAZANDINIZ! 🏅";
                }
                userRepository.save(user);
            }
        } else if (currentUser != null) {
            // 80 altı olsa bile istatistikleri güncellemeliyiz
            User user = userRepository.findByUsername(currentUser.getUsername()).orElse(null);
            if (user != null) {
                user.setCorrectAnswers(user.getCorrectAnswers() + score);
                user.setWrongAnswers(user.getWrongAnswers() + (totalQuestions - score));
                user.setTotalScore(user.getTotalScore() + (score * 10));
                userRepository.save(user);
            }
        }

        model.addAttribute("score", percentage);
        model.addAttribute("badgeMessage", badgeMessage);
        model.addAttribute("totalCorrect", score);
        return "quiz-result";
    }
}
