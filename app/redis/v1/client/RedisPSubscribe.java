package redis.v1.client;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.v1.client.common.PubSubCommons;
import redis.v1.client.listener.RedisMsgPubSubListener;
import redis.v1.client.server.JedisPoolClient;
import redis.v1.client.server.RedisClient;

public class RedisPSubscribe {

	public static void main(String[] args) {
		Jedis jedis = JedisPoolClient.getJedis();
		System.out.println("发布服务器运行状态："+jedis.ping());
		int channels = PubSubCommons.listener.getSubscribedChannels();
		System.out.println("RedisPSubscribe---channels---"+channels);
		
		List<String> list = jedis.pubsubChannels("*");
		for(String channel:list) {
			System.out.println("RedisPSubscribe---订阅频道："+channel);
		}
		try {
			Thread.sleep(1000*1);
			jedis.publish("redisChat".getBytes(), "---redisChat发送消息...".getBytes());
			Thread.sleep(1000*3);
			jedis.publish("redisChat1".getBytes(), "---redisChat1发送消息...".getBytes());
			Thread.sleep(1000*5);
			jedis.publish("redisChat2".getBytes(), "---redisChat2发送消息...".getBytes());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
