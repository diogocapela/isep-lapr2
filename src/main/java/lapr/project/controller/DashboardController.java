package lapr.project.controller;

import lapr.project.model.User;

public class DashboardController extends SuperController {


    /**
     * Test if the password is valid for the user
     * @param password : Password String
     * @return True if it's valid or False if it isn't.
     */
    public boolean isPasswordValid(String password) {
        return User.isPasswordValid(password);
    }

    /**
     * Encode password given a String
     * @param password : Cleartext password as String
     * @return Encoded password as String
     * @throws IllegalArgumentException in case a invalid cleartext password is given
     */
    public String encodePassword(String password){
        return User.encodePassword(password);
    }

}
