
package controllers;

import ggframework.bottom.store.mongodb.BasicDBObject;
import ggframework.bottom.store.mongodb.DBCollection;
import ggframework.bottom.store.mongodb.DBCursor;
import ggframework.bottom.store.mongodb.DBObject;
import ggframework.bottom.store.mongodb.GGDBCursor;
import ggframework.bottom.store.mongodb.GGMongoOperator;
import ggframework.bottom.store.mongodb.WriteResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.ProductLine;


import play.mvc.Controller;
import play.mvc.With;
import utils.MongoUtil;
import utils.PageUtil;
import utils.ResultUtil;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月7日 下午2:04:18
 */
@With(LoginAction.class)
public class ProductLineAction extends Controller{

	/**
	 * 
	 * @Description:获取产品线列表
	 * @author yaomy
	 * @date 2017年9月8日 下午5:57:55
	 */
	public static void list(Integer page){
		//每页显示的条数
		int rows=10;
		//第几页
		if (page == null || page < 1) {
			page = 1;
		}
		DBCollection collection = MongoUtil.getGGUserCollection("gg_product_line");
		
		BasicDBObject query = new BasicDBObject();
		query.put("status", 1);
		
		BasicDBObject sort = new BasicDBObject();
    	sort.put("create_time", -1);
    	
    	DBCursor cursor = collection.find(query).sort(sort).limit(rows).skip((page-1)*rows);
    	List<Map<String, Object>> list = GGDBCursor.find(cursor);
    	
    	//数据总条数
		int count = Long.valueOf(collection.count(query)).intValue();
		//总页数
		int maxPage = count == 0 ? 1 : (count - 1) / rows + 1; 
		if (page > maxPage) {
			page = maxPage;
		}
		//页面显示的页面索引
		List<String> pages = PageUtil.getShowPages(page, maxPage); 
		render(list, pages, maxPage, page);
	}
	/**
	 * 
	 * @Description:渲染新增产品线视图
	 * @author yaomy
	 * @date 2017年9月7日 下午7:03:23
	 */
	public static void addProductLineView(){
		render();
	}
	/**
	 * 
	 * @Description:新增产品线信息
	 * @author yaomy
	 * @date 2017年9月7日 下午7:03:55
	 */
	public static void insertProductLine(ProductLine product){
		DBCollection collection = MongoUtil.getGGUserCollection("gg_product_line");
		
		BasicDBObject doc = GGMongoOperator.newDBObject(collection);
		doc.append("product_line", product.getProduct_line());
		doc.append("product_name", product.getProduct_name());
		doc.append("product_desc", product.getProduct_desc());
		doc.append("status", 1);
		doc.append("creator", "admin");
		doc.append("operator", "admin");
		doc.append("create_time", new Date());
		doc.append("update_time", new Date());
		
		collection.insert(doc);
		renderJSON(ResultUtil.getReturnResult(100, "新增产品线成功！"));
	}
	/**
	 * 删除产品线
	 * @param id
	 */
	public static void delProductLine(Long id){
		DBCollection collection = MongoUtil.getGGUserCollection("gg_product_line");
		
		BasicDBObject query = new BasicDBObject();
		query.append("_id", id);
		
		BasicDBObject value = new BasicDBObject();
		value.append("status", 0);
		value.append("operator", "admin");
		value.append("update_time", new Date());
		
		DBObject doc = collection.findAndModify(query, new BasicDBObject("$set", value));
		if(doc != null) {
			renderJSON(ResultUtil.getReturnResult(100, "删除产品线成功！"));
		} else {
			renderJSON(ResultUtil.getReturnResult(101, "删除产品线失败！"));
		}
	}
	/**
	 * 编辑产品线
	 * @param id
	 */
	public static void editProductLineView(Long id){
		DBCollection collection = MongoUtil.getGGUserCollection("gg_product_line");
		
		BasicDBObject query = new BasicDBObject();
		query.append("_id", id);
		
		BasicDBObject fields = new BasicDBObject();
		fields.append("product_name", 1);
		fields.append("product_desc", 1);
		fields.append("product_line", 1);
		DBObject p = collection.findOne(query, fields);
		
		render(p);
	}
	/**
	 * 更新产品线
	 * @param product
	 */
	public static void updateProductLine(ProductLine product) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_product_line");
		
		BasicDBObject query = new BasicDBObject();
		query.append("_id", product.getId());
		
		BasicDBObject update = new BasicDBObject();
		update.append("product_name", product.getProduct_name());
		update.append("product_desc", product.getProduct_desc());
		update.append("operator", "admin");
		update.append("update_time", new Date());
		
		DBObject doc = collection.findAndModify(query, new BasicDBObject("$set", update));
		if(doc != null) {
			renderJSON(ResultUtil.getReturnResult(100, "更新产品线成功！"));
		} else {
			renderJSON(ResultUtil.getReturnResult(101, "更新产品线失败！"));
		}
	}
}
