package lapr.project.controller;

import lapr.project.model.Event;
import lapr.project.model.Stand;
import lapr.project.model.StandDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UC34CalculateMinimumAmountElectricalCableControllerTest {
    UC34CalculateMinimumAmountElectricalCableController controller;

    Event TEST_EVENT_1 = new Event(2, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
    Event TEST_EVENT_2 = new Event(2, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
    Event TEST_EVENT_3 = new Event(2, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");

    Stand TEST_STAND_1 = new Stand("1", 1);
    Stand TEST_STAND_2 = new Stand("2", 2);
    Stand TEST_STAND_3 = new Stand("3", 3);
    Stand TEST_STAND_4 = new Stand("4", 4);
    Stand TEST_STAND_5 = new Stand("5", 5);

    Stand TEST_STAND_31 = new Stand("31", 5);
    Stand TEST_STAND_32 = new Stand("32", 5);
    Stand TEST_STAND_33 = new Stand("33", 5);
    Stand TEST_STAND_34 = new Stand("34", 5);
    Stand TEST_STAND_35 = new Stand("35", 5);
    Stand TEST_STAND_36 = new Stand("36", 5);

    public UC34CalculateMinimumAmountElectricalCableControllerTest() {
        controller = new UC34CalculateMinimumAmountElectricalCableController();

        TEST_STAND_1.addRelativeDistance(new StandDistance("2", 5));
        TEST_STAND_1.addRelativeDistance(new StandDistance("4", 20));
        TEST_STAND_2.addRelativeDistance(new StandDistance("3", 20));
        TEST_STAND_2.addRelativeDistance(new StandDistance("5", 2));
        TEST_STAND_3.addRelativeDistance(new StandDistance("5", 4));
        TEST_STAND_4.addRelativeDistance(new StandDistance("5", 3));

        TEST_EVENT_2.addStand(TEST_STAND_1);
        TEST_EVENT_2.addStand(TEST_STAND_2);
        TEST_EVENT_2.addStand(TEST_STAND_3);
        TEST_EVENT_2.addStand(TEST_STAND_4);
        TEST_EVENT_2.addStand(TEST_STAND_5);

        TEST_STAND_31.addRelativeDistance(new StandDistance("32",5));
        TEST_STAND_31.addRelativeDistance(new StandDistance("33",3));
        TEST_STAND_31.addRelativeDistance(new StandDistance("34",8));
        TEST_STAND_32.addRelativeDistance(new StandDistance("34",6));
        TEST_STAND_32.addRelativeDistance(new StandDistance("35",4));
        TEST_STAND_33.addRelativeDistance(new StandDistance("34",7));
        TEST_STAND_33.addRelativeDistance(new StandDistance("36",4));
        TEST_STAND_34.addRelativeDistance(new StandDistance("35",8));
        TEST_STAND_34.addRelativeDistance(new StandDistance("36",5));
        TEST_STAND_35.addRelativeDistance(new StandDistance("36",2));

        TEST_EVENT_3.addStand(TEST_STAND_31);
        TEST_EVENT_3.addStand(TEST_STAND_32);
        TEST_EVENT_3.addStand(TEST_STAND_33);
        TEST_EVENT_3.addStand(TEST_STAND_34);
        TEST_EVENT_3.addStand(TEST_STAND_35);
        TEST_EVENT_3.addStand(TEST_STAND_36);
    }

    @Test
    void calculateMinimumSpanningTreeEmptyEvent() {
        String expected = "0.00 meters";

        String result = controller.calculateMinimumSpanningTree(TEST_EVENT_1.getStandList());

        assertTrue(result.contains(expected));
    }

    @Test
    void calculateMinimumSpanningTreeNotEmptyEvent() {
        String expected = "14.00 meters";

        String result = controller.calculateMinimumSpanningTree(TEST_EVENT_2.getStandList());

        assertTrue(result.contains(expected));
    }

    @Test
    void calculateMinimumSpanningTreeNotEmptyEventWithUselessBranch() {
        String expected = "18.00 meters";

        String result = controller.calculateMinimumSpanningTree(TEST_EVENT_3.getStandList());

        assertTrue(result.contains(expected));

    }
}