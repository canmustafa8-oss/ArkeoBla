package com.arkeobla.model;

public class QuizQuestion {
    private int id;
    private QuizCategory category;
    private String text;
    private String[] options;
    private String correctAnswer;

    // Default constructor for Jackson
    public QuizQuestion() {
    }

    public QuizQuestion(int id, QuizCategory category, String text, String[] options, String correctAnswer) {
        this.id = id;
        this.category = category;
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuizCategory getCategory() {
        return category;
    }

    public void setCategory(QuizCategory category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
