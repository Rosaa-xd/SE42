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
}
