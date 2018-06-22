/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lapr.project.controller.UC07SubmitApplicationReviewController;
import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.utils.AuthManager;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class UC07SubmitApplicationReviewUI implements Initializable {

    AuthManager authManager = new AuthManager();
    private UC07SubmitApplicationReviewController controller;

    @FXML
    private Button btnSubmit;
    @FXML
    private ComboBox<Event> cboEvent;
    @FXML
    private ComboBox<Application> cboApplication;
    @FXML
    private TextField txtstkr;
    @FXML
    private TextField txtear;
    @FXML
    private TextField txtia;
    @FXML
    private TextField txtrsa;
    @FXML
    private TextField txtor;
    @FXML
    private TextField txtJustification;
    @FXML
    private CheckBox chkAccept;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new UC07SubmitApplicationReviewController();
        cboEvent.getItems().addAll(controller.getEvents());
    }

    @FXML
    private void cboEventSelect(ActionEvent event) {
        cboApplication.getItems().clear();
        cboApplication.getItems().addAll(cboEvent.getSelectionModel().getSelectedItem().getApplicationList());
    }

    @FXML
    private void cboApplicationSelect(ActionEvent event) {
        //get selected app
        Application app = cboApplication.getSelectionModel().getSelectedItem();
        //test if current user is a staff member of this selected application
        if (!app.getStaffReviewers().contains(controller.getLoggedInUser())) {
            FXUtils.openAlertError("You are not assigned for review this application!");
            cboApplication.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void btnSubmitClick() {
        if (cboEvent.getSelectionModel().getSelectedItem() == null) {
            FXUtils.openAlertError("You must select an event!");
            return;
        }
        if (cboApplication.getSelectionModel().getSelectedItem() == null) {
            FXUtils.openAlertError("You must select an application to review!");
            return;
        }
        controller.setEvent(cboEvent.getSelectionModel().getSelectedItem());
        controller.setApplication(cboApplication.getSelectionModel().getSelectedItem());
        try {
            controller.setEventAdequacyRating(Integer.parseInt(txtear.getText()));
        } catch (NumberFormatException nfe) {
            FXUtils.openAlertError("You specified an invalid number on Event Adequacy Rating");
            return;
        }
        try {
            controller.setInviteAdequacyRating(Integer.parseInt(txtia.getText()));
        } catch (NumberFormatException nfe) {
            FXUtils.openAlertError("You specified an invalid number on Invite Adequacy Rating");
            return;
        }
        try {
            controller.setOverallRecommendationRating(Integer.parseInt(txtor.getText()));
        } catch (NumberFormatException nfe) {
            FXUtils.openAlertError("You specified an invalid number on Overall Recommendation Rating");
            return;
        }
        try {
            controller.setRequestedStandAreaRating(Integer.parseInt(txtrsa.getText()));
        } catch (NumberFormatException nfe) {
            FXUtils.openAlertError("You specified an invalid number on Requested Stand Area Rating");
            return;
        }
        try {
            controller.setStaffTopicKnowledgeRating(Integer.parseInt(txtstkr.getText()));
        } catch (NumberFormatException nfe) {
            FXUtils.openAlertError("You specified an invalid number on Staff Topic Knowledge Rating");
            return;
        }
        controller.setJustification(txtJustification.getText());
        controller.setAccept(chkAccept.isSelected());
        Optional<ButtonType> btn = FXUtils.openDialogBox("You are about to Submit an application review. Confirm?");
        if (btn.isPresent() && btn.get() == ButtonType.OK) {
            boolean result = controller.confirm();
            if (result) {
                FXUtils.openAlertSuccess(authManager.getLoggedInUser().getUsername() + " submitted an application review to application: " + cboApplication.getSelectionModel().getSelectedItem().getCompanyTradeName() + " on event: " + cboEvent.getSelectionModel().getSelectedItem().getTitle());
            } else {
                FXUtils.openAlertError("This Staff Member has already submitted a review for this Application");
            }
        }
    }
}
