package lapr.project.controller;

import lapr.project.model.User;
import lapr.project.utils.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A user get registered within the Expo system. The user is prompted with a
 * message to input his name, email address, username and password. The username
 * and the email address must be unique within the system and the password must
 * be at least 8 characters long, include a digit, include an upper and a
 * lower-case character and include a punctuation mark (",", ".", ";", ":" or
 * "-"). This is a creating of a regular user. It's permissions vary depending
 * on the events (eg. he can be event manager on one event and a regular
 * participant on another event).
 *
 * @author Diogo Capela
 */
public class UC04UserRegistrationController extends SuperController {

    /**
     * Add the user to User Registration
     *
     * @param user : User to be added
     */
    public void addUser(User user) {
        if (!isEmailValid(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email!");
        } else if (isEmailDuplicated(user.getEmail())) {
            throw new IllegalArgumentException("Duplicated email!");
        } else if (!isUsernameValid(user.getUsername())) {
            throw new IllegalArgumentException("Invalid username!");
        } else if (isUsernameDuplicated(user.getUsername())) {
            throw new IllegalArgumentException("Duplicated username!");
        } else if (user.getUsername().isEmpty() && user.getEmail().isEmpty() && user.getName().isEmpty()) {
            throw new IllegalArgumentException("You need to input all fields!");
        } else if (isPasswordValid(user.getPassword()) && !user.getUsername().isEmpty() && !user.getEmail().isEmpty() && !user.getName().isEmpty()) {
            String encodedPassword = User.encodePassword(user.getPassword());
            user.setPassword(encodedPassword);
            expo.getUserRegistry().addUser(user);
            Logger.log(getLoggedInUser() != null ? USERSTR + getLoggedInUser().getUsername() + " registed an user:" + user : user + " was registered!");

        } else {
            throw new IllegalArgumentException("Passwords must have exactly 8 numerical digits!");
        }
    }

    /**
     * Delete a selected user
     *
     * @param user : User to be deleted
     */
    public void deleteUser(User user) {
        expo.getUserRegistry().deleteUser(user);
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " deleted an user:" + user);

    }

    /**
     * Test if a password is valid for a selected User
     *
     * @param password : Password in cleartext to be tested
     * @return : True if it's valid (login) or False wrong password
     */
    public boolean isPasswordValid(String password) {
        return User.isPasswordValid(password);
    }

    /**
     * Test if a email is valid for a selected User The following regex is
     * applied: ^[A-Za-z0-9+_.-]+@(.+)$
     *
     * @param email : Email to be tested
     * @return : True if it's valid or False wrong e-mail
     */
    public boolean isEmailValid(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Test if a user name is valid given certain rules. The following regex is
     * applied: ^[A-Za-z0-9]+
     *
     * @param username : User name to be tested
     * @return : True if it's valid or False if invalid
     */
    public boolean isUsernameValid(String username) {
        String regex = "^[A-Za-z0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    /**
     * Test if e-mail already present on the User Registry
     *
     * @param email : E-mail to check
     * @return : True if already present or False if not
     */
    public boolean isEmailDuplicated(String email) {
        boolean duplicated = false;
        for (User user : expo.getUserRegistry().getUsers()) {
            if (email.equals(user.getEmail())) {
                duplicated = true;
            }
        }
        return duplicated;
    }

    /**
     * Test if user name already present on the User Registry
     *
     * @param username : User to check
     * @return : True if already present or False if not
     */
    public boolean isUsernameDuplicated(String username) {
        boolean duplicated = false;
        for (User user : expo.getUserRegistry().getUsers()) {
            if (username.equals(user.getUsername())) {
                duplicated = true;
            }
        }
        return duplicated;
    }

}
