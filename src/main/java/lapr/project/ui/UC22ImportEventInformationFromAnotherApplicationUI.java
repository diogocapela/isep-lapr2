package lapr.project.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lapr.project.controller.UC22ImportEventInformationFromAnotherApplicationController;
import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.model.User;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UC22ImportEventInformationFromAnotherApplicationUI implements Initializable {

    private UC22ImportEventInformationFromAnotherApplicationController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new UC22ImportEventInformationFromAnotherApplicationController();
    }

    public void addUsers(Event event) {
        for(User user: event.getOrganisers()) {
            if(!controller.getUsers().contains(user)) {
                controller.addUser(user);
            }
        }
        for(User user: event.getStaffMembers()) {
            if(!controller.getUsers().contains(user)) {
                controller.addUser(user);
            }
        }
        for(User user: event.getAttendees()) {
            if(!controller.getUsers().contains(user)) {
                controller.addUser(user);
            }
        }
        for(Application application: event.getApplicationList()) {
            User user = application.getAuthor();
            if(!controller.getUsers().contains(user)) {
                controller.addUser(user);
            }
        }
    }

    @FXML
    private void handleImportFromXMLFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            try {
                Event event = controller.importEventFromXMLFile(selectedFile.toString());
                controller.addEvent(event);
                addUsers(event);
                FXUtils.openAlertSuccess("Event information imported from XML with success!");
            } catch (Exception e) {
                FXUtils.openAlertError("Error importing event from the selected XML file! " + e.getMessage());
            }
        } else {
            FXUtils.openAlertError("No XML file was selected!");
        }
    }

    @FXML
    private void handleImportFromCSVFileAdapter1() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File (Adapter 1)");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            try {
                Event event = controller.importEventFromCSVFileAdapter1(selectedFile.toString());
                controller.addEvent(event);
                addUsers(event);
                FXUtils.openAlertSuccess("Event information imported from CSV (Adapter 1) with success!");
            } catch (Exception e) {
                FXUtils.openAlertError("Error importing event from the selected CSV (Adapter 1) file! " + e.getMessage());
            }
        } else {
            FXUtils.openAlertError("No CSV (Adapter 1) file was selected!");
        }
    }

    @FXML
    private void handleImportFromCSVFileAdapter2() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File (Adapter 2)");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            try {
                Event event = controller.importEventFromCSVFileAdapter2(selectedFile.toString());
                controller.addEvent(event);
                addUsers(event);
                FXUtils.openAlertSuccess("Event information imported from CSV (Adapter 2) with success!");
            } catch (Exception e) {
                FXUtils.openAlertError("Error importing event from the selected CSV (Adapter 2) file! " + e.getMessage());
            }
        } else {
            FXUtils.openAlertError("No CSV (Adapter 2) file was selected!");
        }
    }

}
