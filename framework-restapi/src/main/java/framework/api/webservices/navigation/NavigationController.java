package framework.api.webservices.navigation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import framework.api.webservices.BaseController;
import framework.core.domain.navigation.Navigation;
import framework.core.domain.navigation.NavigationService;

@Named
@Path("/navigation")
public class NavigationController extends BaseController {

    private static final long serialVersionUID = -5698557027479498976L;

    private NavigationService navigationService;
    
    @Inject
    protected NavigationController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NavigationResponse> loadMenu() {
        List<NavigationResponse> navigationResponses = new ArrayList<NavigationResponse>();
        /*List<Navigation> navigations = this.navigationService.findNavigationByUsergroup(getAuthenticatedUser());
        for (Navigation navigation : navigations) {
            navigationResponses.add(new NavigationResponse(navigation));
        }*/
        return navigationResponses;
    }
}
