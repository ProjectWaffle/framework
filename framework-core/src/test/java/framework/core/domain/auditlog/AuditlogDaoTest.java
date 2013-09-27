package framework.core.domain.auditlog;

import org.junit.Test;

import framework.core.domain.BaseDaoTest;

public class AuditlogDaoTest extends BaseDaoTest {

    @Test
    public void test() {
        this.entityManager();
        AuditlogDaoImpl auditlogDao = new AuditlogDaoImpl();
        auditlogDao.findById("sa");
    }
}
