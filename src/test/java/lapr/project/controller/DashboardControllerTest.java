package lapr.project.controller;

import lapr.project.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DashboardControllerTest {
    DashboardController controller;

    User TEST_USER_0 = new User("Rafael", "rpm@gmail.com", "12345678", "Rafa");
    User TEST_USER_1 = new User("Rafael", "rpm@gmail.com", "1234567a", "Rafa");


    public DashboardControllerTest() {
        controller = new DashboardController();

    }

    @Test
    void testIsValidPassword() {
        boolean expected = true;
        String validPassword = "12345678";
        boolean result = controller.isPasswordValid(validPassword);
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidPasswordFormat() {
        boolean expected = false;
        String validPassword = "1234567";
        boolean result = controller.isPasswordValid(validPassword);
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidPasswordData() {
        boolean expected = false;
        String validPassword = "1234567a";
        boolean result = controller.isPasswordValid(validPassword);
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidPasswordDataAndFormat() {
        boolean expected = false;
        String validPassword = "123457a";
        boolean result = controller.isPasswordValid(validPassword);
        assertEquals(expected, result);
    }

    @Test
    void testValidEncodePassword() {
        BigDecimal expected = BigDecimal.valueOf(Double.parseDouble("0"));
        String validPassword = "00000000";
        BigDecimal result = BigDecimal.valueOf(Double.parseDouble(controller.encodePassword(validPassword)));
        assertEquals(expected, result);
    }

    @Test
    void testNotValidEncodePasswordValue() {
        BigDecimal expected = BigDecimal.valueOf(Double.parseDouble("0"));
        String validPassword = "00000100";
        BigDecimal result = BigDecimal.valueOf(Double.parseDouble(controller.encodePassword(validPassword)));
        assertNotEquals(expected, result);
    }

    @Test
    void testNotValidEncodePasswordFormat() {
        String validPassword = "0000010a";
        assertThrows(IllegalArgumentException.class, () -> {
            controller.encodePassword(validPassword);
        });
    }

    @Test
    void testNotValidEncodePasswordData() {
        String validPassword = "0000010";
        assertThrows(IllegalArgumentException.class, () -> {
            controller.encodePassword(validPassword);
        });
    }
}