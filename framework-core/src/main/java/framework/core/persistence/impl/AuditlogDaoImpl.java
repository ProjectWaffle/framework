package framework.core.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.entity.Auditlog;
import framework.core.persistence.AuditlogDao;

@Named
public class AuditlogDaoImpl extends AbstractDao<Auditlog> implements AuditlogDao {

    private static final long serialVersionUID = -7807736241131100379L;

    @Override
    public List<Auditlog> findLastAuditlogByDetail(String detail) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("detail", detail + "%");
        return this.find("findLastAuditlogByDetail", parameters, 0, 1);
    }
}
