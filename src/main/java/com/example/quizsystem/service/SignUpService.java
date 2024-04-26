package com.example.quizsystem.service;

import com.example.quizsystem.DatabaseConnection;
import com.example.quizsystem.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpService {

    static Connection cn = DatabaseConnection.getConnection();

    public static boolean ajouter(Connection connection, User user) {

        if (emailExists(connection, user.getEmail())) {
            showAlert("Email already exists", Alert.AlertType.ERROR);
            return false;
        }
        System.out.println("User: " + user.toString());
        String request = "INSERT INTO users (username, email, password,type) VALUES (?, ?, ?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(request);
            pst.setString(2, user.getEmail());
            pst.setString(1, user.getUsername());
            pst.setString(4, user.getType());

            // Encrypt the password before storing it
            String encryptedPassword = encryptPassword(user.getPassword());
            pst.setString(3, encryptedPassword);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected >= 1) {
                showAlert("USER CREATED SUCCESSFULLY, Welcome to our application", Alert.AlertType.INFORMATION);
                return true;
            }
        } catch (SQLException e) {
            showAlert("SORRY!, Something wrong happened", Alert.AlertType.ERROR);
        }

        return false;
    }


    public static boolean emailExists(Connection connection, String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Problem with query: " + e.getMessage());
        }
        return false;
    }
    private static String encryptPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                stringBuilder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Encryption algorithm not found: " + e.getMessage());
        }

        return null;
    }

    private static void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.setHeaderText("Success");
        alert.setTitle("Facture");
        alert.show();
    }

}
