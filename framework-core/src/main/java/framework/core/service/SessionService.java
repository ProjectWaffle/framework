package framework.core.service;

import framework.core.entity.Session;

public interface SessionService extends Service<Session> {

    Session extendSession(Long userid, String sessionid);

    Session findSession(Long userid, String sessionid);

}
