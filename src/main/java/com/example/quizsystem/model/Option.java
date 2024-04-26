package com.example.quizsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Option {
    private int id;
    private String text;
    private boolean isCorrect;
    private int questionId;

    public Option(int id,String text, boolean isCorrect, int questionId) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }

    public Option(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

// Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}