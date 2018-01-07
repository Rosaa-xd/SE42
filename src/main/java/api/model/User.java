package api.model;

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

    public User(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.goldCard = false;
        this.score = 0;
    }

    public User(Integer id, String firstName, String lastName, String password, String email, Boolean goldCard, Integer score, Set<Goal> goals, Set<Team> teams, Set<Team> teamsLeading, Set<Feedback> receivedFeedback, Set<Feedback> sentFeedback) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.goldCard = goldCard;
        this.score = score;
        this.goals = goals;
        this.teams = teams;
        this.teamsLeading = teamsLeading;
        this.receivedFeedback = receivedFeedback;
        this.sentFeedback = sentFeedback;
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

    public void setEmail(String email) {
        this.email = email;
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
}
