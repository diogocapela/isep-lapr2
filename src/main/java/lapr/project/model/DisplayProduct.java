package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a display product.
 * <p>
 * Created by Diogo Capela [1171316@isep.ipp.pt] on 02/06/2018.
 */
@XmlRootElement
public class DisplayProduct {

    // Instance variables
    private String name = "";

    /**
     * Default empty constructor.
     */
    public DisplayProduct() {

    }

    /**
     * Constructor for the DisplayProduct class.
     *
     * @param name Name of the display product.
     */
    public DisplayProduct(String name) {
        this.name = name;
    }


    /* Getters & Setters
    ===================================== */

    /**
     * Gets the DisplayProduct's name.
     *
     * @return name
     */
    @XmlElement
    public String getName() {
        return name;
    }

    /**
     * Sets the DisplayProduct's name.
     *
     * @param name Name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /* toString, equals & hashCode
    ===================================== */

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DisplayProduct that = (DisplayProduct) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
