package framework.core.domain.navigation;

import java.util.List;

import framework.core.domain.Service;
import framework.core.domain.user.User;

public interface NavigationService extends Service<Navigation> {

    List<Navigation> findNavigationByUsergroup(User user);
    
}
