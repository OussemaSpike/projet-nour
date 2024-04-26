package com.example.quizsystem.model;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;

    private String type;

    public int getId() {
        return id;
    }



    // Constructor for sign up
    public User(int id ,String username, String email, String password,String type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    // Constructor for sign up
    public User(String username, String email, String password,String type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }
    // Constructor for login
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
