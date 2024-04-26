package com.example.quizsystem.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Group {
    private IntegerProperty id;
    private StringProperty name;
    private IntegerProperty studentsNumber;

    public Group(IntegerProperty id, StringProperty name, IntegerProperty studentsNumber) {
        this.id = id;
        this.name = name;
        this.studentsNumber = studentsNumber;
    }


    public Group(int id, String name, int studentsNumber) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.studentsNumber = new SimpleIntegerProperty(studentsNumber);
    }


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getStudentsNumber() {
        return studentsNumber.get();
    }

    public IntegerProperty studentsNumberProperty() {
        return studentsNumber;
    }

    public void setStudentsNumber(int studentsNumber) {
        this.studentsNumber.set(studentsNumber);
    }
}
