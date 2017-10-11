package utils;

import ggframework.bottom.store.mongodb.DBCollection;
import ggframework.bottom.store.mongodb.GGMongoOperator;

/**
 * 
 * @Description:Mongo工具类
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年10月11日 下午4:03:41
 */
public class MongoUtil {
	
	/**
	 * 
	 * 方法描述 获取用户数据库
	 *
	 * @param collection
	 * @return
	 * 
	 * @author yaomy
	 * @date 2017年7月25日 下午1:35:11
	 */
	public static DBCollection getGGUserCollection(String collection){
		return GGMongoOperator.getGGBusinessCollection("userdb", collection);
	}
}
