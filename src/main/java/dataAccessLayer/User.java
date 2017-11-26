package dataAccessLayer;

import org.jboss.logging.Param;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Shadowphoenix on 14-11-2017
 */

@Entity
@Table (name = "`User`")
@NamedQueries({
        //return users that contain the same characters as the string that is given
        //checked
        @NamedQuery(name = "User.findUserOnName",
                query = "SELECT u FROM User u WHERE u.firstName LIKE :name or u.lastName LIKE :name"),

        //return the teams where the user is part of
        //checked
        @NamedQuery(name = "User.getAllUserTeam",
                query = "SELECT u.teams FROM User u WHERE u.id = :id"),

        //return the top score , return the (5 if limit does work) users with the highest score
        //checked -- LIMIT doesnt work
        @NamedQuery(name = "User.getTopScore",
                query = "SELECT u  FROM User u ORDER BY u.score DESC"),

        //return the latest feedback ordered by date
        //checked -- no data attribute in feedback
        @NamedQuery(name = "User.getLatestFeedback",
                query = "SELECT f FROM User u INNER JOIN Feedback f on u.id = f.receiver.id WHERE u.id = :id"),
                //query = "SELECT f FROM User u INNER JOIN Feedback f on u.id = f.receiver.id ORDER BY f.data DESC"),

        //return get all goals/improvementpoints from the user
        //checked
        @NamedQuery(name = "User.getGoals",
                query = "SELECT u.goals FROM User u WHERE u.id = :id"),

        //return all question from the user
        //checked
        @NamedQuery(name = "User.getQuestions",
                query = "SELECT q FROM Question q INNER JOIN Feedback f ON f.question.id = q.id INNER JOIN User u ON f.receiver.id = u.id"),
                //query = "SELECT q FROM Question q INNER JOIN Feedback f ON f.question.id = q.id INNER JOIN User u ON f.receiver.id = u.id where u.id = :id")

        //return return all the feedback given to the user
        //checked
        @NamedQuery(name = "User.getAllGivenFeedback",
            query = "SELECT f FROM Feedback f INNER JOIN User u ON u.id = f.sender.id  WHERE u.id = :id"),

        //return all the feedback the user has received
        //checked
        @NamedQuery(name = "User.getAllReceivedFeedback",
                query = "SELECT f FROM Feedback f INNER JOIN User u ON u.id = f.receiver.id WHERE u.id = :id"),

        //return the users that have given the most feedback
        //checked
        @NamedQuery(name = "User.getMostGivenFeedback",
                query = "SELECT u FROM User u INNER JOIN Feedback f ON u.id = f.sender.id GROUP BY u.id ORDER BY COUNT(*) DESC"),

        //return the users thar have been given the most feedback
        //checked
        @NamedQuery(name = "User.getMostReceivedFeedback",
                query = "SELECT u FROM User u INNER JOIN Feedback f ON u.id = f.receiver.id GROUP BY u.id ORDER BY COUNT(*) DESC")

})

public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "`firstName`",
        nullable = false)
    private String firstName;

    @Column (name = "`lastName`",
        nullable = false)
    private String lastName;

    @Column (name = "`password`",
        nullable = false)
    private String password;

    @Column (name = "`email`",
        nullable = false,
        unique = true)
    private String email;

    @Column (name = "`goldCard`")
    private Boolean goldCard = false;

    @Column (name = "`score`")
    private Integer score = 0;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (
            name = "`User_Goal`",
            joinColumns = {@JoinColumn (name = "`user_id`")},
            inverseJoinColumns = {@JoinColumn (name = "`goal_id`")}
    )
    Set<Goal> goals = new HashSet<>();

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (
            name = "`UserTeam`",
            joinColumns = {@JoinColumn (name = "`user_id`")},
            inverseJoinColumns = {@JoinColumn (name = "`team_id`")}
    )
    Set<Team> teams = new HashSet<>();

    @OneToMany (mappedBy = "sender")
    Set<Feedback> sendFeedback = new HashSet<>();

    @OneToMany (mappedBy = "receiver")
    Set<Feedback> receivedFeedback = new HashSet<>();

    @OneToMany (mappedBy = "teamLead")
    Set<Team> teamsLeading = new HashSet<>();

    public User() {}

    public User(String firstName, String lastName, String password, String email, Boolean goldCard, Integer score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.goldCard = goldCard;
        this.score = score;
    }

    public User(String firstName, String lastName, String password, String email, Boolean goldCard, Integer score, Set<Goal> goals, Set<Team> teams) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.goldCard = goldCard;
        this.score = score;
        this.goals = goals;
        this.teams = teams;
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Boolean getGoldCard() {
        return goldCard;
    }

    public User setGoldCard(Boolean goldCard) {
        this.goldCard = goldCard;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public User setScore(Integer score) {
        this.score = score;
        return this;
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

    public List<Feedback> getSendFeedback() {
        return sendFeedback;
    }

    public void setSendFeedback(Set<Feedback> sendFeedback) {
        this.sendFeedback = sendFeedback;
    }

    public Set<Feedback> getReceivedFeedback() {
        return receivedFeedback;
    }

    public void setReceivedFeedback(Set<Feedback> receivedFeedback) {
        this.receivedFeedback = receivedFeedback;
    }

    public void addTeam(Team team) {
        teams.add(team);
        team.addTeamMember(this);
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
        goal.addUser(this);
    }

    public void addSendFeedback(Feedback feedback) {
        sendFeedback.add(feedback);
    }

    public void addReceivedFeedback(Feedback feedback) {
        receivedFeedback.add(feedback);
    }

    public void addTeamLeading(Team team) {
        teamsLeading.add(team);
    }

    public void setFirstName(EntityManager em, String firstName) {
        em.getTransaction().begin();
        setFirstName(firstName);
        em.getTransaction().commit();
    }

    public void setEmail(EntityManager em, String email) {
        em.getTransaction().begin();
        setEmail(email);
        em.getTransaction().commit();
    }
}
