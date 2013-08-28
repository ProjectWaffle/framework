package framework.core.domain.client;

import java.util.List;

import framework.core.domain.Dao;

public interface ClientDao extends Dao<Client> {

    List<Client> findClientByName(String name);

}
