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

    public Question() {}

    public Question(Goal goal, String question, QuestionType questionType) {
        this.goal = goal;
        this.question = question;
        this.questionType = questionType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
}

