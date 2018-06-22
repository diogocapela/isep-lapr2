package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegistryTest {
    UserRegistry TEST_USER_REGISTRY_0 = new UserRegistry();
    UserRegistry TEST_USER_REGISTRY_1 = new UserRegistry();
    User TEST_USER_1 = new User("1", "2", "12345678", "4");

    public UserRegistryTest() {
        TEST_USER_REGISTRY_1.addUser(TEST_USER_1);
    }

    @Test
    public void ensureDeleteUserWorks() {
        UserRegistry userRegistry = new UserRegistry();
        User u1 = new User("user1", "user1@gmail.com", "password1", "User One");
        userRegistry.addUser(u1);
        userRegistry.deleteUser(u1);
        assertEquals(0, userRegistry.getUsers().size());
    }

    @Test
    public void ensureAddingDuplicateUsersThrowsException() {
        UserRegistry userRegistry = new UserRegistry();
        User u1 = new User("user1", "user1@gmail.com", "password1", "User One");
        assertThrows(Exception.class, () -> {
            userRegistry.addUser(u1);
            userRegistry.addUser(u1);
        });
    }

    @Test
    void testEqualsSameUserRegistry() {
        UserRegistry expected = TEST_USER_REGISTRY_1;
        UserRegistry result = TEST_USER_REGISTRY_1;

        assertEquals(expected,result);
    }

    @Test
    void testEqualsNotSameUserRegistry() {
        UserRegistry expected = TEST_USER_REGISTRY_1;
        UserRegistry result = TEST_USER_REGISTRY_0;

        assertFalse(expected.equals(result));
    }

    @Test
    void testEqualsNotSameClass() {
        UserRegistry expected = TEST_USER_REGISTRY_1;
        User result = TEST_USER_1;

        assertFalse(expected.equals(result));
    }

    @Test
    void testHashCode() {
        int expected = 1419;
        int result = TEST_USER_REGISTRY_1.hashCode();

        assertEquals(expected,result);
    }
}
