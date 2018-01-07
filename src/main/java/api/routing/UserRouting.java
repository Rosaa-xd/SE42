package api.routing;

import api.controller.UserController;
import spark.*;

import static api.JsonUtil.json;
import static spark.Spark.*;

/**
 * Created by Shadowphoenix on 07/01/2018
 */
public class UserRouting {
    public UserRouting() {
        post("/login", (req, res) -> UserController.logIn(
                req.queryParams("email"),
                req.queryParams("password")
        ), json());

        post("/create", (req, res) -> UserController.createUser(
                req.queryParams("firstName"),
                req.queryParams("lastName"),
                req.queryParams("password"),
                req.queryParams("email")
        ), json());

        put("/changeEmail", (req, res) -> UserController.changeEmail(req.queryParams("email")), json());
    }
}
