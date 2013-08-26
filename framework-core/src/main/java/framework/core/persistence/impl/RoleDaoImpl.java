package framework.core.persistence.impl;

import javax.inject.Named;

import framework.core.entity.Role;
import framework.core.persistence.RoleDao;

@Named
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {

    private static final long serialVersionUID = 3411175442925454595L;

}
