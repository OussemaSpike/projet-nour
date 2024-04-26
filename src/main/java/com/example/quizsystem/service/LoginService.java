package com.example.quizsystem.service;

import com.example.quizsystem.DatabaseConnection;
import com.example.quizsystem.model.LoginResult;
import com.example.quizsystem.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    static Connection cn = DatabaseConnection.getConnection();

    public static LoginResult login(User c) {

        String userQuery = "SELECT * FROM users WHERE email=? AND password=?";
        String teacherQuery = "SELECT * FROM teacher WHERE email=? AND password=?";
        boolean isAuthenticated = false;
        boolean isTeacher = false;

        try {
            // Check in users table
            PreparedStatement pst = cn.prepareStatement(userQuery);
            pst.setString(1, c.getEmail());
            String encryptedPassword = encryptPassword(c.getPassword());
            pst.setString(2, encryptedPassword);
            ResultSet r = pst.executeQuery();
            if (r.next()) {
                isAuthenticated = true;
            }

            // Check in teachers table
            pst = cn.prepareStatement(teacherQuery);
            pst.setString(1, c.getEmail());
            pst.setString(2, c.getPassword()); // Compare the entered password directly
            r = pst.executeQuery();
            if (r.next()) {
                isAuthenticated = true;
                isTeacher = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert dia3 = new Alert(Alert.AlertType.INFORMATION, "ERROR LOGIN", ButtonType.OK);
            dia3.setHeaderText("Error");
            dia3.setTitle("quiz");
            dia3.show();
        }

        return new LoginResult(isAuthenticated, isTeacher);
    }

    private static String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception
            e.printStackTrace();
        }
        return null;
    }
}