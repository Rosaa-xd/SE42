package api.routing;

import api.controller.FeedbackController;

import static api.JsonUtil.json;
import static spark.Spark.*;

public class FeedbackRouting {
    public FeedbackRouting() {
        post("/create", (req, res) -> FeedbackController.createFeedback(
                req.body()
        ), json());
    }
}
