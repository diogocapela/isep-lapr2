package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Vítor Hugo Silva <1140825@isep.ipp.pt>
 */
public class WorkshopTest {

    User TEST_USER_1 = new User("diogocapela", "diogocapela@gmail.com", "12365478", "Diogo Capela");
    User TEST_USER_2 = new User("rafamarquez", "rpm@gmail.com", "12345678", "Rafael Marques");

    Workshop TEST_WORKSHOP_1 = new Workshop("Title Test", "Workshop Description", 10, 20);
    Workshop TEST_WORKSHOP_2 = new Workshop("Title Test 2", "Workshop Description 2", 60);

    List<Workshop> TEST_WORKSHOP_LIST = new ArrayList<>();

    public WorkshopTest() {
        TEST_WORKSHOP_1.addInterestedUser(TEST_USER_1);

        TEST_WORKSHOP_LIST.add(TEST_WORKSHOP_1);
        TEST_WORKSHOP_LIST.add(TEST_WORKSHOP_2);
    }

    /**
     * Test of getTitle and setTitle method, of class Workshop.
     */
    @Test
    public void testGetSetTitle() {
        String expResult = "Title Test edited";
        TEST_WORKSHOP_1.setWorkshopTitle(expResult);
        String result = TEST_WORKSHOP_1.getWorkshopTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue and setDescription method, of class Workshop.
     */
    @Test
    public void testGetSetDescription() {
        String expResult = "Workshop Description edited";
        TEST_WORKSHOP_1.setWorkshopDescription(expResult);
        String result = TEST_WORKSHOP_1.getWorkshopDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRoom and setRoom method, of class Workshop.
     */
    @Test
    public void testGetSetRoom() {
        int expResult = 12;
        TEST_WORKSHOP_1.setRoom(expResult);
        int result = TEST_WORKSHOP_1.getRoom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDuration and setDuration method, of class Workshop.
     */
    @Test
    public void testGetSetDuration() {
        int expResult = 22;
        TEST_WORKSHOP_1.setDuration(expResult);
        int result = TEST_WORKSHOP_1.getDuration();
        assertEquals(expResult, result);
    }

    /**
     * Test of addNecessaryEquipment method, of class Workshop
     */
    @Test
    public void testaddNecessaryEquipment() {
        try {
            TEST_WORKSHOP_1.addNecessaryEquipment("Eq test 1");
            assertEquals(true, true);
        } catch (Exception ex) {
            assertEquals(false, true);
        }
    }

    /**
     * Test of addInterestedUser method, of class Workshop.
     *
     * @throws java.lang.Exception : If already exists
     */
    @Test
    public void testAddValidInterestedUser() throws Exception {
        int expected = TEST_WORKSHOP_1.getInterestedUsers().size() + 1;
        TEST_WORKSHOP_1.addInterestedUser(TEST_USER_2);
        int result = TEST_WORKSHOP_1.getInterestedUsers().size();
        assertEquals(expected, result);
    }

    /**
     * Test of addInterestedUser method, of class Workshop.
     *
     * @throws java.lang.Exception : If already exists
     */
    @Test
    public void testAddNotValidInterestedUser() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_WORKSHOP_1.addInterestedUser(TEST_USER_1);
        });
    }

    @Test
    public void testToString() {
        String expected = "Title: Banana Workshop | Description: We eat apples | Room: 55";
        Workshop workshop = new Workshop();
        workshop.setWorkshopTitle("Banana Workshop");
        workshop.setWorkshopDescription("We eat apples");
        workshop.setRoom(55);
        String result = workshop.toString();
        assertEquals(expected, result);
    }

    /**
     * Test of equals method, of class Workshop.
     */
    @Test
    public void testEquals() {
        boolean expResult = true;
        boolean result = TEST_WORKSHOP_1.equals(TEST_WORKSHOP_1);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Workshop.
     */
    @Test
    public void testEqualsInstanceOf() {
        boolean expResult = false;
        boolean result = TEST_WORKSHOP_1.equals("Not Workshop Instance");
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Workshop.
     */
    @Test
    public void testHashCode() {
        int expResult = -578071135;
        int result = TEST_WORKSHOP_1.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of empty constructor, of class Workshop
     */
    @Test
    public void testEmptyConstructorWorkshop() {
        Workshop w = new Workshop();
        w.setWorkshopDescription(TEST_WORKSHOP_1.getWorkshopDescription());
        w.setDuration(TEST_WORKSHOP_1.getDuration());
        w.setRoom(TEST_WORKSHOP_1.getRoom());
        w.setWorkshopTitle(TEST_WORKSHOP_1.getWorkshopTitle());
        w.addInterestedUser(TEST_USER_1);
        boolean expectedResult = true;
        assertEquals(expectedResult, w.equals(TEST_WORKSHOP_1));
    }

    @Test
    void testCompareTo() {
        int maxCountInterestedUsers = 0;
        Workshop expected = new Workshop();
        for (Workshop workshopTmp : TEST_WORKSHOP_LIST) {
            if (workshopTmp.getInterestedUsers().size() > maxCountInterestedUsers) {
                maxCountInterestedUsers = workshopTmp.getInterestedUsers().size();
                expected = workshopTmp;
            }
        }

        Collections.sort(TEST_WORKSHOP_LIST, Collections.reverseOrder());

        Workshop result = TEST_WORKSHOP_LIST.get(0);

        assertEquals(expected, result);
    }

    @Test
    void testCompareToReverseOrder() {
        int maxCountInterestedUsers = 0;
        Workshop expected = new Workshop();
        for (Workshop workshopTmp : TEST_WORKSHOP_LIST) {
            if (workshopTmp.getInterestedUsers().size() > maxCountInterestedUsers) {
                maxCountInterestedUsers = workshopTmp.getInterestedUsers().size();
                expected = workshopTmp;
            }
        }

        Collections.sort(TEST_WORKSHOP_LIST);

        Workshop result = TEST_WORKSHOP_LIST.get(TEST_WORKSHOP_LIST.size() - 1);

        assertEquals(expected, result);
    }
}
