package com.example.quizsystem.model;

public class LoginResult {
    private final boolean isAuthenticated;
    private final boolean isTeacher;

    public LoginResult(boolean success, boolean role) {
        this.isAuthenticated = success;
        this.isTeacher = role;
    }

    public boolean isSuccess() {
        return isAuthenticated;
    }

    public boolean getRole() {
        return isTeacher;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}