package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Testing the Equipment class.
 * <p>
 * Created by Diogo Capela [1171316@isep.ipp.pt] on 04/06/2018.
 */
public class EquipmentTest {

    @Test
    public void testGetAndSetTitle() {
        String expected = "Scuba-diving Equipment";
        Equipment equipment = new Equipment();
        equipment.setTitle("Scuba-diving Equipment");
        String result = equipment.getTitle();
        assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        String expected = "Scuba-diving Equipment";
        String result = new Equipment("Scuba-diving Equipment").toString();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameContentObjectsAreEqual() {
        Equipment expected = new Equipment("Scuba-diving Equipment");
        Equipment result = new Equipment("Scuba-diving Equipment");
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        Equipment expected = new Equipment("Scuba-diving Equipment");
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        Equipment expected = new Equipment("Scuba-diving Equipment");
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        Equipment equipment = new Equipment("Scuba-diving Equipment");
        int expected = 485001576;
        int result = equipment.hashCode();
        assertEquals(expected, result);
    }

}
