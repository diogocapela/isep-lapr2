package lapr.project.controller;

import lapr.project.model.Event;
import lapr.project.model.User;
import lapr.project.utils.Logger;

/**
 * At a later stage, any organiser may specify which employees (staff) will
 * support the event. The organiser initialises the process of assigning staff
 * members to an event. The system will list all the events that the current
 * user is an organiser. The system will list all the available users that are
 * not already assigned as staff members for the current event and the organiser
 * will select the ones that he wants to assign as staff.
 *
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class UC02AssignStaffMemberToEventController extends SuperController {

    /**
     * Add an User to be Staff member given an Event
     *
     * @param user : User to be promoted to Staff member
     * @param event : Event
     */
    public void addUserToStaffMembers(User user, Event event) {
        event.addStaffMember(user);
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " added the user:" + user + " to event:" + event);
    }

    /**
     * Delete an User from Staff member's team given an Event
     *
     * @param user : User to be demoted
     * @param event : Event
     */
    public void deleteUserFromStaffMembers(User user, Event event) {
        event.removeStaffMember(user);
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " deleted the user:" + user + " from event:" + event);

    }

}
