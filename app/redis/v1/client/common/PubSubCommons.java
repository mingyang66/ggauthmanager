package redis.v1.client.common;

import redis.v1.client.listener.RedisMsgPubSubListener;

public class PubSubCommons {
	
	public static RedisMsgPubSubListener listener = null;
	
	static {
		listener = new RedisMsgPubSubListener();
	}
}
