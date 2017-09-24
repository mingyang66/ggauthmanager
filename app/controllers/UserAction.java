
package controllers;

import java.util.List;
import java.util.Map;

import framework.store.util.ResultUtil;
import ggauth.shiro.user.model.User;
import ggauth.shiro.user.service.UserService;
import ggauth.shiro.user.serviceImpl.UserServiceImpl;
import play.mvc.Controller;
import play.mvc.With;
import utils.PageUtil;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月19日 上午9:08:07
 */
@With(LoginAction.class)
public class UserAction extends Controller{
	
	private static UserService service = new UserServiceImpl();
	/**
	 * 
	 * @Description:获取用户列表
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
    	List<Map<String, Object>> list = service.findUsers(rows, page);
    	//数据总条数
		int count = Long.valueOf(service.findUserCount()).intValue();
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
	 * @Description:渲染用户视图
	 * @author yaomy
	 * @date 2017年9月19日 上午10:25:36
	 */
	public static void addUserView(){
		render();
	}
	/**
	 * 
	 * @Description:新增用户
	 * @author yaomy
	 * @date 2017年9月19日 上午10:25:22
	 */
	public static void insertUser(User user){
		boolean flag = service.createUser(user);
		if(flag) {
			 renderJSON(ResultUtil.getReturnResult(100, "新增用户成功！"));
		} else {
			renderJSON(ResultUtil.getReturnResult(101, "新增用户失败！"));
		}
	}
	/**
	 * 
	 * @Description:编辑用户信息
	 * @author yaomy
	 * @date 2017年9月19日 上午10:25:08
	 */
	public static void editUserView(Long id){
		User user = service.findByUserId(id);
		render(user);
	}
	/**
	 * 
	 * @Description:更新用户成功
	 * @author yaomy
	 * @date 2017年9月19日 上午10:33:06
	 */
	public static void updateUser(User user) {
		boolean flag = service.findUserAndUpdate(user);
		if(flag) {
			renderJSON(ResultUtil.getReturnResult(100, "更新用户成功！"));
		} else {
			renderJSON(ResultUtil.getReturnResult(101, "更新用户失败！"));
		}
	}
	/**
	 * 
	 * @Description:删除用户信息
	 * @author yaomy
	 * @date 2017年9月19日 上午10:45:40
	 */
	public static void delUser(Long id){
		boolean flag = service.findUserAndDel(id);
		if(flag) {
			renderJSON(ResultUtil.getReturnResult(100, "删除用户成功！"));
		} else {
			renderJSON(ResultUtil.getReturnResult(101, "删除用户失败！"));
		}
	}
}
