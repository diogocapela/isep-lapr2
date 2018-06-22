package lapr.project.utils;

import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.model.Expo;
import lapr.project.model.User;

public class AuthManager {

    private Expo expo;

    public AuthManager() {
        expo = Expo.getInstance();
    }

    public User getLoggedInUser() {
        return expo.getLoggedInUser();
    }

    public void setLoggedInUser(User user) {
        expo.setLoggedInUser(user);
    }

    public boolean isEventManager() {
        if (expo.getLoggedInUser() != null) {
            return expo.getLoggedInUser().getIsAdmin();
        }
        return false;
    }

    public boolean isOrganiserAtEvent(Event event) {
        return event.getOrganisers().contains(expo.getLoggedInUser());
    }

    public boolean isStaffMemberAtEvent(Event event) {
        return event.getStaffMembers().contains(expo.getLoggedInUser());
    }

    public boolean isAttendeeAtEvent(Event event) {
        return event.getAttendees().contains(expo.getLoggedInUser());
    }

    public boolean isUser() {
        return expo.getLoggedInUser() != null;
    }

    public boolean isAuthorOfEventApplication(Event event, Application application) {
        if (isUser()) {
            if (!event.getApplicationList().contains(application)) {
                return false;
            }
            return application.getAuthor().equals(this.getLoggedInUser());
        }
        return false;
    }

}
