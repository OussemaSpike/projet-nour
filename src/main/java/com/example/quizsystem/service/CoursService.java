package com.example.quizsystem.service;

import com.example.quizsystem.DatabaseConnection;
import com.example.quizsystem.model.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursService {

    private Connection connection = DatabaseConnection.getConnection();



    public void create(Cours cours) throws SQLException {
        String sql = "INSERT INTO cours (id, name) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cours.getId());
        statement.setString(2, cours.getName());
        statement.executeUpdate();
    }

    public List<Cours> getAll() throws SQLException {
        String sql = "SELECT * FROM cours";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Cours> courses = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            courses.add(new Cours(id, name));
        }

        return courses;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM cours WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
