
package controllers;

import java.util.List;
import java.util.Map;

import framework.store.util.ResultUtil;
import ggauth.shiro.user.model.Role;
import ggauth.shiro.user.service.RoleService;
import ggauth.shiro.user.serviceImpl.RoleServiceImpl;
import play.mvc.Controller;
import utils.PageUtil;

/**
 * @Description:角色action
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月19日 上午9:08:07
 */
public class RoleAction extends Controller{
	
	private static RoleService service = new RoleServiceImpl();
	/**
	 * 
	 * @Description:获取角色列表
	 * @author yaomy
	 * @date 2017年9月19日 上午9:10:07
	 */
	public static void list(Integer page){
		//每页显示的条数
		int rows=10;
		//第几页
		if (page == null || page < 1) {
			page = 1;
		}
    	List<Map<String, Object>> list = service.findRoles(rows, page);
    	//数据总条数
		int count = Long.valueOf(service.findRoleCount()).intValue();
		//总页数
		int maxPage = count == 0 ? 1 : (count - 1) / rows + 1; 
		if (page > maxPage) {
			page = maxPage;
		}
		//页面显示的页面索引
		List<String> pages = PageUtil.getShowPages(page, maxPage); 
		render(list, pages, maxPage, page);
	}
	/**
	 * 
	 * @Description:渲染角色视图
	 * @author yaomy
	 * @date 2017年9月19日 上午10:25:36
	 */
	public static void addRoleView(){
		render();
	}
	/**
	 * 
	 * @Description:新增角色
	 * @author yaomy
	 * @date 2017年9月19日 上午10:25:22
	 */
	public static void insertRole(Role role){
		boolean flag = service.createRole(role);
		if(flag) {
			 renderJSON(ResultUtil.getReturnResult(100, "新增角色成功！"));
		} else {
			renderJSON(ResultUtil.getReturnResult(101, "新增角色失败！"));
		}
	}
	/**
	 * 
	 * @Description:编辑角色信息
	 * @author yaomy
	 * @date 2017年9月19日 上午10:25:08
	 */
	public static void editRoleView(Long id){
		Role role = service.findByRoleId(id);
		render(role);
	}
	/**
	 * 
	 * @Description:更新角色成功
	 * @author yaomy
	 * @date 2017年9月19日 上午10:33:06
	 */
	public static void updateRole(Role role) {
		boolean flag = service.findRoleAndUpdate(role);
		if(flag) {
			renderJSON(ResultUtil.getReturnResult(100, "更新角色成功！"));
		} else {
			renderJSON(ResultUtil.getReturnResult(101, "更新角色失败！"));
		}
	}
	/**
	 * 
	 * @Description:删除角色信息
	 * @author yaomy
	 * @date 2017年9月19日 上午10:45:40
	 */
	public static void delRole(Long id){
		boolean flag = service.findRoleAndDel(id);
		if(flag) {
			renderJSON(ResultUtil.getReturnResult(100, "删除角色成功！"));
		} else {
			renderJSON(ResultUtil.getReturnResult(101, "删除角色失败！"));
		}
	}
}
