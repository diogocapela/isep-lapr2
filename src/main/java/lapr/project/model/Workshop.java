package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
@XmlRootElement
public class Workshop implements Comparable<Workshop> {

    // Instance variables
    private String title = "";
    private String description = "";
    private int room;
    private int duration;
    private List<String> necessaryEquipment = new ArrayList<>();
    private List<Integer> interestedUsers = new ArrayList<>();

    /**
     * Default public empty constructor.
     */
    public Workshop() {

    }

    /**
     * Full constructor of Workhsop.
     *
     * @param title       : String title of workshop
     * @param description : String description of workshop
     * @param room        : int of assigned room
     * @param duration    : int of duration
     */
    public Workshop(String title, String description, int room, int duration) {
        this.title = title;
        this.description = description;
        this.room = room;
        this.duration = duration;
    }

    /**
     * Full constructor of Workhsop.
     *
     * @param title       : String title of workshop
     * @param description : String description of workshop
     * @param duration    : int of duration
     */
    public Workshop(String title, String description, int duration) {
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    /* Getters & Setters
    ===================================== */

    /**
     * Get title of the workshop
     *
     * @return String representing the title
     */
    @XmlElement
    public String getWorkshopTitle() {
        return title;
    }

    /**
     * Set title of the workshop
     *
     * @param title : String representing the title
     */
    public void setWorkshopTitle(String title) {
        this.title = title;
    }

    /**
     * Get description of the workshop
     *
     * @return String representing the description
     */
    @XmlElement
    public String getWorkshopDescription() {
        return description;
    }

    /**
     * Set description of the workshop
     *
     * @param description : String representing the description
     */
    public void setWorkshopDescription(String description) {
        this.description = description;
    }

    /**
     * Get room ID of the workshop
     *
     * @return : int representing the ID room assigned
     */
    @XmlElement
    public int getRoom() {
        return room;
    }

    /**
     * Set room ID of the workshop
     *
     * @param room : int representing the room assigned for the workshop
     */
    public void setRoom(int room) {
        this.room = room;
    }

    /**
     * Get duration of the workshop
     *
     * @return int representing the duration of the workshop
     */
    @XmlElement
    public int getDuration() {
        return duration;
    }

    /**
     * Set duration of the workshop
     *
     * @param duration : int representing the duration of the workshop
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Get necessary equipment
     *
     * @return
     */
    @XmlElement
    public List<String> getNecessaryEquipment() {
        return necessaryEquipment;
    }

    /**
     * Set necessary equipment
     *
     * @param necessaryEquipment
     */
    public void setNecessaryEquipment(List<String> necessaryEquipment) {
        this.necessaryEquipment = necessaryEquipment;
    }

    /**
     * Get duration of the workshop
     *
     * @return
     */
    @XmlElement
    public List<Integer> getInterestedUsers() {
        return this.interestedUsers;
    }

    /**
     * Set interested users
     *
     * @param interestedUsers
     */
    public void setInterestedUsers(List<Integer> interestedUsers) {
        this.interestedUsers = interestedUsers;
    }

    /* toString, equals & hashCode
    ===================================== */

    @Override
    public String toString() {
        return String.format("Title: %s | Description: %s | Room: %d", this.title, this.description, this.room);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Workshop workshop = (Workshop) o;
        return this.getWorkshopTitle().equals(workshop.getWorkshopTitle()) && this.getWorkshopDescription().equals(workshop.getWorkshopDescription()) && this.getDuration() == workshop.getDuration() && this.getNecessaryEquipment().equals(workshop.getNecessaryEquipment()) && this.getInterestedUsers().equals(workshop.getInterestedUsers());
    }

    @Override
    public int hashCode() {
        return (this.title.hashCode() + this.description.hashCode() + Integer.hashCode(duration) + this.necessaryEquipment.hashCode() + this.interestedUsers.hashCode());
    }

    /* Custom
    ===================================== */

    /**
     * Methods for this model class
     */
    /**
     * Add equipment
     *
     * @param equipment String representing the equipment
     */
    public void addNecessaryEquipment(String equipment) {
        this.necessaryEquipment.add(equipment);
    }

    /**
     * Add user
     *
     * @param user User that is interested on this workshop
     */
    public void addInterestedUser(User user) {
        int userHash = user.hashCode();
        if (!this.interestedUsers.contains(userHash)) {
            this.interestedUsers.add(userHash);
        } else {
            throw new IllegalArgumentException(String.format("User '%s' is already interested in Workshop '%s'", user.getUsername(), this.getWorkshopTitle()));
        }
    }

    @Override
    public int compareTo(Workshop o) {
        return Integer.compare(this.interestedUsers.size(), o.getInterestedUsers().size());
    }

}
