package dataAccessLayer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shadowphoenix on 18/11/2017
 */

@Entity
@Table (name = "Team")
public class Team {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "`teamLead_id`")
    private User teamLead;

    @ManyToMany (mappedBy = "teams")
    private Set<User> teamMembers = new HashSet<>();

    public Team() {}

    public Team(User teamLead) {
        this.teamLead = teamLead;
    }

    public Team(User teamLead, Set<User> teamMembers) {
        this.teamLead = teamLead;
        this.teamMembers = teamMembers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(User teamLead) {
        this.teamLead = teamLead;
    }

    public Set<User> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<User> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
