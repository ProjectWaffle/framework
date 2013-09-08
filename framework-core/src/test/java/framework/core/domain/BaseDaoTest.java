package framework.core.domain;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class BaseDaoTest {

    protected EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("testDB").createEntityManager();
    }
    
}
