package redis.v1.client.server;

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
}
