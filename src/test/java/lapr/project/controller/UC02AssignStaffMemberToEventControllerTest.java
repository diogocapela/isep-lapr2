package lapr.project.controller;

import lapr.project.model.Event;
import lapr.project.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UC02AssignStaffMemberToEventControllerTest {

    private UC02AssignStaffMemberToEventController controller;
    private Event TEST_EVENT_1 = new Event(1, "Motard Gathering 05", "Melhor evento de sempre","Porto","2018/8/22", "2018/8/15");
    private User TEST_USER_1 = new User("Xaker", "xaker@gmail.com", "12345678", "Filipe Couto");

    public UC02AssignStaffMemberToEventControllerTest() {
        controller = new UC02AssignStaffMemberToEventController();
        
    }

    @Test
    public void ensureAddAndDeleteStaffMemberWorks() {
        //add
        controller.addUserToStaffMembers(TEST_USER_1, TEST_EVENT_1);
        boolean added = false;
        for(User user : TEST_EVENT_1.getStaffMembers()) {
            if(user.equals(TEST_USER_1)) {
                added = true;
            }
        }
        assertTrue(added);
        //Delete
        controller.deleteUserFromStaffMembers(TEST_USER_1, TEST_EVENT_1);
        for(User u : TEST_EVENT_1.getStaffMembers()){
            assertNotEquals(TEST_USER_1, u);
        }
    }
}
