
package ggauth.shiro.user.permission;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.Permission;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月21日 上午10:57:09
 */
public class UserPermission implements Permission, Serializable{

	private String roleName;
	
	public UserPermission(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public boolean implies(Permission p) {
		System.out.println("方法implies被调用...");
		if(!(p instanceof UserPermission)){
			return false;
		}
		UserPermission permission = (UserPermission)p;
		if(StringUtils.equalsIgnoreCase(this.roleName, permission.roleName)) {
			return true;
		}
		return false;
	}
}
