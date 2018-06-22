package lapr.project.controller;

import lapr.project.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class UC04UserRegistrationControllerTest {

    private UC04UserRegistrationController controller;
    public UC04UserRegistrationControllerTest() {
        controller = new UC04UserRegistrationController();
    }
    
    @Test
    public void testAddUser(){
        User u1 = new User();
        u1.setIsAdmin(true);
        u1.setName("Nome");
        u1.setEmail("mail@mail.com");
        u1.setPassword("12345678");
        u1.setUsername("username");
        controller.addUser(u1);
    }
    
    @Test
    public void testNotValidPass(){
        User u1 = new User();
        u1.setIsAdmin(true);
        u1.setName("Nome");
        u1.setEmail("mail@mail.com");
        u1.setPassword("1234678");
        u1.setUsername("username");
        assertThrows(IllegalArgumentException.class, ()->{
            controller.addUser(u1);
        });
    }
    
    @Test
    public void testDelUser(){
        User u1 = new User();
        u1.setIsAdmin(true);
        u1.setName("Nome2");
        u1.setEmail("mail2@mail.com");
        u1.setPassword("12345678");
        u1.setUsername("username2");
        controller.addUser(u1);
        controller.deleteUser(u1);
    }
}
