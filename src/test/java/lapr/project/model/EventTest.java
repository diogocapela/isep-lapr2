package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    private User TEST_USER_1 = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
    private User TEST_USER_11 = new User("Rafael2", "rpm@gmail.com", "batatoon", "Rafa");
    private User TEST_USER_2 = new User("Mafalda", "mpm@gmail.com", "companhia", "Mafi");
    private User TEST_USER_22 = new User("Mafalda2", "mpm@gmail.com", "companhia", "Mafi");
    private User TEST_USER_3 = new User("Neutro", "npn@gmail.com", "TVI", "Neu");
    private User TEST_USER_33 = new User("Neutro2", "npn@gmail.com", "TVI", "Neu");
    private User TEST_USER_4 = new User("Pim", "ppp@gmail.com", "PAM", "PUM");
    private Stand TEST_STAND_1 = new Stand("ab", 1);
    private Stand TEST_STAND_2 = new Stand("cd", 2);
    private Keyword TEST_KEYWORD = new Keyword("TEST_KEYWORD");
    private List<Keyword> TEST_KEYWORD_LIST = new ArrayList<>();
    private DisplayProduct TEST_DISPLAY_PRODUCT = new DisplayProduct("TEST_PRODUCT");
    private List<DisplayProduct> TEST_DISPLAY_PRODUCT_LIST = new ArrayList<>();
    private ApplicationReview TEST_APPLICATION_REVIEW = new ApplicationReview();
    private List<ApplicationReview> TEST_APPLICATION_REVIEW_LIST = new ArrayList<>();
    private Application TEST_APPLICATION_1 = new Application();
    private Application TEST_APPLICATION_2 = new Application();
    private Event TEST_EVENT_1 = new Event(1, "Real Estate Fair", "Right here, right now", "Exponor", "2018/12/12", "2018/1/1");
    private Event TEST_EVENT_2 = new Event(2, "NOT IT", "Everywhere", "Exposul", "2018/11/11", "2018/2/2");

    public EventTest() {
        TEST_DISPLAY_PRODUCT_LIST.add(TEST_DISPLAY_PRODUCT);

        TEST_KEYWORD_LIST.add(TEST_KEYWORD);

        TEST_APPLICATION_REVIEW.setUser(TEST_USER_1);
        TEST_APPLICATION_REVIEW.setIsAccepted(false);
        TEST_APPLICATION_REVIEW.setJustification("because");
        TEST_APPLICATION_REVIEW.setStaffTopicKnowledgeRating(1);
        TEST_APPLICATION_REVIEW.setEventAdequacyRating(2);
        TEST_APPLICATION_REVIEW.setInviteAdequacyRating(3);
        TEST_APPLICATION_REVIEW.setRequestedStandAreaRating(4);
        TEST_APPLICATION_REVIEW.setOverallRecommendationRating(5);
        TEST_APPLICATION_REVIEW_LIST.add(TEST_APPLICATION_REVIEW);

        TEST_APPLICATION_1.setCompanyTradeName("Sonae");
        TEST_APPLICATION_1.setVatNumber(123654789);
        TEST_APPLICATION_1.setPhoneNumber(963258741);
        TEST_APPLICATION_1.setNumberOfInvitations(5);
        TEST_APPLICATION_1.setIntendedStandArea(5);
        TEST_APPLICATION_1.setDisplayProducts(TEST_DISPLAY_PRODUCT_LIST);
        TEST_APPLICATION_1.setKeywords(TEST_KEYWORD_LIST);
        TEST_APPLICATION_1.setStatus("reviewPending");
        TEST_APPLICATION_1.setAssignedStand(TEST_STAND_2);
        TEST_APPLICATION_1.setReviews(TEST_APPLICATION_REVIEW_LIST);

        TEST_APPLICATION_2.setCompanyTradeName("Oracle");
        TEST_APPLICATION_2.setVatNumber(123654789);
        TEST_APPLICATION_2.setPhoneNumber(963258741);
        TEST_APPLICATION_2.setNumberOfInvitations(20);
        TEST_APPLICATION_2.setIntendedStandArea(20);
        TEST_APPLICATION_2.setDisplayProducts(TEST_DISPLAY_PRODUCT_LIST);
        TEST_APPLICATION_2.setKeywords(TEST_KEYWORD_LIST);
        TEST_APPLICATION_2.setStatus("accepted");
        TEST_APPLICATION_2.setAssignedStand(TEST_STAND_1);
        TEST_APPLICATION_2.setReviews(TEST_APPLICATION_REVIEW_LIST);

        TEST_EVENT_1.setIsOpenToApplications(false);
        TEST_EVENT_1.addOrganiser(TEST_USER_1);
        TEST_EVENT_1.addOrganiser(TEST_USER_11);
        TEST_EVENT_1.addStaffMember(TEST_USER_2);
        TEST_EVENT_1.addStaffMember(TEST_USER_22);
        TEST_EVENT_1.addAttendee(TEST_USER_3);
        TEST_EVENT_1.addAttendee(TEST_USER_33);
        TEST_EVENT_1.addStand(TEST_STAND_1);
        TEST_EVENT_1.addApplication(TEST_APPLICATION_1);
    }

    @Test
    void testGetId() {
        int expected = 1;
        Event event = new Event();
        event.setId(expected);
        int result = event.getId();
        assertEquals(expected, result);
    }

    @Test
    void testGetTitle() {
        String expected = "Test Title";
        Event event = new Event();
        event.setTitle(expected);
        String result = event.getTitle();
        assertEquals(expected, result);
    }

    @Test
    void testGetDescription() {
        String expected = "Test description...";
        Event event = new Event();
        event.setDescription(expected);
        String result = event.getDescription();
        assertEquals(expected, result);
    }

    @Test
    void testGetLocation() {
        String expected = "Porto";
        Event event = new Event();
        event.setLocation(expected);
        String result = event.getLocation();
        assertEquals(expected, result);
    }

    @Test
    void testValidGetDate() {
        Date expected = new Date(2018,5,5);
        Event event = new Event();
        event.setDate("2018/5/5");
        Date result = event.getDate();
        assertEquals(expected, result);
    }

    @Test
    void testNotValidGetDate() {
        Date expected = new Date(2017,4,4);
        assertThrows(IllegalArgumentException.class,()->{
            TEST_EVENT_1.setDate("2017/4/4");
        });
        Event event = new Event();
        event.setDate("2017/4/4");
        Date result = event.getDate();
        assertEquals(expected, result);
    }

    @Test
    void testValidGetDeadline() {
        Date expected = new Date(2018,8,8);
        Event event = new Event();
        event.setDate("2019/8/8");
        event.setDeadline("2018/8/8");
        Date result = event.getDeadline();
        assertEquals(expected, result);
    }

    @Test
    void testNotValidGetDeadline() {
        Date expected = new Date(2019,5,5);
        assertThrows(IllegalArgumentException.class,()->{
            TEST_EVENT_1.setDeadline("2019/5/5");
        });
    }

    @Test
    void testGetIsOpenToApplications() {
        boolean expected = false;
        Event event = new Event();
        event.setIsOpenToApplications(false);
        boolean result = event.getIsOpenToApplications();
        assertEquals(expected, result);
    }

    @Test
    void testGetOrganisers() {
        List<User> organisers = new ArrayList<>();
        List<User> expected = organisers;
        Event event = new Event();
        event.setOrganisers(organisers);
        List<User> result = event.getOrganisers();
        assertEquals(expected, result);
    }

    @Test
    void testGetStaffMembers() {
        List<User> staffMembers = new ArrayList<>();
        List<User> expected = staffMembers;
        Event event = new Event();
        event.setStaffMembers(staffMembers);
        List<User> result = event.getStaffMembers();
        assertEquals(expected, result);
    }

    @Test
    void testGetAtendees() {
        List<User> atendees = new ArrayList<>();
        List<User> expected = atendees;
        Event event = new Event();
        event.setAttendees(atendees);
        List<User> result = event.getAttendees();
        assertEquals(expected, result);
    }

    @Test
    void testGetStands() {
        List<Stand> expected = new ArrayList<>();
        Event event = new Event();
        event.setStandList(expected);
        List<Stand> result = event.getStandList();
        assertEquals(expected, result);
    }

    @Test
    void testGetApplications() {
        List<Application> expected = new ArrayList<>();
        Event event = new Event();
        event.setApplicationList(expected);
        List<Application> result = event.getApplicationList();
        assertEquals(expected, result);
    }

    @Test
    void testAddValidOrganiser() {
        int size = TEST_EVENT_1.getOrganisers().size();
        TEST_EVENT_1.addOrganiser(TEST_USER_4);
        assertTrue(TEST_EVENT_1.getOrganisers().size() > size);
    }

    @Test
    void testAddRepeatedOrganiser() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.addOrganiser(TEST_USER_11);
        });
    }

    @Test
    void testAddOrganiserWhichIsStaffMember() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.addOrganiser(TEST_USER_22);
        });
    }

    @Test
    void testAddOrganiserWhichIsAtendee() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.addOrganiser(TEST_USER_33);
        });
    }

    @Test
    void testRemoveValidOrganiser() {
        int size = TEST_EVENT_1.getOrganisers().size();
        TEST_EVENT_1.removeOrganiser(TEST_USER_1);
        assertTrue(TEST_EVENT_1.getOrganisers().size() < size);
    }

    @Test
    void testRemoveInvalidOrganiser() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.removeOrganiser(TEST_USER_22);
        });
    }

    @Test
    void testAddValidStaffMember() {
        int size = TEST_EVENT_1.getStaffMembers().size();
        TEST_EVENT_1.addStaffMember(TEST_USER_4);
        assertTrue(TEST_EVENT_1.getStaffMembers().size() > size);
    }

    @Test
    void testAddRepeatedStaffMember() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.addStaffMember(TEST_USER_22);
        });
    }

    @Test
    void testAddStaffMemberWhichIsOrganiser() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.addStaffMember(TEST_USER_11);
        });
    }

    @Test
    void testAddStaffMemberWhichIsAtendee() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.addStaffMember(TEST_USER_33);
        });
    }

    @Test
    void testRemoveValidStaffMember() {
        int size = TEST_EVENT_1.getStaffMembers().size();
        TEST_EVENT_1.removeStaffMember(TEST_USER_2);
        assertTrue(TEST_EVENT_1.getStaffMembers().size() < size);
    }

    @Test
    void testRemoveInvalidStaffMember() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.removeStaffMember(TEST_USER_33);
        });
    }

    @Test
    void testAddValidAttendee() {
        int size = TEST_EVENT_1.getAttendees().size();
        TEST_EVENT_1.addAttendee(TEST_USER_4);
        assertTrue(TEST_EVENT_1.getAttendees().size() > size);
    }

    @Test
    void testAddRepeatedAttendee() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.addAttendee(TEST_USER_33);
        });
    }

    @Test
    void testAddAttendeeWhichIsOrganiser() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.addAttendee(TEST_USER_11);
        });
    }

    @Test
    void testAddAttendeeWhichIsStaffMember() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.addAttendee(TEST_USER_22);
        });
    }

    @Test
    void testRemoveValidAttendee() {
        int size = TEST_EVENT_1.getAttendees().size();
        TEST_EVENT_1.removeAttendee(TEST_USER_3);
        assertTrue(TEST_EVENT_1.getAttendees().size() < size);
    }

    @Test
    void testRemoveInvalidAttendee() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.removeAttendee(TEST_USER_11);
        });
    }

    @Test
    void testAddValidStand() {
        int size = TEST_EVENT_1.getStandList().size();
        TEST_EVENT_1.addStand(TEST_STAND_2);
        assertTrue(TEST_EVENT_1.getStandList().size() > size);
    }

    @Test
    void testAddInvalidStand() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.addStand(TEST_STAND_1);
        });
    }

    @Test
    void testRemoveValidStand() {
        int size = TEST_EVENT_1.getStandList().size();
        TEST_EVENT_1.removeStand(TEST_STAND_1);
        assertTrue(TEST_EVENT_1.getStandList().size() < size);
    }

    @Test
    void testRemoveInvalidStand() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.removeStand(TEST_STAND_2);
        });
    }

    @Test
    void testAddValidApplication() {
        int size = TEST_EVENT_1.getApplicationList().size();
        TEST_EVENT_1.addApplication(TEST_APPLICATION_2);
        assertTrue(TEST_EVENT_1.getApplicationList().size() > size);
    }

    @Test
    void testAddInvalidApplication() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.addApplication(TEST_APPLICATION_1);
        });
    }

    @Test
    void testRemoveValidApplication() {
        int size = TEST_EVENT_1.getApplicationList().size();
        TEST_EVENT_1.removeApplication(TEST_APPLICATION_1);
        assertTrue(TEST_EVENT_1.getApplicationList().size() < size);
    }

    @Test
    void testRemoveInvalidApplication() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_EVENT_1.removeApplication(TEST_APPLICATION_2);
        });
    }

    @Test
    void testToString() {
        String expected = "Title: A Title | Description: A banana | Location: Porto | Date: 2020/10/10 | Application's Deadline: 2019/10/10";
        Event event = new Event();
        event.setId(56);
        event.setTitle("A Title");
        event.setDescription("A banana");
        event.setLocation("Porto");
        event.setDate("2020/10/10");
        event.setDeadline("2019/10/10");
        assertEquals(expected,event.toString());
    }

    @Test
    void ensureSameObjectIsEqual() {
        assertTrue(TEST_EVENT_1.equals(TEST_EVENT_1));
    }

    @Test
    void ensureObjectsDifferentIDAreNotEqual() {
        assertFalse(TEST_EVENT_1.equals(TEST_EVENT_2));
    }

    @Test
    void ensureDifferentObjectsAreNotEqual() {
        assertFalse(TEST_EVENT_1.equals(TEST_STAND_1));
    }

    @Test
    void ensureHashCodeIsCorrect() {
        int expected = -1110674067;
        int result = TEST_EVENT_1.hashCode();
        assertEquals(expected, result);
    }

}
