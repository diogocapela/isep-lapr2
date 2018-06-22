package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpoTest {

    @Test
    public void ensureGetInstanceReturnsSameObject() {
        Expo expo1 = Expo.getInstance();
        Expo expo2 = Expo.getInstance();
        assertEquals(expo1, expo2);
    }
    @Test
    public void testgetUserRegistryAndEventRegistry() {
        Expo expo = Expo.getInstance();
        Assertions.assertNotNull(expo.getEventRegistry());
        Assertions.assertNotNull(expo.getUserRegistry());
    }

}