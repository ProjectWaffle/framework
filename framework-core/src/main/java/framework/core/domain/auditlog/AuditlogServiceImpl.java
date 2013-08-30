package framework.core.domain.auditlog;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.ServiceImpl;

@Named
class AuditlogServiceImpl extends ServiceImpl<Auditlog> implements AuditlogService {

    private static final long serialVersionUID = 3214032897547552133L;

    private final AuditlogDao auditlogDao;

    @Inject
    protected AuditlogServiceImpl(AuditlogDao auditlogDao) {
        super(auditlogDao);
        this.auditlogDao = auditlogDao;
    }

    @Override
    public Auditlog findLastAuditlogByCurrentDetail(String detail) {
        final Pattern pattern = Pattern
                .compile("[a-zA-Z]*\\[id=\"[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}\"");
        final Matcher matcher = pattern.matcher(detail);
        if (matcher.find()) {
            final List<Auditlog> auditlogs = this.auditlogDao.findLastAuditlogByDetail(matcher.group(0));
            if (auditlogs.size() > 0) {
                return auditlogs.get(0);
            }
        }
        return null;
    }

}
