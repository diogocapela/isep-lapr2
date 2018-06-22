package lapr.project.utils;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XMLSerializerTest {
    public static final String EVENT_TEST_DIRECTORY = "./src/test/resources/event_test_registry.xml";
    public static final String USER_TEST_DIRECTORY = "./src/test/resources/user_test_registry.xml";
    Expo expo = Expo.getInstance();
    private Event TEST_EVENT_1 = new Event(10, "2", "3", "5", "2000/6/9", "2000/2/6");
    private User TEST_USER_1 = new User("1", "2", "12345678", "4");

    public XMLSerializerTest() {
        try {
            expo.getUserRegistry().deleteUser(TEST_USER_1);
        } catch (Exception e) {

        }
        try {
            expo.getEventRegistry().deleteEvent(TEST_EVENT_1);
        } catch (Exception e) {

        }
    }

    @Test
    void testToFromXMLStatesNotEmptyEventRegistry() {
        expo.getEventRegistry().addEvent(TEST_EVENT_1);
        EventRegistry expectedER = expo.getEventRegistry();
        EventRegistry resultER = null;
        try {
            XMLSerializer.serializeToXML(expectedER, EventRegistry.class, EVENT_TEST_DIRECTORY);
            resultER = (EventRegistry) XMLSerializer.deserializeFromXML(EventRegistry.class, EVENT_TEST_DIRECTORY);
        } catch (Exception eXML) {

        }

        boolean expected = true;
        boolean result = true;
        for (Event e : resultER.getEvents()) {
            result = result && expectedER.getEvents().contains(e);
        }
        assertEquals(expected, result);
    }

    @Test
    void testToFromXMLStatesNotEmptyUR() {
        expo.getUserRegistry().addUser(TEST_USER_1);
        UserRegistry expectedUR = expo.getUserRegistry();
        UserRegistry resultUR = null;
        try {
            XMLSerializer.serializeToXML(expectedUR, UserRegistry.class, USER_TEST_DIRECTORY);
            resultUR = (UserRegistry) XMLSerializer.deserializeFromXML(UserRegistry.class, USER_TEST_DIRECTORY);
        } catch (Exception eXML) {

        }

        boolean expected = true;
        boolean result = true;
        for (User u : resultUR.getUsers()) {
            result = result && expectedUR.getUsers().contains(u);
        }
        assertEquals(expected, result);
    }

}
