package com.example.quizsystem.model;

public class Cours {

    private int id;
    private String name;



    public Cours (String name) {
        this.name = name;
    }

    public Cours(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
