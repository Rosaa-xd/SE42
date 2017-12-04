package dataAccessLayer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shadowphoenix on 4-12-2017
 */

@Entity
@DiscriminatorValue(value = "TeamLead")
public class TeamLead extends User{
    @OneToMany (mappedBy = "teamLead")
    public Set<Team> teamsLeading = new HashSet<>();

    public TeamLead() {

    }

    public TeamLead(String firstName, String lastName, String password, String email, Boolean goldCard, Integer score) {
        super(firstName, lastName, password, email, goldCard, score);
    }
}
