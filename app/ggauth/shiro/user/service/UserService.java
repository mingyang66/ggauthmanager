
package ggauth.shiro.user.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ggauth.shiro.user.model.User;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月18日 下午4:10:17
 */
public interface UserService {
	public boolean createUser(User user); //创建账户  
	public boolean findUserAndUpdate(User user);//更新用户信息
	public boolean findUserAndDel(Long userId);//删除用户
	public long findUserCount();//查询用户数量
	public List<Map<String, Object>> findUsers(Integer rows, Integer page);//查询用户列表
    public void changePassword(Long userId, String newPassword);//修改密码  
    public void correlationRoles(Long userId, Long... roleIds); //添加用户-角色关系  
    public void uncorrelationRoles(Long userId, Long... roleIds);// 移除用户-角色关系  
    public Set<String> findRoles(String username);// 根据用户名查找其角色  
    public Set<String> findPermissions(String username); //根据用户名查找其权限  
    public User findByUsername(String username);// 根据用户名查找用户  
    public User findByUserId(Long userId);//根据用户id查询用户
}
