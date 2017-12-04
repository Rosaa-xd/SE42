import dataAccessLayer.*;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shadowphoenix on 14-11-2017.
 */
public class Main {
    public static void main(String[] args) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

        //testGetTopScore(em);
        //testInsert(em);
        //testQuerySearchUser(em);
        //testQueryTeam(em);
        //testCurrentQuery(em);
        //testInsertFeedback(em);
        testHRMandTeamLead(em, true);
        testHRMandTeamLead(em, false);
    }

    private static void testCurrentQuery(EntityManager em) {
        int id = 5;
        Query q = em.createNamedQuery("User.getQuestions", Question.class);
        List<Question> result = q.getResultList();
        for(Question g : result){
            System.out.println(g.getId() + ' ' + g.getQuestion());
        }

        em.close();
    }

    private static void testInsert(EntityManager em) {
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
        //team.setTeamLead(teamLead);
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

        PersistenceManager.INSTANCE.create(em, objects);
        em.close();
        PersistenceManager.INSTANCE.close();
    }

    private static void testUpdate(EntityManager em) {
        User user = em.find(User.class, 30);
        user.setEmail(em, "UpdateAwesomeMethod@shadowphoenix.nl");
        em.close();
    }

    //get top 5 users with highest score
    private static void testGetTopScore(EntityManager em){
        Query q = em.createNamedQuery("User.getTopScore", User.class);
        List<User> result = q.getResultList();
        for(User u : result){
            System.out.println(u.getId() + u.getScore());
        }
        em.close();
    }

    //get all the team the user is a member from
    private static void testQueryTeam(EntityManager em){
        int id = 30;
        Query q = em.createNamedQuery("Team.getAllMembers", Team.class);
        List<Team> result = q.getResultList();
        for(Team o : result){
            System.out.println(o.getId() + " " + o.getTeamLead().getId() + " " + o.getTeamMembers().size());
        }
        em.close();
    }


    //get the user based on a string that fits partly with their first- or lastname
    private static void testQuerySearchUser(EntityManager em){
        String name = "%o%";
        Query q = em.createNamedQuery("User.findUserOnName", User.class).setParameter("name", name);
        List<User> result = q.getResultList();
        System.out.println("amount of people that have names with " + name +": " + result.size());
        for(User u : result ){
            System.out.println("person: " + u.getFirstName() + " " + u.getLastName());
        }
        em.close();
    }

    private static void testRemove(EntityManager em) {
        User user = em.find(User.class, 4);
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    private static void testLocalDateTime(EntityManager em) {
        Feedback feedback = em.find(Feedback.class, 14);
        System.out.println(feedback.getCreatedAt());
        em.close();
    }

    private static void testInsertFeedback(EntityManager em) {
        Feedback feedback = new Feedback();
        feedback.setSender(em.find(User.class, 45));
        feedback.setReceiver(em.find(User.class, 46));
        feedback.setQuestion(em.find(Question.class, 1));
        feedback.setTop(true);
        feedback.setTip(false);
        feedback.setCreatedAt(LocalDateTime.now());

        PersistenceManager.INSTANCE.create(em, feedback);
        testLocalDateTime(em);
    }

    private static void testHRMandTeamLead(EntityManager em, boolean firstPart) {
        HRM hrm;
        TeamLead teamLead;
        if (firstPart) {
            teamLead = em.find(TeamLead.class, 101);
            Team team = new Team();
            team.setTeamLead(teamLead);
            team.addTeamMember(em.find(User.class, 97));
            team.addTeamMember(em.find(User.class, 98));
            team.addTeamMember(em.find(User.class, 99));
            PersistenceManager.INSTANCE.create(em, team);
        }
        else {
            hrm = em.find(HRM.class, 100);
            teamLead = em.find(TeamLead.class, 101);
            hrm.getAllUsers(em);
            System.out.println(hrm.users.size());
            for (Team team : teamLead.teamsLeading) {
                System.out.println(team.getTeamMembers().size());
            }
        }
    }
}
