
package ggauth.shiro.user.common;

import java.io.Serializable;
import java.util.UUID;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import framework.store.log.GGLogger;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月28日 下午4:59:11
 */
public class UuidSessionIdGenerator implements SessionIdGenerator{

	@Override
	public Serializable generateId(Session session) {
		Serializable uuid = new JavaUuidSessionIdGenerator().generateId(session);
		GGLogger.info("生成的sessionid是："+uuid);
		return uuid;
	}

}
