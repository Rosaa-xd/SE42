package dataAccessLayer;

import javax.persistence.*;

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
    private Boolean top;

    @Column (name = "tip",
        nullable = false)
    private Boolean tip;

    @Column (name = "comment")
    private String comment;
}
