package api.controller;

import api.JsonUtil;
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

    public static Object logIn(String json) {
        JsonUtil.parse(json);
        String email = (String) JsonUtil.jsonObject.get("email");
        String password = (String) JsonUtil.jsonObject.get("password");
        user = User.login(email, password);
        if (user != null) {
            return user;
        }
        else {
            return "Log in failed. Wrong credentials.";
        }
    }

    public static Object byId(String json) {
        int id = Integer.parseInt(json);
        user = User.byId(id);
        if (user != null) {
            return user;
        }
        else {
            return "Oh halp";
        }
    }

    public static Object byEmail(String json) {
        user = User.byEmail(json);
        if (user != null) {
            return user;
        }
        else {
            return "Oh halp";
        }
    }
}
