
package ggauth.shiro.user.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import framework.store.log.GGLogger;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月25日 下午5:46:45
 */
public class UserSessionListener implements SessionListener{

	@Override
	public void onStart(Session session) {
		GGLogger.info("----session会话创建--"+session.getId());
		
	}

	@Override
	public void onStop(Session session) {
		System.out.println("----session会话停止：" + session.getId());  
		
	}

	@Override
	public void onExpiration(Session session) {
		System.out.println("----session会话过期：" + session.getId());  
		
	}

}
