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
            InputStream inputStream = resource.getInputStream();
            allQuestions = objectMapper.readValue(inputStream, new TypeReference<List<QuizQuestion>>() {
            });
            System.out.println("Quiz soruları yüklendi: " + allQuestions.size() + " adet.");
        } catch (IOException e) {
            System.err.println("Sorular yüklenirken hata oluştu: " + e.getMessage());
        }
    }

    // Seçim Sayfası veya Soru Sayfası
    @GetMapping("/quiz")
    public String showQuiz(@RequestParam(required = false) String category, Model model) {
        if (category == null) {
            return "quiz-select"; // Kategori seçme ekranı
        }

        List<QuizQuestion> filteredQuestions = new ArrayList<>();
        QuizCategory selectedCat = QuizCategory.valueOf(category);

        if (selectedCat == QuizCategory.KARISIK) {
            filteredQuestions.addAll(allQuestions);
        } else {
            filteredQuestions = allQuestions.stream()
                    .filter(q -> q.getCategory() == selectedCat)
                    .collect(Collectors.toList());
        }

        // --- RASTGELE SEÇİM MANTIĞI ---
        // Listeyi karıştır
        Collections.shuffle(filteredQuestions);

        // İlk 5 soruyu al (veya daha az varsa hepsini)
        int questionLimit = 5;
        List<QuizQuestion> randomQuestions = filteredQuestions.subList(0,
                Math.min(filteredQuestions.size(), questionLimit));

        model.addAttribute("questions", randomQuestions);
        model.addAttribute("selectedCategory", selectedCat.label);
        return "quiz";
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
