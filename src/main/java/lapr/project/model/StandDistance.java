package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Represents a Stand Distance.
 * <p>
 * Created by Rafael Marques (1090441@isep.ipp.pt) on 04/06/2018.
 */
@XmlRootElement
public class StandDistance {

    private String distanceDescription = "";
    private int distanceValue;

    public StandDistance() {

    }

    public StandDistance(String distanceDescription, int distanceValue) {
        this.distanceDescription = distanceDescription;
        this.distanceValue = distanceValue;
    }

    /* Getters & Setters
    ===================================== */

    @XmlElement
    public String getDistanceDescription() {
        return distanceDescription;
    }

    public void setDistanceDescription(String distanceDescription) {
        this.distanceDescription = distanceDescription;
    }

    @XmlElement
    public int getDistanceValue() {
        return distanceValue;
    }

    public void setDistanceValue(int distanceValue) {
        this.distanceValue = distanceValue;
    }

    /* toString, equals & hashCode
    ===================================== */

    @Override
    public String toString() {
        return String.format("Description: %s | Value: %d", this.distanceDescription, this.distanceValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StandDistance that = (StandDistance) o;
        return getDistanceValue() == that.getDistanceValue() && getDistanceDescription().equals(that.getDistanceDescription());
    }

    @Override
    public int hashCode() {
        return Double.hashCode(distanceValue) + distanceDescription.hashCode();
    }

}
