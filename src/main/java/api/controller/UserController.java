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

    public static String changeEmail(String email) {
        return user.setEmail(email);
    }

    public static String logIn(String email, String password) {
        user = User.login(email, password);
        if (user != null) {
            return user.getFirstName() + " " + user.getLastName() + ", you have successfully logged in.";
        }
        else {
            return "I'm sorry, but I think your credentials are wrong!";
        }
    }
}
