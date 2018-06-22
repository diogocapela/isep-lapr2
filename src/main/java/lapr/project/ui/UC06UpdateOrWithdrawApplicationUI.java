package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lapr.project.controller.UC06UpdateOrWithdrawApplicationController;
import lapr.project.model.Application;
import lapr.project.model.DisplayProduct;
import lapr.project.model.Event;
import lapr.project.model.Keyword;
import lapr.project.utils.AuthManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UC06UpdateOrWithdrawApplicationUI implements Initializable {
    UC06UpdateOrWithdrawApplicationController controller;
    AuthManager authManager = new AuthManager();

    @FXML
    ComboBox<Event> eventComboBoxUC06;
    @FXML
    ComboBox<Application> applicationComboBoxUC06;
    @FXML
    TextField textFieldUC06TradeName;
    @FXML
    TextArea textAreaUC06Description;
    @FXML
    TextField textFieldUC06VATNumber;
    @FXML
    TextField textFieldUC06PhoneNumber;
    @FXML
    TextField textFieldUC06NumberInvitations;
    @FXML
    TextField textFieldUC06StandArea;
    @FXML
    TextField textFieldUC06Keyword;
    @FXML
    ListView<Keyword> listViewUC06Keywords;
    @FXML
    TextField textFieldUC06DisplayProduct;
    @FXML
    ListView<DisplayProduct> listViewUC06DisplayProducts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new UC06UpdateOrWithdrawApplicationController();
        eventComboBoxUC06.getItems().addAll(FXCollections.observableArrayList(controller.getEvents()));
        eventComboBoxUC06.setOnAction(e -> {
            applicationComboBoxUC06.getItems().clear();
            Event event = eventComboBoxUC06.getSelectionModel().getSelectedItem();
            applicationComboBoxUC06.getItems().addAll(FXCollections.observableArrayList(event.getApplicationList()));
        });
        applicationComboBoxUC06.setOnAction(e -> {
            Application a = applicationComboBoxUC06.getSelectionModel().getSelectedItem();
            textFieldUC06TradeName.setText(a.getCompanyTradeName());
            textAreaUC06Description.setText(a.getDescription());
            textFieldUC06VATNumber.setText(Integer.toString(a.getVatNumber()));
            textFieldUC06PhoneNumber.setText(Integer.toString(a.getVatNumber()));
            textFieldUC06NumberInvitations.setText(Integer.toString(a.getNumberOfInvitations()));
            textFieldUC06StandArea.setText(Double.toString(a.getVatNumber()));
            textFieldUC06Keyword.clear();
            listViewUC06Keywords.getItems().clear();
            listViewUC06Keywords.getItems().addAll(FXCollections.observableArrayList(a.getKeywords()));
            textFieldUC06DisplayProduct.clear();
            listViewUC06DisplayProducts.getItems().clear();
            listViewUC06DisplayProducts.getItems().addAll(FXCollections.observableArrayList(a.getDisplayProducts()));
        });
    }

    @FXML
    private boolean handleButtonValidations(Event e, Application a) {
        if (e == null || a == null) {
            String eventString = e == null ? "Event must be selected!" : "";
            String connectString = (e == null && a == null) ? "\n" : "";
            String applicationString = a == null ? "Application must be selected!" : "";
            FXUtils.openAlertError(String.format("%s%s%s", eventString, connectString, applicationString));
        } else if (authManager.isAuthorOfEventApplication(e, a)) {
            FXUtils.openAlertError("You are not the Author of this Application!");
        } else {
            return true;
        }
        return false;
    }

    @FXML
    public void handleWithdrawButton() {
        Event e = eventComboBoxUC06.getSelectionModel().getSelectedItem();
        Application a = applicationComboBoxUC06.getSelectionModel().getSelectedItem();
        if (handleButtonValidations(e, a)) {
            e.removeApplication(a);
            textFieldUC06TradeName.clear();
            textAreaUC06Description.clear();
            textFieldUC06VATNumber.clear();
            textFieldUC06PhoneNumber.clear();
            textFieldUC06NumberInvitations.clear();
            textFieldUC06StandArea.clear();
            textFieldUC06Keyword.clear();
            listViewUC06Keywords.getItems().clear();
            textFieldUC06DisplayProduct.clear();
            listViewUC06DisplayProducts.getItems().clear();

            FXUtils.openAlertSuccess("Application Withdrawn!");
        }
    }

    @FXML
    public void handleUpdateButton() {
        Event e = eventComboBoxUC06.getSelectionModel().getSelectedItem();
        Application a = applicationComboBoxUC06.getSelectionModel().getSelectedItem();
        if (handleButtonValidations(e, a)) {
            boolean valid = true;
            StringBuilder sb = new StringBuilder();
            int vatNumber = 0;
            int phoneNumber = 0;
            int invitationNumber = 0;
            double requestedArea = 0;
            if (textFieldUC06TradeName.getText().length() == 0) {
                sb.append("Company Trade Name is Empty!");
                valid = false;
            }

            if (textFieldUC06VATNumber.getText().length() == 0) {
                sb.append(String.format("%sVAT Number is Empty!", valid ? "" : "\n"));
                valid = false;
            } else {
                try {
                    vatNumber = Integer.parseInt(textFieldUC06VATNumber.getText());
                } catch (Exception exc) {
                    sb.append(String.format("%s%s", exc.getMessage(), valid ? "" : "\n"));
                }
            }

            if (textFieldUC06PhoneNumber.getText().length() == 0) {
                sb.append(String.format("%sPhone Number is Empty!", valid ? "" : "\n"));
                valid = false;
            } else {
                try {
                    phoneNumber = Integer.parseInt(textFieldUC06PhoneNumber.getText());
                } catch (Exception exc) {
                    sb.append(String.format("%s%s", exc.getMessage(), valid ? "" : "\n"));
                }
            }

            if (textFieldUC06NumberInvitations.getText().length() == 0) {
                sb.append(String.format("%sNumber of Invitations is Empty!", valid ? "" : "\n"));
                valid = false;
            } else {
                try {
                    invitationNumber = Integer.parseInt(textFieldUC06NumberInvitations.getText());
                } catch (Exception exc) {
                    sb.append(String.format("%s%s", exc.getMessage(), valid ? "" : "\n"));
                }
            }

            if (textFieldUC06StandArea.getText().length() == 0) {
                sb.append(String.format("%sRequested Stand Area is Empty!", valid ? "" : "\n"));
                valid = false;
            } else {
                try {
                    requestedArea = Double.parseDouble(textFieldUC06StandArea.getText());
                } catch (Exception exc) {
                    sb.append(String.format("%s%s", exc.getMessage(), valid ? "" : "\n"));
                }
            }


            if (valid) {
                a.setCompanyTradeName(textFieldUC06TradeName.getText());
                a.setDescription(textAreaUC06Description.getText());
                a.setVatNumber(vatNumber);
                a.setPhoneNumber(phoneNumber);
                a.setNumberOfInvitations(invitationNumber);
                a.setIntendedStandArea(requestedArea);
                a.setKeywords(listViewUC06Keywords.getItems());
                if (!listViewUC06DisplayProducts.getItems().isEmpty()) {
                    a.setDisplayProducts(listViewUC06DisplayProducts.getItems());
                }
                FXUtils.openAlertSuccess("Application Updated!");
            } else {
                FXUtils.openAlertError(sb.toString());
            }
        }
    }

    @FXML
    public void handleAddKeyword() {
        String keywordString = textFieldUC06Keyword.getText();
        if (keywordString.length() > 0) {
            List<Keyword> keywordList = listViewUC06Keywords.getItems();
            if (keywordList.size() == 5) {
                FXUtils.openAlertError("An application cannot contain more than 5 Topics");
            } else if (keywordList.contains(new Keyword(keywordString))) {
                FXUtils.openAlertError("Topic is already present in this Application!");
            } else {
                listViewUC06Keywords.getItems().add(new Keyword(keywordString));
            }
        } else {
            FXUtils.openAlertError("The Keyword input field has no content");
        }

    }

    @FXML
    public void handleRemoveKeyword() {
        Keyword k = listViewUC06Keywords.getSelectionModel().getSelectedItem();
        if (k == null) {
            FXUtils.openAlertError("No Keyword selected!");
        } else {
            List<Keyword> keywordList = listViewUC06Keywords.getItems();
            if (keywordList.size() == 2) {
                FXUtils.openAlertError("An application cannot contain less than 2 Topics");
            } else {
                listViewUC06Keywords.getItems().remove(k);
            }
        }
    }

    @FXML
    public void handleAddDisplayProduct() {
        String displayProductString = textFieldUC06DisplayProduct.getText();
        if (displayProductString.length() > 0) {
            List<DisplayProduct> displayProductList = listViewUC06DisplayProducts.getItems();
            if (displayProductList.contains(new DisplayProduct(textAreaUC06Description.getText()))) {
                FXUtils.openAlertError("Display Product is already present in this Application!");
            } else {
                listViewUC06DisplayProducts.getItems().add(new DisplayProduct(textFieldUC06DisplayProduct.getText()));
            }
        } else {
            FXUtils.openAlertError("The Display Product input field has no content");
        }
    }

    @FXML
    public void handleRemoveDisplayProduct() {
        DisplayProduct dp = listViewUC06DisplayProducts.getSelectionModel().getSelectedItem();
        if (dp == null) {
            FXUtils.openAlertError("No Display Product selected!");
        } else {
            listViewUC06DisplayProducts.getItems().remove(dp);
        }
    }
}
