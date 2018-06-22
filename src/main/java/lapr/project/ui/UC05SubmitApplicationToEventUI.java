package lapr.project.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lapr.project.controller.UC05SubmitApplicationToEventController;
import lapr.project.model.DisplayProduct;
import lapr.project.model.Event;
import lapr.project.model.Keyword;
import lapr.project.model.Workshop;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import lapr.project.model.Equipment;

/**
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class UC05SubmitApplicationToEventUI implements Initializable {

    private static final String UNAUTHMSG = "You must be authenticated!";
    UC05SubmitApplicationToEventController controller;
    @FXML
    private ComboBox<Event> cboLstEvents;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnAddProducts;
    @FXML
    private Button btnRemoveProducts;
    @FXML
    private Button btnAddKeyword;
    //workshop
    @FXML
    private Button btnRemoveKeyword;
    @FXML
    private Button btnAddWorkshop;
    @FXML
    private Button btnRemoveWorkshop;
    @FXML
    private Button btnAddNecessaryEquipment;
    @FXML
    private Button btnRemoveNecessaryEquipment;
    @FXML
    private TextField txtCompanyTradeName;
    @FXML
    private TextField txtVAT;
    @FXML
    private TextField txtIntendedArea;
    @FXML
    private TextField txtProduct;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtKeyword;
    @FXML
    private TextField txtInventations;
    //workshop
    @FXML
    private TextField txtWorkshopTitle;
    @FXML
    private TextField txtWorkshopDescription;
    @FXML
    private TextField txtWorkshopRoom;
    @FXML
    private TextField txtWorkshopDuration;
    @FXML
    private TextField txtEquipments;
    @FXML
    private ListView<DisplayProduct> lstProducts;
    @FXML
    private ListView<Keyword> lstKeywords;
    //workshop
    @FXML
    private ListView<String> lstNecessaryEquipment;
    @FXML
    private ListView<Workshop> lstWorkshop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            controller = new UC05SubmitApplicationToEventController();
            refreshCboEvents();
        } catch (IllegalArgumentException ex) {
            FXUtils.openAlertError(ex.getMessage());
        }
    }

    /**
     * Events
     */
    @FXML
    private void refreshCboEvents() {
        controller.getEvents().stream().filter(e -> (!cboLstEvents.getItems().contains(e))).forEachOrdered(e -> {
            cboLstEvents.getItems().add(e);
        });
    }

    @FXML
    private void btnSubmitClick() {
        if (controller == null) {
            FXUtils.openAlertError(UNAUTHMSG);
        } else if (cboLstEvents.getSelectionModel().getSelectedItem() != null) {
            if (!cboLstEvents.getSelectionModel().getSelectedItem().getIsOpenToApplications()) {
                FXUtils.openAlertError("This event is not open to applications!");
                return;
            }
            if (controller.getLoggedInUser().getIsAdmin()) {
                FXUtils.openAlertError("Event managers  or Administrators cannot submit applications!");
                return;
            }
            if (cboLstEvents.getSelectionModel().getSelectedItem().getStaffMembers().contains(controller.getLoggedInUser())) {
                FXUtils.openAlertError("You are a staff member for this event!");
                return;
            }
            if (cboLstEvents.getSelectionModel().getSelectedItem().getOrganisers().contains(controller.getLoggedInUser())) {
                FXUtils.openAlertError("You are an organizer for this event!");
                return;
            }
            controller.setEventForApplication(cboLstEvents.getSelectionModel().getSelectedItem());
            try {
                if (txtCompanyTradeName.getText().length() < 1) {
                    throw new IllegalArgumentException();
                }
                controller.setCompanyTradeName(txtCompanyTradeName.getText());
            } catch (IllegalArgumentException ex) {
                FXUtils.openAlertError("Company trade name must be filled.");
                return;
            }
            try {
                if (txtVAT.getText().length() < 1) {
                    throw new IllegalArgumentException();
                }
                controller.setVatNumber(Integer.parseInt(txtVAT.getText()));
            } catch (IllegalArgumentException ex) {
                FXUtils.openAlertError("Data for VAT is wrong.");
                return;
            }
            try {
                if (txtIntendedArea.getText().length() < 1) {
                    throw new IllegalArgumentException();
                }
                controller.setIntendedStandArea(Double.parseDouble(txtIntendedArea.getText()));
            } catch (IllegalArgumentException ex) {
                FXUtils.openAlertError("Data for intended area is wrong.");
                return;
            }
            try {
                if (txtPhoneNumber.getText().length() < 1) {
                    throw new IllegalArgumentException();
                }
                controller.setPhoneNumber(Integer.parseInt(txtPhoneNumber.getText()));
            } catch (IllegalArgumentException ex) {
                FXUtils.openAlertError("Data phone number is wrong.");
                return;
            }
            try {
                if (txtInventations.getText().length() < 1) {
                    throw new IllegalArgumentException();
                }
                controller.setNumberOfInvitations(Integer.parseInt(txtInventations.getText()));

            } catch (IllegalArgumentException ex) {
                FXUtils.openAlertError("Data for number of inventations is wrong.");
                return;
            }
            List<DisplayProduct> dplist = new ArrayList<>();
            for(DisplayProduct dp : lstProducts.getItems()){
                dplist.add(new DisplayProduct(dp.getName()));
            }
            controller.setDisplayProducts(dplist);
            
            if (lstKeywords.getItems().size() < 2 || lstKeywords.getItems().size() > 5) {
                FXUtils.openAlertError("The number of keywords must be from 2 to 5.");
                return;
            }
            List<Keyword> lk = new ArrayList<>();
            for(Keyword k : lstKeywords.getItems()){
                lk.add(new Keyword(k.getValue()));
            }
            controller.setKeywords(lk);
            
            List<Workshop> lw = new ArrayList<>();
            for(Workshop w : lstWorkshop.getItems()){
                Workshop tmp = new Workshop();
                for(String eq : w.getNecessaryEquipment()){
                    tmp.addNecessaryEquipment(eq);
                }
                tmp.setDuration(w.getDuration());
                tmp.setRoom(w.getRoom());
                tmp.setWorkshopTitle(w.getWorkshopTitle());
                tmp.setWorkshopDescription(w.getWorkshopDescription());
                lw.add(tmp);
            }
            controller.setWorkshopList(lw);
            Optional<ButtonType> bt = FXUtils.openDialogBox("Do you want to submit this form application to selected event?");
            if (bt.isPresent() && bt.get() == ButtonType.OK) {
                if (!controller.confirm()) {
                    FXUtils.openAlertError("Error when submiting application to event!");
                } else {
                    txtCompanyTradeName.clear();
                    txtVAT.clear();
                    txtIntendedArea.clear();
                    txtPhoneNumber.clear();
                    txtInventations.clear();
                    lstKeywords.getItems().clear();
                    lstWorkshop.getItems().clear();
                    lstProducts.getItems().clear();
                    FXUtils.openAlertSuccess("Submitted!");
                }
            }
        } else {
            FXUtils.openAlertError("You must select an event!");
        }
    }

    @FXML
    private void btnAddProductClick() {
        DisplayProduct dp = new DisplayProduct(txtProduct.getText());
        if (dp.getName().length() < 1) {
            FXUtils.openAlertError("You tried to add an empty product name!");
            return;
        }
        if (!lstProducts.getItems().contains(dp)) {
            lstProducts.getItems().add(dp);
            txtProduct.clear();
        }
    }

    @FXML
    private void btnRemoveProductClick() {
        if (lstProducts.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        DisplayProduct dp = new DisplayProduct(lstProducts.getSelectionModel().getSelectedItem().getName());
        if (lstProducts.getItems().contains(dp)) {
            lstProducts.getItems().remove(dp);
        }
    }

    @FXML
    private void btnAddKeywordClick() {
        Keyword kw = new Keyword(txtKeyword.getText());
        if (kw.getValue().length() < 1) {
            FXUtils.openAlertError("You tried to add an empty keyword!");
        } else if (!lstKeywords.getItems().contains(kw)) {
            lstKeywords.getItems().add(kw);
            txtKeyword.clear();
        }
    }

    @FXML
    private void btnRemoveKeywordClick() {
        if (lstKeywords.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Keyword kw = new Keyword(lstKeywords.getSelectionModel().getSelectedItem().getValue());
        if (lstKeywords.getItems().contains(kw)) {
            lstKeywords.getItems().remove(kw);
        }
    }

    @FXML
    private void btnAddNecessaryEquipmentClick() {
        if (txtEquipments.getText().length() < 1) {
            FXUtils.openAlertError("You tried to add an empty equipment!");
            return;
        }
        String ne = txtEquipments.getText();
        if (!lstNecessaryEquipment.getItems().contains(ne)) {
            lstNecessaryEquipment.getItems().add(ne);
            txtEquipments.clear();
        }
    }

    @FXML
    private void btnRemoveNecessaryEquipmentClick() {
        String selected = lstNecessaryEquipment.getSelectionModel().getSelectedItem();
        if (selected != null && lstNecessaryEquipment.getItems().contains(selected)) {
            lstNecessaryEquipment.getItems().remove(selected);
        }
    }

    @FXML
    private void btnAddWorkshopClick() {
        Workshop wshop = new Workshop();
        //test title
        if (txtWorkshopTitle.getText().isEmpty()) {
            FXUtils.openAlertError("You specified an empty workshop title!");
            return;
        } else {
            wshop.setWorkshopTitle(txtWorkshopTitle.getText());
            txtWorkshopTitle.clear();
        }
        //test description
        if (txtWorkshopDescription.getText().isEmpty()) {
            FXUtils.openAlertError("You specified an empty workshop description!");
            return;
        } else {
            wshop.setWorkshopDescription(txtWorkshopDescription.getText());
            txtWorkshopDescription.clear();
        }
        //test room
        try {
            wshop.setRoom(Integer.parseInt(txtWorkshopRoom.getText()));
            txtWorkshopRoom.clear();
        } catch (NumberFormatException nfe) {
            FXUtils.openAlertError("You specified an invalid workshop room!\n" + nfe.getMessage());
            return;
        }

        //test duration
        try {
            wshop.setDuration(Integer.parseInt(txtWorkshopDuration.getText()));
            txtWorkshopDuration.clear();
        } catch (NumberFormatException nfe) {
            FXUtils.openAlertError("You specified an invalid workshop duration!\n" + nfe.getMessage());
            return;
        }

        //add necessary equipment
        for (String ne : lstNecessaryEquipment.getItems()) {
            wshop.addNecessaryEquipment(ne);
        }
        lstNecessaryEquipment.getItems().clear();

        //test if already contains
        if (!lstWorkshop.getItems().contains(wshop)) {
            lstWorkshop.getItems().add(wshop);
        }

    }

    @FXML
    private void btnRemoveWorkshopClick() {
        Workshop selected = lstWorkshop.getSelectionModel().getSelectedItem();
        if (selected != null && lstWorkshop.getItems().contains(selected)) {
            lstWorkshop.getItems().remove(selected);
        }
    }
}
