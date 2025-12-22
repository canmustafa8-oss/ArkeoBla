package com.arkeobla.model;

public enum QuizCategory {
    ARKEOLOJI("Arkeoloji"),
    GENEL_KULTUR("Genel Kültür"),
    SINEMA("Sinema & Hobi"),
    BILIM("Bilim & Teknoloji"),
    SPOR("Spor"),
    SANAT("Sanat & Edebiyat"),
    KARISIK("Ortaya Karışık");

    public final String label;

    QuizCategory(String label) {
        this.label = label;
    }
}
