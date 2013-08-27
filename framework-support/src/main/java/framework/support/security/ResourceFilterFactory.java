package framework.support.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory;
import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;

@Named
public class ResourceFilterFactory extends RolesAllowedResourceFilterFactory {

    private final SecurityContextFilter securityContextFilter;

    @Inject
    protected ResourceFilterFactory(SecurityContextFilter securityContextFilter) {
        this.securityContextFilter = securityContextFilter;
    }

    @Override
    public List<ResourceFilter> create(AbstractMethod am) {
        List<ResourceFilter> rolesFilters = super.create(am);
        if (null == rolesFilters) {
            rolesFilters = new ArrayList<ResourceFilter>();
        }
        final List<ResourceFilter> filters = new ArrayList<ResourceFilter>(rolesFilters);
        filters.add(0, this.securityContextFilter);
        return filters;
    }

}
