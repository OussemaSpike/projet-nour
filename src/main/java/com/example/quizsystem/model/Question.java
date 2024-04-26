package com.example.quizsystem.model;

import java.util.List;

public class Question {

    private int id;
    private String text;
    private String difficulty;
    private Cours course;
    private List<Option> options;


    public Question( String text, String difficulty, Cours course, List<Option> options) {
        this.id = id;
        this.text = text;
        this.difficulty = difficulty;
        this.course = course;
        this.options = options;
    }

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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Cours getCourse() {
        return course;
    }

    public void setCourse(Cours course) {
        this.course = course;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
