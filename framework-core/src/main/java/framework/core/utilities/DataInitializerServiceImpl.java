package framework.core.utilities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.constants.ParameterType;
import framework.core.domain.systemparameter.SystemParameter;
import framework.core.domain.systemparameter.SystemParameterService;

/**
 * Provides Transactional for {@link DataInitializationStartup}.
 * 
 * @author Frederick Yap
 */
@Named
public class DataInitializerServiceImpl implements DataInitializerService {

    private Cryptography cryptography;
    private List<DataGenerator> dataGenerators;
    private SystemParameterService systemParameterService;
    
    protected DataInitializerServiceImpl() {
    }

    /*
     * (non-Javadoc)
     * @see framework.core.utilities.DataInitializerService#update()
     */
    @Override
    public void update() {
        this.sort();
        for (final DataGenerator dataGenerator : this.dataGenerators) {
            SystemParameter systemParameter = this.systemParameterService.findDatabaseVersion();
            if (systemParameter != null) {
                final Integer currentDBVersion = Integer.valueOf(this.cryptography.decrypt(systemParameter.getValue()));
                if (dataGenerator.getDBVersion() > currentDBVersion) {
                    dataGenerator.performDataOperation();
                    systemParameter.setValue(this.cryptography.encrypt(String.valueOf(dataGenerator.getDBVersion())));
                    this.systemParameterService.saveOrUpdate(systemParameter);
                }
            } else {
                systemParameter = new SystemParameter();
                systemParameter.setCode(ParameterCode.DB_VERSION);
                systemParameter.setDescription("label.db_version");
                systemParameter.setReadonly(true);
                systemParameter.setType(ParameterType.NUMERIC);
                systemParameter.setMaximum(Long.valueOf(256));
                systemParameter.setMinimum(Long.valueOf(0));
                systemParameter.setValue(this.cryptography.encrypt(String.valueOf(dataGenerator.getDBVersion())));
                dataGenerator.performDataOperation();
                this.systemParameterService.saveOrUpdate(systemParameter);
            }
        }

    }

    @Inject
    protected void setCryptography(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    @Inject
    protected void setDataGenerators(List<DataGenerator> dataGenerators) {
        this.dataGenerators = dataGenerators;
    }

    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
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
