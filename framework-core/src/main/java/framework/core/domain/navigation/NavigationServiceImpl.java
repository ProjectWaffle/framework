package framework.core.domain.navigation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.ServiceImpl;
import framework.core.domain.role.Role;
import framework.core.domain.user.Credential;

@Named
class NavigationServiceImpl extends ServiceImpl<Navigation> implements NavigationService {

    private static final long serialVersionUID = -6029148647815989471L;

    private NavigationDao navigationDao;

    @Inject
    protected NavigationServiceImpl(NavigationDao navigationDao) {
        super(navigationDao);
        this.navigationDao = navigationDao;
    }

    @Override
    public List<Navigation> findNavigationByUsergroup(Credential user) {
        List<Navigation> navigations = new ArrayList<Navigation>();
        final List<String> roles = new ArrayList<String>();
        if (user != null) {
            for (final Role role : user.getUsergroup().getRoles()) {
                roles.add(role.getName());
            }
            navigations = this.navigationDao.findNavigationByRoles(roles);
        }
        return navigations;
    }

}
