package framework.core.utilities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ReferenceCode;
import framework.core.constants.ReferenceType;
import framework.core.domain.configuration.Configuration;
import framework.core.domain.configuration.ConfigurationService;
import framework.core.domain.reference.Reference;
import framework.core.domain.reference.ReferenceService;

/**
 * Provides Transactional for {@link DataInitializationStartup}.
 * 
 * @author Frederick Yap
 */
@Named
public class DataInitializerServiceImpl implements DataInitializerService {

    private final ConfigurationService configurationService;
    private final List<DataGenerator> dataGenerators;
    private final ReferenceService referenceService;

    @Inject
    protected DataInitializerServiceImpl(List<DataGenerator> dataGenerators, ReferenceService referenceService,
            ConfigurationService configurationService) {
        super();
        this.dataGenerators = dataGenerators;
        this.configurationService = configurationService;
        this.referenceService = referenceService;
    }

    @Override
    public void update() {
        this.sort();
        for (final DataGenerator dataGenerator : this.dataGenerators) {
            Configuration configuration = this.configurationService.findDatabaseVersion();
            if (configuration != null) {
                final Integer currentDBVersion = Integer.valueOf(configuration.getValue());
                if (dataGenerator.getDBVersion() > currentDBVersion) {
                    dataGenerator.performDataOperation();
                    configuration.setValue(dataGenerator.getDBVersion());
                    this.configurationService.saveOrUpdate(configuration);
                }
            } else {
                final Reference reference = new Reference();
                reference.setCode(ReferenceCode.CONFIGURATION_DB_VERSION);
                reference.setLabel("label.db_version");
                reference.setType(ReferenceType.SYSTEM_PARAMETERS);
                configuration = new Configuration();
                configuration.setReference(this.referenceService.saveOrUpdate(reference));
                configuration.setValue(dataGenerator.getDBVersion());
                dataGenerator.performDataOperation();
                this.configurationService.saveOrUpdate(configuration);
            }
        }

    }

    /**
     * Sorts all {@link DataGenerator} based on version.
     */
    protected void sort() {
        Collections.sort(this.dataGenerators, new Comparator<DataGenerator>() {
            @Override
            public int compare(DataGenerator o1, DataGenerator o2) {
                return o1.getDBVersion() - o2.getDBVersion();
            }
        });
    }

}
