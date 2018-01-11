package redis.v1.client;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.v1.client.listener.RedisMsgPubSubListener;

public class RedisJava {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1",6379);
		jedis.auth("619868");
		System.out.println("服务正在运行："+jedis.ping());
		jedis.set("runoobkey", "www.runoob.com");
		System.out.println(jedis.get("runoobkey"));
		for(Iterator<String> it = jedis.keys("*").iterator();it.hasNext();) {
			String key = it.next();
			System.out.println(key+"---");
		}
		RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
		String[] str = {"redisChat","redisChat1","redisChat2"};
		jedis.subscribe(listener, str);
//		jedis.close();
	}
}
