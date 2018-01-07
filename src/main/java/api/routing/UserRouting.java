package api.routing;

import api.controller.UserController;
import spark.*;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by Shadowphoenix on 07/01/2018
 */
public class UserRouting {
    public UserRouting() {
        post("/create", (req, res) -> UserController.createUser(
                req.queryParams("firstName"),
                req.queryParams("lastName"),
                req.queryParams("password"),
                req.queryParams("email")
        ));
    }
}
