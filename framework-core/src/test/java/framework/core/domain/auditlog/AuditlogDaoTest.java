package framework.core.domain.auditlog;

import org.junit.Test;

import framework.core.domain.BaseDaoTest;
import framework.core.domain.auditlog.Auditlog;

public class AuditlogDaoTest extends BaseDaoTest {

    @Test
    public void test() {
        this.entityManager();
        AuditlogDaoImpl auditlogDao = new AuditlogDaoImpl();
        auditlogDao.findById("sa");
    }
}
