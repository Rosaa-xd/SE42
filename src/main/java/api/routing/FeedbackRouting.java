package api.routing;

import api.controller.FeedbackController;

import static api.JsonUtil.json;
import static spark.Spark.*;

public class FeedbackRouting {
    public FeedbackRouting() {
        post("/create", (req, res) -> FeedbackController.createFeedback(
                Integer.parseInt(req.queryParams("sender_id")),
                Integer.parseInt(req.queryParams("receiver_id")),
                Integer.parseInt(req.queryParams("question_id")),
                Boolean.parseBoolean(req.queryParams("anonymous")),
                Boolean.parseBoolean(req.queryParams("top")),
                Boolean.parseBoolean(req.queryParams("tip")),
                req.queryParams("comment")
        ), json());
    }
}
