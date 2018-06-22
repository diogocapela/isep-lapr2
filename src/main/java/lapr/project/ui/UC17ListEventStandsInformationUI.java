package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lapr.project.controller.UC17ListEventStandsInformationController;
import lapr.project.model.Event;
import lapr.project.model.Stand;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UC17ListEventStandsInformationUI implements Initializable {
    UC17ListEventStandsInformationController controller;

    @FXML
    TableView<Event> eventTableUC17;
    @FXML
    TableColumn<Event, String> eventTableUC17ColTitle;
    @FXML
    TableColumn<Event, String> eventTableUC17ColDescription;
    @FXML
    VBox vBoxUC17BarChart;
    @FXML
    BarChart<String,Double> barChartUC17;
    @FXML
    CategoryAxis barChartUC17CategoryAxis;
    @FXML
    NumberAxis barChartUC17NumberAxis;
    @FXML
    Label labelListStandInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new UC17ListEventStandsInformationController();

        eventTableUC17.setItems(FXCollections.observableArrayList(controller.getEvents()));
        eventTableUC17ColTitle.setText("Title");
        eventTableUC17ColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        eventTableUC17ColDescription.setText("Description");
        eventTableUC17ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        barChartUC17.setAnimated(false);
    }

    @FXML
    public void handleListButton() {
        barChartUC17.getData().clear();
        Event e = eventTableUC17.getSelectionModel().getSelectedItem();
        if (e == null) {
            FXUtils.openAlertError("Event must be selected!");
        } else {
            List<Stand> standList = e.getStandList();
            if (standList.isEmpty()) {
                FXUtils.openAlertError("The Event '" + e.getTitle() + "' has no Assigned Stands");
            } else {
                List<List<Double>> result = controller.prepareStandDisplayInfo(standList);

                XYChart.Series<String,Double> series1 = new XYChart.Series<>();

                for (int resultCounter = 0; resultCounter < result.size() - 1; resultCounter++) {
                    series1.getData().add(new XYChart.Data<>(String.format("[%.2f ; %.2f[", result.get(resultCounter).get(0), result.get(resultCounter).get(1)), result.get(resultCounter).get(2)));
                }

                barChartUC17.getData().add(series1);

                labelListStandInfo.setText(String.format("Event '%s' : Average %.2f // Standard Deviation %.2f", e.getTitle(), result.get(result.size() - 1).get(0), result.get(result.size() - 1).get(1)));

            }
        }
    }
}
