import dataAccessLayer.PersistenceManager;
import dataAccessLayer.User;

import javax.persistence.EntityManager;

import static spark.Spark.*;

/**
 * Created by Shadowphoenix on 14-11-2017.
 */
public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setFirstName("Java")
                .setLastName("Test")
                .setPassword("password")
                .setEmail("email3");

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        PersistenceManager.INSTANCE.close();
    }
}
