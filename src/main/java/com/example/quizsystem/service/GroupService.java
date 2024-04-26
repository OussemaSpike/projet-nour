package com.example.quizsystem.service;

import com.example.quizsystem.DatabaseConnection;
import com.example.quizsystem.model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GroupService {
    // SQL queries
    private static final String INSERT_QUERY = "INSERT INTO `groups` (name, student_nbr) VALUES (?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `groups`";

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM `groups` WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE `groups` SET student_nbr= ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM `groups` WHERE id = ? ";

    // Method to create a group
    public void create(Group group) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
            stmt.setString(1, group.getName());
            stmt.setInt(2, group.studentsNumberProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Group group = new Group(rs.getInt("id"), rs.getString("name"), rs.getInt("student_nbr"));
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public Group getById(int id) {
        Group group = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_QUERY)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                group = new Group(rs.getInt("id"), rs.getString("name"), rs.getInt("student_nbr"));
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }
    // Method to update a group
    public void update( int id, int students_nbr) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
            stmt.setInt(1, students_nbr);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a group
    public void delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}