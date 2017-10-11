
package ggauth.shiro.user.common;

import org.apache.shiro.util.ThreadContext;

/**
 * @Description:线程管理类
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月30日 上午9:10:23
 */
public class ThreadHelper {

	/**
	 * 
	 * @Description:移除线程中的subject
	 * @author yaomy
	 * @date 2017年9月30日 上午9:12:56
	 */
	public static void removeThreadSubject(){
		ThreadContext.remove(ThreadContext.SUBJECT_KEY);//移除线程中的subject
	}
}
