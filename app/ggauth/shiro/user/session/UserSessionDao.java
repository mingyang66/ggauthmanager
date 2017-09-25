
package ggauth.shiro.user.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.SessionDAO;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月25日 下午6:19:30
 */
public class UserSessionDao implements SessionDAO{

	@Override
	public Serializable create(Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session readSession(Serializable sessionId)
			throws UnknownSessionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Session session) {
		session.removeAttribute(session.getId());
	}

	@Override
	public Collection<Session> getActiveSessions() {
		// TODO Auto-generated method stub
		return null;
	}

}
