package com.example.quizsystem.controller;

import com.example.quizsystem.model.Group;
import com.example.quizsystem.service.GroupService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class GroupManagementController {


    @FXML
    private TextField groupNameTextField;

    @FXML
    private TextField studentsNumberTextField;

    @FXML TextField groupeId;

    @FXML
    private TableView<Group> groupTableView;

    @FXML
    private TextField resultTextField;

    private GroupService groupService = new GroupService();

    @FXML
    private void initialize() {
        // Initialize TableView columns

        TableColumn<Group, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Group, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<Group, Integer> studentsNumberColumn = new TableColumn<>("Students Number");
        studentsNumberColumn.setCellValueFactory(cellData -> cellData.getValue().studentsNumberProperty().asObject());

        groupTableView.getColumns().addAll(idColumn, nameColumn, studentsNumberColumn);

        // Add a listener to the TableView's selection model
        groupTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateGroup();
            }
        });

        // Load data into TableView
        refreshTable();
    }

    @FXML
    private void addGroup() {
        // Retrieve group name and students number from text fields
        String groupName = groupNameTextField.getText();
        int studentsNumber = Integer.parseInt(studentsNumberTextField.getText());

        // Create a new group
        Group newGroup = new Group(0, groupName, studentsNumber);
        groupService.create(newGroup);

        resultTextField.setText("Group added successfully");
        refreshTable();
    }

    @FXML
    void FillForm(MouseEvent event) {
        Group selectedGroup = groupTableView.getSelectionModel().getSelectedItem();
        if (selectedGroup != null) {
            groupNameTextField.setText(selectedGroup.getName());
            groupNameTextField.setDisable(true); // Disable groupNameTextField

            studentsNumberTextField.setText(String.valueOf(selectedGroup.getStudentsNumber()));

            groupeId.setText(String.valueOf(selectedGroup.getId()));
            groupeId.setDisable(true); // Disable groupeId
        }
    }

    @FXML
    private void updateGroup() {
        // Retrieve group name and students number from text fields
        String newGroupName = groupNameTextField.getText();
        // Check if studentsNumberTextField is empty
        if (studentsNumberTextField.getText().isEmpty()) {
            resultTextField.setText("Waiting to be updated");
            return;
        }
        int newStudentsNumber = Integer.parseInt(studentsNumberTextField.getText());
        int selectedGroupId = groupTableView.getSelectionModel().getSelectedItem().getId();
        Group selectedGroup = groupService.getById(selectedGroupId);
        if (selectedGroup != null) {
            selectedGroup.setName(newGroupName);
            selectedGroup.setStudentsNumber(newStudentsNumber);
            groupService.update(selectedGroup.getId(), newStudentsNumber);
            resultTextField.setText("Group updated successfully");
            refreshTable();
        } else {
            resultTextField.setText("Please select a group to update");
        }
    }


    private void refreshTable() {
        List<Group> groups = groupService.getAll();
        groupTableView.setItems(FXCollections.observableArrayList(groups));
    }

    @FXML
    private void deleteGroup() {
        Group selectedGroup = groupTableView.getSelectionModel().getSelectedItem();
        if (selectedGroup != null) {
            // Delete the selected group
            groupService.delete(selectedGroup.getId());
            resultTextField.setText("Group deleted successfully");
            refreshTable();
        } else {
            resultTextField.setText("Please select a group to delete");
        }
    }
}
