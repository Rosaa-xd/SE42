package api.model;

import org.jasypt.util.text.BasicTextEncryptor;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shadowphoenix on 19/12/2017
 */
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Boolean goldCard;
    private Integer score;
    private Set<Goal> goals;
    private Set<Team> teams;
    private Set<Team> teamsLeading;
    private Set<Feedback> receivedFeedback;
    private Set<Feedback> sentFeedback;
    private static dataAccessLayer.User dalUser;

    public User(Integer id) {
        this.id = id;
    }

    public User(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.goldCard = false;
        this.score = 0;
        this.goals = new HashSet<>();
        this.teams = new HashSet<>();
        this.teamsLeading = new HashSet<>();
        this.receivedFeedback = new HashSet<>();
        this.sentFeedback = new HashSet<>();
    }

    public User(Integer id, String firstName, String lastName, String password, String email, Boolean goldCard, Integer score) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.goldCard = goldCard;
        this.score = score;
        this.goals = new HashSet<>();
        this.teams = new HashSet<>();
        this.teamsLeading = new HashSet<>();
        this.receivedFeedback = new HashSet<>();
        this.sentFeedback = new HashSet<>();
    }

    public User(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Object setEmail(String email) {
        try {
            dalUser.setEmail(email);
            this.email = email;
            return this;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "Failed updating email to " + email;
        }
    }

    public Boolean getGoldCard() {
        return goldCard;
    }

    public void setGoldCard(Boolean goldCard) {
        this.goldCard = goldCard;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Set<Goal> getGoals() {
        return goals;
    }

    public void setGoals(Set<Goal> goals) {
        this.goals = goals;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Team> getTeamsLeading() {
        return teamsLeading;
    }

    public void setTeamsLeading(Set<Team> teamsLeading) {
        this.teamsLeading = teamsLeading;
    }

    public Set<Feedback> getReceivedFeedback() {
        return receivedFeedback;
    }

    public void setReceivedFeedback(Set<Feedback> receivedFeedback) {
        this.receivedFeedback = receivedFeedback;
    }

    public Set<Feedback> getSentFeedback() {
        return sentFeedback;
    }

    public void setSentFeedback(Set<Feedback> sentFeedback) {
        this.sentFeedback = sentFeedback;
    }

    public static User create(String firstName, String lastName, String password, String email) {
        try {
            dalUser = new dataAccessLayer.User();
            dalUser.setFirstName(firstName)
                    .setLastName(lastName)
                    .setPassword(password)
                    .setEmail(email)
                    .setGoldCard(false)
                    .setScore(0);
            dalUser.create();
            return new User(firstName, lastName, password, email);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static User login(String email, String password) {
        try {
            dalUser = new dataAccessLayer.User();
            dalUser = dalUser.login(email, password);
            User user = new User(
                    dalUser.getId(),
                    dalUser.getFirstName(),
                    dalUser.getLastName(),
                    dalUser.getPassword(),
                    dalUser.getEmail(),
                    dalUser.getGoldCard(),
                    dalUser.getScore()
            );
            for (dataAccessLayer.Goal dalGoal : dalUser.getGoals()) {
                user.goals.add(Goal.convert(dalGoal));
            }
            for (dataAccessLayer.Team dalTeam : dalUser.getTeams()) {
                user.teams.add(Team.convert(dalTeam));
            }
            for (dataAccessLayer.Team dalTeam : dalUser.getTeamsLeading()) {
                user.teamsLeading.add(Team.convert(dalTeam, user));
            }
            for (dataAccessLayer.Feedback dalFeedback : dalUser.getReceivedFeedback()) {
                user.receivedFeedback.add(Feedback.convert(dalFeedback));
            }
            for (dataAccessLayer.Feedback dalFeedback : dalUser.getSendFeedback()) {
                user.sentFeedback.add(Feedback.convert(dalFeedback));
            }
            return user;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static User byEmail(String email) {
        try {
            dalUser = new dataAccessLayer.User();
            dalUser = dalUser.getByEmail(email);
            User user = new User(
                    dalUser.getId()
            );
            return user;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static User byId(int id) {
        try {
            dalUser = new dataAccessLayer.User();
            dalUser = dalUser.getById(id);
            User user = new User(
                    dalUser.getId(),
                    dalUser.getFirstName(),
                    dalUser.getLastName(),
                    dalUser.getPassword(),
                    dalUser.getEmail(),
                    dalUser.getGoldCard(),
                    dalUser.getScore()
            );
            for (dataAccessLayer.Goal dalGoal : dalUser.getGoals()) {
                user.goals.add(Goal.convert(dalGoal));
            }
            for (dataAccessLayer.Team dalTeam : dalUser.getTeams()) {
                user.teams.add(Team.convert(dalTeam));
            }
            for (dataAccessLayer.Team dalTeam : dalUser.getTeamsLeading()) {
                user.teamsLeading.add(Team.convert(dalTeam, user));
            }
            for (dataAccessLayer.Feedback dalFeedback : dalUser.getReceivedFeedback()) {
                user.receivedFeedback.add(Feedback.convert(dalFeedback));
            }
            for (dataAccessLayer.Feedback dalFeedback : dalUser.getSendFeedback()) {
                user.sentFeedback.add(Feedback.convert(dalFeedback));
            }
            return user;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
