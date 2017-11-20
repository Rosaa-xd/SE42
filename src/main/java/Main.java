import dataAccessLayer.*;

import javax.persistence.EntityManager;

/**
 * Created by Shadowphoenix on 14-11-2017.
 */
public class Main {
    public static void main(String[] args) {
        User teamLead = new User();
        teamLead.setFirstName("TeamLead")
                .setLastName("Test")
                .setPassword("password")
                .setEmail("email14");
        User teamMember = new User();
        teamMember.setFirstName("TeamMember")
                  .setLastName("Test")
                  .setPassword("password")
                  .setEmail("email15");
        Goal goal = new Goal();
        goal.setGoalName("Samenwerken");
        teamLead.addGoal(goal);
        teamMember.addGoal(goal);
        Team team = new Team();
        team.setTeamLead(teamLead);
        teamMember.addTeam(team);
        Question question = new Question();
        question.setGoal(goal);
        question.setQuestion("Hoe vind je de samenwerking met mij?");
        question.setQuestionType(QuestionType.OPEN);
        Feedback feedbackTeamLead = new Feedback();
        feedbackTeamLead.setSender(teamLead);
        feedbackTeamLead.setReceiver(teamMember);
        feedbackTeamLead.setQuestion(question);
        feedbackTeamLead.setTop(true);

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        PersistenceManager.INSTANCE.create(em, teamLead);
        PersistenceManager.INSTANCE.create(em, teamMember);
        PersistenceManager.INSTANCE.create(em, team);
        PersistenceManager.INSTANCE.create(em, goal);
        PersistenceManager.INSTANCE.create(em, question);
        PersistenceManager.INSTANCE.create(em, feedbackTeamLead);
        em.close();
        PersistenceManager.INSTANCE.close();
    }
}
