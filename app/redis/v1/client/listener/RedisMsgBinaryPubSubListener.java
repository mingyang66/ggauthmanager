package redis.v1.client.listener;

import redis.clients.jedis.BinaryJedisPubSub;

public class RedisMsgBinaryPubSubListener extends BinaryJedisPubSub{

	@Override
	public void onMessage(byte[] channel, byte[] message) {
		System.out.println("onMessage---"+new String(channel)+"---"+new String(message));
	}
	
	@Override
	public void onPMessage(byte[] pattern, byte[] channel, byte[] message) {
		System.out.println("onPMessage---"+new String(channel)+"---"+new String(message));
	}
	
	@Override
	public void onSubscribe(byte[] channel, int subscribedChannels) {
		System.out.println("---"+new String(channel)+"---"+subscribedChannels);
	}
	
	@Override
	public void onUnsubscribe(byte[] channel, int subscribedChannels) {
		System.out.println("onSubscribe---"+new String(channel)+"---"+subscribedChannels);
	}
	
	@Override
	public void onPUnsubscribe(byte[] pattern, int subscribedChannels) {
		System.out.println("---"+new String(pattern)+"---"+subscribedChannels);
	}
	
	@Override
	public void onPSubscribe(byte[] pattern, int subscribedChannels) {
		System.out.println("onPUnsubscribe---"+new String(pattern)+"---"+subscribedChannels);
	}	
}
