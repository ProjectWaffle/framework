package framework.core.domain.auditlog;

import java.util.List;

import javax.inject.Named;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import framework.core.domain.DaoImpl;

@Named
class AuditlogDaoImpl extends DaoImpl<Auditlog> implements AuditlogDao {

    private static final long serialVersionUID = -7807736241131100379L;

    @Override
    public List<Auditlog> findLastAuditlogByDetail(String detail) {
        Root<Auditlog> fromAuditlog = getRoot();
        Predicate condition = getCriteriaBuilder().equal(fromAuditlog.get("detail"), detail);
        return this.getResultList(condition);
    }
}
