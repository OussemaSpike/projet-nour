package com.example.quizsystem.model;

public class Session {
    private String username;
    private int role;

    public Session() {
    }


    public String getUsername() {
        return username;
    }

    public int getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(int role) {
        this.role = role;
    }
}