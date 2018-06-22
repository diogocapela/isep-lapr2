package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UC30ShowEventAcceptanceRateController;
import lapr.project.model.Event;

import java.net.URL;
import java.util.ResourceBundle;

public class UC30ShowEventAcceptanceRateUI implements Initializable {
    UC30ShowEventAcceptanceRateController controller;

    @FXML
    private TableView<Event> eventTable;
    @FXML
    private TableColumn<Event, String> eventColTitle;
    @FXML
    private TableColumn<Event, String> eventColDescription;
    @FXML
    private Label labelAcceptanceRate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new UC30ShowEventAcceptanceRateController();
        eventTable.setItems(FXCollections.observableArrayList(controller.getEvents()));
        eventColTitle.setText("Title");
        eventColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        eventColDescription.setText("Description");
        eventColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @FXML
    public void handleGetButtonEvent() {
        Event e = eventTable.getSelectionModel().getSelectedItem();
        if(e != null){
            labelAcceptanceRate.setText("Acceptance Rate : " + Float.toString(controller.getEventAcceptanceRate(e)) + " %");
        } else {
            FXUtils.openAlertError("Event must be selected!");
        }

    }
}
