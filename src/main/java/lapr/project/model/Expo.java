package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Expo {

    // Variable for the singleton pattern
    private static Expo uniqueInstance;

    // Instance variables
    private UserRegistry userRegistry;
    private EventRegistry eventRegistry;
    private User loggedInUser;

    /**
     * Expo default constructor.
     */
    private Expo() {
        userRegistry = new UserRegistry();
        eventRegistry = new EventRegistry();
        loggedInUser = null;
    }

    /**
     * Method to get the instance of Expo using the singleton pattern.
     * @return Expo unique instance.
     */
    public static Expo getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Expo();
        }
        return uniqueInstance;
    }

    @XmlElement
    public UserRegistry getUserRegistry() {
        return userRegistry;
    }

    @XmlElement
    public EventRegistry getEventRegistry() {
        return eventRegistry;
    }

    public void setUserRegistry(UserRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    public void setEventRegistry(EventRegistry eventRegistry) {
        this.eventRegistry = eventRegistry;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

}
