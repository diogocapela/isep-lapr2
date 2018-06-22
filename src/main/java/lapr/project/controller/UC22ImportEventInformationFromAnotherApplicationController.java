package lapr.project.controller;

import lapr.project.model.Event;
import lapr.project.model.User;
import lapr.project.utils.CsvUtils;
import lapr.project.utils.Logger;
import lapr.project.utils.XMLImportAdapter;
import lapr.project.utils.XMLParser;
import org.w3c.dom.Node;

import java.util.List;

public class UC22ImportEventInformationFromAnotherApplicationController extends SuperController {

    private XMLParser xmlParser;

    public UC22ImportEventInformationFromAnotherApplicationController() {
        xmlParser = new XMLParser();
    }

    public Event importEventFromXMLFile(String filePath) throws Exception {
        Node node = xmlParser.readXMLElementFromFile(filePath);
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " imported event from another application using XML");
        return XMLImportAdapter.importEvent(node);
    }

    public Event importEventFromCSVFileAdapter1(String filePath) throws Exception {
        CsvUtils csvUtils = new CsvUtils();
        csvUtils.prepareCsv(filePath);
        List<Event> events = csvUtils.getEvents();
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " imported event from another application using CSV1");
        return events.get(0);
    }

    public Event importEventFromCSVFileAdapter2(String filePath) throws Exception {
        CsvUtils csvUtils = new CsvUtils();
        csvUtils.prepareCsv(filePath);
        List<Event> events = csvUtils.getEventsByStd();
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " imported event from another application using CSV2");
        return events.get(0);
    }

    public void addEvent(Event event) {
        expo.getEventRegistry().addEvent(event);
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " added event using import from another application");
    }

    public void addUser(User user) {
        if (user != null && user.getUsername().length() > 0) {
            expo.getUserRegistry().addUser(user);
        }
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " added user using import from another application");

    }

}
