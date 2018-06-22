package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a keyword.
 *
 * @author by Nuno Bettencourt [nmb@isep.ipp.pt] on 29/05/16.
 */
@XmlRootElement
public class Keyword {

    // Instance variables
    private String value = "";

    /**
     * Default public empty constructor.
     */
    public Keyword() {

    }

    /**
     * Constructor for Keyword Class.
     *
     * @param value Keyword being used.
     */
    public Keyword(String value) {
        this.value = value;
    }

    /* Getters & Setters
    ===================================== */

    @XmlElement
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /* toString, equals & hashCode
    ===================================== */

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Keyword keyword = (Keyword) o;
        return getValue().equals(keyword.getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
