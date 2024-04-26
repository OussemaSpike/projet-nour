package com.example.quizsystem.controller;

import com.example.quizsystem.model.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.Node;

public class AdminController {
    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @FXML
    private Button logoutButton;

    @FXML
    private void handleLogoutButton(ActionEvent event) {
        // Clear session information
        session = null;

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
    private Button courseButton ;

    @FXML
    private Button teacherButton ;

    @FXML
    private Button studentButton ;

    @FXML
    private Button groupButton ;

    private final LoginController loginController = new LoginController();

    @FXML
    private void handleGroupButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GroupManagementScene.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the group management scene
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Group Management");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCourseButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CoursScene.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the course management scene
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Course Management");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTeacherButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TeacherScene.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the teacher management scene
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Teacher Management");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
