
package ggauth.shiro.user.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public boolean createRole(Role role);  
	public boolean findRoleAndUpdate(Role role);//更新角色信息
	public boolean findRoleAndDel(Long roleId);//删除角色
	public long findRoleCount();//查询用户数量
	public Role findByRoleId(Long userId);//根据角色id查询用户
	public List<Map<String, Object>> findRoles(Integer rows, Integer page);//查询角色列表
	public Set<String> findRoles(String username);// 根据用户名查找其角色  
    public void deleteRole(Long roleId);  
    //添加角色-权限之间关系  
    public void correlationPermissions(Long roleId, Long... permissionIds);  
    //移除角色-权限之间关系  
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);//  
}
