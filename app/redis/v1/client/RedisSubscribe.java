package redis.v1.client;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.v1.client.common.PubSubCommons;
import redis.v1.client.listener.RedisMsgPubSubListener;
import redis.v1.client.server.RedisClient;
import redis.v1.client.thread.UnsubscribeRunable;

public class RedisSubscribe {

	public static void main(String[] args) {
		Jedis jedis = RedisClient.getJedis();
		System.out.println("订阅服务器运行状态："+jedis.ping());
		
		new Thread(new UnsubscribeRunable()).start();
		
		String[] str = {"redisChat","redisChat1","redisChat2"};
		jedis.subscribe(PubSubCommons.listener, str);
//		jedis.close();
	}
}
