package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTest {

    @Test
    public void ensureLineBreakIsCorrect() throws Exception {
        assertEquals(String.format("%n"), new StringUtils().getLineBreak());
    }

}