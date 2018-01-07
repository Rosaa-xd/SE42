package api.controller;

import api.model.User;

/**
 * Created by Shadowphoenix on 07/01/2018
 */
public final class UserController {
    private static User user;
    public static Object createUser(String firstName, String lastName, String password, String email) {
        user = User.create(firstName, lastName, password, email);
        if (user != null) {
            return user;
        }
        else {
            return "Creating user failed.";
        }
    }

    public static Object changeEmail(String email) {
        return user.setEmail(email);
    }

    public static Object logIn(String email, String password) {
        user = User.login(email, password);
        if (user != null) {
            return user;
        }
        else {
            return "Log in failed. Wrong credentials.";
        }
    }
}
