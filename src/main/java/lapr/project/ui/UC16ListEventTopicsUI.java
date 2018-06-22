package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.UC16ListEventTopicsController;
import lapr.project.model.Event;
import lapr.project.model.Keyword;

import java.net.URL;
import java.util.*;

/**
 * UI class for the UC16 (List Event Topics).
 * <p>
 * Organisers want to be able to have information about which topics are in
 * vogue. As a result, a topic frequency table for each event should be created
 * and a ranking using that frequency table should be shown.
 * <p>
 * Created by Diogo Capela (1171316@isep.ipp.pt) on 05/06/2018.
 */
public class UC16ListEventTopicsUI implements Initializable {

    private UC16ListEventTopicsController controller;

    @FXML
    private ChoiceBox<Event> eventsChoiceBox;
    @FXML
    private ChoiceBox<String> statusChoiceBox;
    @FXML
    private PieChart pieChart;
    @FXML
    private TableView<TopicMap> table;
    @FXML
    private TableColumn<TopicMap, String> col1;
    @FXML
    private TableColumn<TopicMap, String> col2;
    @FXML
    private TableColumn<TopicMap, Integer> col3;
    @FXML
    private TableColumn<TopicMap, Double> col4;

    private int totalTopics;
    private List<String> topicsList;
    private List<Integer> countList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new UC16ListEventTopicsController();
        eventsChoiceBox.setItems(FXCollections.observableArrayList(controller.getEvents()));
        statusChoiceBox.setItems(FXCollections.observableArrayList(
                "submitted", "reviewPending", "accepted", "rejected"
        ));
        //tableString.setText("");
        totalTopics = 0;
        topicsList = new ArrayList<>();
        countList = new ArrayList<>();
    }

    public void handleShowDataForSelectedEvent() {
        List<TopicMap> topicsMaps = new ArrayList<>();
        table.refresh();
        //tableString.setText("");
        totalTopics = 0;
        topicsList = new ArrayList<>();
        countList = new ArrayList<>();
        // Get the event and filter
        Event event = eventsChoiceBox.getValue();
        String filter = statusChoiceBox.getValue();
        // If the user selected an event
        if (event != null && filter != null) {
            // Get the list of keywords for that event
            List<Keyword> keywords = controller.getKeywordsFromEvent(event, filter);
            // Get the map frequency for the keyword list
            Map<String, Integer> keywordMap = controller.getKeywordFrequencyMap(keywords);
            // Populate the PieChart with the keyword data
            List<PieChart.Data> pieChartData = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : keywordMap.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                pieChartData.add(new PieChart.Data(key + " (" + value + ")", value));

                // add values to custom lists
                totalTopics = totalTopics + value;
                topicsList.add(key);
                countList.add(value);
            }
            if (totalTopics != 0) {
                for (int i = 0; i < topicsList.size(); i++) {
                    double percentage = ((double) countList.get(i) / totalTopics) * 100;
                    topicsMaps.add(new TopicMap(topicsList.get(i), countList.get(i), percentage));
                }
            }

            Collections.sort(topicsMaps);

            for (int i = 0; i < topicsMaps.size(); i++) {
                topicsMaps.get(i).setRank(String.format("#%d", i + 1));
            }

            col1.setCellValueFactory(new PropertyValueFactory<>("rank"));
            col2.setCellValueFactory(new PropertyValueFactory<>("name"));
            col3.setCellValueFactory(new PropertyValueFactory<>("count"));
            col4.setCellValueFactory(new PropertyValueFactory<>("percentage"));
            table.setItems(FXCollections.observableArrayList(topicsMaps));
            table.refresh();

            pieChart.setData(FXCollections.observableArrayList(pieChartData));

        } else {
            // If the user didn't select an event
            FXUtils.openAlertError("You need to first select an event and a status filter!");
        }

    }

    public static class TopicMap implements Comparable<TopicMap> {
        private String rank;
        private String name;
        private int count;
        private double percentage;

        public TopicMap(String name, int count, double percentage) {
            this.name = name;
            this.count = count;
            this.percentage = percentage;
        }

        public String getRank() {
            return rank;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getPercentage() {
            return percentage;
        }

        public void setPercentage(double percentage) {
            this.percentage = percentage;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TopicMap topicMap = (TopicMap) o;
            return count == topicMap.count && Double.compare(topicMap.percentage, percentage) == 0 && Objects.equals(name, topicMap.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, count, percentage);
        }

        @Override
        public int compareTo(TopicMap o) {
            return Integer.compare(o.getCount(), this.getCount());
        }

    }

}
