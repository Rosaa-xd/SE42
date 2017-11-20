package dataAccessLayer;

import javax.persistence.*;

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

    public void close() {
        emFactory.close();
    }
}
