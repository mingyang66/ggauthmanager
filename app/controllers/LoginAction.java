
package controllers;

import framework.yaomy.log.GGLogger;
import ggauth.shiro.user.securitymanager.SecurityManagerPool;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

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

	public static void login(){
		String username = request.params.get("username", String.class);
		String password = request.params.get("password", String.class);
		//获取主题对象
		Subject subject = SecurityManagerPool.getSubject();
		//创建用户名密码身份验证token(即：用户身份/凭证)
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
		
		try{
			//登录，即身份验证
			subject.login(token);
			GGLogger.info("是否登录成功---"+subject.isAuthenticated());
			GGLogger.info("是否记住我----"+subject.isRemembered());
			
			if(subject.isPermitted("system+edit1+10")){
				System.out.println("----------------------拥有打印权限----------------");
			} else {
				System.out.println("----------------------无打印权限---------------------");
				
			}
			if(subject.hasRole("admin")){
				System.out.println("拥有权限");
			}else {
				GGLogger.info("无权限");
			}
		} catch(UnknownAccountException e){
			GGLogger.info("身份验证失败"+e);
		} catch (IncorrectCredentialsException  e) {
			GGLogger.info("身份验证失败"+e);
		} catch (LockedAccountException e) {
			GGLogger.info("身份验证失败"+e);
		} catch (ExcessiveAttemptsException e) {
			GGLogger.info("身份验证失败"+e);
		} catch (AuthenticationException e) {
			GGLogger.info("身份验证失败"+e);
		}
	}
	/**
	 * 
	 * @Description:退出账号登录
	 * @author yaomy
	 * @date 2017年9月22日 上午9:20:30
	 */
	public static void logout(){
		GGLogger.info("退出登录！");
		Subject subject = SecurityManagerPool.getSubject();
		subject.logout();
		redirect("/MainAction/index");
	}
}
