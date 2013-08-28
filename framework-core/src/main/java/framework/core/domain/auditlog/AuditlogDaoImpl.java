package framework.core.domain.auditlog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.domain.DaoImpl;

@Named
public class AuditlogDaoImpl extends DaoImpl<Auditlog> implements AuditlogDao {

    private static final long serialVersionUID = -7807736241131100379L;

    @Override
    public List<Auditlog> findLastAuditlogByDetail(String detail) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("detail", detail + "%");
        return this.find("findLastAuditlogByDetail", parameters, 0, 1);
    }
}
