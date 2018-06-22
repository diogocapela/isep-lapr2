package lapr.project.controller;

import lapr.project.model.Event;
import lapr.project.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class UC03StartAnEventsApplicationSubmissionPeriodControllerTest {

    private UC03StartAnEventsApplicationSubmissionPeriodController controller;
    private Event TEST_EVENT_2 = new Event(2, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");

    public UC03StartAnEventsApplicationSubmissionPeriodControllerTest() {
        controller = new UC03StartAnEventsApplicationSubmissionPeriodController();
        User u1 = new User();
        u1.setIsAdmin(true);
        u1.setName("Nome");
        u1.setEmail("mail@mail.com");
        u1.setPassword("12345678");
        u1.setUsername("username");
        controller.setLoggedInUser(u1);

    }

    @Test
    public void testValidUC() {
        controller.startApplicationSubmissionPeriod(TEST_EVENT_2);
        assertTrue(TEST_EVENT_2.getIsOpenToApplications());
        controller.stopApplicationSubmissionPeriod(TEST_EVENT_2);
        assertFalse(TEST_EVENT_2.getIsOpenToApplications());
    }
}
