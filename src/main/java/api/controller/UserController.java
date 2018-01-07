package api.controller;

import api.model.User;

/**
 * Created by Shadowphoenix on 07/01/2018
 */
public final class UserController {
    private static User user;
    public static String createUser(String firstName, String lastName, String password, String email) {
        user = User.create(firstName, lastName, password, email);
        if (user != null) {
            return "New user " + firstName + " " + lastName + " has been made.";
        }
        else {
            return "I'm sorry, but something went wrong.";
        }
    }
}
