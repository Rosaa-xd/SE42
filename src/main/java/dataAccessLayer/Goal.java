package dataAccessLayer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shadowphoenix on 18/11/2017
 */

@Entity
@Table (name = "`Goal`")
public class Goal {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "`goalName`",
        unique = true,
        nullable = false)
    private String goalName;

    @ManyToMany (mappedBy = "goals")
    private Set<User> users = new HashSet<>();

    public Goal() {}

    public Goal(String goalName) {
        this.goalName = goalName;
    }

    public Goal(String goalName, Set<User> users) {
        this.goalName = goalName;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
