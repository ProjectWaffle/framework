package framework.core.service;

import framework.core.entity.Session;

public interface SessionService extends Service<Session> {

    Session extendSession(String username, String sessionid);

    Session findSession(String username, String sessionid);

}
