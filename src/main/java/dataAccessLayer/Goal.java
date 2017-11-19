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
}
