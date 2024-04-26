package com.example.quizsystem.model;

public class Teacher extends User{

    private int idClass;

    private int idGroup;

    public Teacher(int id, String name, String email, String password, int idClass, int idGroup) {
        super(id, name, email, password, "teacher");
        this.idClass = idClass;
        this.idGroup = idGroup;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getIdCours() {
        return idGroup;
    }

    public void setIdCours(int idGroup) {
        this.idGroup = idGroup;
    }
}
