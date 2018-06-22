package lapr.project.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UC33ListWorkshopsMapColouringController;
import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.model.Workshop;
import lapr.project.utils.AuthManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class UC33ListWorkshopsMapColouringUI implements Initializable {
    UC33ListWorkshopsMapColouringController controller;
    private AuthManager authManager = new AuthManager();

    @FXML
    private TableView<Event> eventTableUC33;
    @FXML
    private TableColumn<Event, String> eventTableUC33ColTitle;
    @FXML
    private TableColumn<Event, String> eventTableUC33ColDescription;

    @FXML
    private TableView<WorkshopDegreeColor> workshopDegreeColorTableUC33;
    @FXML
    private TableColumn<WorkshopDegreeColor, String> workshopDegreeColorTableUC33ColName;
    @FXML
    private TableColumn<WorkshopDegreeColor, String> workshopDegreeColorTableUC33ColAmountInterested;
    @FXML
    private TableColumn<WorkshopDegreeColor, String> workshopDegreeColorTableUC33ColVertexDegree;
    @FXML
    private TableColumn<WorkshopDegreeColor, String> workshopDegreeColorTableUC33ColMapColor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new UC33ListWorkshopsMapColouringController();

        eventTableUC33.setItems(FXCollections.observableArrayList(controller.getEvents()));
        eventTableUC33ColTitle.setText("Title");
        eventTableUC33ColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        eventTableUC33ColDescription.setText("Description");
        eventTableUC33ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        workshopDegreeColorTableUC33ColName.setText("Workshop Title");
        workshopDegreeColorTableUC33ColAmountInterested.setText("Amount of Interested Attendees");
        workshopDegreeColorTableUC33ColVertexDegree.setText("Workshop Vertex Degree");
        workshopDegreeColorTableUC33ColMapColor.setText("Workshop Assigned Map color");
    }

    @FXML
    public void handleListButton() {
        Event e = eventTableUC33.getSelectionModel().getSelectedItem();
        List<Workshop> workshopList = new ArrayList<>();
        if (e == null) {
            FXUtils.openAlertError("Event must be selected!");
        } else {
            if (authManager.isOrganiserAtEvent(e)) {
                for (Application applicationTmp : e.getApplicationList()) {
                    workshopList.addAll(applicationTmp.getWorkshopList());
                }

                if (workshopList.isEmpty()) {
                    FXUtils.openAlertError("Selected Event has no workshops!");
                } else {
                    Collections.sort(workshopList);

                    List<WorkshopDegreeColor> workshopDegreeColorList;
                    workshopDegreeColorList = controller.listMapColouring(workshopList);

                    workshopDegreeColorTableUC33.setItems(FXCollections.observableArrayList(workshopDegreeColorList));
                    workshopDegreeColorTableUC33ColName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getWorkshop().getWorkshopTitle()));
                    workshopDegreeColorTableUC33ColAmountInterested.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getWorkshop().getInterestedUsers().size())));
                    workshopDegreeColorTableUC33ColVertexDegree.setCellValueFactory(new PropertyValueFactory<>("degree"));
                    workshopDegreeColorTableUC33ColMapColor.setCellValueFactory(new PropertyValueFactory<>("color"));

                }
            } else {
                FXUtils.openAlertError("Only Organizers can access the Workshop Map Colouring!");
            }
        }
    }

    public static class WorkshopDegreeColor implements Comparable<WorkshopDegreeColor> {
        private Workshop workshop;
        private int degree;
        private int color;

        public WorkshopDegreeColor(Workshop workshop, int degree) {
            this.workshop = workshop;
            this.degree = degree;
        }

        public Workshop getWorkshop() {
            return this.workshop;
        }

        public int getDegree() {
            return this.degree;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public int getColor() {
            return this.color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        @Override
        public int compareTo(WorkshopDegreeColor o) {
            return this.getDegree() < o.getDegree() ? 1 : this.getDegree() > o.getDegree() ? -1 : 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof WorkshopDegreeColor)) {
                return false;
            }
            WorkshopDegreeColor wdc = (WorkshopDegreeColor) o;
            return (this.getWorkshop().equals(wdc.getWorkshop()) && this.getDegree() == wdc.getDegree() && this.getColor() == wdc.getColor());
        }

        @Override
        public int hashCode() {
            return this.workshop.hashCode() + Integer.hashCode(this.degree) + Integer.hashCode(this.color);
        }
    }
}
