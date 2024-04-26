package com.example.quizsystem.controller;

import com.example.quizsystem.model.Teacher;
import com.example.quizsystem.service.TeacherService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class TeacherController {

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private ComboBox<Integer> idClassComboBox;

    @FXML
    private ComboBox<Integer> idCoursComboBox;

    private TeacherService teacherService = new TeacherService();

    @FXML
    private void initialize() throws SQLException {
        idClassComboBox.setItems(teacherService.getClassIds());
        idCoursComboBox.setItems(teacherService.getCourseIds());
    }

    @FXML
    private void addTeacher() throws SQLException {
        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        int idClass = idClassComboBox.getValue();
        int idCours = idCoursComboBox.getValue();
        Teacher newTeacher = new Teacher(id, name, email, password, idClass, idCours);
        teacherService.createTeacher(newTeacher);
    }

    @FXML
    private void deleteTeacher() throws SQLException {
        int id = Integer.parseInt(idTextField.getText());
        teacherService.deleteTeacher(id);
    }
}