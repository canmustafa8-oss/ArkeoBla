package com.arkeobla.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000) // Uzun metinler için
    private String body;

    private String category; // Arkeoloji, Tarih, Sanat vb.

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    public Content() {
        this.createdAt = LocalDateTime.now();
    }

    // Getter ve Setterlar
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }
}
