package dataAccessLayer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shadowphoenix on 18/11/2017
 */

@Entity
@Table (name = "`Team`")
@NamedQueries({
        @NamedQuery(name = "Team.getAllMembers",
                query = "SELECT t FROM Team t")
})
public class Team {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "`teamLead_id`")
    private TeamLead teamLead;

    @ManyToMany (mappedBy = "teams")
    private Set<User> teamMembers = new HashSet<>();

    public Team() {}

    public Team(TeamLead teamLead) {
        this.teamLead = teamLead;
    }

    public Team(TeamLead teamLead, Set<User> teamMembers) {
        this.teamLead = teamLead;
        this.teamMembers = teamMembers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TeamLead getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(TeamLead teamLead) {
        this.teamLead = teamLead;
        teamLead.addTeamLeading(this);
    }

    public Set<User> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<User> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public void addTeamMember(User teamMember) {
        teamMembers.add(teamMember);
    }
}
