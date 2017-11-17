package dataAccessLayer;

import javax.persistence.*;

/**
 * Created by Shadowphoenix on 14-11-2017
 */
public enum PersistenceManager {
    INSTANCE;

    private EntityManagerFactory emFactory;

    private PersistenceManager() {
        emFactory = Persistence.createEntityManagerFactory("d3srtpmknpf27d?sslmode=require");
    }

    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public void close() {
        emFactory.close();
    }
}
