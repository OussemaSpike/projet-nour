package com.example.quizsystem.service;

import com.example.quizsystem.DatabaseConnection;
import com.example.quizsystem.model.Cours;
import com.example.quizsystem.model.Option;
import com.example.quizsystem.model.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {


    private Connection connection = DatabaseConnection.getConnection();

    public Question addQuestion(Question question, String courseName) throws SQLException {
        int courseId = getCourseIdByName(courseName);
        if (courseId == -1) {
            throw new SQLException("Course with name " + courseName + " does not exist.");
        }
        String query = "INSERT INTO question (text, difficulty, course_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, question.getText());
            preparedStatement.setString(2, question.getDifficulty());
            preparedStatement.setInt(3, courseId);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                question.setId(id);
                for (Option option : question.getOptions()) {
                    option.setQuestionId(id); // Set the question id for the option
                    addOptionForQuestion(option);
                }
            }
        }
        return question;
    }

    public ObservableList<String> getCourseNames() throws SQLException {
        String query = "SELECT name FROM cours";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            ObservableList<String> courseNames = FXCollections.observableArrayList();
            while (resultSet.next()) {
                courseNames.add(resultSet.getString("name"));
            }
            return courseNames;
        }
    }

    public int getCourseIdByName(String courseName) throws SQLException {
        String query = "SELECT id FROM cours WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        return -1;
    }


    private void addOptionForQuestion(Option option) throws SQLException {
        String query = "INSERT INTO options (text, isCorrect, question_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, option.getText());
            preparedStatement.setBoolean(2, option.isCorrect());
            preparedStatement.setInt(3, option.getQuestionId());
            preparedStatement.executeUpdate();
        }
    }
    public List<Question> getAllQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM question";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String questionText = resultSet.getString("text");
                String difficulty = resultSet.getString("difficulty");
                String courseName = getCourseNameById(resultSet.getInt("course_id"));
                List<Option> options = getOptionsForQuestion(id);
                Question question = new Question(questionText, difficulty, new Cours(courseName), options);
                question.setId(id);
                questions.add(question);
            }
        }
        return questions;
    }

    private String getCourseNameById(int courseId) throws SQLException {
        String query = "SELECT name FROM Course WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        }
        return null;
    }

    private List<Option> getOptionsForQuestion(int questionId) throws SQLException {
        List<Option> options = new ArrayList<>();
        String query = "SELECT * FROM option WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, questionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                boolean isCorrect = resultSet.getBoolean("isCorrect");
                Option option = new Option(id, text, isCorrect, questionId);
                options.add(option);
            }
        }
        return options;
    }

}
