package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an equipment.
 * <p>
 * Created by Diogo Capela [1171316@isep.ipp.pt] on 04/06/2018.
 */
@XmlRootElement
public class Equipment {

    // Instance variables
    private String title = "";

    /**
     * Default empty constructor.
     */
    public Equipment() {

    }

    /**
     * Constructor for the Equipment class.
     *
     * @param title Title of the equipment.
     */
    public Equipment(String title) {
        this.title = title;
    }

    /* Getters & Setters
    ===================================== */

    /**
     * Gets the Equipment's title.
     *
     * @return title
     */
    @XmlElement
    public String getTitle() {
        return title;
    }

    /**
     * Sets the Equipment's title.
     *
     * @param title Equipment's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /* toString, equals & hashCode
    ===================================== */

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Equipment equipment = (Equipment) o;
        return getTitle().equals(equipment.getTitle());
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

}
