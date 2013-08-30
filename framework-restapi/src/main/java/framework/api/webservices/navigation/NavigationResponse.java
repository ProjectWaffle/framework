package framework.api.webservices.navigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import framework.core.domain.navigation.Navigation;

public class NavigationResponse implements Serializable {

    private static final long serialVersionUID = -242992238896597898L;

    private final String display;

    private final List<NavigationResponse> subItems;

    private final String url;

    protected NavigationResponse(Navigation navigation) {
        this.display = navigation.getDisplay();
        this.url = navigation.getUrl();
        this.subItems = new ArrayList<NavigationResponse>();
        for (final Navigation child : navigation.getChilds()) {
            this.subItems.add(new NavigationResponse(child));
        }
    }

    public String getDisplay() {
        return this.display;
    }

    public List<NavigationResponse> getSubItems() {
        return this.subItems;
    }

    public String getUrl() {
        return this.url;
    }
}
