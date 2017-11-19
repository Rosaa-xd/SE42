package dataAccessLayer;

import javax.persistence.*;

/**
 * Created by Shadowphoenix on 18/11/2017
 */

@Entity
@Table (name = "`Question`")
public class Question {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "`goal_id`")
    private Goal goal;

    @Column (name = "question",
        nullable = false)
    private String question;

    @Column (name = "`question_type`",
        nullable = false)
    private QuestionType questionType;
}

enum QuestionType {
    OPEN, CLOSED
}
