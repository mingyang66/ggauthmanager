package utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;


/**
 * 
 * 工具类
 * 
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company 上海朝阳永续信息技术有限公司
 * @copyright (c) 2017 SunTime Co'Ltd Inc. All rights reserved.
 * @date 2017年12月1日 下午2:14:44
 */
public class ObjectUtils {


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
		Map<String, Object> map = Maps.newHashMap();
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
		Map<String, Object> map = Maps.newHashMap();
		map.put("code", code);
		map.put(paramName, paramValue);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 
	 * 方法描述 如果对象为非空返回true 否则返回false
	 * 支持 Map、List、Set、Collection、String、8中数据类型的包装类型
	 * @param obj
	 * @return
	 * 
	 * @author yaomy
	 * @date 2017年12月1日 下午2:12:38
	 */
	public static boolean isNotNull(Object obj) {
		if(obj != null) {
			if(obj instanceof Map) {
				Map map = (Map)obj;
				if(!map.isEmpty()) {
					return true;
				}
				return false;
			} else if(obj instanceof List) {
				List list = (List)obj;
				if(!list.isEmpty()) {
					return true;
				}
				return false;
			} else if(obj instanceof String) {
				String str = obj.toString();
				if(str != null && str != "" && str.trim() != "") {
					return true;
				}
				return false;
			}else if(obj instanceof Set) {
				Set set = (Set)obj;
				if(!set.isEmpty()) {
					return true;
				}
				return false;
			} else if (obj instanceof Collection) {
				Collection collection = (Collection)obj;
				if(!collection.isEmpty()) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}
	/**
	 * 
	 * 方法描述 如果对象为空返回 true 否则返回false
	 * 支持 Map、List、Set、Collection、String、8中数据类型的包装类型
	 * @param obj
	 * @return
	 * 
	 * @author yaomy
	 * @date 2017年12月1日 下午2:45:02
	 */
	public static boolean isNull(Object obj) {
		if(obj != null) {
			if(obj instanceof Map) {
				Map map = (Map)obj;
				if(!map.isEmpty()) {
					return false;
				}
				return true;
			} else if(obj instanceof List) {
				List list = (List)obj;
				if(!list.isEmpty()) {
					return false;
				}
				return true;
			} else if(obj instanceof String) {
				String str = obj.toString();
				if(str != null && str != "" && str.trim() != "") {
					return false;
				}
				return true;
			} else if(obj instanceof Set) {
				Set set = (Set)obj;
				if(!set.isEmpty()) {
					return false;
				}
				return true;
			} else if (obj instanceof Collection) {
				Collection collection = (Collection)obj;
				if(!collection.isEmpty()) {
					return false;
				}
				return true;
			}
			return false;
		}
		return true;
	}
}
