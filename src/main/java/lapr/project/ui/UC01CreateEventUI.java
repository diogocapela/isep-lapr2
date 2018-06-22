package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UC01CreateEventController;
import lapr.project.model.Event;
import lapr.project.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class UC01CreateEventUI implements Initializable {

    private UC01CreateEventController controller;

    @FXML
    private TableView<Event> table;
    @FXML
    private TableColumn<Event, String> col2;
    @FXML
    private TableColumn<Event, String> col3;
    @FXML
    private TableColumn<Event, String> col4;
    @FXML
    private TableColumn<Event, String> col5;
    @FXML
    private TableColumn<Event, String> col6;
    @FXML
    private TableColumn<Event, String> col7;
    @FXML
    private TableColumn<Event, String> col8;
    @FXML
    private ChoiceBox<String> inputType;
    @FXML
    private TextField inputTitle;
    @FXML
    private TextField inputDescription;
    @FXML
    private TextField inputLocation;
    @FXML
    private TextField inputDate;
    @FXML
    private TextField inputDeadline;
    @FXML
    private ChoiceBox<Boolean> inputIsOpenToApplications;
    @FXML
    private ChoiceBox<User> inputOrganiser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new UC01CreateEventController();
        updateView();
    }

    private void updateView() {
        // update table
        col2.setCellValueFactory(new PropertyValueFactory<>("type"));
        col3.setCellValueFactory(new PropertyValueFactory<>("title"));
        col4.setCellValueFactory(new PropertyValueFactory<>("description"));
        col5.setCellValueFactory(new PropertyValueFactory<>("location"));
        col6.setCellValueFactory(new PropertyValueFactory<>("date"));
        col7.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        col8.setCellValueFactory(new PropertyValueFactory<>("isOpenToApplications"));
        table.setItems(FXCollections.observableArrayList(controller.getEvents()));
        table.refresh();

        inputType.setItems(FXCollections.observableArrayList("exhibition", "congress"));

        inputIsOpenToApplications.setItems(FXCollections.observableArrayList(true, false));

        inputOrganiser.setItems(FXCollections.observableArrayList(controller.getUsers()));

        // clear input fields
        inputType.getSelectionModel().clearSelection();
        inputTitle.clear();
        inputDescription.clear();
        inputLocation.clear();
        inputDate.clear();
        inputDeadline.clear();
        inputIsOpenToApplications.getSelectionModel().clearSelection();
        inputOrganiser.getSelectionModel().clearSelection();

        if (!controller.getLoggedInUser().getIsAdmin()) {
            table.setVisible(false);
        }

    }

    @FXML
    private void handleAddEvent() {
        try {
            Event event = new Event();
            boolean valid = true;
            StringBuilder sb = new StringBuilder();
            if (inputType.getSelectionModel().getSelectedItem() != null) {
                event.setType(inputType.getValue());
            } else {
                sb.append("Event Type must be selected!");
                valid = false;
            }
            event.setTitle(inputTitle.getText());
            event.setDescription(inputDescription.getText());
            event.setLocation(inputLocation.getText());
            event.setDate(inputDate.getText());
            event.setDeadline(inputDeadline.getText());
            if (inputIsOpenToApplications.getSelectionModel().getSelectedItem() != null) {
                event.setIsOpenToApplications(inputIsOpenToApplications.getValue());
            } else {
                sb.append(String.format("%sEvent's Open to Applications setting must be selected!", valid ? "" : "\n"));
                valid = false;
            }
            if (inputOrganiser.getSelectionModel().getSelectedItem() != null) {
                event.addOrganiser(inputOrganiser.getValue());
            } else {
                sb.append(String.format("%sEvent's Organiser must be selected!", valid ? "" : "\n"));
                valid = false;
            }

            if (valid) {
                controller.addEvent(event);
                FXUtils.openAlertSuccess("Event added successfully!");
                updateView();
            } else {
                FXUtils.openAlertError(sb.toString());
            }

        } catch (Exception e) {
            FXUtils.openAlertError(e.getMessage());
        }
    }

    @FXML
    private void handleDeleteEvent() {
        try {
            Event selectedEvent = table.getSelectionModel().getSelectedItem();
            if (selectedEvent != null) {
                controller.deleteEvent(selectedEvent);
                FXUtils.openAlertSuccess("Event deleted successfully!");
                updateView();
            }
        } catch (Exception e) {
            FXUtils.openAlertError(e.getMessage());
        }
    }

}
