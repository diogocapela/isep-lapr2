package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UC31ShowStaffMemberMeanRatingController;
import lapr.project.controller.UC32ShowDeviationBetweenStaffAverageRatingForEventMeanController;
import lapr.project.model.Event;
import lapr.project.model.Expo;
import lapr.project.model.User;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UC32ShowDeviationBetweenStaffAverageRatingForEventMeanUI implements Initializable {
    UC32ShowDeviationBetweenStaffAverageRatingForEventMeanController controllerUC32;

    UC31ShowStaffMemberMeanRatingController controllerUC31;

    @FXML
    TableView<Event> eventTableUC32;
    @FXML
    TableColumn<Event, String> eventTableUC32ColTitle;
    @FXML
    TableColumn<Event, String> eventTableUC32ColDescription;

    @FXML
    Label labelResultMeanDeviation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controllerUC31 = new UC31ShowStaffMemberMeanRatingController();
        controllerUC32 = new UC32ShowDeviationBetweenStaffAverageRatingForEventMeanController();

        eventTableUC32.setItems(FXCollections.observableArrayList(controllerUC32.getEvents()));
        eventTableUC32ColTitle.setText("Title");
        eventTableUC32ColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        eventTableUC32ColDescription.setText("Description");
        eventTableUC32ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @FXML
    public void handleCalculateButton() {
        Event e = eventTableUC32.getSelectionModel().getSelectedItem();
        if (e == null) {
            FXUtils.openAlertError("Event must be selected!");
            labelResultMeanDeviation.setText("");
        } else {

            List<BigDecimal> usersAverageRatings = new ArrayList<>();
            for (User u : Expo.getInstance().getUserRegistry().getUsers()) {
                BigDecimal userAvgRating = BigDecimal.valueOf(controllerUC31.calculateMeanRatingAllEvents(u, Expo.getInstance().getEventRegistry().getEvents()));
                if (!userAvgRating.equals(BigDecimal.valueOf(-1f))) {
                    usersAverageRatings.add(userAvgRating);
                }
            }

            if (usersAverageRatings.isEmpty()) {
                labelResultMeanDeviation.setText("This EXPO has no user reviews");
            } else {
                BigDecimal result = controllerUC32.calculateMeanDeviation(e, usersAverageRatings);
                if (result.equals(BigDecimal.valueOf(-6f))) {
                    labelResultMeanDeviation.setText(String.format("The Event '%s' has no user reviews", e.getTitle()));
                } else {
                    labelResultMeanDeviation.setText(String.format("The Mean Deviation Between the Average Ratings of Staff Members and the '%s' Event's Mean Rating is : %s", e.getTitle(), result.toString()));
                }
            }

        }
    }
}
