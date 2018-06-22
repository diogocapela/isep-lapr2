package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UCXXListEventApplicationsController;
import lapr.project.model.Application;
import lapr.project.model.Event;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UCXXListEventApplicationsUI implements Initializable {

    private UCXXListEventApplicationsController controller;

    @FXML
    private TableView<Application> table;

    @FXML
    private TableColumn<Application, String> colCompanyTradeName;

    @FXML
    private TableColumn<Application, Integer> colVatNumber;

    @FXML
    private TableColumn<Application, Integer> colPhoneNumber;

    @FXML
    private TableColumn<Application, Integer> colNumberOfInvitations;

    @FXML
    private TableColumn<Application, Integer> colIntendedStandArea;

    @FXML
    private TableColumn<Application, Boolean> colStatus;

    @FXML
    private ChoiceBox<Event> eventsChoiceBox;

    @FXML
    private ChoiceBox<String> applicationStatusChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new UCXXListEventApplicationsController();
        eventsChoiceBox.setItems(FXCollections.observableArrayList(controller.getEvents()));
        applicationStatusChoiceBox.setItems(FXCollections.observableArrayList(
                "Submitted", "Review-Pending", "Accepted", "Rejected"
        ));
    }

    private void updateTable(List<Application> applicationList) {
        colCompanyTradeName.setCellValueFactory(new PropertyValueFactory<>("companyTradeName"));
        colVatNumber.setCellValueFactory(new PropertyValueFactory<>("vatNumber"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colNumberOfInvitations.setCellValueFactory(new PropertyValueFactory<>("numberOfInvitations"));
        colIntendedStandArea.setCellValueFactory(new PropertyValueFactory<>("intendedStandArea"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        table.setItems(FXCollections.observableArrayList(applicationList));
    }

    @FXML
    private void handleShowApplications() {
        Event event = eventsChoiceBox.getValue();
        String filter = applicationStatusChoiceBox.getValue();
        if(event != null && filter != null) {
            switch (filter) {
                case "Submitted":
                    updateTable(controller.getAllApplications(event));
                    break;
                case "Review-Pending":
                    updateTable(controller.getFilteredApplications(event, "reviewPending"));
                    break;
                case "Accepted":
                    updateTable(controller.getFilteredApplications(event, "accepted"));
                    break;
                case "Rejected":
                    updateTable(controller.getFilteredApplications(event, "rejected"));
                    break;
                default:
                    FXUtils.openAlertError("Error in the selected filter!");
                    break;
            }
        } else {
            FXUtils.openAlertError("You must select an event and a status filter!");
        }
    }

}
