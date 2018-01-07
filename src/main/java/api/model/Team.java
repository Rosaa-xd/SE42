package api.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shadowphoenix on 07/01/2018
 */
public class Team {
    private Integer id;
    private User teamLead;
    private Set<User> teamMembers;

    // Construstor for creating a team
    public Team (User teamLead) {
        this.teamLead = teamLead;
        this.teamMembers = new HashSet<>();
    }

    // Constructor for retrieving a team
    public Team (Integer id, User teamLead) {
        this.id = id;
        this.teamLead = teamLead;
        this.teamMembers = new HashSet<>();
    }

    public void addMember(User teamMember) {
        teamMembers.add(teamMember);
    }

    public static Team convert(dataAccessLayer.Team dalTeam) {
        Team team = new Team(
                dalTeam.getId(),
                new User(
                        dalTeam.getTeamLead().getId(),
                        dalTeam.getTeamLead().getFirstName(),
                        dalTeam.getTeamLead().getLastName()
                )
        );
        for (dataAccessLayer.User teamMember : dalTeam.getTeamMembers()) {
            team.addMember(new User(
                    teamMember.getId(),
                    teamMember.getFirstName(),
                    teamMember.getLastName()
            ));
        }
        return team;
    }

    public static Team convert(dataAccessLayer.Team dalTeam, User user) {
        Team team = new Team(
                dalTeam.getId(),
                user
        );
        for (dataAccessLayer.User teamMember : dalTeam.getTeamMembers()) {
            team.addMember(new User(
                    teamMember.getId(),
                    teamMember.getFirstName(),
                    teamMember.getLastName()
            ));
        }
        return team;
    }
}
