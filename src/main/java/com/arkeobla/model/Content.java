package com.arkeobla.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String body;

    private String summary; // Kısa özet
    private String imageUrl; // Kapak resmi URL

    private String category; // Arkeoloji, Tarih, Sanat vb.

    private String era; // Dönem (Antik Çağ, Orta Çağ vs.)
    private String historicalDate; // Tarihi (M.Ö. 3000, 1453 vs.)

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    public Content() {
        this.createdAt = LocalDateTime.now();
    }

    // Getter ve Setterlar
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public String getHistoricalDate() {
        return historicalDate;
    }

    public void setHistoricalDate(String historicalDate) {
        this.historicalDate = historicalDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
