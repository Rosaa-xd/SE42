import api.model.User;
import api.routing.FeedbackRouting;
import api.routing.UserRouting;
import dataAccessLayer.*;
import org.jasypt.util.text.BasicTextEncryptor;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

/**
 * Created by Shadowphoenix on 14-11-2017.
 */
public class Main {
    public static EntityManager em;
    public static BasicTextEncryptor encryptor;
    public static void main(String[] args) {
        enableCORS("", "", "");
        encryptor = new BasicTextEncryptor();
        encryptor.setPassword("Dit is een sterk wachtwoord voor het SE42 project.");
        em = PersistenceManager.INSTANCE.getEntityManager();
        dataAccessLayer.User.em = em;
        api.model.Feedback.encryptor = encryptor;
        dataAccessLayer.Feedback.em = em;
        dataAccessLayer.Question.em = em;
        get("/", (req, res) -> "Hello there, the API is running!");
        path("/", () -> {
            path("user", () -> new UserRouting());
            path("feedback", () -> new FeedbackRouting());
        });
    }

    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }


//
//    private static void testCurrentQuery(EntityManager em) {
//        int id = 5;
//        Query q = em.createNamedQuery("User.getQuestions", Question.class);
//        List<Question> result = q.getResultList();
//        for(Question g : result){
//            System.out.println(g.getId() + ' ' + g.getQuestion());
//        }
//
//        em.close();
//    }
//
//    private static void testInsert(EntityManager em) {
//        User teamLead = new User();
//        teamLead.setFirstName("TeamLead")
//                .setLastName("Test")
//                .setPassword("password")
//                .setEmail("email20");
//        User teamMember = new User();
//        teamMember.setFirstName("TeamMember")
//                .setLastName("Test")
//                .setPassword("password")
//                .setEmail("email21");
//        Goal goal = new Goal();
//        goal.setGoalName("Programmeren");
//        teamLead.addGoal(goal);
//        teamMember.addGoal(goal);
//        Team team = new Team();
//        team.setTeamLead(teamLead);
//        teamMember.addTeam(team);
//        Question question = new Question();
//        question.setGoal(goal);
//        question.setQuestion("Hoe vind je mijn programmeerwerk?");
//        question.setQuestionType(QuestionType.OPEN);
//        Feedback feedbackTeamLead = new Feedback();
//        feedbackTeamLead.setSender(teamLead);
//        feedbackTeamLead.setReceiver(teamMember);
//        feedbackTeamLead.setQuestion(question);
//        feedbackTeamLead.setTop(true);
//
//        ArrayList<Object> objects = new ArrayList<>();
//        objects.add(teamLead);
//        objects.add(teamMember);
//        objects.add(goal);
//        objects.add(team);
//        objects.add(question);
//        objects.add(feedbackTeamLead);
//
//        PersistenceManager.INSTANCE.create(em, objects);
//        em.close();
//        PersistenceManager.INSTANCE.close();
//    }
//
//    //get top 5 users with highest score
//    private static void testGetTopScore(EntityManager em){
//        Query q = em.createNamedQuery("User.getTopScore", User.class);
//        List<User> result = q.getResultList();
//        for(User u : result){
//            System.out.println(u.getId() + u.getScore());
//        }
//        em.close();
//    }
//
//    //get all the team the user is a member from
//    private static void testQueryTeam(EntityManager em){
//        int id = 30;
//        Query q = em.createNamedQuery("Team.getAllMembers", Team.class);
//        List<Team> result = q.getResultList();
//        for(Team o : result){
//            System.out.println(o.getId() + " " + o.getTeamLead().getId() + " " + o.getTeamMembers().size());
//        }
//        em.close();
//    }
//
//
//    //get the user based on a string that fits partly with their first- or lastname
//    private static void testQuerySearchUser(EntityManager em){
//        String name = "%o%";
//        Query q = em.createNamedQuery("User.findUserOnName", User.class).setParameter("name", name);
//        List<User> result = q.getResultList();
//        System.out.println("amount of people that have names with " + name +": " + result.size());
//        for(User u : result ){
//            System.out.println("person: " + u.getFirstName() + " " + u.getLastName());
//        }
//        em.close();
//    }
//
//    private static void testRemove(EntityManager em) {
//        User user = em.find(User.class, 4);
//        em.getTransaction().begin();
//        em.remove(user);
//        em.getTransaction().commit();
//        em.close();
//    }
}
