package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a User.
 * <p>
 * Created by Diogo Capela (1171316@isep.ipp.pt) on 31/05/2018.
 */
@XmlRootElement
public class User {

    // Instance variables
    private String username = "";
    private String email = "";
    private String password;
    private String name = "";
    private boolean isAdmin = false;

    /**
     * Default public empty constructor.
     */
    public User() {

    }

    /**
     * Constructor for the User class.
     *
     * @param username Username (only lowercase letters and numbers, must be unique within the system)
     * @param email    Email address (must be unique within the system)
     * @param password Encrypted password
     * @param name     Full name
     */
    public User(String username, String email, String password, String name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    /* Getters & Setters
    ===================================== */

    /**
     * Checks if a password is valid. Valid passwords must have exactly 8 numerical digits.
     *
     * @param password A string representing a password.
     * @return boolean If the password is valid.
     */
    public static boolean isPasswordValid(String password) {
        return password.matches("[0-9]+") && password.length() == 8;
    }

    /**
     * Because passwords take a longer time to fill-in in mobile applications,
     * that company suggested using a pin code with 8 digits, where each digit can be
     * a value between 0 and 9 and any digit can be repeated (e.g. 23837493).
     *
     * @param passwordString A string representing a password.
     * @return String Encoded password.
     */
    public static String encodePassword(String passwordString) {
        if (isPasswordValid(passwordString)) {
            // Set the constants
            int passwordLength = 8;
            int numberOfPossibleChars = 10;
            // Map each of the possible chars to a probability
            Map<Integer, Double> probabilityMap = new HashMap<>();
            probabilityMap.put(0, 0.05);
            probabilityMap.put(1, 0.1);
            probabilityMap.put(2, 0.20);
            probabilityMap.put(3, 0.15);
            probabilityMap.put(4, 0.1);
            probabilityMap.put(5, 0.1);
            probabilityMap.put(6, 0.05);
            probabilityMap.put(7, 0.01);
            probabilityMap.put(8, 0.15);
            probabilityMap.put(9, 0.1);
            // Transform password in an array of Integers
            int[] passwordArray = new int[passwordLength];
            for (int i = 0; i < passwordLength; i++) {
                passwordArray[i] = passwordString.charAt(i) - '0';
            }
            // Calculate the range of probabilities
            Map<Integer, double[]> rangeProbMap = new HashMap<>();
            double accumulator = 0.0;
            for (int i = 0; i < numberOfPossibleChars; i++) {
                double[] array = new double[2];
                array[0] = accumulator;
                array[1] = accumulator + probabilityMap.get(i);
                rangeProbMap.put(i, array);
                accumulator = accumulator + probabilityMap.get(i);
            }
            // Set the variables for the encoding
            double minExtreme = 0.0;
            double maxExtreme = 1.0;
            double minMiddle = 0.0;
            double maxMiddle;
            // Iterate through the password array digits one by one
            for (int i = 0; i < passwordLength; i++) {
                // Set current digit
                int currentDigit = passwordArray[i];
                // Set current digit min and max
                minMiddle = minExtreme + ((maxExtreme - minExtreme) * rangeProbMap.get(currentDigit)[0]);
                maxMiddle = minExtreme + ((maxExtreme - minExtreme) * rangeProbMap.get(currentDigit)[1]);
                // Set next extreme values
                minExtreme = minMiddle;
                maxExtreme = maxMiddle;
            }
            // return minMiddle as a String (encoded password)
            return Double.toString(minMiddle);
        } else {
            throw new IllegalArgumentException("Invalid password.");
        }
    }

    /**
     * Gets the User's username.
     *
     * @return username
     */
    @XmlElement
    public String getUsername() {
        return username;
    }

    /**
     * Sets the User's username.
     *
     * @param username Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the User's email address.
     *
     * @return email
     */
    @XmlElement
    public String getEmail() {
        return email;
    }

    /**
     * Sets the User's email address.
     *
     * @param email E-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the User's password.
     *
     * @return password
     */
    @XmlElement
    public String getPassword() {
        return password;
    }

    /**
     * Sets the User's password.
     *
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the User's full name.
     *
     * @return name
     */
    @XmlElement
    public String getName() {
        return name;
    }

    /**
     * Sets the User's full name.
     *
     * @param name Full name
     */
    public void setName(String name) {
        this.name = name;
    }

    /* toString, equals & hashCode
    ===================================== */

    /**
     * Gets the admin status of the User.
     *
     * @return isAdmin
     */
    @XmlElement
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets the admin status of the User.
     *
     * @param isAdmin if it's event manager
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return String.format("Username: %s | Email: %s | Name: %s", this.username, this.email, this.name);
    }

    /* Custom
    ===================================== */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return getUsername().equals(user.getUsername()) && getEmail().equals(user.getEmail()) && getName().equals(user.getName());
    }

    @Override
    public int hashCode() {
        return (username.hashCode() + email.hashCode() + name.hashCode() + Boolean.hashCode(isAdmin));
    }

}
