package framework.core.domain.reference;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.ServiceImpl;

@Named
class ReferenceServiceImpl extends ServiceImpl<Reference> implements ReferenceService {

    /**
     * 
     */
    private static final long serialVersionUID = -204123854740400485L;

    @Inject
    protected ReferenceServiceImpl(ReferenceDao referenceDao) {
        super(referenceDao);
    }

}
