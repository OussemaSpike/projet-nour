package com.example.quizsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherDashboardController {

    @FXML
    private Button logoutButton;

    @FXML
    private void handleLogoutButton(ActionEvent event) {

        // Navigate back to login scene
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoginScene.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the login scene
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

            // Close the current stage (window)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button questionButton ;

    @FXML
    private Button courseButton ;

    @FXML
    private Button teacherButton ;

    @FXML
    private Button studentButton ;

    @FXML
    private Button groupButton ;

    @FXML
    private void handleQuestionButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/QuestionScene.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the question scene
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Question");
            stage.show();

            // Close the current stage (window)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
