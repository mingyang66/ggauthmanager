
package framework.store.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:返回结果类
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月7日 下午7:11:22
 */
public class ResultUtil {
	/**
	 * 
	 * 方法描述 封装返回结果集
	 *
	 * @param code
	 * @param msg
	 * @return
	 * 
	 * @author yaomy
	 * @date 2017年5月23日 上午10:27:00
	 */
	public static Map<String, Object> getReturnResult(Integer code, String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 
	 * 方法描述 封装返回结果集 多加一个key--value
	 *
	 * @param code
	 * @param msg
	 * @param paramName
	 * @param paramValue
	 * @return
	 * 
	 * @author yaomy
	 * @date 2017年5月23日 上午10:30:14
	 */
	public static Map<String, Object> getReturnResult(Integer code, String msg, String paramName, Object paramValue){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put(paramName, paramValue);
		map.put("msg", msg);
		return map;
	}
}
