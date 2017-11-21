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

    public boolean create(EntityManager entityManager, Object obj) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean create(EntityManager entityManager, ArrayList<Object> objects) {
        try {
            entityManager.getTransaction().begin();
            for (Object object : objects) {
                entityManager.persist(object);
            }
            entityManager.getTransaction().commit();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(EntityManager entityManager, Object obj) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(obj);
            entityManager.getTransaction().commit();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(EntityManager entityManager, ArrayList<Object> objects) {
        try {
            entityManager.getTransaction().begin();
            for (Object object : objects) {
                entityManager.remove(object);
            }
            entityManager.getTransaction().commit();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void close() {
        emFactory.close();
    }
}
