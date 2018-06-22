package lapr.project.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggerTest {

    /**
     * Utils variable to access utils for Strings.
     */
    private StringUtils stringUtils = new StringUtils();

    /**
     * Get OS independent line break.
     *
     * @return OS independent line break "%n".
     */
    private String getLineBreak() {
        if (stringUtils == null) {
            stringUtils = new StringUtils();
        }
        return stringUtils.getLineBreak();
    }

    @Test
    public void ensureLoggerIsWorking() {
        ByteArrayOutputStream printedContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printedContent));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Logger.log("Testing message");
        String expected = sdf.format(timestamp) + " - Testing message" + getLineBreak(); // Notice the \n for new line.
        assertEquals(expected, printedContent.toString());
    }
    
    @Test
    public void testgetLogFromFile(){
        Assertions.assertNotNull(Logger.getLogFromFile(Logger.LOG_FILE_PATH));
    }
}
