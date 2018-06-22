package lapr.project.controller;

import lapr.project.model.Event;
import lapr.project.utils.Logger;

/**
 * The event manager creates an event by specifying its title, a descriptive text about its scope, the time and place of the event and the group of people (organisers) responsible for carrying out the exhibition process.
 * @author Diogo Capela
 */
public class UC01CreateEventController extends SuperController {

    /**
     * Add an Event to the Singleton Expo
     * Only Event Managers can create events.
     * @param event : Event object representing the Event
     */
    public void addEvent(Event event) {
        if (getLoggedInUser() != null && getLoggedInUser().getIsAdmin()) {
            expo.getEventRegistry().addEvent(event);
            Logger.log(USERSTR+getLoggedInUser().getUsername()+" added event:"+event);
        } else {
            throw new IllegalArgumentException("Only users who are Event Managers (Admins) can create events.");
        }
    }

    /**
     * Remove an Event from Singleton Expo
     * Only Event Managers can remove events
     * @param event : Event object representing the Event to be deleted
     */
    public void deleteEvent(Event event) {
        if (getLoggedInUser() != null && getLoggedInUser().getIsAdmin()) {
            expo.getEventRegistry().deleteEvent(event);
            Logger.log(USERSTR+getLoggedInUser().getUsername()+" removed event:"+event);
        } else {
            throw new IllegalArgumentException("Only users who are Event Managers (Admins) can create events.");
        }
    }

}
