package framework.core.service;

import framework.core.entity.Client;

public interface ClientService extends Service<Client> {

    Client findClientByName(String name);

}
