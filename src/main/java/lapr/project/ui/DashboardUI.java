package lapr.project.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import lapr.project.controller.DashboardController;
import lapr.project.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardUI implements Initializable {

    private DashboardController controller;

    @FXML
    private VBox loginBox;

    @FXML
    private VBox logoutBox;

    @FXML
    private TextField usernameOrEmailInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label loggedInUsername;

    @FXML
    private Label loggedInEmail;

    @FXML
    private Label loggedInName;

    @FXML
    private Label loggedInIsAdmin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new DashboardController();
        updateView();
    }

    private void updateView() {
        // Make setVisible act as CSS display: none
        loginBox.managedProperty().bind(loginBox.visibleProperty());
        logoutBox.managedProperty().bind(logoutBox.visibleProperty());

        User loggedUser = controller.getLoggedInUser();

        if (loggedUser != null) {
            // If a user is logged in
            loggedInUsername.setText(loggedUser.getUsername());
            loggedInEmail.setText(loggedUser.getEmail());
            loggedInName.setText(loggedUser.getName());
            loggedInIsAdmin.setText(Boolean.toString(loggedUser.getIsAdmin()));
            // If a user is logged in show the logoutBox
            loginBox.setVisible(false);
            logoutBox.setVisible(true);
        } else {
            // If no user is logged in show the loginBox
            loginBox.setVisible(true);
            logoutBox.setVisible(false);
        }

        // Clear the input fields
        usernameOrEmailInput.clear();
        passwordInput.clear();
    }

    @FXML
    public void handleLogin() {
        // Get the inputs as a String
        String usernameOrEmail = usernameOrEmailInput.getText();
        String password = passwordInput.getText();
        try {
            if ("".equals(usernameOrEmail)) {
                // If usernameOrEmail is empty
                FXUtils.openAlertError("You need to input a username or an email address.");
            } else if (!controller.isPasswordValid(password)) {
                // If password is invalid
                FXUtils.openAlertError("You need to input a valid password Passwords must have exactly 8 numerical digits.");
            } else {
                // If both usernameOrEmail and password are filled encode password and continue
                String encodedPassword = controller.encodePassword(password);
                if (usernameOrEmail.contains("@")) {
                    // If the input is an email
                    for (User user : controller.getUsers()) {
                        if (usernameOrEmail.equals(user.getEmail()) && encodedPassword.equals(user.getPassword())) {
                            controller.setLoggedInUser(user);
                            updateView();
                        }
                    }
                    if (controller.getLoggedInUser() == null) {
                        FXUtils.openAlertError("Incorrect email or password.");
                    } else {
                        FXUtils.openAlertSuccess("You are now logged in as " + controller.getLoggedInUser().getEmail() + ".");
                    }
                } else {
                    // If the input is a username
                    for (User user : controller.getUsers()) {
                        if (usernameOrEmail.equals(user.getUsername()) && encodedPassword.equals(user.getPassword())) {
                            controller.setLoggedInUser(user);
                            updateView();
                        }
                    }
                    if (controller.getLoggedInUser() == null) {
                        FXUtils.openAlertError("Incorrect username or password.");
                    } else {
                        FXUtils.openAlertSuccess("You are now logged in as " + controller.getLoggedInUser().getUsername() + ".");
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            FXUtils.openAlertError(e.getMessage());
        }
    }

    @FXML
    public void handleLogout() {
        controller.setLoggedInUser(null);
        FXUtils.openAlertSuccess("The user is now logged out.");
        updateView();
    }

    @FXML
    public void keyPressedPw(KeyEvent e){
        if(e.getCode()==KeyCode.ENTER){
            handleLogin();
        }
    }
}
