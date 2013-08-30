package framework.core.domain.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.domain.DaoImpl;

@Named
class ClientDaoImpl extends DaoImpl<Client> implements ClientDao {

    private static final long serialVersionUID = 3925222390268583407L;

    @Override
    public List<Client> findClientByName(String name) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        return this.find("findClientByName", parameters);
    }
}
