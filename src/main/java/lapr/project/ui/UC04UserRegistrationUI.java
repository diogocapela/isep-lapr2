package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UC04UserRegistrationController;
import lapr.project.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class UC04UserRegistrationUI implements Initializable {

    UC04UserRegistrationController controller;

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> col1;
    @FXML
    private TableColumn<User, String> col2;
    @FXML
    private TableColumn<User, String> col3;
    @FXML
    private TableColumn<User, String> col4;
    @FXML
    private TableColumn<User, String> col5;
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputPassword;
    @FXML
    private TextField inputName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new UC04UserRegistrationController();
        updateView();
    }

    private void updateView() {
        // update table
        col1.setCellValueFactory(new PropertyValueFactory<>("username"));
        col2.setCellValueFactory(new PropertyValueFactory<>("email"));
        col3.setCellValueFactory(new PropertyValueFactory<>("password"));
        col4.setCellValueFactory(new PropertyValueFactory<>("name"));
        col5.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
        table.setItems(FXCollections.observableArrayList(controller.getUsers()));
        // clear input fields
        inputUsername.clear();
        inputEmail.clear();
        inputPassword.clear();
        inputName.clear();
        
        if(controller.getLoggedInUser()==null || !(controller.getLoggedInUser().getIsAdmin())){
            table.setVisible(false);
        }
    }

    @FXML
    private void handleAddUser() {
        try {
            controller.addUser(new User(
                    inputUsername.getText(),
                    inputEmail.getText(),
                    inputPassword.getText(),
                    inputName.getText()
            ));
            FXUtils.openAlertSuccess("User registered successfully!");
            updateView();
        } catch (IllegalArgumentException e) {
            FXUtils.openAlertError(e.getMessage());
        }
    }

    @FXML
    private void handleDeleteUser() {
        User selectedUser = table.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            controller.deleteUser(selectedUser);
            FXUtils.openAlertSuccess("User deleted successfully!");
            updateView();
        }
    }

}
