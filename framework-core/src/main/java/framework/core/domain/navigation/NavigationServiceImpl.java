package framework.core.domain.navigation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.ServiceImpl;
import framework.core.domain.session.SessionService;
import framework.core.domain.user.User;

@Named
public class NavigationServiceImpl extends ServiceImpl<Navigation> implements NavigationService {

    private static final long serialVersionUID = -6029148647815989471L;

    private SessionService sessionService;
    @Inject
    protected NavigationServiceImpl(NavigationDao navigationDao, SessionService sessionService) {
        super(navigationDao);
        this.sessionService = sessionService;
    }

    @Override
    public List<Navigation> findNavigationByUsergroup(User user) {
        List<Navigation> navigations = new ArrayList<Navigation>();
        if (sessionService.findActiveSessionByUser(user).size() > 0) {
            
        }
        return navigations;
    }

}
