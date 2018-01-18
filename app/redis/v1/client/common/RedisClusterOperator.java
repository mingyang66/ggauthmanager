package redis.v1.client.common;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.v1.client.server.JedisClusterClient;

public class RedisClusterOperator {

	/**
	 * 
	 *KEYS pattern
	 *查找所有符合给定模式 pattern 的 key 。
	 *KEYS * 匹配数据库中所有 key 。
	 *KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。
	 *KEYS h*llo 匹配 hllo 和 heeeeello 等。
	 *KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。
	 *特殊符号用 \ 隔开
	 * @param pattern
	 * @return
	 * 
	 * @author yaomy
	 * @date 2018年1月18日 上午11:20:02
	 */
	public static TreeSet<String> keys(String pattern) {
		TreeSet<String> keys = new TreeSet<String>();
		Map<String, JedisPool> clusterNodes = JedisClusterClient.getRedisCluster().getClusterNodes();
		for(Iterator<JedisPool> it=clusterNodes.values().iterator();it.hasNext();) {
			JedisPool pool = it.next();
			Jedis jedis = pool.getResource();
			try {
				keys.addAll(jedis.keys(pattern));
			} catch (Exception e) {
				System.out.println("获取keys发生异常！");
			} finally {
				jedis.close();
			}
		}
		return keys;
	}
}
