
package controllers;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ThreadContext;

import play.mvc.Before;
import play.mvc.Controller;
import framework.store.log.GGLogger;
import framework.store.util.DateUtil;
import framework.store.util.ResultUtil;
import ggauth.shiro.user.common.EhCacheHelper;
import ggauth.shiro.user.common.ThreadHelper;
import ggauth.shiro.user.realm.UserRealm;

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
			GGLogger.info("已经登录时间："+(System.currentTimeMillis()-session.getStartTimestamp().getTime())/1000+"秒"+
					"-----距离上次访问:"+(System.currentTimeMillis()-session.getLastAccessTime().getTime())/1000+"秒"+
					"------登录时间："+DateUtil.dateToString(session.getStartTimestamp(), "yyyy-MM-dd HH:mm:ss")+
					"----最后访问时间："+DateUtil.dateToString(session.getLastAccessTime(), "yyyy-MM-dd HH:mm:ss")+
					"-------会话有效时间："+session.getTimeout()/1000+"秒");
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
			map = ResultUtil.getReturnResult(101, "密码不正确");
		} catch (LockedAccountException e) {
			map = ResultUtil.getReturnResult(101, "账号被锁定");
		} catch (ExcessiveAttemptsException e) {
			map = ResultUtil.getReturnResult(101, "登录失败次数过多");
		} catch (AuthenticationException e) {
			map = ResultUtil.getReturnResult(101, "认证失败");
		} catch (UnknownSessionException e) {
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
		String sb = "是否登录成功:"+subject.isAuthenticated()+"--------是否记住我:"+subject.isRemembered()+
				"---------sessionid:"+subject.getSession().getId();
		GGLogger.info(sb);
		
		if(subject.isPermitted("system+edit1+10")){
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
		if(session.getAttribute(session.getId()) != null){
			subject.logout();
		}
		GGLogger.info("退出登录---最后访问时间是："+DateUtil.dateToString(session.getLastAccessTime(), "yyyy-MM-dd HH:mm:ss") );
		redirect("/LoginAction/index");
	}
}
