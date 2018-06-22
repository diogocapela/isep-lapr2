package lapr.project.controller;

import lapr.project.model.Event;
import lapr.project.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class of UC01 controller
 *
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class UC01CreateEventControllerTest {

    private Event TEST_EVENT_1 = new Event(1, "Motard Gathering 05", "Melhor evento de sempre", "Porto", "2018/12/08", "2018/5/08");

    private User TEST_ADMIN_USER, TEST_NONADMIN_USER;

    UC01CreateEventController controller;

    public UC01CreateEventControllerTest() {
        controller = new UC01CreateEventController();
        TEST_ADMIN_USER = new User();
        TEST_ADMIN_USER.setUsername("adminTEST");
        TEST_ADMIN_USER.setEmail("admintest@admin.pt");
        TEST_ADMIN_USER.setName("name admin");
        TEST_ADMIN_USER.setPassword("12345678");
        TEST_ADMIN_USER.setIsAdmin(true);

        TEST_NONADMIN_USER = new User();
        TEST_NONADMIN_USER.setUsername("normalUser");
        TEST_NONADMIN_USER.setEmail("normaluser@notadmin.pt");
        TEST_NONADMIN_USER.setName("name not admin");
        TEST_NONADMIN_USER.setPassword("12345678");
        TEST_NONADMIN_USER.setIsAdmin(false);
        controller.setLoggedInUser(TEST_ADMIN_USER);

    }

    @Test
    public void ensureAddAndDeleteEventWorks() {
        controller.addEvent(TEST_EVENT_1);
        controller.deleteEvent(TEST_EVENT_1);
    }

    @Test
    public void testThrowsWhenNotEventManagerAdd() {
        controller.setLoggedInUser(TEST_NONADMIN_USER);
        assertThrows(IllegalArgumentException.class, () -> {
            controller.addEvent(TEST_EVENT_1);
        });
    }
    
    @Test
    public void testThrowsWhenNotEventManagerRemove() {
        controller.setLoggedInUser(TEST_ADMIN_USER);
        controller.addEvent(TEST_EVENT_1);
        controller.setLoggedInUser(TEST_NONADMIN_USER);
        assertThrows(IllegalArgumentException.class, () -> {
            controller.deleteEvent(TEST_EVENT_1);
        });
    }

}
