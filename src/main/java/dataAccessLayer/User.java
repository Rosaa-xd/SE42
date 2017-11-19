package dataAccessLayer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shadowphoenix on 14-11-2017
 */

@Entity
@Table (name = "`User`")
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
            joinColumns = {@JoinColumn (name = "`team_id`")},
            inverseJoinColumns = {@JoinColumn (name = "`user_id`")}
    )
    Set<Team> teams = new HashSet<>();


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
}
