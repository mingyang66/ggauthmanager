
package ggauth.shiro.user.service;

import org.apache.shiro.authz.Permission;


/**
 * @Description:权限接口类
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月18日 下午4:02:16
 */
public interface PermissionService {

	public Permission createPermission(Permission permisson);
	public void deletePermission(Long permissionId);
}
