
package controllers;

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
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

import framework.store.log.GGLogger;
import framework.store.util.DateUtil;
import framework.store.util.ResultUtil;
import ggauth.shiro.user.securitymanager.SecurityManagerPool;
import play.mvc.Before;
import play.mvc.Controller;

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
		Subject subject = SecurityManagerPool.pool.getSubject();
		if(!subject.isAuthenticated()){
			redirect("/LoginAction/index");
		}
		Session session = subject.getSession();
		try{
			session.touch();//更新回话最后访问时间，JAVASE项目需要自动的调用更新回话访问的最后时间
			GGLogger.info("------登录时间："+DateUtil.dateToString(session.getStartTimestamp(), "yyyy-MM-dd HH:mm:ss")+"----最后访问时间："+DateUtil.dateToString(session.getLastAccessTime(), "yyyy-MM-dd HH:mm:ss")+"-------会话有效时间："+session.getTimeout()/1000+"秒");
		} catch (UnknownSessionException e) {
			GGLogger.info("不能识别的session");
			redirect("/LoginAction/index");
		} catch (ExpiredSessionException e) {
			GGLogger.info("缓存过期");
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
		String username = request.params.get("username", String.class);
		String password = request.params.get("password", String.class);
		Map<String, Object> map = ResultUtil.getReturnResult(100, "登录成功");
		//创建用户名密码身份验证token(即：用户身份/凭证)
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
		//获取主题对象
		Subject subject = SecurityManagerPool.pool.getSubject();
		try{
			//验证是否登录成功，如果未登录成功登录验证
			if(!subject.isAuthenticated()){
				GGLogger.info("登录验证身份！");
				//登录，即身份验证
				subject.login(token);
			}
			GGLogger.info("是否登录成功---"+subject.isAuthenticated());
			GGLogger.info("是否记住我----"+subject.isRemembered());
			
			if(subject.isPermitted("system+edit1+10")){
				System.out.println("----------------------拥有打印权限----------------");
			} else {
				System.out.println("----------------------无打印权限---------------------");
				
			}
//			if(subject.hasRole("admin")){
//				System.out.println("拥有权限");
//			}else {
//				GGLogger.info("无权限");
//			}
		} catch(UnknownAccountException e){
			GGLogger.info("账号不存在"+e);
			map = ResultUtil.getReturnResult(101, "账号不存在");
		} catch (IncorrectCredentialsException  e) {
			GGLogger.info("密码不正确"+e);
			map = ResultUtil.getReturnResult(101, "密码不正确");
		} catch (LockedAccountException e) {
			GGLogger.info("账号被锁定"+e);
			map = ResultUtil.getReturnResult(101, "账号被锁定");
		} catch (ExcessiveAttemptsException e) {
			GGLogger.info("登录失败次数过多"+e);
			map = ResultUtil.getReturnResult(101, "登录失败次数过多");
		} catch (AuthenticationException e) {
			GGLogger.info("认证失败"+e);
			map = ResultUtil.getReturnResult(101, "认证失败");
		} catch (UnknownSessionException e) {
			GGLogger.info("会话过期"+e);
			map = ResultUtil.getReturnResult(101, "会话过期");
		}
		renderJSON(map);
	}
	/**
	 * 
	 * @Description:退出账号登录
	 * @author yaomy
	 * @date 2017年9月22日 上午9:20:30
	 */
	public static void logout(){
		Subject subject = SecurityManagerPool.pool.getSubject();
		Session session = subject.getSession();
		if(session.getAttribute(session.getId()) != null){
			subject.logout();
		}
		GGLogger.info("退出登录---最后访问时间是："+DateUtil.dateToString(session.getLastAccessTime(), "yyyy-MM-dd HH:mm:ss") );
		redirect("/LoginAction/index");
	}
}
