package framework.core.domain.repository;

import java.util.List;

import framework.core.domain.model.Client;

public interface ClientRepository extends Repository<Client> {

    List<Client> findClientByName(String name);

}
