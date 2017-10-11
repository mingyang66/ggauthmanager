
package ggauth.shiro.user.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import framework.store.log.GGLogger;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年10月11日 下午2:17:18
 */
public class MyPermissionResolver implements PermissionResolver{

	@Override
	public Permission resolvePermission(String permissionString) {
		GGLogger.info("----------------------权限是："+permissionString+"-------------");
		return new WildcardPermission(permissionString, true);
	}

}
