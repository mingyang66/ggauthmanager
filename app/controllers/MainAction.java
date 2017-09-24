
package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月6日 下午2:47:41
 */
@With(LoginAction.class)
public class MainAction extends Controller{

	public static void dashboard(){
		render();
	}
	public static void manageblog(){
		render();
	}
	public static void messages(){
		render();
	}
	public static void reports(){
		render();
	}
}
