package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Example of a domain class that is used in Candidatura.
 * Created by Nuno Bettencourt [NMB] on 29/05/16.
 */
public class KeywordTest {

    @Test
    public void testGetAndSetValue() {
        String expected = "Doors";
        Keyword keyword = new Keyword();
        keyword.setValue("Doors");
        String result = keyword.getValue();
        assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        String expected = "Banana";
        String result = new Keyword("Banana").toString();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameContentObjectsAreEqual() {
        Keyword expected = new Keyword("Doors");
        Keyword result = new Keyword("Doors");
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        Keyword expected = new Keyword("Doors");
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        Keyword expected = new Keyword("Doors");
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        Keyword firstKeyword = new Keyword("Doors");
        int expected = 66216549;
        int result = firstKeyword.hashCode();
        assertEquals(expected, result);
    }

}