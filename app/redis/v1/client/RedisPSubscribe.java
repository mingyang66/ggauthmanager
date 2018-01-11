package redis.v1.client;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.v1.client.common.PubSubCommons;
import redis.v1.client.listener.RedisMsgPubSubListener;

public class RedisPSubscribe {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1",6379);
		jedis.auth("619868");
		System.out.println("发布服务器运行状态："+jedis.ping());
		
		try {
			Thread.sleep(1000*1);
			jedis.publish("redisChat".getBytes(), "---redisChat发送消息...".getBytes());
			Thread.sleep(1000*3);
			jedis.publish("redisChat1".getBytes(), "---redisChat1发送消息...".getBytes());
			Thread.sleep(1000*5);
			jedis.publish("redisChat2".getBytes(), "---redisChat2发送消息...".getBytes());
			Thread.sleep(1000*7);
			jedis.publish("redisChat".getBytes(), "---redisChat取消订阅...".getBytes());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		jedis.close();
	}
}
