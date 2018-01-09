package ggmes.websocket.builder;

import java.util.Map;

import org.elasticsearch.common.collect.Maps;
import org.java_websocket.WebSocket;

import ggmes.websocket.bean.User;
import utils.ObjectUtils;
/**
 * 
 * 保存ws对象
 * 
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @date 2018年1月8日 下午5:00:39
 */
public class WebSocketBuilder {

	private static Map<WebSocket, User> wsMap = Maps.newConcurrentMap();
	
	public static Map<WebSocket, User> getWs(){
		return wsMap;
	}
	/**
	 * 
	 * 方法描述 新增WS对象
	 *
	 * @param ws
	 * @param key
	 * 
	 * @author yaomy
	 * @date 2018年1月8日 下午5:01:08
	 */
	public static void addWs(WebSocket ws, User user) {
		if(ObjectUtils.isNotNull(ws) && !wsMap.containsKey(ws)) {
			wsMap.put(ws, user);
		}
		System.out.println(wsMap);
	}
	/**
	 * 
	 * 方法描述 删除ws对象
	 *
	 * @param ws
	 * 
	 * @author yaomy
	 * @date 2018年1月8日 下午5:01:24
	 */
	public static void removeWs(WebSocket ws) {
		if(ObjectUtils.isNotNull(ws) && wsMap.containsKey(ws)) {
			wsMap.remove(ws);
		}
	}
	
}
