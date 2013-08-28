package framework.core.domain.client;

import framework.core.domain.Service;

public interface ClientService extends Service<Client> {

    Client findClientByName(String name);

}
