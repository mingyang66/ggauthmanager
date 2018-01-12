package redis.v1.client.server;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClusterClient {

	private static JedisCluster cluster = null;
	
	static {
		if(cluster == null) {
	        Set<HostAndPort> clusterNodes = new HashSet<HostAndPort>();
	        clusterNodes.add(new HostAndPort("127.0.0.1", 7001));
	        clusterNodes.add(new HostAndPort("127.0.0.1", 7002));
	        clusterNodes.add(new HostAndPort("127.0.0.1", 7003));
	        clusterNodes.add(new HostAndPort("127.0.0.1", 7004));
	        clusterNodes.add(new HostAndPort("127.0.0.1", 7005));
	        clusterNodes.add(new HostAndPort("127.0.0.1", 7006));
	        clusterNodes.add(new HostAndPort("127.0.0.1", 7007));
	        
	        JedisPoolConfig config = new JedisPoolConfig();
	        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			config.setMaxTotal(50);
			//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(5);
			//表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；单位毫秒
			//小于零:阻塞不确定的时间,  默认-1
			config.setMaxWaitMillis(1000*100);
			//在borrow(引入)一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			//return 一个jedis实例给pool时，是否检查连接可用性（ping()）
			config.setTestOnReturn(true);
			
			/**
			 * @param nodes 集群服务器地址
			 * @param connectionTimeout 连接超时 （毫秒）
			 * @param soTimeout 响应超时（毫秒）
			 * @param maxAttempts 出现异常最大重试次数
			 */
			cluster = new JedisCluster(clusterNodes, 2000, 2000, 10, "619868", config);
		}
	}
	
}
