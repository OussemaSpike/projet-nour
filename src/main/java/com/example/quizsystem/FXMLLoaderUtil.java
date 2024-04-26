package com.example.quizsystem;

import com.example.quizsystem.controller.AdminController;
import com.example.quizsystem.model.Session;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLLoaderUtil {
    public static void loadFXML(String fxmlFile, Session session) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FXMLLoaderUtil.class.getResource(fxmlFile));
            Parent root = fxmlLoader.load();

            // Get the controller and set the session
            Object controller = fxmlLoader.getController();
            if (controller instanceof AdminController) {
                ((AdminController) controller).setSession(session);
            /*} else if (controller instanceof TeacherController) {
                ((TeacherController) controller).setSession(session);
            } else if (controller instanceof StudentController) {
                ((StudentController) controller).setSession(session);*/
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("User Role Specific Scene");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}