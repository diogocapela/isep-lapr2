package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.DisplayProduct;
import lapr.project.model.Event;
import lapr.project.model.Keyword;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UCXXListEventApplicationsTest {

    private UCXXListEventApplicationsController controller = new UCXXListEventApplicationsController();

    @Test
    public void testGetAllApplications() {
        DisplayProduct dp1 = new DisplayProduct("Display Product 1");
        DisplayProduct dp2 = new DisplayProduct("Display Product 2");
        DisplayProduct dp3 = new DisplayProduct("Display Product 3");
        List<DisplayProduct> displayProductList1 = new ArrayList<>();
        displayProductList1.add(dp1);
        displayProductList1.add(dp2);
        displayProductList1.add(dp3);
        Keyword k1 = new Keyword("Banana");
        Keyword k2 = new Keyword("Banana");
        Keyword k3 = new Keyword("Banana");
        Keyword k4 = new Keyword("Apple");
        Keyword k5 = new Keyword("Apple");
        Keyword k6 = new Keyword("Orange");
        Keyword k7 = new Keyword("Lemon");
        Keyword k8 = new Keyword("Strawberry");
        Keyword k9 = new Keyword("Peach");
        List<Keyword> keywordList1 = new ArrayList<>();
        keywordList1.add(k1);
        keywordList1.add(k4);
        keywordList1.add(k6);
        List<Keyword> keywordList2 = new ArrayList<>();
        keywordList2.add(k2);
        keywordList2.add(k8);
        keywordList2.add(k9);
        List<Keyword> keywordList3 = new ArrayList<>();
        keywordList2.add(k3);
        keywordList2.add(k5);
        keywordList2.add(k7);
        Application a1 = new Application("Company 1", 111111111, 111111111, 10, 20, displayProductList1, keywordList1);
        Application a2 = new Application("Company 2", 222222222, 222222222, 20, 40, displayProductList1, keywordList2);
        Application a3 = new Application("Company 3", 333333333, 333333333, 30, 60, displayProductList1, keywordList3);
        Application a4 = new Application("Company 4", 444444444, 444444444, 40, 80, displayProductList1, keywordList1);
        Application a5 = new Application("Company 5", 555555555, 555555555, 50, 100, displayProductList1, keywordList2);
        Application a6 = new Application("Company 6", 666666666, 666666666, 60, 120, displayProductList1, keywordList1);
        List<Application> expectedApplicationList = new ArrayList<>();
        expectedApplicationList.add(a1);
        expectedApplicationList.add(a2);
        expectedApplicationList.add(a3);
        expectedApplicationList.add(a4);
        expectedApplicationList.add(a5);
        expectedApplicationList.add(a6);
        Event event = new Event(1, "Motard Gathering 05", "Melhor evento de sempre","Porto","2018/8/22", "2018/8/15");
        event.addApplication(a1);
        event.addApplication(a2);
        event.addApplication(a3);
        event.addApplication(a4);
        event.addApplication(a5);
        event.addApplication(a6);
        List<Application> applicationList = controller.getAllApplications(event);
        assertEquals(applicationList, expectedApplicationList);
    }


    @Test
    public void testGetFilteredApplications() {
        DisplayProduct dp1 = new DisplayProduct("Display Product 1");
        DisplayProduct dp2 = new DisplayProduct("Display Product 2");
        DisplayProduct dp3 = new DisplayProduct("Display Product 3");
        List<DisplayProduct> displayProductList1 = new ArrayList<>();
        displayProductList1.add(dp1);
        displayProductList1.add(dp2);
        displayProductList1.add(dp3);
        Keyword k1 = new Keyword("Banana");
        Keyword k2 = new Keyword("Banana");
        Keyword k3 = new Keyword("Banana");
        Keyword k4 = new Keyword("Apple");
        Keyword k5 = new Keyword("Apple");
        Keyword k6 = new Keyword("Orange");
        Keyword k7 = new Keyword("Lemon");
        Keyword k8 = new Keyword("Strawberry");
        Keyword k9 = new Keyword("Peach");
        List<Keyword> keywordList1 = new ArrayList<>();
        keywordList1.add(k1);
        keywordList1.add(k4);
        keywordList1.add(k6);
        List<Keyword> keywordList2 = new ArrayList<>();
        keywordList2.add(k2);
        keywordList2.add(k8);
        keywordList2.add(k9);
        List<Keyword> keywordList3 = new ArrayList<>();
        keywordList2.add(k3);
        keywordList2.add(k5);
        keywordList2.add(k7);
        Application a1 = new Application("Company 1", 111111111, 111111111, 10, 20, displayProductList1, keywordList1);
        Application a2 = new Application("Company 2", 222222222, 222222222, 20, 40, displayProductList1, keywordList2);
        Application a3 = new Application("Company 3", 333333333, 333333333, 30, 60, displayProductList1, keywordList3);
        Application a4 = new Application("Company 4", 444444444, 444444444, 40, 80, displayProductList1, keywordList1);
        Application a5 = new Application("Company 5", 555555555, 555555555, 50, 100, displayProductList1, keywordList2);
        Application a6 = new Application("Company 6", 666666666, 666666666, 60, 120, displayProductList1, keywordList1);
        List<Application> expectedApplicationList = new ArrayList<Application>();
        expectedApplicationList.add(a1);
        expectedApplicationList.add(a2);
        expectedApplicationList.add(a3);
        expectedApplicationList.add(a4);
        expectedApplicationList.add(a5);
        expectedApplicationList.add(a6);
        Event event = new Event(1, "Gamecon", "Conferencia de video jogos","Porto","2018/8/20", "2018/8/10");
        event.addApplication(a1);
        event.addApplication(a2);
        event.addApplication(a3);
        event.addApplication(a4);
        event.addApplication(a5);
        event.addApplication(a6);
        List<Application>reviewPendingApplicationsList =  controller.getFilteredApplications(event, "reviewPending");
        assertEquals(reviewPendingApplicationsList,expectedApplicationList);
    }

}
