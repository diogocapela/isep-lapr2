package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.model.User;
import lapr.project.model.Workshop;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UC08SubmitWorkshopSurveyControllerTest {

    @Test
    public void ensureGetAcceptedWorkshopsWorks() {
        Event event = new Event();
        Application application1 = new Application();
        Application application2 = new Application();
        Workshop w1 = new Workshop();
        Workshop w2 = new Workshop();
        Workshop w3 = new Workshop();
        Workshop w4 = new Workshop();
        application1.addWorkshop(w1);
        application1.addWorkshop(w2);
        application2.addWorkshop(w3);
        application2.addWorkshop(w4);
        application1.setAccepted();
        event.addApplication(application1);
        event.addApplication(application2);
        UC08SubmitWorkshopSurveyController controller = new UC08SubmitWorkshopSurveyController();
        controller.setLoggedInUser(new User("UsernameTEST", "test@isep.ipp.pt", "123456789", "User TEST"));
        List<Workshop> expected = new ArrayList<>();
        expected.add(w1);
        expected.add(w2);
        List<Workshop> result = controller.getAcceptedWorkshops(event);
        assertEquals(expected, result);
    }

}
