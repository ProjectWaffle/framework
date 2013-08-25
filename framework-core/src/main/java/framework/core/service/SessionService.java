package framework.core.service;

import framework.core.entity.Session;
import framework.core.entity.User;

public interface SessionService extends Service<Session> {

    Session findSessionById(String username, String id);

    String saveOrUpdate(User user);
}
