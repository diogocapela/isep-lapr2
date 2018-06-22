package lapr.project.utils;

import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AuthManagerTest {

    User TEST_USER_LOG = new User("log", "2", "12345678", "4");
    User TEST_USER_ORG = new User("org", "2", "12345678", "4");
    User TEST_USER_STAFF = new User("staff", "2", "12345678", "4");
    User TEST_USER_ATTENDEE = new User("attendee", "2", "12345678", "4");
    User TEST_USER_AUTHOR = new User("author", "2", "12345678", "4");
    User TEST_USER_ADMIN = new User("2", "3", "12345678", "5");
    AuthManager TEST_AUTH = new AuthManager();
    Event TEST_EVENT_0 = new Event();
    Event TEST_EVENT = new Event();
    Application TEST_APPLICATION_0 = new Application();
    Application TEST_APPLICATION_1 = new Application();

    public AuthManagerTest() {
        TEST_USER_ADMIN.setIsAdmin(true);
        TEST_EVENT.addOrganiser(TEST_USER_ORG);
        TEST_EVENT.addStaffMember(TEST_USER_STAFF);
        TEST_EVENT.addAttendee(TEST_USER_ATTENDEE);
        TEST_APPLICATION_1.setCompanyTradeName("TEST");
        TEST_APPLICATION_1.setAuthor(TEST_USER_AUTHOR);
        TEST_EVENT.addApplication(TEST_APPLICATION_1);
    }

    @Test
    void testGetAndSetLoggedInUser() {
        User expected = TEST_USER_LOG;
        TEST_AUTH.setLoggedInUser(TEST_USER_LOG);
        User result = TEST_AUTH.getLoggedInUser();
        assertEquals(expected, result);
    }

    @Test
    void testIsValidEventManager() {
        boolean expected = true;
        TEST_AUTH.setLoggedInUser(TEST_USER_ADMIN);
        boolean result = TEST_AUTH.isEventManager();
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidEventManager() {
        boolean expected = true;
        TEST_AUTH.setLoggedInUser(TEST_USER_LOG);
        boolean result = TEST_AUTH.isEventManager();
        assertNotEquals(expected, result);
    }

    @Test
    void testIsValidOrganiserAtEvent() {
        boolean expected = true;
        TEST_AUTH.setLoggedInUser(TEST_USER_ORG);
        boolean result = TEST_AUTH.isOrganiserAtEvent(TEST_EVENT);
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidOrganiserAtEvent() {
        boolean expected = false;
        TEST_AUTH.setLoggedInUser(TEST_USER_STAFF);
        boolean result = TEST_AUTH.isOrganiserAtEvent(TEST_EVENT);
        assertEquals(expected, result);
    }

    @Test
    void testIsValidStaffMemberAtEvent() {
        boolean expected = true;
        TEST_AUTH.setLoggedInUser(TEST_USER_STAFF);
        boolean result = TEST_AUTH.isStaffMemberAtEvent(TEST_EVENT);
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidStaffMemberAtEvent() {
        boolean expected = false;
        TEST_AUTH.setLoggedInUser(TEST_USER_ORG);
        boolean result = TEST_AUTH.isStaffMemberAtEvent(TEST_EVENT);
        assertEquals(expected, result);
    }

    @Test
    void testIsValidAttendeeAtEvent() {
        boolean expected = true;
        TEST_AUTH.setLoggedInUser(TEST_USER_ATTENDEE);
        boolean result = TEST_AUTH.isAttendeeAtEvent(TEST_EVENT);
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidAttendeeAtEvent() {
        boolean expected = false;
        TEST_AUTH.setLoggedInUser(TEST_USER_ORG);
        boolean result = TEST_AUTH.isAttendeeAtEvent(TEST_EVENT);
        assertEquals(expected, result);
    }

    @Test
    void testIsValidUser() {
        boolean expected = true;
        TEST_AUTH.setLoggedInUser(TEST_USER_LOG);
        boolean result = TEST_AUTH.isUser();
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidUser() {
        boolean expected = false;
        TEST_AUTH.setLoggedInUser(null);
        boolean result = TEST_AUTH.isUser();
        assertEquals(expected, result);
    }

    @Test
    void testIsValidAuthorOfEventApplication() {
        boolean expected = true;
        TEST_AUTH.setLoggedInUser(TEST_USER_AUTHOR);
        boolean result = TEST_AUTH.isAuthorOfEventApplication(TEST_EVENT, TEST_APPLICATION_1);
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidAuthorOfEventApplicationWrongUser() {
        boolean expected = false;
        TEST_AUTH.setLoggedInUser(TEST_USER_LOG);
        boolean result = TEST_AUTH.isAuthorOfEventApplication(TEST_EVENT, TEST_APPLICATION_1);
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidAuthorOfEventApplicationWrongApplication() {
        boolean expected = false;
        TEST_AUTH.setLoggedInUser(TEST_USER_AUTHOR);
        boolean result = TEST_AUTH.isAuthorOfEventApplication(TEST_EVENT, TEST_APPLICATION_0);
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidAuthorOfEventApplicationWrongEvent() {
        boolean expected = false;
        TEST_AUTH.setLoggedInUser(TEST_USER_AUTHOR);
        boolean result = TEST_AUTH.isAuthorOfEventApplication(TEST_EVENT_0, TEST_APPLICATION_1);
        assertEquals(expected, result);
    }
}