package framework.core.domain.navigation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.domain.DaoImpl;

@Named
class NavigationDaoImpl extends DaoImpl<Navigation> implements NavigationDao {

    private static final long serialVersionUID = -6770381343562399686L;

    @Override
    public List<Navigation> findNavigationByRoles(List<String> roles) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("roles", roles);
        return this.find("findNavigationByRoles", parameters);
    }

}
