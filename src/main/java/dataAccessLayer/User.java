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
        @NamedQuery(name = "User.findUserOnName",
                query = "SELECT u FROM User u WHERE u.firstName LIKE :name or u.lastName LIKE :name")
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
