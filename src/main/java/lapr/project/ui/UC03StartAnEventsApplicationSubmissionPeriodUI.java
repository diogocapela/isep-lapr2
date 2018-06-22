package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UC03StartAnEventsApplicationSubmissionPeriodController;
import lapr.project.model.Event;
import lapr.project.utils.AuthManager;

import java.net.URL;
import java.util.ResourceBundle;

public class UC03StartAnEventsApplicationSubmissionPeriodUI implements Initializable {

    private UC03StartAnEventsApplicationSubmissionPeriodController controller;
    private AuthManager authManager;

    @FXML
    private TableView<Event> table;
    @FXML
    private TableColumn<Event, String> colTitle;
    @FXML
    private TableColumn<Event, String> colDescription;
    @FXML
    private TableColumn<Event, String> colIsOpenToApplications;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new UC03StartAnEventsApplicationSubmissionPeriodController();
        authManager = new AuthManager();
        updateView();
    }

    private void updateView() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colIsOpenToApplications.setCellValueFactory(new PropertyValueFactory<>("isOpenToApplications"));
        table.setItems(FXCollections.observableArrayList(controller.getEvents()));
        table.refresh();
    }

    @FXML
    private void handleStartEventApplicationSubmissionPeriod() {
        Event selectedEvent = table.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            FXUtils.openAlertError("You must select an event!");
            return;
        }
        if (!selectedEvent.getOrganisers().contains(authManager.getLoggedInUser())) {
            FXUtils.openAlertError("You need to be an organiser to start this event application submission period!");
            return;
        }
        try {
            controller.startApplicationSubmissionPeriod(selectedEvent);
            FXUtils.openAlertSuccess("The application submission period is now open for the selected event!");
            updateView();
        } catch (Exception e) {
            FXUtils.openAlertError(e.getMessage());
        }

    }

    @FXML
    private void handleStopEventApplicationSubmissionPeriod() {
        Event selectedEvent = table.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            FXUtils.openAlertError("You must select an event!");
            return;
        }
        if (!selectedEvent.getOrganisers().contains(authManager.getLoggedInUser())) {
            FXUtils.openAlertError("You need to be an organiser to stop this event application submission period!");
            return;
        }
        try {
            controller.stopApplicationSubmissionPeriod(selectedEvent);
            FXUtils.openAlertSuccess("The application submission period is now closed for the selected event!");
            updateView();
        } catch (Exception e) {
            FXUtils.openAlertError(e.getMessage());
        }
    }

}
