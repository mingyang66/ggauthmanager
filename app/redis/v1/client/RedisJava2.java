package redis.v1.client;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.v1.client.listener.RedisMsgPubSubListener;

public class RedisJava2 {

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
		
		try {
			Thread.sleep(1000*10);
			jedis.publish("redisChat".getBytes(), "---redisChat发送消息...".getBytes());
			Thread.sleep(1000*10);
			jedis.publish("redisChat1".getBytes(), "---redisChat1发送消息...".getBytes());
			Thread.sleep(1000*10);
			jedis.publish("redisChat2".getBytes(), "---redisChat2发送消息...".getBytes());
			Thread.sleep(1000*10);
			jedis.publish("redisChat".getBytes(), "---redisChat取消订阅...".getBytes());
			String[] str = {"redisChat2"};
			jedis.psubscribe(listener, str);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		jedis.close();
	}
}
