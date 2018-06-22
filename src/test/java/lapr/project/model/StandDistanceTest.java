package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StandDistanceTest {

    @Test
    public void testGetDistanceDescription() {
        String expected = "Banana";
        StandDistance standDistance = new StandDistance("Banana", 50);
        String result = standDistance.getDistanceDescription();
        assertEquals(expected, result);
    }

    @Test
    public void testGetDistanceValue() {
        int expected = 50;
        StandDistance standDistance = new StandDistance("Banana", 50);
        int result = standDistance.getDistanceValue();
        assertEquals(expected, result);
    }

    @Test
    public void testSetDistanceDescription() {
        String expected = "Orange";
        StandDistance standDistance = new StandDistance("Banana", 50);
        standDistance.setDistanceDescription("Orange");
        String result = standDistance.getDistanceDescription();
        assertEquals(expected, result);
    }

    @Test
    public void testSetDistanceValue() {
        int expected = 123;
        StandDistance standDistance = new StandDistance("Banana", 50);
        standDistance.setDistanceValue(123);
        int result = standDistance.getDistanceValue();
        assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        String expected = "Description: Banana | Value: 50";
        String result = new StandDistance("Banana", 50).toString();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameContentObjectsAreEqual() {
        StandDistance expected = new StandDistance("Banana", 50);
        StandDistance result = new StandDistance("Banana", 50);
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        StandDistance expected = new StandDistance("Banana", 50);
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        StandDistance expected = new StandDistance("Banana", 50);
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        int expected = -1233962107;
        StandDistance standDistance = new StandDistance("Banana", 50);
        int result = standDistance.hashCode();
        assertEquals(expected, result);
    }

}
