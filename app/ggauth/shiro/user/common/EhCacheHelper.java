
package ggauth.shiro.user.common;

import framework.store.log.GGLogger;
import ggauth.shiro.user.realm.UserRealm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月30日 上午9:27:52
 */
public class EhCacheHelper {

	public static void getCache(){
		//获取主题对象
//		Subject subject = SecurityUtils.getSubject();
//		
//		Session session = subject.getSession();
//		GGLogger.info("value:"+session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY));//记录的是用户认证
//		GGLogger.info("value:"+session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));//这个属性记录的是用户名
//		GGLogger.info("value:"+session.getAttribute(DefaultSubjectContext.SESSION_CREATION_ENABLED));
		
		RealmSecurityManager securityManager =  (RealmSecurityManager) SecurityUtils.getSecurityManager();  
		UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();  
		
		Cache<Object, AuthenticationInfo> info = userRealm.getAuthenticationCache();
		GGLogger.info(info.keys().iterator().next());
		AuthenticationInfo authenInfo = info.get(info.keys().iterator().next());
		PrincipalCollection principal = authenInfo.getPrincipals();
		String password1 = authenInfo.getCredentials().toString();
		GGLogger.info("用户名 ："+principal+"----------密码："+password1);
	}
}
