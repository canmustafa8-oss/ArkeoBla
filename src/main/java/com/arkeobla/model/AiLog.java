package com.arkeobla.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AiLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String question;

    @Column(length = 2000)
    private String answer;

    private LocalDateTime timestamp;

    public AiLog() {
    }

    public AiLog(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
