
package ggauth.shiro.user.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import framework.store.log.GGLogger;
import ggauth.shiro.user.common.PasswordHelper;
import ggauth.shiro.user.model.User;
import ggauth.shiro.user.service.UserService;
import ggauth.shiro.user.serviceImpl.UserServiceImpl;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月19日 下午1:26:46
 */
public class MyRealm1 implements Realm{
	
	private UserService service = new UserServiceImpl();
	private PasswordHelper passwordHelper = new PasswordHelper();
	
	@Override
	public String getName() {
		return "a";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException, UnknownAccountException {
		final String username = (String)token.getPrincipal();//用户名（认证）
		String password = new String((char[])token.getCredentials());//用户密码（凭证）
		GGLogger.info("-----------------"+username + "--------------" + password+"------------------");
		User user = service.findByUsername(username);
		final String salt = user.getSalt();
		final String password_date = user.getPassword();
		
		if(!user.getUsername().equals(username)) {  
            throw new UnknownAccountException("用户名不正确"); //如果用户名错误  
        }  
        if(!user.getPassword().equals(passwordHelper.encryptPassword(password, salt))) {  
            throw new IncorrectCredentialsException("密码不正确"); //如果密码错误  
        }  
        
		return new SaltedAuthenticationInfo() {
			
			@Override
			public PrincipalCollection getPrincipals() {
				return new SimplePrincipalCollection(username, getName());
			}
			
			@Override
			public Object getCredentials() {
				return password_date;
			}
			
			@Override
			public ByteSource getCredentialsSalt() {
				return ByteSource.Util.bytes(salt);
			}
		};
	}

}
