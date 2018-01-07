package dataAccessLayer;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Shadowphoenix on 14-11-2017
 */
public enum PersistenceManager {
    INSTANCE;

    private EntityManagerFactory emFactory;

    private PersistenceManager() {
        emFactory = Persistence.createEntityManagerFactory("feedMe");
    }

    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public void create(EntityManager entityManager, Object obj) throws Exception {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }

    public void create(EntityManager entityManager, ArrayList<Object> objects) {
        entityManager.getTransaction().begin();
        for (Object object : objects) {
            entityManager.persist(object);
        }
        entityManager.getTransaction().commit();
    }

    public void delete(EntityManager entityManager, Object obj) {
        entityManager.getTransaction().begin();
        entityManager.remove(obj);
        entityManager.getTransaction().commit();
    }

    public void delete(EntityManager entityManager, ArrayList<Object> objects) {
        entityManager.getTransaction().begin();
        for (Object object : objects) {
            entityManager.remove(object);
        }
        entityManager.getTransaction().commit();
    }

    public void close() {
        emFactory.close();
    }
}
