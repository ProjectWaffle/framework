package framework.core.persistence;

import java.util.List;

import framework.core.entity.Client;

public interface ClientDao extends Dao<Client> {

    List<Client> findClientByName(String name);

}
