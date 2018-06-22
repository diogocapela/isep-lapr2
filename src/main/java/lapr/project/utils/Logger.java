package lapr.project.utils;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Custom utility logger class that logs messages with a timestamp to
 * the console and also a log file.
 * <p>
 * Created by Diogo Capela (1171316@isep.ipp.pt) on 29/05/2018.
 */
public final class Logger {

    public static final String LOG_FILE_PATH = "./src/main/resources/logger.log";

    /**
     * Private constructor to hide implicit public one.
     * Static utility classes don't need to be instantiated.
     */
    private Logger() {

    }

    /**
     * Logs a message to the console with the current timestamp.
     *
     * @param message Message to be logged.
     */
    public static void log(String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String log = sdf.format(timestamp) + " - " + message;
        System.out.println(log);
        try {
            writeLogToFile(log, LOG_FILE_PATH);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Writes a log message to a file (where most recent messages
     * appear on top of the file).
     *
     * @param log      Message to be logged.
     * @param filePath Path to the log file.
     */
    public static void writeLogToFile(String log, String filePath) throws IOException {
        File file = new File(filePath);
        if (file.createNewFile() || file.exists()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                String result = "";
                String line;
                while ((line = br.readLine()) != null) {
                    result = String.format("%s%n%s", result, line);
                }
                result = log + result;
                if (file.delete()) {
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        fos.write(result.getBytes());
                        fos.flush();
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Reads a log file and returns the logs as an ArrayList of strings.
     *
     * @param filePath Path to the log file.
     * @return logData
     */
    public static List<String> getLogFromFile(String filePath) {
        List<String> logData = new ArrayList<>();
        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                logData.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            Logger.log(e.getMessage());
        }
        return logData;
    }

}