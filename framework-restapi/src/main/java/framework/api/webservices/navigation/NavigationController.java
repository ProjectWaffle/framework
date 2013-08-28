package framework.api.webservices.navigation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import framework.api.webservices.Controller;

@Named
@Path("/menu")
public class NavigationController extends Controller {

    private static final long serialVersionUID = -5698557027479498976L;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> loadMenu() {
        return new ArrayList<>();
    }
}
