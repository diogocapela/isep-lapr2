package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class EventRegistry {

    private List<Event> eventList = new ArrayList<>();

    public EventRegistry() {
        // Registry works like a Data Base
    }

    @XmlElement
    public List<Event> getEvents() {
        return eventList;
    }

    public void addEvent(Event event){
        if (eventList.contains(event)) {
            throw new IllegalArgumentException("This event is already registered.");
        } else {
            eventList.add(event);
        }
    }

    public void deleteEvent(Event event){
        if (!eventList.contains(event)) {
            throw new IllegalArgumentException("The selected event is not registered.");
        } else {
            eventList.remove(event);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EventRegistry otherEventRegistry = (EventRegistry) obj;
        return this.getEvents().equals(otherEventRegistry.getEvents());
    }

    @Override
    public int hashCode() {
        return this.getEvents().hashCode();
    }
}
