import dataAccessLayer.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;

/**
 * Created by Shadowphoenix on 14-11-2017.
 */
public class Main {
    public static void main(String[] args) {

    }

    private static void testInsert() {
        User teamLead = new User();
        teamLead.setFirstName("TeamLead")
                .setLastName("Test")
                .setPassword("password")
                .setEmail("email20");
        User teamMember = new User();
        teamMember.setFirstName("TeamMember")
                .setLastName("Test")
                .setPassword("password")
                .setEmail("email21");
        Goal goal = new Goal();
        goal.setGoalName("Programmeren");
        teamLead.addGoal(goal);
        teamMember.addGoal(goal);
        Team team = new Team();
        team.setTeamLead(teamLead);
        teamMember.addTeam(team);
        Question question = new Question();
        question.setGoal(goal);
        question.setQuestion("Hoe vind je mijn programmeerwerk?");
        question.setQuestionType(QuestionType.OPEN);
        Feedback feedbackTeamLead = new Feedback();
        feedbackTeamLead.setSender(teamLead);
        feedbackTeamLead.setReceiver(teamMember);
        feedbackTeamLead.setQuestion(question);
        feedbackTeamLead.setTop(true);

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(teamLead);
        objects.add(teamMember);
        objects.add(goal);
        objects.add(team);
        objects.add(question);
        objects.add(feedbackTeamLead);

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        PersistenceManager.INSTANCE.create(em, objects);
        em.close();
        PersistenceManager.INSTANCE.close();
    }
}
