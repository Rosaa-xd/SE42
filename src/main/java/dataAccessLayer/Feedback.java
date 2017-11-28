package dataAccessLayer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Shadowphoenix on 18/11/2017
 */

@Entity
@Table (name = "`Feedback`")
public class Feedback {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "`sender_id`")
    private User sender;

    @ManyToOne
    @JoinColumn (name = "`receiver_id`")
    private User receiver;

    @ManyToOne
    @JoinColumn (name = "`question_id`")
    private Question question;

    @Column (name = "anonymous")
    private Boolean anonymous = false;

    @Column (name = "top",
        nullable = false)
    private Boolean top = false;

    @Column (name = "tip",
        nullable = false)
    private Boolean tip = false;

    @Column (name = "comment")
    private String comment;

    @Column (name = "created_at")
    private java.sql.Timestamp createdAt;

    public Feedback() {}

    public Feedback(User sender, User receiver, Question question, Boolean anonymous, Boolean top, Boolean tip, String comment) {
        this.sender = sender;
        this.receiver = receiver;
        this.question = question;
        this.anonymous = anonymous;
        this.top = top;
        this.tip = tip;
        this.comment = comment;
        this.createdAt = new java.sql.Timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
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
        sender.addSendFeedback(this);
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
        receiver.addReceivedFeedback(this);
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime ldt) {
        this.createdAt = new java.sql.Timestamp(ldt.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
    }
}
