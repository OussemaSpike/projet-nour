package com.example.quizsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/quizsystem";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";

    private  static Connection connection;


    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }
}