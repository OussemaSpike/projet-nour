package com.example.quizsystem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FXMLLoaderGeneral {

    public static void loadFXML(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FXMLLoaderUtil.class.getResource(fxmlFile));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("FXML Scene");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

