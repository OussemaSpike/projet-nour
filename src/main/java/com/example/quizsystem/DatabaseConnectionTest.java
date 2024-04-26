package com.example.quizsystem;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        // Attempt to establish a connection
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
}