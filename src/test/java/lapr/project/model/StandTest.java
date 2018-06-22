package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class StandTest {

    private Stand TEST_STAND_1 = new Stand("ab", 1);
    private Stand TEST_STAND_2 = new Stand("ab", 1);
    private Stand TEST_STAND_3 = new Stand();
    private User TEST_USER = new User();

    @Test
    public void testGetAndSetArea() {
        BigDecimal expected = new BigDecimal(TEST_STAND_1.getArea());
        Stand stand = new Stand();
        stand.setArea(TEST_STAND_1.getArea());
        BigDecimal result = new BigDecimal(stand.getArea());
        assertEquals(expected, result);
    }

    @Test
    public void testGetAndSetDescription() {
        String expected = TEST_STAND_1.getDescription();
        Stand stand = new Stand();
        stand.setDescription(TEST_STAND_1.getDescription());
        String result = stand.getDescription();
        assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        String expected = "Description: Stand A | Area: 20.00";
        Stand stand = new Stand();
        stand.setDescription("Stand A");
        stand.setArea(20);
        String result = stand.toString();
        assertEquals(expected, result);
    }

    @Test
    void ensureSameObjectIsEqual() {
        assertTrue(TEST_STAND_1.equals(TEST_STAND_1));
    }

    @Test
    void ensureObjectsSameIDAreEqual() {
        assertTrue(TEST_STAND_1.equals(TEST_STAND_2));
    }

    @Test
    void ensureObjectsDifferentIDAreNotEqual() {
        assertFalse(TEST_STAND_1.equals(TEST_STAND_3));
    }

    @Test
    void ensureDifferentObjectsAreNotEqual() {
        assertFalse(TEST_STAND_1.equals(TEST_USER));
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        int expected = 1072696354;
        int result = TEST_STAND_1.hashCode();
        assertEquals(expected, result);
    }
    
    @Test
    public void testSetGets(){
        StandDistance std = new StandDistance();
        std.setDistanceDescription("Stand 2");
        assertEquals(std.getDistanceDescription(), "Stand 2");
        std.setDistanceValue(10);
        assertEquals(std.getDistanceValue(), 10);
        
    }

}