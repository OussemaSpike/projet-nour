package com.example.quizsystem.controller;

import com.example.quizsystem.DatabaseConnection;
import com.example.quizsystem.model.User;
import com.example.quizsystem.service.SignUpService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class SignUpController {

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    @FXML
    private StackPane s;

    @FXML
    private ComboBox<String> userType;

    private Connection cn;

    public void initialize() {
        cn = DatabaseConnection.getConnection();

        userType.getItems().addAll("Admin", "Student","teacher");

    }

    @FXML
    public void signUp(ActionEvent event) {
        if (username.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty()) {
            showAlert("Veuillez remplir tous les champs", Alert.AlertType.WARNING);
        } else {
            String usernameText = username.getText();
            String passwordText = password.getText();
            String emailText = email.getText();
            String selectedType = userType.getValue();
            User user = new User(usernameText,emailText,passwordText,selectedType);

            if (SignUpService.ajouter(cn, user)) {
                clearFields();

                // Load FXML file and navigate to it
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginScene.fxml"));
                    Parent root = loader.load();
                    LoginController loginController = loader.getController();

                    // Access the current stage
                    Stage currentStage = (Stage) s.getScene().getWindow();

                    // Replace the content of the current scene with the content of FXMLLogin.fxml
                    Scene scene = new Scene(root);
                    currentStage.setScene(scene);
                    currentStage.setTitle("Login");
                    currentStage.show();
                } catch (IOException e) {
                    showAlert("Failed to load FXMLLogin.fxml", Alert.AlertType.ERROR);
                    e.printStackTrace();
                }
            } else {
                showAlert("Something went wrong. Failed to create user.", Alert.AlertType.ERROR);
            }
        }
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.setHeaderText("Attention");
        alert.setTitle("Facture");
        alert.show();
    }

    private void clearFields() {
        username.clear();
        password.clear();
        email.clear();
    }
    public void goToLogin(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginScene.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();

            // Access the current stage
            Stage currentStage = (Stage) s.getScene().getWindow();

            // Replace the content of the current scene with the content of FXMLLogin.fxml
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.setTitle("Login");
            currentStage.show();
        } catch (IOException e) {
            showAlert("Failed to load FXMLLogin.fxml", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }
}
