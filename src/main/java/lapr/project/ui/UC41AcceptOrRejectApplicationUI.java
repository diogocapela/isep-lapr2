package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UC41AcceptOrRejectApplicationController;
import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.utils.AuthManager;

import java.net.URL;
import java.util.ResourceBundle;

public class UC41AcceptOrRejectApplicationUI implements Initializable {
    UC41AcceptOrRejectApplicationController controller;
    AuthManager authManager = new AuthManager();

    @FXML
    ComboBox<Event> eventComboBoxUC41;
    @FXML
    TableView<Application> applicationTableUC41;
    @FXML
    TableColumn<Application, String> applicationTableUC41ColTradeName;
    @FXML
    TableColumn<Application, String> applicationTableUC41ColDescription;
    @FXML
    TableColumn<Application, String> applicationTableUC41ColIsAccepted;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new UC41AcceptOrRejectApplicationController();
        eventComboBoxUC41.setItems(FXCollections.observableArrayList(controller.getEvents()));
        applicationTableUC41ColTradeName.setText("Company Trade Name");
        applicationTableUC41ColDescription.setText("Description");
        applicationTableUC41ColIsAccepted.setText("Application Status");
        eventComboBoxUC41.setOnAction(e -> {
            applicationTableUC41.setItems(FXCollections.observableArrayList(eventComboBoxUC41.getSelectionModel().getSelectedItem().getApplicationList()));
            applicationTableUC41ColTradeName.setCellValueFactory(new PropertyValueFactory<>("companyTradeName"));
            applicationTableUC41ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            applicationTableUC41ColIsAccepted.setCellValueFactory(new PropertyValueFactory<>("status"));
        });
    }

    private boolean handlePreButtonUC41(Event e, Application a) {
        if (e == null) {
            FXUtils.openAlertError("Event must be selected!");
        } else if (a == null) {
            FXUtils.openAlertError("Application must be selected!");
        } else if (!(authManager.isEventManager() || authManager.isOrganiserAtEvent(e))) {
            FXUtils.openAlertError("Only Event Managers or Organizers can accept/reject Applications");
        } else {
            return true;
        }
        return false;
    }

    private void handlePostButtonUC41() {
        applicationTableUC41.getItems().clear();
        applicationTableUC41.setItems(FXCollections.observableArrayList(eventComboBoxUC41.getSelectionModel().getSelectedItem().getApplicationList()));
    }

    @FXML
    public void handleAcceptApplication() {
        Event e = eventComboBoxUC41.getSelectionModel().getSelectedItem();
        Application a = applicationTableUC41.getSelectionModel().getSelectedItem();
        if (handlePreButtonUC41(e, a)) {
            a.setAccepted();
            FXUtils.openAlertSuccess("Application has been successfully accepted!");
            handlePostButtonUC41();
        }
    }

    @FXML
    public void handleRejectApplication() {
        Event e = eventComboBoxUC41.getSelectionModel().getSelectedItem();
        Application a = applicationTableUC41.getSelectionModel().getSelectedItem();
        if (handlePreButtonUC41(e, a)) {
            a.setRejected();
            FXUtils.openAlertSuccess("Application has been successfully rejected!");
            handlePostButtonUC41();
        }
    }

}
