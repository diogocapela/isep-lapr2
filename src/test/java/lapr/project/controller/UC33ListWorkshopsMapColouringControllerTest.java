package lapr.project.controller;

import lapr.project.model.User;
import lapr.project.model.Workshop;
import lapr.project.ui.UC33ListWorkshopsMapColouringUI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UC33ListWorkshopsMapColouringControllerTest {
    UC33ListWorkshopsMapColouringController controller;

    User TEST_USER_1 = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
    User TEST_USER_11 = new User("Rafael2", "rpm@gmail.com", "batatoon", "Rafa");
    User TEST_USER_2 = new User("Mafalda", "mpm@gmail.com", "companhia", "Mafi");
    User TEST_USER_22 = new User("Mafalda2", "mpm@gmail.com", "companhia", "Mafi");
    User TEST_USER_3 = new User("Neutro", "npn@gmail.com", "TVI", "Neu");
    User TEST_USER_33 = new User("Neutro2", "npn@gmail.com", "TVI", "Neu");
    User TEST_USER_4 = new User("Pim", "ppp@gmail.com", "PAM", "PUM");

    Workshop TEST_WORKSHOP_1 = new Workshop("AngularJS","1",60);
    Workshop TEST_WORKSHOP_2 = new Workshop("ReactJS","1",60);
    Workshop TEST_WORKSHOP_3 = new Workshop("SQL1","1",60);
    Workshop TEST_WORKSHOP_4 = new Workshop("SQL2","1",60);
    Workshop TEST_WORKSHOP_5 = new Workshop("SQL3","1",60);
    Workshop TEST_WORKSHOP_6 = new Workshop("SQL4","1",60);

    List<Workshop> TEST_WORKSHOP_LIST_1 = new ArrayList<>();
    List<Workshop> TEST_WORKSHOP_LIST_2 = new ArrayList<>();
    List<Workshop> TEST_WORKSHOP_LIST_3 = new ArrayList<>();
    List<Workshop> TEST_WORKSHOP_LIST_4 = new ArrayList<>();
    List<Workshop> TEST_WORKSHOP_LIST_5 = new ArrayList<>();
    List<Workshop> TEST_WORKSHOP_LIST_6 = new ArrayList<>();
    List<Workshop> TEST_WORKSHOP_LIST_7 = new ArrayList<>();

    public UC33ListWorkshopsMapColouringControllerTest(){
        controller = new UC33ListWorkshopsMapColouringController();

        TEST_WORKSHOP_1.addInterestedUser(TEST_USER_1);
        TEST_WORKSHOP_LIST_1.add(TEST_WORKSHOP_1);

        TEST_WORKSHOP_2.addInterestedUser(TEST_USER_11);
        TEST_WORKSHOP_LIST_2.add(TEST_WORKSHOP_1);
        TEST_WORKSHOP_LIST_2.add(TEST_WORKSHOP_2);

        TEST_WORKSHOP_3.addInterestedUser(TEST_USER_1);
        TEST_WORKSHOP_LIST_3.add(TEST_WORKSHOP_1);
        TEST_WORKSHOP_LIST_3.add(TEST_WORKSHOP_3);

        TEST_WORKSHOP_LIST_4.add(TEST_WORKSHOP_1);
        TEST_WORKSHOP_LIST_4.add(TEST_WORKSHOP_2);
        TEST_WORKSHOP_LIST_4.add(TEST_WORKSHOP_3);

        TEST_WORKSHOP_4.addInterestedUser(TEST_USER_1);
        TEST_WORKSHOP_4.addInterestedUser(TEST_USER_11);
        TEST_WORKSHOP_4.addInterestedUser(TEST_USER_33);

        TEST_WORKSHOP_5.addInterestedUser(TEST_USER_1);
        TEST_WORKSHOP_5.addInterestedUser(TEST_USER_22);
        TEST_WORKSHOP_5.addInterestedUser(TEST_USER_33);

        TEST_WORKSHOP_6.addInterestedUser(TEST_USER_22);
        TEST_WORKSHOP_6.addInterestedUser(TEST_USER_3);
        TEST_WORKSHOP_6.addInterestedUser(TEST_USER_4);

        TEST_WORKSHOP_LIST_5.add(TEST_WORKSHOP_4);
        TEST_WORKSHOP_LIST_5.add(TEST_WORKSHOP_5);
        TEST_WORKSHOP_LIST_5.add(TEST_WORKSHOP_6);
    }

    @Test
    void testListMapColouringOneWorkShop(){
        boolean equals=false;

        List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> expected = new ArrayList<>();
        expected.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(TEST_WORKSHOP_1,0));
        expected.get(0).setColor(1);

        List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> result = controller.listMapColouring(TEST_WORKSHOP_LIST_1);

        for (int i = 0; i < expected.size(); i++) {
            equals = expected.get(i).equals(result.get(i));
        }
        assertTrue(equals);
    }

    @Test
    void testListMapColouringTwoWorkShopsOneUserNoCommon(){
        boolean equals=false;

        List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> expected = new ArrayList<>();
        expected.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(TEST_WORKSHOP_1,0));
        expected.get(0).setColor(1);
        expected.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(TEST_WORKSHOP_2,0));
        expected.get(1).setColor(1);

        List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> result = controller.listMapColouring(TEST_WORKSHOP_LIST_2);

        for (int i = 0; i < expected.size(); i++) {
            equals = expected.get(i).equals(result.get(i));
        }
        assertTrue(equals);
    }

    @Test
    void testListMapColouringTwoWorkShopsOneUserOneCommon(){
        boolean equals=false;

        List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> expected = new ArrayList<>();
        expected.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(TEST_WORKSHOP_1,1));
        expected.get(0).setColor(1);
        expected.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(TEST_WORKSHOP_3,1));
        expected.get(1).setColor(2);

        List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> result = controller.listMapColouring(TEST_WORKSHOP_LIST_3);

        for (int i = 0; i < expected.size(); i++) {
            equals = expected.get(i).equals(result.get(i));
        }
        assertTrue(equals);
    }

    @Test
    void testListMapColouringThreeWorkShopsOneCommonOneDiff(){
        boolean equals=false;

        List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> expected = new ArrayList<>();
        expected.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(TEST_WORKSHOP_1,1));
        expected.get(0).setColor(1);
        expected.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(TEST_WORKSHOP_3,1));
        expected.get(1).setColor(2);
        expected.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(TEST_WORKSHOP_2,0));
        expected.get(2).setColor(1);

        List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> result = controller.listMapColouring(TEST_WORKSHOP_LIST_4);

        for (int i = 0; i < expected.size(); i++) {
            equals = expected.get(i).equals(result.get(i));
        }
        assertTrue(equals);
    }

    @Test
    void testListMapColouringThreeWorkShopsThreeElements(){
        boolean equals=false;

        List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> expected = new ArrayList<>();
        expected.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(TEST_WORKSHOP_4,2));
        expected.get(0).setColor(1);
        expected.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(TEST_WORKSHOP_5,1));
        expected.get(1).setColor(2);
        expected.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(TEST_WORKSHOP_6,1));
        expected.get(2).setColor(2);

        List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> result = controller.listMapColouring(TEST_WORKSHOP_LIST_5);

        for (int i = 0; i < expected.size(); i++) {
            equals = expected.get(i).equals(result.get(i));
        }
        assertTrue(equals);
    }
}