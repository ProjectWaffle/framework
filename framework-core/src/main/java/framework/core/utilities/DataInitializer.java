package framework.core.utilities;

public interface DataInitializer {

    /**
     * Calls the saveOrUpdate method of all {@link DataGenerator} instances.
     */
    void update();

}