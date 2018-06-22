package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Event {

    // Instance variables
    private int id = 0;
    private String type = "exhibition";
    private String title = "";
    private String description = "";
    private String location = "";
    private Date date = new Date(1990, 2, 2);
    private Date deadline = new Date(1990, 1, 1);
    private boolean isOpenToApplications = false;
    private List<User> organisers = new ArrayList<>();
    private List<User> staffMembers = new ArrayList<>();
    private List<User> attendees = new ArrayList<>();
    private List<Stand> standList = new ArrayList<>();
    private List<Application> applicationList = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public Event() {
    }

    public Event(int id, String title, String description, String location, String date, String deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        setDate(date);
        setDeadline(deadline);
    }

    /* Getters & Setters
    ===================================== */

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlElement
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String dateString) {
        Date newDate = new Date(dateString);
        if (this.deadline == null || newDate.isGreater(this.deadline)) {
            this.date = newDate;
        } else {
            throw new IllegalArgumentException("Event Date must be greater than Event's Application Submission Deadline");
        }
    }

    @XmlElement
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setDeadline(String deadlineString) {
        Date newDeadline = new Date(deadlineString);
        if (this.date == null || this.date.isGreater(newDeadline)) {
            this.deadline = newDeadline;
        } else {
            throw new IllegalArgumentException("Event's Application Submission Deadline must not be greater than Event Date");
        }
    }

    @XmlElement
    public boolean getIsOpenToApplications() {
        return isOpenToApplications;
    }

    public void setIsOpenToApplications(boolean isOpenToApplications) {
        this.isOpenToApplications = isOpenToApplications;
    }

    @XmlElement
    public List<User> getOrganisers() {
        return organisers;
    }

    public void setOrganisers(List<User> organisers) {
        this.organisers = organisers;
    }

    @XmlElement
    public List<User> getStaffMembers() {
        return staffMembers;
    }

    public void setStaffMembers(List<User> staffMembers) {
        this.staffMembers = staffMembers;
    }

    @XmlElement
    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    @XmlElement
    public List<Stand> getStandList() {
        return standList;
    }

    public void setStandList(List<Stand> standList) {
        this.standList = standList;
    }

    @XmlElement
    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }


    /* toString, equals & hashCode
    ===================================== */

    @Override
    public String toString() {
        return String.format("Title: %s | Description: %s | Location: %s | Date: %s | Application's Deadline: %s", this.title, this.description, this.location, this.date, this.deadline);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return getId() == event.getId() && getType().equals(event.getType()) && getTitle().equals(event.getTitle()) /*&& description.equals(event.description) && location.equals(event.location) && date.equals(event.date) && deadline.equals(event.deadline) && organisers.equals(event.organisers) && staffMembers.equals(event.staffMembers) && attendees.equals(event.attendees) && standList.equals(event.standList) && applicationList.equals(event.applicationList)*/;
    }

    @Override
    public int hashCode() {
        return (id + type.hashCode() + title.hashCode() + description.hashCode() + location.hashCode() + date.hashCode() + deadline.hashCode() + Boolean.hashCode(isOpenToApplications) + organisers.hashCode() + staffMembers.hashCode() + attendees.hashCode() + standList.hashCode() + applicationList.hashCode());
    }

    /* Custom
    ===================================== */

    public void addOrganiser(User u){
        if (this.organisers.contains(u)) {
            throw new IllegalArgumentException("This Organiser is already an Organiser for this Event");
        } else if (this.staffMembers.contains(u)) {
            throw new IllegalArgumentException("This Organiser is already a Staff Member for this Event");
        } else if (this.attendees.contains(u)) {
            throw new IllegalArgumentException("This Organiser is already an Atendee for this Event");
        } else {
            this.organisers.add(u);
        }
    }

    public void removeOrganiser(User u){
        if (this.organisers.contains(u)) {
            this.organisers.remove(u);
        } else {
            throw new IllegalArgumentException("This Organiser is not assigned to this Event");
        }
    }

    public void addStaffMember(User u){
        if (this.staffMembers.contains(u)) {
            throw new IllegalArgumentException("This Staff Member is already a Staff Member for this Event");
        } else if (this.organisers.contains(u)) {
            throw new IllegalArgumentException("This Staff Member is already an Organiser for this Event");
        } else if (this.attendees.contains(u)) {
            throw new IllegalArgumentException("This Staff Member is already an Atendee for this Event");
        } else {
            this.staffMembers.add(u);
        }
    }

    public void removeStaffMember(User u){
        if (this.staffMembers.contains(u)) {
            this.staffMembers.remove(u);
        } else {
            throw new IllegalArgumentException("This Staff Member is not assigned to this Event");
        }
    }

    public void addAttendee(User u){
        if (this.staffMembers.contains(u)) {
            throw new IllegalArgumentException("This Attendee is already a Staff Member for this Event");
        } else if (this.organisers.contains(u)) {
            throw new IllegalArgumentException("This Attendee is already an Organiser for this Event");
        } else if (this.attendees.contains(u)) {
            throw new IllegalArgumentException("This Attendee is already an Atendee for this Event");
        } else {
            this.attendees.add(u);
        }
    }

    public void removeAttendee(User u){
        if (this.attendees.contains(u)) {
            this.attendees.remove(u);
        } else {
            throw new IllegalArgumentException("This Attendee is not assigned to this Event");
        }
    }

    public void addStand(Stand s){
        if (this.standList.contains(s)) {
            throw new IllegalArgumentException("This Stand is already assigned to this Event");
        } else {
            this.standList.add(s);
        }
    }

    public void removeStand(Stand s){
        if (!this.standList.contains(s)) {
            throw new IllegalArgumentException("This Stand is not assigned to this Event");
        } else {
            this.standList.remove(s);
        }
    }

    public void addApplication(Application a){
        if (this.applicationList.contains(a)) {
            throw new IllegalArgumentException("This Application has already been made to this Event");
        } else {
            this.applicationList.add(a);
        }
    }

    public void removeApplication(Application a){
        if (!this.applicationList.contains(a)) {
            throw new IllegalArgumentException("This Application has not been made to this Event");
        } else {
            this.applicationList.remove(a);
        }
    }

    public void updateApplication(Application a) {
        if (!this.applicationList.contains(a)) {
            throw new IllegalArgumentException("This Application does not exist, can not update");
        } else {
            this.applicationList.add(this.applicationList.indexOf(a), a);
        }
    }

}
