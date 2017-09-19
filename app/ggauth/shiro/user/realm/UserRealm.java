
package ggauth.shiro.user.realm;

import java.util.HashSet;
import java.util.Set;

import framework.yaomy.log.GGLogger;
import ggauth.shiro.user.model.User;
import ggauth.shiro.user.service.UserService;
import ggauth.shiro.user.serviceImpl.UserServiceImpl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @Description:获取subject相关信息
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月19日 上午11:13:43
 */
public class UserRealm extends AuthorizingRealm{
	private UserService service = new UserServiceImpl();  
	/**
	 * 
	 * @Description:获取授权信息
	 * @author yaomy
	 * @date 2017年9月19日 上午11:20:43
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();
		
		GGLogger.info("---------------username-----------------");
		GGLogger.info(username);
		GGLogger.info("---------------username-----------------");
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<String>();
		roles.add("role");
		Set<String> permission = new HashSet<String>();
		permission.add("update");
		authorizationInfo.setRoles(roles);
		authorizationInfo.setStringPermissions(permission);
		return authorizationInfo;
	}

	/**
	 * 
	 * @Description:获取身份验证信息
	 * AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）
	 * @author yaomy
	 * @date 2017年9月19日 上午11:21:06
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		
		User user = service.findByUsername(username);
		if(user == null){
			throw new UnknownAccountException();//没找到账号
		}
		if(Boolean.TRUE.equals(user.getLocked())){
			throw new LockedAccountException();//账号锁定
		}
		/**
		 * 生成AuthenticationInfo信息，交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配，
		 * 如果不匹配将抛出密码错误异常IncorrectCredentialsException；
		 * 另外如果密码重试此处太多将抛出超出重试次数异常ExcessiveAttemptsException；
		 */
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
												user.getUsername(), //身份信息（用户名）
												user.getPassword(), //凭据（密文密码）
												ByteSource.Util.bytes(user.getCredentialsSalt()), //盐（username+salt）
												this.getName()
												);
		return authenticationInfo;
	}

}
