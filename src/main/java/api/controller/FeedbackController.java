package api.controller;

import api.model.Feedback;

public class FeedbackController {
    private static Feedback feedback;
    public static Object createFeedback(int sender_id, int receiver_id, int question_id, boolean anonymous, boolean top, boolean tip, String comment) {
        feedback = Feedback.create(sender_id, receiver_id, question_id, anonymous, top, tip, comment);
        if (feedback != null) {
            return feedback;
        }
        else {
            return "Creating feedback failed.";
        }
    }
}
