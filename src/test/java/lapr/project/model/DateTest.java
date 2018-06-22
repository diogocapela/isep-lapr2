package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    Date TEST_DATE_0 = new Date();
    Date TEST_DATE_100 = new Date(100, 4, 4);
    Date TEST_DATE_400 = new Date(400, 4, 4);
    Date TEST_DATE_2000 = new Date(2000, 4, 4);
    Date TEST_DATE_1 = new Date(2016, 1, 1);
    Date TEST_DATE_2 = new Date(2017, 2, 2);
    Date TEST_DATE_3 = new Date(2018, 5, 5);
    Date TEST_DATE_4 = new Date(2019, 7, 7);
    Date TEST_DATE_5 = new Date(2020, 8, 8);

    public DateTest() {
        TEST_DATE_0.setDate(4, 1, 1);
    }

    @Test
    void testIsValidStringDate() {
        String expected = "2018/05/09";
        Date test = new Date(expected);
        String result = test.toString();
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidDayMaxValueStringDate() {
        String expected = "2018/05/50";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsNotValidDayMinValueStringDate() {
        String expected = "2018/05/-5";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsNotValidDayZeroMinValueStringDate() {
        String expected = "2018/05/0";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsNotValidDayFormatStringDate() {
        String expected = "2018/05/ab";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsNotValidMonthMaxValueStringDate() {
        String expected = "2018/50/3";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsNotValidMonthMinValueStringDate() {
        String expected = "2018/-8/3";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsNotValidMonthZeroMinValueStringDate() {
        String expected = "2018/0/3";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsNotValidMonthFormatStringDate() {
        String expected = "2018/ab/5";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsNotValidYearMinValueStringDate() {
        String expected = "-5/2/3";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsNotValidYearMinValueSetYear() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_DATE_0.setYear(-5);
        });
    }

    @Test
    void testIsNotValidYearFormatStringDate() {
        String expected = "ab/2/1";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsNotValidDateFormatNotNumberStringDate() {
        String expected = "ab/ab/ab/ab";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsNotValidDateFormatNumberStringDate() {
        String expected = "1/1/1/1";
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(expected);
        });
    }

    @Test
    void testIsValidLeepYear4() {
        boolean expected = true;
        boolean result = Date.isLeepYear(TEST_DATE_0.getYear());
        assertEquals(expected, result);
    }

    @Test
    void testIsValidLeepYear100() {
        boolean expected = false;
        boolean result = Date.isLeepYear(TEST_DATE_100.getYear());
        assertEquals(expected, result);
    }

    @Test
    void testIsValidLeepYear400() {
        boolean expected = true;
        boolean result = Date.isLeepYear(TEST_DATE_400.getYear());
        assertEquals(expected, result);
    }

    @Test
    void testIsValidLeepYear2000() {
        boolean expected = true;
        boolean result = Date.isLeepYear(TEST_DATE_2000.getYear());
        assertEquals(expected, result);
    }

    @Test
    void testIsValidLeepYear2016() {
        boolean expected = true;
        boolean result = Date.isLeepYear(TEST_DATE_1.getYear());
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidLeepYear() {
        boolean expected = false;
        boolean result = Date.isLeepYear(TEST_DATE_2.getYear());
        assertEquals(expected, result);
    }

    @Test
    void testIsValidCurrentDate() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1; // janeiro é representado por 0.
        int day = today.get(Calendar.DAY_OF_MONTH);
        String expected = (new Date(year, month, day)).toString();
        String result = Date.currentDate().toString();
        assertEquals(expected, result);
    }

    @Test
    void testIsNotValidCurrentDate() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1; // janeiro é representado por 0.
        int day = today.get(Calendar.DAY_OF_MONTH) + 1;
        String expected = (new Date(year, month, day)).toString();
        String result = Date.currentDate().toString();
        assertNotEquals(expected, result);
    }

    @Test
    void testGetYear() {
        int expected = 2016;
        int result = TEST_DATE_1.getYear();
        assertEquals(expected, result);
    }

    @Test
    void testGetMonth() {
        int expected = 2;
        int result = TEST_DATE_2.getMonth();
        assertEquals(expected, result);
    }

    @Test
    void testGetDay() {
        int expected = 5;
        int result = TEST_DATE_3.getDay();
        assertEquals(expected, result);
    }

    @Test
    void testNotValidMonthSetDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_DATE_5.setDate(TEST_DATE_4.getYear(), TEST_DATE_4.getMonth() + 12, TEST_DATE_4.getDay());
        });
    }

    @Test
    void testNotValidDaySetDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_DATE_5.setDate(TEST_DATE_4.getYear(), TEST_DATE_4.getMonth(), TEST_DATE_4.getDay() + 35);
        });
    }

    @Test
    void testValidSetDateOtherDate() {
        Date expected = TEST_DATE_4;
        TEST_DATE_5.setDate(TEST_DATE_4.getYear(), TEST_DATE_4.getMonth(), TEST_DATE_4.getDay());
        Date result = TEST_DATE_5;
        assertEquals(expected, result);
    }

    @Test
    void testValidOtherDateConstructor() {
        Date expected = TEST_DATE_5;
        Date result = new Date(expected);
        assertEquals(expected, result);
    }

    @Test
    void testHashCode() {
        int expected = -523884381;
        int result = TEST_DATE_1.hashCode();
        assertEquals(expected, result);
    }

    @Test
    void testToString() {
        String expected = "1990/01/01";
        String result = new Date(1990, 1, 1).toString();
        assertEquals(expected, result);
    }

    @Test
    void testEquals() {
        Date expected = TEST_DATE_1;
        Date result = TEST_DATE_1;
        assertEquals(expected, result);
    }

    @Test
    void testNotSameDateNotEquals() {
        Date expected = TEST_DATE_1;
        Date result = new Date(TEST_DATE_2);
        assertNotEquals(expected, result);
    }

    @Test
    void testNotSameObjectNotEquals() {
        Date expected = TEST_DATE_1;
        Stand result = new Stand();
        assertNotEquals(expected, result);
    }

    @Test
    void testCompareToEqual() {
        int expected = 0;
        int result = TEST_DATE_1.compareTo(TEST_DATE_1);
        assertEquals(expected, result);
    }

    @Test
    void testCompareToNotEqual() {
        int result = TEST_DATE_1.compareTo(TEST_DATE_2);
        assertTrue(result != 0);
    }

    @Test
    void testIsGreater() {
        boolean expected = true;
        boolean result = TEST_DATE_2.isGreater(TEST_DATE_1);
        assertEquals(expected, result);
    }

    @Test
    void testIsNotGreater() {
        boolean expected = false;
        boolean result = TEST_DATE_2.isGreater(TEST_DATE_4);
        assertEquals(expected, result);
    }

    @Test
    void testDifferenceParamsGreater() {
        int expected = 398;
        int result = TEST_DATE_1.difference(TEST_DATE_2.getYear(), TEST_DATE_2.getMonth(), TEST_DATE_2.getDay());
        assertEquals(expected, result);
    }

    @Test
    void testDifferenceParamsNotGreater() {
        int expected = 457;
        int result = TEST_DATE_3.difference(TEST_DATE_2.getYear(), TEST_DATE_2.getMonth(), TEST_DATE_2.getDay());
        assertEquals(expected, result);
    }

    @Test
    void testDifferenceDateGreater() {
        int expected = 428;
        int result = TEST_DATE_3.difference(new Date(TEST_DATE_4.getYear(), TEST_DATE_4.getMonth(), TEST_DATE_4.getDay()));
        assertEquals(expected, result);
    }

    @Test
    void testDifferenceDateNotGreater() {
        int expected = 398;
        int result = TEST_DATE_4.difference(new Date(TEST_DATE_5.getYear(), TEST_DATE_5.getMonth(), TEST_DATE_5.getDay()));
        assertEquals(expected, result);
    }

}