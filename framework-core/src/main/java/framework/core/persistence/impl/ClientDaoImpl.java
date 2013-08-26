package framework.core.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.entity.Client;
import framework.core.persistence.ClientDao;

@Named
public class ClientDaoImpl extends AbstractDao<Client> implements ClientDao {

    private static final long serialVersionUID = 3925222390268583407L;

    @Override
    public List<Client> findClientByName(String name) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        return this.find("findClientByName", parameters);
    }
}
