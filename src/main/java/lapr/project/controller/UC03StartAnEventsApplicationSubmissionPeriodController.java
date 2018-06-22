package lapr.project.controller;

import lapr.project.model.Event;
import lapr.project.utils.Logger;

/**
 * Applications are only possible for events with established organisers, event
 * staff and application deadline. Applications’ submission period becomes open
 * when organisers manually trigger it.
 */
public class UC03StartAnEventsApplicationSubmissionPeriodController extends SuperController {

    /**
     * Start period of Application submittions given an Event.
     *
     * @param event : Event to start accepting Applications.
     */
    public void startApplicationSubmissionPeriod(Event event) {
        event.setIsOpenToApplications(true);
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " started application period for event:" + event);
    }

    /**
     * Stop period of Application submittions given an Event.
     *
     * @param event : Event to stop accepting Applications.
     */
    public void stopApplicationSubmissionPeriod(Event event) {
        event.setIsOpenToApplications(false);
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " stopped application period for event:" + event);

    }

}
