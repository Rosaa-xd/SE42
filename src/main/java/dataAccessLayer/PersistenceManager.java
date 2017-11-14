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

    public void close() {
        emFactory.close();
    }
}
