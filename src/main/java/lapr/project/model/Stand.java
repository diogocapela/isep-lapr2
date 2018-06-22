package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a Stand.
 * <p>
 * Created by Rafael Marques (1090441@isep.ipp.pt) on 31/05/2018.
 */
@XmlRootElement
public class Stand {

    private String description = "";
    private double area;
    private List<StandDistance> relativeDistanceSet = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public Stand() {

    }

    public Stand(String description, double area) {
        setDescription(description);
        setArea(area);
    }

    /* Getters & Setters
    ===================================== */

    @XmlElement
    public double getArea() {
        return this.area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @XmlElement
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public List<StandDistance> getRelativeDistanceSet() {
        return relativeDistanceSet;
    }

    public void setRelativeDistanceSet(List<StandDistance> relativeDistanceSet) {
        this.relativeDistanceSet = relativeDistanceSet;
    }

    /* toString, equals & hashCode
    ===================================== */

    @Override
    public String toString() {
        return String.format("Description: %s | Area: %.2f", this.description, this.area);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stand stand = (Stand) o;
        BigDecimal thisArea = BigDecimal.valueOf(getArea());
        BigDecimal otherArea = BigDecimal.valueOf(stand.getArea());
        return thisArea.equals(otherArea) && getDescription().equals(stand.getDescription()) && getRelativeDistanceSet().equals(stand.getRelativeDistanceSet());
    }

    @Override
    public int hashCode() {
        return description.hashCode() + Double.hashCode(area) + relativeDistanceSet.hashCode();
    }

    /* Custom
    ===================================== */

    public void addRelativeDistance(StandDistance distance) {
        this.relativeDistanceSet.add(distance);
    }

}
