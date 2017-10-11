
package ggauth.shiro.user.plugins;

import ggauth.shiro.user.securitymanager.SecurityManagerPool;
import ggframework.bottom.log.GGLogger;
import play.PlayPlugin;

/**
 * @Description:shiro安全管理器初始化
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月21日 下午8:12:04
 */
public class SecurityManagerPlugin extends PlayPlugin{

	 public void onApplicationStart() {	
		 GGLogger.info("SecurityManager初始化成功...");
		 SecurityManagerPool.pool.initSecurityManager();
	 }
	 public void onApplicationStop() {
	 }
}
