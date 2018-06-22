package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UC40OrganizerAssignsStaffMemberToApplicationController;
import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.model.User;
import lapr.project.utils.AuthManager;

import java.net.URL;
import java.util.ResourceBundle;

public class UC40OrganizerAssignsStaffMemberToApplicationUI implements Initializable {
    UC40OrganizerAssignsStaffMemberToApplicationController controller;
    AuthManager authManager = new AuthManager();

    @FXML
    TableView<Event> eventTableUC40;
    @FXML
    TableColumn<Event, String> eventTableUC40ColTitle;
    @FXML
    TableColumn<Event, String> eventTableUC40ColDescription;
    @FXML
    TableView<Application> applicationTableUC40;
    @FXML
    TableColumn<Application, String> applicationTableUC40ColTradeName;
    @FXML
    TableColumn<Application, String> applicationTableUC40ColDescription;
    @FXML
    TableView<User> staffMemberTableUC40;
    @FXML
    TableColumn<User, String> staffMemberTableUC40ColUserName;
    @FXML
    TableColumn<User, String> staffMemberTableUC40ColMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new UC40OrganizerAssignsStaffMemberToApplicationController();

        eventTableUC40.setItems(FXCollections.observableArrayList(controller.getEvents()));
        eventTableUC40ColTitle.setText("Title");
        eventTableUC40ColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        eventTableUC40ColDescription.setText("Description");
        eventTableUC40ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @FXML
    public void handleSelectEventUC40() {
        Event e = eventTableUC40.getSelectionModel().getSelectedItem();
        if (e == null) {
            FXUtils.openAlertError("Event must be Selected!");
            applicationTableUC40.getItems().clear();
            staffMemberTableUC40.getItems().clear();
        } else if (authManager.isOrganiserAtEvent(e)) {
            applicationTableUC40.setItems(FXCollections.observableArrayList(e.getApplicationList()));
            applicationTableUC40ColTradeName.setText("Company Trade Name");
            applicationTableUC40ColTradeName.setCellValueFactory(new PropertyValueFactory<>("companyTradeName"));
            applicationTableUC40ColDescription.setText("Description");
            applicationTableUC40ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

            staffMemberTableUC40.setItems(FXCollections.observableArrayList(e.getStaffMembers()));
            staffMemberTableUC40ColUserName.setText("User Name");
            staffMemberTableUC40ColUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
            staffMemberTableUC40ColMail.setText("Email");
            staffMemberTableUC40ColMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        } else {
            FXUtils.openAlertError("Logged In User must be Organiser at Event!");
        }
    }

    @FXML
    public void handleAssignStaffMemberUC40() {
        Application a = applicationTableUC40.getSelectionModel().getSelectedItem();
        User u = staffMemberTableUC40.getSelectionModel().getSelectedItem();
        if (a == null || u == null) {
            String applicationString = a == null ? "Application must be selected!" : "";
            String connectString = (a == null && u == null) ? "\n" : "";
            String staffMemberString = u == null ? "Staff Member must be selected!" : "";
            FXUtils.openAlertError(String.format("%s%s%s", applicationString, connectString, staffMemberString));
        } else {
            try {
                a.addStaffReviewer(u);
                FXUtils.openAlertSuccess("This Staff Member was successfully assigned as a reviewer in this Application");
            } catch (IllegalArgumentException eAssign) {
                FXUtils.openAlertError(eAssign.getMessage());
            }
        }
    }
}
