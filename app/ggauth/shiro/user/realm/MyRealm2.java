
package ggauth.shiro.user.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

import framework.yaomy.log.GGLogger;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月19日 下午1:29:37
 */
public class MyRealm2 implements Realm{

	@Override
	public String getName() {
		return "b";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String)token.getPrincipal();//用户名（认证）
		String password = new String((char[])token.getCredentials());//用户密码（凭证）
		GGLogger.info("-----------------"+username + "--------------" + password+"------------------");
		if(!"wang".equals(username)) {  
            throw new UnknownAccountException("用户名不正确"); //如果用户名错误  
        }  
        if(!"123".equals(password)) {  
            throw new IncorrectCredentialsException("密码不正确"); //如果密码错误  
        }  
		return new SimpleAuthenticationInfo(
				"wang", //身份
				"123", //凭据
				this.getName());//Realm Name
	}

}
