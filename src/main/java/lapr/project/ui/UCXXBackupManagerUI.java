package lapr.project.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lapr.project.controller.UCXXBackupManagerController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UCXXBackupManagerUI implements Initializable {

    private UCXXBackupManagerController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new UCXXBackupManagerController();
    }

    @FXML
    private void handleCreateBackup() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        try {
            if (selectedDirectory == null) {
                FXUtils.openAlertError("No valid directory selected!");
            } else {
                controller.createBackup(selectedDirectory.getAbsolutePath());
                FXUtils.openAlertSuccess("Backup created with success!");
            }
        } catch (Exception e) {
            FXUtils.openAlertError(e.getMessage());
        }
    }

    @FXML
    private void handleImportBackup() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML Backup");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            try {
                controller.importBackup(selectedFile.toString());
                FXUtils.openAlertSuccess("Backup imported from XML with success!");
            } catch (Exception e) {
                FXUtils.openAlertError("Error importing backup from the selected XML file! " + e.getMessage());
            }
        } else {
            FXUtils.openAlertError("No XML file was selected.");
        }
    }

}
