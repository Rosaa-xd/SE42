package api.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by Shadowphoenix on 19/12/2017
 */
public class Feedback {
    private Integer id;
    private User sender;
    private User receiver;
    private Question question;
    private Boolean anonymous;
    private Boolean top;
    private Boolean tip;
    private String comment;

    // Constructor for creating feedback
    public Feedback(User sender, User receiver, Question question, Boolean anonymous, Boolean top, Boolean tip, String comment) {
        this.sender = sender;
        this.receiver = receiver;
        this.question = question;
        this.anonymous = anonymous;
        this.top = top;
        this.tip = tip;
        this.comment = comment;
    }

    // Constructor for retrieving feedback
    public Feedback(Integer id, User sender, User receiver, Question question, Boolean anonymous, Boolean top, Boolean tip, String comment) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.question = question;
        this.anonymous = anonymous;
        this.top = top;
        this.tip = tip;
        this.comment = comment;
    }

    // Method for retrieving feedback from dal
    public static Feedback convert (dataAccessLayer.Feedback dalFeedback) {
        return new Feedback(
                dalFeedback.getId(),
                new User(
                        dalFeedback.getSender().getId(),
                        dalFeedback.getSender().getFirstName(),
                        dalFeedback.getSender().getLastName()
                ),
                new User(
                        dalFeedback.getReceiver().getId(),
                        dalFeedback.getReceiver().getFirstName(),
                        dalFeedback.getReceiver().getLastName()
                ),
                new Question(
                        dalFeedback.getQuestion().getId(),
                        new Goal(
                                dalFeedback.getQuestion().getGoal().getId(),
                                dalFeedback.getQuestion().getGoal().getGoalName()
                        ),
                        dalFeedback.getQuestion().getQuestion(),
                        dalFeedback.getQuestion().getQuestionType()
                ),
                dalFeedback.getAnonymous(),
                dalFeedback.getTop(),
                dalFeedback.getTip(),
                dalFeedback.getComment()
        );
    }
}
