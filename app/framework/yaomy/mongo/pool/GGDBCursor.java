
package framework.yaomy.mongo.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;

/**
 * @Description:转换类
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月8日 下午5:53:56
 */
public class GGDBCursor {

	/**
	 * 
	 * @Description:将返回的游标对象转换成List
	 * @author yaomy
	 * @date 2017年9月8日 下午5:56:27
	 */
	public static List<Document> getListDocs(DBCursor cursor) {
		List<Document> list = new ArrayList<Document>();
		while(cursor.hasNext()) {
			Document doc = cursor.next();
			list.add(doc);
		}
		cursor.close();
		return list;
	}
}
