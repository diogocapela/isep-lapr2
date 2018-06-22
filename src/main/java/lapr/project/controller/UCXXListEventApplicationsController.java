package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for UCXX List Event Applications.
 * <p>
 * After the review process for exhibitions, organisers should assign stands to
 * the various accepted applications. At any given time, the system must allow
 * the organiser to list filtered applications.
 * </p>
 * Created by Filipe Couto (1050469@isep.ipp.pt) on 05/06/2018.
 */
public class UCXXListEventApplicationsController extends SuperController {

    public List<Application> getAllApplications(Event event) {
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " enumerated all application for event:" + event);
        return event.getApplicationList();
    }

    /**
     * Returns a List of accepted applications for an event
     *
     * @param event An event
     * @return Accepted applications list of event
     */
    public List<Application> getFilteredApplications(Event event, String filter) {
        List<Application> applicationsList = event.getApplicationList();
        List<Application> applicationsAcceptedList = new ArrayList<>();
        for (Application a : applicationsList) {
            if (filter.equals(a.getStatus())) {
                applicationsAcceptedList.add(a);
            }
        }
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " enumerated applications of event:" + event + " using filter:" + filter);
        return applicationsAcceptedList;
    }

}
