package api.model;

import dataAccessLayer.QuestionType;

/**
 * Created by Shadowphoenix on 19/12/2017
 */
public class Question {
    private Integer id;
    private Goal goal;
    private String question;
    private QuestionType questionType;

    public Question(Integer id, Goal goal, String question, QuestionType questionType) {
        this.id = id;
        this.goal = goal;
        this.question = question;
        this.questionType = questionType;
    }
}
