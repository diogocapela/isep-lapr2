package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventRegistryTest {
    EventRegistry TEST_EVENT_REGISTRY_0 = new EventRegistry();
    EventRegistry TEST_EVENT_REGISTRY_1 = new EventRegistry();
    Event TEST_EVENT_1 = new Event(10, "2", "3", "5", "2000/6/9", "2000/2/6");

    public EventRegistryTest() {
        TEST_EVENT_REGISTRY_1.addEvent(TEST_EVENT_1);
    }

    @Test
    public void ensureDeleteEventWorks() throws Exception {
        EventRegistry eventRegistry = new EventRegistry();
        Event e1 = new Event(1, "Event 1 Title", "Event 1 description...", "Porto", "2018/1/1", "2017/1/1");
        eventRegistry.addEvent(e1);
        eventRegistry.deleteEvent(e1);
        assertEquals(0, eventRegistry.getEvents().size());
    }

    @Test
    public void ensureAddingDuplicateEventsThrowsException() {
        EventRegistry eventRegistry = new EventRegistry();
        Event e1 = new Event(1, "Event 1 Title", "Event 1 description...", "Porto", "2018/1/1", "2017/1/1");
        assertThrows(Exception.class, () -> {
            eventRegistry.addEvent(e1);
            eventRegistry.addEvent(e1);
        });
    }

    @Test
    void testEqualsSameEventRegistry() {
        EventRegistry expected = TEST_EVENT_REGISTRY_1;
        EventRegistry result = TEST_EVENT_REGISTRY_1;
        assertEquals(expected, result);
    }

    @Test
    void testEqualsNotSameEventRegistry() {
        EventRegistry expected = TEST_EVENT_REGISTRY_1;
        EventRegistry result = TEST_EVENT_REGISTRY_0;
        assertFalse(expected.equals(result));
    }

    @Test
    void testEqualsNotSameClass() {
        EventRegistry expected = TEST_EVENT_REGISTRY_1;
        Event result = TEST_EVENT_1;
        assertFalse(expected.equals(result));
    }

    @Test
    void testHashCode() {
        int expected = -349108689;
        int result = TEST_EVENT_REGISTRY_1.hashCode();
        assertEquals(expected, result);
    }
}
