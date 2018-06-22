package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import lapr.project.controller.UC08SubmitWorkshopSurveyController;
import lapr.project.model.Event;
import lapr.project.model.Workshop;
import lapr.project.utils.AuthManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UC08SubmitWorkshopSurveyUI implements Initializable {

    private AuthManager authManager;

    @FXML
    private ChoiceBox<Event> inputEventChoiceBox;

    @FXML
    private ListView<Workshop> inputWorkshopsListView;

    private Event selectedEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UC08SubmitWorkshopSurveyController controller;
        controller = new UC08SubmitWorkshopSurveyController();
        authManager = new AuthManager();
        inputEventChoiceBox.setItems(FXCollections.observableArrayList(controller.getEvents()));
        inputWorkshopsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        inputEventChoiceBox.setOnAction(e -> {
            inputWorkshopsListView.getItems().clear();
            selectedEvent = inputEventChoiceBox.getValue();
            List<Workshop> workshops = controller.getAcceptedWorkshops(selectedEvent);
            inputWorkshopsListView.getItems().addAll(workshops);
        });
    }

    @FXML
    private void handleSubmitSurvey() {
        if (authManager.isEventManager()) {
            FXUtils.openAlertError("Event managers cannot submit workshop surveys!");
            return;
        } else if (authManager.isOrganiserAtEvent(selectedEvent)) {
            FXUtils.openAlertError("Organisers cannot submit workshop surveys to their own event!");
            return;
        } else if (authManager.isStaffMemberAtEvent(selectedEvent)) {
            FXUtils.openAlertError("Staff members cannot submit workshop surveys to their own event!");
            return;
        }
        if (inputWorkshopsListView.getSelectionModel().getSelectedItem() == null) {
            FXUtils.openAlertError("You must select a workshop!");
            return;
        }
        if (inputEventChoiceBox.getSelectionModel().getSelectedItem() == null) {
            FXUtils.openAlertError("You must select an Event!");
            return;
        }
        Workshop selectedWorkshop = inputWorkshopsListView.getSelectionModel().getSelectedItem();
        try {
            selectedWorkshop.addInterestedUser(authManager.getLoggedInUser());
            if (!selectedEvent.getAttendees().contains(authManager.getLoggedInUser())) {
                selectedEvent.addAttendee(authManager.getLoggedInUser());
            }
            FXUtils.openAlertSuccess("Survey added with success!");
        } catch (Exception e) {
            FXUtils.openAlertError(e.getMessage());
        }

    }

}
