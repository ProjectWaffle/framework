package framework.core.domain.usergroup;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.ServiceImpl;

@Named
class UsergroupServiceImpl extends ServiceImpl<Usergroup> implements UsergroupService {

    private static final long serialVersionUID = 5404039380441901398L;

    @Inject
    protected UsergroupServiceImpl(UsergroupDao usergroupDao) {
        super(usergroupDao);
    }

}
