package framework.core.domain.client;

import java.util.List;

import javax.inject.Named;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import framework.core.domain.DaoImpl;

@Named
class ClientDaoImpl extends DaoImpl<Client> implements ClientDao {

    private static final long serialVersionUID = 3925222390268583407L;

    @Override
    public List<Client> findClientByName(String name) {
        Root<Client> fromClient = getRoot();
        Predicate condition = getCriteriaBuilder().equal(fromClient.get("name"), "name");
        return this.getResultList(condition);
    }
}
