package lapr.project.controller;

import lapr.project.model.Event;
import lapr.project.model.Expo;
import lapr.project.model.User;

import java.util.List;

abstract class SuperController {

    public static final String USERSTR = "User : ";
    Expo expo = Expo.getInstance();
    User loggedInUser = expo.getLoggedInUser();

    /**
     * Get the list of users provided by the Singleton Expo class
     *
     * @return List of Users
     */
    public List<User> getUsers() {
        return expo.getUserRegistry().getUsers();
    }

    /**
     * Get the list of evenets provided by the Singleton Expo class
     *
     * @return List of events
     */
    public List<Event> getEvents() {
        return expo.getEventRegistry().getEvents();
    }

    /**
     * Get the current logged in User provided by the Singleton Expo class
     *
     * @return User representing the current Logged In User
     */
    public User getLoggedInUser() {
        return expo.getLoggedInUser();
    }

    /**
     * Set an User to be the current Logged In User
     *
     * @param user : User that is logged
     */
    public void setLoggedInUser(User user) {
        expo.setLoggedInUser(user);
    }

}
