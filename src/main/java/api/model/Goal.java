package api.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shadowphoenix on 19/12/2017
 */
public class Goal {
    private Integer id;
    private String goalName;
    private Set<User> users;

    // Constructor for creating new goal
    public Goal(String goalName, User user) {
        this.goalName = goalName;
        users = new HashSet<>();
        users.add(user);
    }

    // Constructor for retrieving goal
    public Goal(Integer id, String goalName) {
        this.id = id;
        this.goalName = goalName;
        this.users = new HashSet<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public static Goal convert (dataAccessLayer.Goal dalGoal) {
        Goal goal = new Goal(
                dalGoal.getId(),
                dalGoal.getGoalName()
        );
        for (dataAccessLayer.User goalUser : dalGoal.getUsers()) {
            goal.addUser(new User(
                    goalUser.getId(),
                    goalUser.getFirstName(),
                    goalUser.getLastName())
            );
        }
        return goal;
    }
}
