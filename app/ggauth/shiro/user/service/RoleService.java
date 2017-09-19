
package ggauth.shiro.user.service;

import ggauth.shiro.user.model.Role;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月18日 下午4:09:45
 */
public interface RoleService {
	public Role createRole(Role role);  
    public void deleteRole(Long roleId);  
    //添加角色-权限之间关系  
    public void correlationPermissions(Long roleId, Long... permissionIds);  
    //移除角色-权限之间关系  
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);//  
}
