package com.example.quizsystem.controller;

import com.example.quizsystem.FXMLLoaderUtil;
import com.example.quizsystem.model.LoginResult;
import com.example.quizsystem.model.User;
import com.example.quizsystem.service.LoginService;
import com.example.quizsystem.model.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    @FXML
    private StackPane s;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private void initialize() {
        // Initialize the controller
    }

    @FXML
    private void login() {
        String userEmail = email.getText();
        String userPassword = password.getText();

        // Create a new User object with the entered email and password
        User user = new User(userEmail, userPassword);

        // Call the login method from LoginService class to authenticate the user
        LoginResult loginResult = LoginService.login(user);

        if (loginResult.isAuthenticated()) {
            try {
                FXMLLoader loader;
                if (loginResult.isTeacher()) {
                    loader = new FXMLLoader(getClass().getResource("/TeacherDashboard.fxml"));
                } else {
                    loader = new FXMLLoader(getClass().getResource("/AdminScene.fxml"));
                }
                Parent root = loader.load();

                // Access the current stage
                Stage currentStage = (Stage) email.getScene().getWindow();

                // Replace the content of the current scene with the new scene
                Scene scene = new Scene(root);
                currentStage.setScene(scene);
                currentStage.show();
            } catch (IOException e) {
                showAlert("Failed to load scene", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } else {
            showAlert("Email or password is incorrect.", Alert.AlertType.ERROR);
        }
    }



    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void goToSignUp(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignUpScene.fxml"));
            Parent root = loader.load();
            SignUpController signupController = loader.getController();

            // Access the current stage
            Stage currentStage = (Stage) s.getScene().getWindow();

            // Replace the content of the current scene with the content of FXMLClient.fxml
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.setTitle("Gestion Quizz");
            currentStage.show();
        } catch (IOException e) {
            showAlert("Failed to load FXMLClient.fxml", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }
}
