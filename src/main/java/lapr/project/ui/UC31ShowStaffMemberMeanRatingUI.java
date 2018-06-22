package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UC31ShowStaffMemberMeanRatingController;
import lapr.project.model.Expo;
import lapr.project.model.User;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class UC31ShowStaffMemberMeanRatingUI implements Initializable {
    UC31ShowStaffMemberMeanRatingController controller;

    @FXML
    TableView<User> userTableUC31;
    @FXML
    private TableColumn<User, String> userTableUC31ColUsername;
    @FXML
    private TableColumn<User, String> userTableUC31ColMail;

    @FXML
    private Label labelCalculateResult;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new UC31ShowStaffMemberMeanRatingController();

        userTableUC31.setItems(FXCollections.observableArrayList(controller.getUsers()));
        userTableUC31ColUsername.setText("User Name");
        userTableUC31ColUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        userTableUC31ColMail.setText("Email");
        userTableUC31ColMail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    public void handleCalculateButton() {
        User u = userTableUC31.getSelectionModel().getSelectedItem();
        if (u == null) {
            labelCalculateResult.setText("");
            FXUtils.openAlertError("User must be selected!");
        } else if (Expo.getInstance().getEventRegistry().getEvents().isEmpty()) {
            FXUtils.openAlertError("This EXPO has no registered Events!");
        } else {
            BigDecimal result = BigDecimal.valueOf(controller.calculateMeanRatingAllEvents(u, Expo.getInstance().getEventRegistry().getEvents()));
            if (result.equals(BigDecimal.valueOf(Double.parseDouble("-1")))) {
                labelCalculateResult.setText(String.format("User '%s' has no reviews", u.getUsername()));
            } else {
                labelCalculateResult.setText(
                        String.format("The Review Mean Rating of the User '%s' is : %.2f", u.getUsername(), result));
            }
        }
    }
}
