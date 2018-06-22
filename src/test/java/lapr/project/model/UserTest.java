package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the User class.
 * <p>
 * Created by Diogo Capela [1171316@isep.ipp.pt] on 01/06/2018.
 */
public class UserTest {

    private User TEST_USER = new User("diogocapela", "diogocapela@gmail.com", "password123", "Diogo Capela");

    @Test
    public void testGetUsername() {
        String expected = "diogocapela";
        String result = TEST_USER.getUsername();
        assertEquals(expected, result);
    }

    @Test
    public void testGetEmail() {
        String expected = "diogocapela@gmail.com";
        String result = TEST_USER.getEmail();
        assertEquals(expected, result);
    }

    @Test
    public void testGetPassword() {
        String expected = "password123";
        String result = TEST_USER.getPassword();
        assertEquals(expected, result);
    }

    @Test
    public void testGetName() {
        String expected = "Diogo Capela";
        String result = TEST_USER.getName();
        assertEquals(expected, result);
    }

    @Test
    public void testSetUsername() {
        String expected = "capela";
        User user = new User();
        user.setUsername("capela");
        String result = user.getUsername();
        assertEquals(expected, result);
    }

    @Test
    public void testSetEmail() {
        String expected = "diogocapela2@gmail.com";
        User user = new User();
        user.setEmail("diogocapela2@gmail.com");
        String result = user.getEmail();
        assertEquals(expected, result);
    }

    @Test
    public void testSetPassword() {
        String expected = "password12345";
        User user = new User();
        user.setPassword("password12345");
        String result = user.getPassword();
        assertEquals(expected, result);
    }

    @Test
    public void testSetName() {
        String expected = "Diogo Capelini";
        User user = new User();
        user.setName("Diogo Capelini");
        String result = user.getName();
        assertEquals(expected, result);
    }

    @Test
    public void testSetIsAdmin() {
        boolean expected = true;
        User user = new User();
        user.setIsAdmin(true);
        boolean result = user.getIsAdmin();
        assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        String expected = "Username: diogocapela | Email: diogocapela@gmail.com | Name: Diogo Capela";
        String result = TEST_USER.toString();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameContentObjectsAreEqual() {
        User expected = TEST_USER;
        User result = new User("diogocapela", "diogocapela@gmail.com", "password123", "Diogo Capela");
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        User expected = TEST_USER;
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        User expected = TEST_USER;
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        int expected = -1641967376;
        int result = TEST_USER.hashCode();
        assertEquals(expected, result);
    }

    @Test
    public void testisPasswordValid() {
        assertTrue(User.isPasswordValid("12345678"));
    }

    @Test
    public void testisPasswordNotValid() {
        assertTrue(!User.isPasswordValid("1234568"));
    }

    @Test
    public void testEncodePassword() {
        String str = User.encodePassword("12345679");
        assertEquals("0.07370213865000001", str);
    }

    @Test
    public void testEncodePasswordInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            User.encodePassword("1234567");
        });

    }

}
