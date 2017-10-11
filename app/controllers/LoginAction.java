
package controllers;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import play.mvc.Before;
import play.mvc.Controller;
import framework.store.log.GGLogger;
import framework.store.util.DateUtil;
import framework.store.util.ResultUtil;
import ggauth.shiro.user.common.ThreadHelper;

/**
 * @Description:登录控制类
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月21日 下午8:25:36
 */
public class LoginAction extends Controller{
	/**
	 * 过滤器
	 */
	@Before(unless={"index","login"})
	static void validate(){
		try{
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isAuthenticated()){
				redirect("/LoginAction/index");
			}
			Session session = subject.getSession();
			GGLogger.info("-----登录时长："+(System.currentTimeMillis()-session.getStartTimestamp().getTime())/1000+"秒"+
						  "-----距离上次访问时长:"+(System.currentTimeMillis()-session.getLastAccessTime().getTime())/1000+"秒"+
						  "-----登录时间："+DateUtil.dateToString(session.getStartTimestamp(), "yyyy-MM-dd HH:mm:ss")+
						  "-----最后访问时间："+DateUtil.dateToString(session.getLastAccessTime(), "yyyy-MM-dd HH:mm:ss")+
						  "-----会话有效时间："+session.getTimeout()/1000+"秒");
			session.touch();//更新回话最后访问时间，JAVASE项目需要自动的调用更新回话访问的最后时间
		} catch (UnknownSessionException e) {
			GGLogger.info("session会话已过期但是subject还未过期");
			redirect("/LoginAction/index");
		} catch (ExpiredSessionException e) {
			GGLogger.info("会话过期");
			redirect("/LoginAction/index");
		}
	}
	/**
	 * 首页登录页
	 */
	public static void index(){
		render();
	}
	/**
	 * 用户登录action
	 */
	public static void login(){
		Map<String, Object> map = ResultUtil.getReturnResult(100, "登录成功");
		String username = request.params.get("username", String.class);
		String password = request.params.get("password", String.class);
		try{
			verifyUserAuth(username, password);
		} catch(UnknownAccountException e){
			map = ResultUtil.getReturnResult(101, "账号不存在");
		} catch (IncorrectCredentialsException  e) {
			map = ResultUtil.getReturnResult(102, "密码不正确");
		} catch (LockedAccountException e) {
			map = ResultUtil.getReturnResult(103, "账号被锁定");
		} catch (ExcessiveAttemptsException e) {
			map = ResultUtil.getReturnResult(104, "登录失败次数过多");
		} catch (AuthenticationException e) {
			map = ResultUtil.getReturnResult(105, "认证失败");
		} catch (UnknownSessionException e) {
			ThreadHelper.removeThreadSubject();//删除线程中的subject
			verifyUserAuth(username, password);
		} catch (ExpiredSessionException e) {
			ThreadHelper.removeThreadSubject();//删除线程中的subject
			verifyUserAuth(username, password);
		}
		GGLogger.info(map.get("msg"));
		
		renderJSON(map);
	}
	/**
	 * 
	 * @Description:验证用户登录信息
	 * @author yaomy
	 * @date 2017年9月29日 下午6:07:45
	 */
	private static void verifyUserAuth(String username, String password){
		//创建用户名密码身份验证token(即：用户身份/凭证)
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
		//获取主题对象
		Subject subject = SecurityUtils.getSubject();
		//验证是否登录成功，如果未登录成功登录验证
		if(!subject.isAuthenticated()){
			GGLogger.info("登录验证身份！");
			//登录，即身份验证
			subject.login(token);
		}
		PrincipalCollection principal = subject.getPrincipals();
		List list = principal.asList();
		GGLogger.info(list);
		String sb = "是否登录成功:"+subject.isAuthenticated()+"--------是否记住我:"+subject.isRemembered()+
				"---------sessionid:"+subject.getSession().getId()+"--角色是："+subject.hasRole("admin");
		GGLogger.info(sb);
		
		if(subject.isPermitted("system+edit+10")){
			GGLogger.info("----------------------拥有打印权限----------------");
		} else {
			GGLogger.info("----------------------无打印权限---------------------");
		}
	}
	/**
	 * 
	 * @Description:退出账号登录
	 * @author yaomy
	 * @date 2017年9月22日 上午9:20:30
	 */
	public static void logout(){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		GGLogger.info(
				  "-----退出时间："+DateUtil.dateToString(session.getLastAccessTime(), "yyyy-MM-dd HH:mm:ss")+
				  "-----登录时长："+(System.currentTimeMillis()-session.getStartTimestamp().getTime())/1000+"秒"+
				  "-----距离上次访问时长:"+(System.currentTimeMillis()-session.getLastAccessTime().getTime())/1000+"秒"+
				  "-----登录时间："+DateUtil.dateToString(session.getStartTimestamp(), "yyyy-MM-dd HH:mm:ss")+
				  "-----会话有效时间："+session.getTimeout()/1000+"秒");
		subject.logout();
		redirect("/LoginAction/index");
	}
}
