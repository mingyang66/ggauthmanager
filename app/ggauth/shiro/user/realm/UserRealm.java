
package ggauth.shiro.user.realm;

import framework.store.log.GGLogger;
import ggauth.shiro.user.common.PasswordHelper;
import ggauth.shiro.user.model.User;
import ggauth.shiro.user.service.UserService;
import ggauth.shiro.user.serviceImpl.UserServiceImpl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
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
	private PasswordHelper passwordHelper = new PasswordHelper();
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
		authorizationInfo.addRole("admin");
		/**
		 * 只有当SimpleAuthorizationInfo中设置了权限后，自定义的Permission中implies方法才会被调用。
		 */
		authorizationInfo.addStringPermission("system+edit+10");
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
		final String username = (String)token.getPrincipal();//用户名（认证）
		final String password = new String((char[])token.getCredentials());//用户密码（凭证）
		GGLogger.info("-----------------"+username + "--------------" + password+"------------------");
		User user = service.findByUsername(username);
		if(user == null) {
			throw new UnknownAccountException("账号不存在");
		}
		final String salt = user.getSalt();
		final String password_date = user.getPassword();
		if(!password_date.equals(passwordHelper.encryptPassword(password, salt))) {  
			throw new IncorrectCredentialsException("密码不正确"); //如果密码错误  
		}  

		return new SaltedAuthenticationInfo() {
			
			@Override
			public PrincipalCollection getPrincipals() {
				return new SimplePrincipalCollection(username, getName());
			}
			
			@Override
			public Object getCredentials() {
				return password;
			}
			
			@Override
			public ByteSource getCredentialsSalt() {
				return ByteSource.Util.bytes(salt);
			}
		};
	}
}
