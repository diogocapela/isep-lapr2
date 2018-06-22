package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.model.Keyword;
import lapr.project.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the UC16ListEventTopicsController class.
 * <p>
 * Created by Diogo Capela (1171316@isep.ipp.pt) on 05/06/2018. Improved by
 * Vítor Hugo Silva (1140825@isep.ipp.pt) on 13/06/2018.
 */
public class UC16ListEventTopicsControllerTest {

    private UC16ListEventTopicsController controller;
    private User TEST_ADMIN_USER;
    private Event TEST_EVENT_1 = new Event(1, "Motard Gathering 05", "Melhor evento de sempre", "Porto", "2018/12/08", "2018/5/08");
    private List<Keyword> lstK = new ArrayList<>();

    public UC16ListEventTopicsControllerTest() {
        controller = new UC16ListEventTopicsController();
        TEST_ADMIN_USER = new User();
        TEST_ADMIN_USER.setUsername("adminTEST");
        TEST_ADMIN_USER.setEmail("admintest@admin.pt");
        TEST_ADMIN_USER.setName("name admin");
        TEST_ADMIN_USER.setPassword("12345678");
        TEST_ADMIN_USER.setIsAdmin(true);
        controller.setLoggedInUser(TEST_ADMIN_USER);

        Application app = new Application("Trade", 233233, 232323, 100, 50, new ArrayList<>(), new ArrayList<>());
        app.setAccepted();
        lstK.add(new Keyword("cenas"));
        lstK.add(new Keyword("coisas"));
        app.setKeywords(lstK);
        TEST_EVENT_1.addApplication(app);
    }

    @Test
    public void testGetKeywordFrequencyMap() {
        // Arrange
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Banana", 3);
        expected.put("Apple", 2);
        expected.put("Orange", 1);
        // Act
        UC16ListEventTopicsController controller = new UC16ListEventTopicsController();
        List<Keyword> keywordList = new ArrayList<>();
        Keyword k1 = new Keyword("Banana");
        Keyword k2 = new Keyword("Banana");
        Keyword k3 = new Keyword("Banana");
        Keyword k4 = new Keyword("Apple");
        Keyword k5 = new Keyword("Apple");
        Keyword k6 = new Keyword("Orange");
        keywordList.add(k1);
        keywordList.add(k2);
        keywordList.add(k3);
        keywordList.add(k4);
        keywordList.add(k5);
        keywordList.add(k6);
        Map<String, Integer> result = controller.getKeywordFrequencyMap(keywordList);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetKeywordsEvent() {
        List<Keyword> newLst;
        newLst = controller.getKeywordsFromEvent(TEST_EVENT_1, "accepted");
        assertEquals(lstK, newLst);
    }

}
