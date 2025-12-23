package com.arkeobla.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true)
    private String email; // E-posta adresi

    private boolean enabled = false; // E-posta doğrulanana kadar false
    private String verificationCode;

    private String badges; // Virgülle ayrılmış rozetler: "ROZET_1,ROZET_2"

    private String firstName;
    private String lastName;

    private java.time.LocalDate birthDate;

    private int totalScore = 0;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    private String profilePhotoUrl; // Profil fotoğrafı URL'si
    private int weeklyScore = 0; // Haftalık skor
    private int monthlyScore = 0; // Aylık skor (New)
    private java.time.LocalDate lastQuizDate; // Son quiz tarihi

    // Gamification Fields
    private int dailyStreak = 0; // Günlük giriş serisi
    private java.time.LocalDate lastLoginDate; // Son giriş tarihi
    private int coins = 0; // Sanal para (ödüller için)
    private int totalQuizzes = 0; // Toplam çözülen quiz sayısı
    private int totalPuzzles = 0; // Toplam çözülen puzzle sayısı
    private int totalExcavations = 0; // Toplam kazı sayısı

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getBadges() {
        return badges;
    }

    public void setBadges(String badges) {
        this.badges = badges;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public java.time.LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(java.time.LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public int getWeeklyScore() {
        return weeklyScore;
    }

    public void setWeeklyScore(int weeklyScore) {
        this.weeklyScore = weeklyScore;
    }

    public int getMonthlyScore() {
        return monthlyScore;
    }

    public void setMonthlyScore(int monthlyScore) {
        this.monthlyScore = monthlyScore;
    }

    public java.time.LocalDate getLastQuizDate() {
        return lastQuizDate;
    }

    public void setLastQuizDate(java.time.LocalDate lastQuizDate) {
        this.lastQuizDate = lastQuizDate;
    }

    // Gamification Getters/Setters
    public int getDailyStreak() {
        return dailyStreak;
    }

    public void setDailyStreak(int dailyStreak) {
        this.dailyStreak = dailyStreak;
    }

    public java.time.LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(java.time.LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getTotalQuizzes() {
        return totalQuizzes;
    }

    public void setTotalQuizzes(int totalQuizzes) {
        this.totalQuizzes = totalQuizzes;
    }

    public int getTotalPuzzles() {
        return totalPuzzles;
    }

    public void setTotalPuzzles(int totalPuzzles) {
        this.totalPuzzles = totalPuzzles;
    }

    public int getTotalExcavations() {
        return totalExcavations;
    }

    public void setTotalExcavations(int totalExcavations) {
        this.totalExcavations = totalExcavations;
    }
}
