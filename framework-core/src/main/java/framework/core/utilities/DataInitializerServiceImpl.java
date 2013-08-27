package framework.core.utilities;

import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Localization;
import framework.core.entity.SystemParameter;
import framework.core.service.SystemParameterService;

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
    private XMLEncoder xmlEncoder;

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
                final ClassLoader classLoader = this.getClass().getClassLoader();
                final InputStream resourceAsStream = classLoader.getResourceAsStream("Default.data");
                systemParameter = this.xmlEncoder.convert(resourceAsStream, SystemParameter.class, Localization.class);
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

    @Inject
    protected void setXmlEncoder(XMLEncoder xmlEncoder) {
        this.xmlEncoder = xmlEncoder;
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
