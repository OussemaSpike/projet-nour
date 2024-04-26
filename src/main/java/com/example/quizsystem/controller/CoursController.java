package com.example.quizsystem.controller;

import com.example.quizsystem.model.Cours;
import com.example.quizsystem.model.Group;
import com.example.quizsystem.service.CoursService;
import com.example.quizsystem.service.GroupService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class CoursController {





    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TableView<Cours> coursTableView;

    @FXML
    private TableColumn<Cours, Integer> idColumn;

    @FXML
    private TableColumn<Cours, String> nameColumn;


    @FXML




    private CoursService coursService = new CoursService();

    @FXML
    private void initialize() throws SQLException {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        refreshTable();
    }

    @FXML
    private void addCours() throws SQLException {
        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        Cours newCours = new Cours(id, name);
        coursService.create(newCours);
        coursTableView.getItems().add(newCours); // Add the new course to the TableView
        refreshTable();
    }

    @FXML
    private void deleteCours() throws SQLException {
        Cours selectedCours = coursTableView.getSelectionModel().getSelectedItem();
        if (selectedCours != null) {
            coursService.delete(selectedCours.getId());
            coursTableView.getItems().remove(selectedCours);
            refreshTable();
        }
    }

    private void refreshTable() throws SQLException {
        List<Cours> cours = coursService.getAll();
        coursTableView.setItems(FXCollections.observableArrayList(cours));
    }
}
