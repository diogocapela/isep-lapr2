package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Testing the DisplayProduct class.
 * <p>
 * Created by Diogo Capela [1171316@isep.ipp.pt] on 02/06/2018.
 */
public class DisplayProductTest {

    @Test
    public void testGetAndSetName() {
        String expected = "TV";
        DisplayProduct displayProduct = new DisplayProduct();
        displayProduct.setName("TV");
        String result = displayProduct.getName();
        assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        String expected = "Televisao";
        String result = new DisplayProduct("Televisao").toString();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        DisplayProduct expected = new DisplayProduct("TV");
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        DisplayProduct expected = new DisplayProduct("TV");
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        DisplayProduct displayProduct = new DisplayProduct("TV");
        int expected = 2690;
        int result = displayProduct.hashCode();
        assertEquals(expected, result);
    }

}
