package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UC34CalculateMinimumAmountElectricalCableController;
import lapr.project.model.Event;
import lapr.project.model.Stand;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UC34CalculateMinimumAmountElectricalCableUI implements Initializable {
    UC34CalculateMinimumAmountElectricalCableController controller;

    @FXML
    TableView<Event> eventTableUC34;
    @FXML
    TableColumn<Event, String> eventTableUC34ColTitle;
    @FXML
    TableColumn<Event, String> eventTableUC34ColDescription;

    @FXML
    Label labelCableAmount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new UC34CalculateMinimumAmountElectricalCableController();

        eventTableUC34.setItems(FXCollections.observableArrayList(controller.getEvents()));
        eventTableUC34ColTitle.setText("Title");
        eventTableUC34ColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        eventTableUC34ColDescription.setText("Description");
        eventTableUC34ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @FXML
    public void calculateCableAmount() {
        Event e = eventTableUC34.getSelectionModel().getSelectedItem();
        if (e == null) {
            FXUtils.openAlertError("Event must be selected!");
        } else {
            try {
                List<Stand> standList = e.getStandList();

                labelCableAmount.setText(controller.calculateMinimumSpanningTree(standList));

            } catch (NullPointerException npe) {
                FXUtils.openAlertError("The Event '" + e.getTitle() + "' has no Assigned Stands");
            }
        }
    }

    public static class StandCable implements Comparable<StandCable> {
        private String stand1;
        private String stand2;
        private float distance;

        public StandCable(String stand1, String stand2, int distance) {
            this.stand1 = stand1;
            this.stand2 = stand2;
            this.distance = distance;
        }

        public String getStand1() {
            return stand1;
        }

        public String getStand2() {
            return stand2;
        }

        public float getDistance() {
            return distance;
        }

        @Override
        public int compareTo(StandCable o) {
            return this.getDistance() > o.getDistance() ? 1 : this.getDistance() < o.getDistance() ? -1 : 0;
        }

        @Override
        public int hashCode() {
            return this.stand1.hashCode()+this.stand2.hashCode()+Float.hashCode(this.distance);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }
            StandCable standCable = (StandCable) obj;
            BigDecimal thisDistance = BigDecimal.valueOf(this.getDistance());
            BigDecimal otherDistance = BigDecimal.valueOf(standCable.getDistance());
            return this.stand1.equals(standCable.getStand1()) && this.stand2.equals(standCable.getStand2()) && thisDistance.equals(otherDistance);
        }
    }
}
