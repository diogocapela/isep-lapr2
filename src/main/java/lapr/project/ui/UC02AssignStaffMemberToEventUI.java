package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import lapr.project.controller.UC02AssignStaffMemberToEventController;
import lapr.project.model.Event;
import lapr.project.model.User;
import lapr.project.utils.AuthManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UC02AssignStaffMemberToEventUI implements Initializable {

    private UC02AssignStaffMemberToEventController controller;
    private AuthManager authManager = new AuthManager();

    @FXML
    private ChoiceBox<User> usersChoiceBox;
    @FXML
    private ChoiceBox<Event> eventsChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new UC02AssignStaffMemberToEventController();
        updateView();
    }

    private void updateView() {
        usersChoiceBox.setItems(FXCollections.observableArrayList(controller.getUsers()));
        eventsChoiceBox.setItems(FXCollections.observableArrayList(controller.getEvents()));
    }

    public void handleAddUserToStaffMembers() {
        User user = usersChoiceBox.getValue();
        Event event = eventsChoiceBox.getValue();
        if (user != null && event != null) {
            List<User> organisers = event.getOrganisers();
            if (!organisers.contains(authManager.getLoggedInUser())) {
                FXUtils.openAlertError("You must be an organiser to add a staff member to the event!");
                return;
            }
            try {
                controller.addUserToStaffMembers(user, event);
            } catch (IllegalArgumentException e) {
                FXUtils.openAlertError(e.getMessage());
            }
            FXUtils.openAlertSuccess("The user with username " + user.getUsername() + " was added as a staff member for the event with the title " + event.getTitle() + ".");
            updateView();
        } else {
            FXUtils.openAlertError("You need to select an user and an event");
        }
    }

    public void handleDeleteUserFromStaffMembers() {
        //Are you sure?
    }

}
