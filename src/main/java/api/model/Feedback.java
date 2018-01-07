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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public Boolean getTip() {
        return tip;
    }

    public void setTip(Boolean tip) {
        this.tip = tip;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
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

    public static Feedback create (User sender, User receiver, Question question, Boolean anonymous, Boolean top, Boolean tip, String comment) {

    }
}
