package redis.v1.client.listener;

import redis.clients.jedis.JedisPubSub;

public class RedisMsgPubSubListener extends JedisPubSub{

	/**
	 * 接收订阅通道发来的消息
	 * @param channel 通道名称
	 * @param message 消息
	 */
	  @Override
	  public void onMessage(String channel, String message){
		  System.out.println("onMessage---"+channel+"---"+message);
	  }
	  @Override
	  public void onPMessage(String pattern, String channel, String message){
		  System.out.println("onPMessage---"+pattern+"---"+channel+"---"+message);
	  }
	  /**
	   * 订阅通道信息
	   * @param channel 订阅的通道名
	   * @subscribedChannels 订阅的通道数量
	   */
	  @Override
	  public void onSubscribe(String channel, int subscribedChannels){
		  System.out.println("onSubscribe---"+channel+"---"+subscribedChannels);
	  }
	  @Override
	  public void onUnsubscribe(String channel, int subscribedChannels){
		  System.out.println("onUnsubscribe---"+channel+"---"+subscribedChannels);
	  }
	  @Override
	  public void onPUnsubscribe(String pattern, int subscribedChannels){
		  System.out.println("onPUnsubscribe---"+pattern+"---"+subscribedChannels);
	  }
	  /**
	   * 取消订阅信息
	   * @param pattern 通道名称或者正则匹配表达式
	   * @param subscribedChannels 通道数量
	   */
	  @Override
	  public void onPSubscribe(String pattern, int subscribedChannels){
		  System.out.println("onPSubscribe---"+pattern+"---"+subscribedChannels);
	  }
	  @Override
	  public void onPong(String pattern){
		  System.out.println("onPong"+"---"+pattern);
	  }
}
