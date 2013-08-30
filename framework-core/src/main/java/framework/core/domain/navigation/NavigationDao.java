package framework.core.domain.navigation;

import java.util.List;

import framework.core.domain.Dao;

public interface NavigationDao extends Dao<Navigation> {

    List<Navigation> findNavigationByRoles(List<String> roles);

}
