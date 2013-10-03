package framework.core.utilities;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Populate new data or update existing data during the start up of the application.
 * 
 * @author Frederick Yap
 */
@Named
class DataInitializationStartup {

    private DataInitializer dataInitializerService;

    protected DataInitializationStartup() {
        super();
    }
    
    /**
     * Default constructor.
     * 
     * @param dataInitializerService
     *            instance of {@link DataInitializerImpl}.
     */
    @Inject
    protected DataInitializationStartup(DataInitializer dataInitializerService) {
        this();
        this.dataInitializerService = dataInitializerService;
    }

    /**
     * Initializes this class.
     */
    @PostConstruct
    public void initialize() {
        this.dataInitializerService.update();
    }

}
