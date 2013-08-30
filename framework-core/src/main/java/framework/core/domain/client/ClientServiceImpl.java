package framework.core.domain.client;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.ServiceImpl;

@Named
class ClientServiceImpl extends ServiceImpl<Client> implements ClientService {

    private static final long serialVersionUID = -8247412604665890406L;

    private final ClientDao clientDao;

    @Inject
    protected ClientServiceImpl(ClientDao clientDao) {
        super(clientDao);
        this.clientDao = clientDao;
    }

    @Override
    public Client findClientByName(String name) {
        final List<Client> clients = this.clientDao.findClientByName(name);
        if (clients.size() > 0) {
            return clients.get(0);
        }
        return null;
    }

}
