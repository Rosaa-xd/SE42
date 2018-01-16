package api.controller;

import api.JsonUtil;
import api.model.Feedback;
import org.json.simple.JSONObject;

public class FeedbackController {
    private static Feedback feedback;
    public static Object createFeedback(String json) {
        JsonUtil.parse(json);
        Long sender = (Long) JsonUtil.jsonObject.get("sender");
        int sender_id = sender.intValue();
        Long receiver = (Long) JsonUtil.jsonObject.get("receiver");
        int receiver_id = receiver.intValue();
        int question_id = 1;
        boolean anonymous = false;
        boolean top = (boolean) JsonUtil.jsonObject.get("top");
        boolean tip = (boolean) JsonUtil.jsonObject.get("tip");
        String comment = (String) JsonUtil.jsonObject.get("comment");
        feedback = Feedback.create(sender_id, receiver_id, question_id, anonymous, top, tip, comment);
        if (feedback != null) {
            return feedback;
        }
        else {
            return "Creating feedback failed.";
        }
    }
}
