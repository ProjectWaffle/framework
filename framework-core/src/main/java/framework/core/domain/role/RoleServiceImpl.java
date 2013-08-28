package framework.core.domain.role;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.ServiceImpl;

@Named
public class RoleServiceImpl extends ServiceImpl<Role> implements RoleService {

    private static final long serialVersionUID = -675261136460381643L;

    @Inject
    protected RoleServiceImpl(RoleDao persistence) {
        super(persistence);
    }

}
