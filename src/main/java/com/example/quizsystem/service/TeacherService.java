package com.example.quizsystem.service;

import com.example.quizsystem.DatabaseConnection;
import com.example.quizsystem.model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherService {


    private Connection connection= DatabaseConnection.getConnection();

    public void createTeacher(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO teacher (id, username, email, password, idGroup, idCours) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, teacher.getId());
        statement.setString(2, teacher.getUsername());
        statement.setString(3, teacher.getEmail());
        statement.setString(4, teacher.getPassword());
        statement.setInt(5, teacher.getIdClass());
        statement.setInt(6, teacher.getIdCours());
        statement.executeUpdate();
    }

    public void deleteTeacher(int id) throws SQLException {
        String sql = "DELETE FROM teacher WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public ObservableList<Integer> getClassIds() throws SQLException {
        ObservableList<Integer> classIds = FXCollections.observableArrayList();
        String sql = "SELECT id FROM groups";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            classIds.add(resultSet.getInt("id"));
        }
        return classIds;
    }

    public ObservableList<Integer> getCourseIds() throws SQLException {
        ObservableList<Integer> courseIds = FXCollections.observableArrayList();
        String sql = "SELECT id FROM cours";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            courseIds.add(resultSet.getInt("id"));
        }
        return courseIds;
    }
}
