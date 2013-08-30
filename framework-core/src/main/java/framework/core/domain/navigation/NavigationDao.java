package framework.core.domain.navigation;

import java.util.List;

import framework.core.domain.Dao;

interface NavigationDao extends Dao<Navigation> {

    List<Navigation> findNavigationByRoles(List<String> roles);

}
