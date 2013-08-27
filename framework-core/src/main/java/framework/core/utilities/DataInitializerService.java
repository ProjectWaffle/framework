package framework.core.utilities;

public interface DataInitializerService {

    /**
     * Calls the saveOrUpdate method of all {@link DataGenerator} instances.
     */
    void update();

}