package framework.core.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Usergroup;
import framework.core.persistence.UsergroupDao;
import framework.core.service.UsergroupService;

@Named
public class UsergroupServiceImpl extends AbstractService<Usergroup> implements UsergroupService {

    private static final long serialVersionUID = 5404039380441901398L;

    @Inject
    protected UsergroupServiceImpl(UsergroupDao usergroupDao) {
        super(usergroupDao);
    }

}
