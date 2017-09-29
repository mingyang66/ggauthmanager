
package ggauth.shiro.user.service;

import ggauth.shiro.user.model.Permission;

import java.util.List;
import java.util.Map;



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

	public List<Map<String, Object>> findPermissions(Integer rows, Integer page);
	public long findPermissionCount();
	public boolean createPermission(Permission permission);
	public boolean findPermissionAndDel(Long permissionId);
	public boolean findPermissionAndUpdate(Permission permission);
	public Permission findByPermissionId(Long permissionid);
	public void deletePermission(Long permissionId);
}
