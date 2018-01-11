package redis.v1.client.thread;

import redis.clients.jedis.Jedis;
import redis.v1.client.common.PubSubCommons;
import redis.v1.client.server.RedisClient;

public class UnsubscribeRunable implements Runnable{

	@Override
	public void run() {
		int i = 1;
		while(true) {
			try {
				Thread.sleep(1000*i);
				System.out.println("休眠第"+i+"秒");
				i = i*2;
				if(i/10 == 0) {
					PubSubCommons.listener.unsubscribe("redisChat1");
					PubSubCommons.listener.unsubscribe("redisChat");
					PubSubCommons.listener.unsubscribe("redisChat2");
				}
//				PubSubCommons.listener.onPong("redisChat?");
				Jedis jedis = RedisClient.getJedis();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
