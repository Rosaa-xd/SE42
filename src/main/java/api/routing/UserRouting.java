package api.routing;

import api.controller.UserController;
import spark.*;

import static api.JsonUtil.json;
import static api.JsonUtil.parse;
import static spark.Spark.*;

/**
 * Created by Shadowphoenix on 07/01/2018
 */
public class UserRouting {
    public UserRouting() {
        post("/login", (req, res) -> UserController.logIn(req.body()), json());

        post("/create", (req, res) -> UserController.createUser(
                req.queryParams("firstName"),
                req.queryParams("lastName"),
                req.queryParams("password"),
                req.queryParams("email")
        ), json());

        get("/byId/:identifier", (req, res) -> UserController.byId(req.params("identifier")), json());

        get("/byEmail/:identifier", (req,res) -> UserController.byEmail(req.params("identifier")), json());

        put("/changeEmail", (req, res) -> UserController.changeEmail(req.queryParams("email")), json());
    }
}
