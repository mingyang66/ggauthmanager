package framework.store.mongo.pool;

import framework.store.config.GGConfigurer;
import framework.store.log.GGLogger;
import play.PlayPlugin;

public class GGMongoPlugin extends PlayPlugin{

	 public void onApplicationStart() {	
		 GGConfigurer.load("conf/application.conf");
		 GGMongoClientPool.pool.initPool(GGMongoClients.getClients());
		 GGLogger.info("数据库初始化成功...");
	 }
	 public void onApplicationStop() {
		 GGMongoClientPool.pool.destoryPool();
	 }
}
