package com.example.quizsystem.controller;

import com.example.quizsystem.model.Cours;
import com.example.quizsystem.model.Option;
import com.example.quizsystem.model.Question;
import com.example.quizsystem.service.QuestionService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionController {

    @FXML
    private TextField questionTextField;

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private ComboBox<String> courseComboBox;

    @FXML
    private TextField option1TextField;

    @FXML
    private TextField option2TextField;

    @FXML
    private TextField option3TextField;

    @FXML
    private ComboBox<String> correctOptionComboBox;

    private QuestionService questionService = new QuestionService();

    @FXML
    private void initialize() throws SQLException {
        courseComboBox.setItems(questionService.getCourseNames());
        difficultyComboBox.setItems(FXCollections.observableArrayList("hard", "easy", "medium"));
        correctOptionComboBox.setItems(FXCollections.observableArrayList("Option 1", "Option 2", "Option 3"));
    }

    @FXML
    private void addQuestion() throws SQLException {
        String questionText = questionTextField.getText();
        String difficulty = difficultyComboBox.getValue();
        String courseName = courseComboBox.getValue();
        List<Option> options = new ArrayList<>();
        if (!option1TextField.getText().isEmpty() && correctOptionComboBox.getValue() != null) {
            Option option1 = new Option(option1TextField.getText(), correctOptionComboBox.getValue().equals("Option 1"));
            options.add(option1);
        }
        if (!option2TextField.getText().isEmpty() && correctOptionComboBox.getValue() != null) {
            Option option2 = new Option(option2TextField.getText(), correctOptionComboBox.getValue().equals("Option 2"));

            options.add(option2);
        }
        if (!option3TextField.getText().isEmpty() && correctOptionComboBox.getValue() != null) {
            Option option3 = new Option(option3TextField.getText(), correctOptionComboBox.getValue().equals("Option 3"));

            options.add(option3);
        }
        if (!options.isEmpty() && questionText != null && !questionText.isEmpty()) {
            Question newQuestion = new Question(questionText, difficulty, new Cours(courseName), options);
            questionService.addQuestion(newQuestion, courseName);
        } else {
            // Handle the case where all option TextFields are empty or not properly initialized
            // or questionText is null or empty
        }
    }
}