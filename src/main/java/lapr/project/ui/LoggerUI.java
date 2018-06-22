package lapr.project.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import lapr.project.utils.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggerUI implements Initializable {

    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (String log : Logger.getLogFromFile(Logger.LOG_FILE_PATH)) {
            listView.getItems().add(log);
        }
    }

}
