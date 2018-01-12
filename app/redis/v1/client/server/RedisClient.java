package redis.v1.client.server;

import java.util.List;

import redis.clients.jedis.Jedis;

public enum RedisClient {

	Clent;
	
	private static Jedis jedis = null;
	
	public static Jedis getJedis() {
		if(jedis == null) {
			jedis = new Jedis("127.0.0.1",6379);
			jedis.auth("619868");
		}
		return jedis;
	}
	/**
	 * 
	 * 方法描述 查看发布订阅的频道
	 *
	 * @param patterns
	 * @return
	 * 
	 * @author yaomy
	 * @date 2018年1月11日 下午4:03:36
	 */
	public static List<String> pubsubChannels(String patterns){
		return getJedis().pubsubChannels(patterns);
	}
}
