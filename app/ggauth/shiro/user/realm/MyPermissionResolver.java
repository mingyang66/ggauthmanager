
package ggauth.shiro.user.realm;

import ggauth.shiro.user.permission.UserPermission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月21日 下午1:17:04
 */
public class MyPermissionResolver implements RolePermissionResolver{

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		// TODO Auto-generated method stub
		System.out.println("==========MyPermissionResolver=====================角色是："+roleString);
		List<Permission> list = new ArrayList<Permission>();
//		UserPermission permission = new UserPermission(auth_name, auth_value)
		return list;
	}

}
