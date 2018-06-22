package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.model.Workshop;
import lapr.project.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class UC08SubmitWorkshopSurveyController extends SuperController {

    public List<Workshop> getAcceptedWorkshops(Event event) {
        List<Workshop> workshops = new ArrayList<>();
        for (Application application : event.getApplicationList()) {
            if ("accepted".equals(application.getStatus())) {
                for (Workshop workshop : application.getWorkshopList()) {
                    workshops.add(workshop);
                }
            }
        }
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " enumerated accepted workshops");
        return workshops;
    }

}
