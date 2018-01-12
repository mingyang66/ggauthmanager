package redis.v1.client.thread;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.v1.client.common.PubSubCommons;
import redis.v1.client.server.JedisPoolClient;
import redis.v1.client.server.RedisClient;

public class UnsubscribeRunable implements Runnable{

	@Override
	public void run() {
		int i = 1;
		while(true) {
			try {
				Jedis jedis = JedisPoolClient.getJedis();
				Thread.sleep(10);
				i = i+1;
				System.out.println("休眠第"+i+"秒,\n客户端数量:\n"+jedis.info("clients"));
//				if(i/8 == 6) {
//					PubSubCommons.listener.unsubscribe("redisChat1");
//					PubSubCommons.listener.unsubscribe("redisChat");
//					PubSubCommons.listener.unsubscribe("redisChat2");
//					break;
//				}
				int channels = PubSubCommons.listener.getSubscribedChannels();
				System.out.println("---channels---"+channels);
				
				List<String> list = jedis.pubsubChannels("*");
				for(String channel:list) {
					System.out.println("---订阅频道："+channel);
				}
				
				JedisPoolClient.returnResource(jedis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
