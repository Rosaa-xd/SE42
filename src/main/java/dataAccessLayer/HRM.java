package dataAccessLayer;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Shadowphoenix on 4-12-2017
 */

@Entity
@DiscriminatorValue(value = "HRM")
@NamedQueries({
        @NamedQuery(name = "HRM.getAllUsers",
            query = "SELECT u FROM User u")
})
public class HRM extends User {
    public transient ArrayList<User> users;

    public HRM() {

    }

    public HRM(String firstName, String lastName, String password, String email, Boolean goldCard, Integer score) {
        super(firstName, lastName, password, email, goldCard, score);
        users = new ArrayList<>();
    }

    public void getAllUsers (EntityManager em) {
        Query q = em.createNamedQuery("HRM.getAllUsers", User.class);
        users = (ArrayList<User>)q.getResultList();
    }
}
