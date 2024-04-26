    module com.example.quizsystem {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;


        opens com.example.quizsystem to javafx.fxml;
        exports com.example.quizsystem;
        exports com.example.quizsystem.controller;
        opens com.example.quizsystem.controller to javafx.fxml;
        exports com.example.quizsystem.model;
        opens com.example.quizsystem.model to javafx.fxml;
        exports com.example.quizsystem.service;
        opens com.example.quizsystem.service to javafx.fxml;
    }