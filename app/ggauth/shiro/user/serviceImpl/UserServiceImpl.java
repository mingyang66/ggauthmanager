
package ggauth.shiro.user.serviceImpl;

import ggauth.shiro.user.common.PasswordHelper;
import ggauth.shiro.user.model.User;
import ggauth.shiro.user.service.UserService;
import ggframework.bottom.log.GGLogger;
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
import java.util.Set;
import java.util.regex.Pattern;

import utils.MongoUtil;


/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月18日 下午4:13:12
 */
public class UserServiceImpl implements UserService{

	private PasswordHelper passwordHelper = new PasswordHelper();
	
	@Override
	public boolean createUser(User user) {
		passwordHelper.encryptPassword(user);
		GGLogger.info(user.toString());
		
		DBCollection collection = MongoUtil.getGGUserCollection("gg_user");
		BasicDBObject doc = GGMongoOperator.newDBObject(collection);
		doc.append("username", user.getUsername());
		doc.append("password", user.getPassword());
		doc.append("salt", user.getSalt());
		doc.append("locked", user.getLocked());
		doc.append("status", 1);
		doc.append("creator", "admin");
		doc.append("operator", "admin");
		doc.append("create_time", new Date());
		doc.append("update_time", new Date());
		
		collection.insert(doc);
		return true;
	}

	@Override
	public void changePassword(Long userId, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * @Description:通过用户名查询用户信息
	 * @author yaomy
	 * @date 2017年9月19日 上午11:38:42
	 */
	@Override
	public User findByUsername(String username) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_user");
		
		BasicDBObject query = new BasicDBObject();
		query.append("status", 1);
		query.append("username", new BasicDBObject("$regex", Pattern.compile(username, Pattern.CASE_INSENSITIVE)));
		
		BasicDBObject fields = new BasicDBObject();
		fields.append("username", 1);
		fields.append("password", 1);
		fields.append("salt", 1);
		fields.append("locked", 1);
		
		DBObject obj = collection.findOne(query, fields);
		if(obj != null){
			User user = new User();
			user.setId(obj.getLong("_id"));
			user.setLocked(obj.getBoolean("locked"));
			user.setPassword(obj.getString("password"));
			user.setUsername(obj.getString("username"));
			user.setSalt(obj.getString("salt"));
			return user;
		}
		return null;
	}

	@Override
	public Set<String> findRoles(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> findPermissions(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findUsers(Integer rows, Integer page) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_user");
		BasicDBObject query = new BasicDBObject();
		query.put("status", 1);
		
		BasicDBObject sort = new BasicDBObject();
    	sort.put("create_time", -1);
    	
    	DBCursor cursor = collection.find(query).sort(sort).limit(rows).skip((page-1)*rows);
    	List<Map<String, Object>> list = GGDBCursor.find(cursor);
    	
		return list;
	}

	@Override
	public long findUserCount() {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_user");
		BasicDBObject query = new BasicDBObject();
		query.put("status", 1);
		
		long count = collection.count(query);
		return count;
	}

	@Override
	public User findByUserId(Long userId) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_user");
		BasicDBObject query = new BasicDBObject();
		query.append("_id", userId);
		
		BasicDBObject fields = new BasicDBObject();
		fields.append("username", 1);
		fields.append("password", 1);
		fields.append("salt", 1);
		fields.append("locked", 1);
		DBObject p = collection.findOne(query, fields);
		
		User user = new User();
		user.setId(p.getLong("_id"));
		user.setLocked(p.getBoolean("locked"));
		user.setPassword(p.getString("password"));
		user.setUsername(p.getString("username"));
		user.setSalt(p.getString("salt"));
		return user;
	}

	@Override
	public boolean findUserAndUpdate(User user) {
		passwordHelper.encryptPassword(user);
		
		DBCollection collection = MongoUtil.getGGUserCollection("gg_user");
		BasicDBObject query = new BasicDBObject();
		query.append("_id", user.getId());
		
		BasicDBObject update = new BasicDBObject();
		update.append("username", user.getUsername());
		update.append("password", user.getPassword());
		update.append("salt", user.getSalt());
		update.append("operator", "admin");
		update.append("update_time", new Date());
		
		DBObject doc = collection.findAndModify(query, new BasicDBObject("$set", update));	
		if(doc != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean findUserAndDel(Long userId) {
		
		DBCollection collection = MongoUtil.getGGUserCollection("gg_user");
		BasicDBObject query = new BasicDBObject();
		query.append("_id", userId);
		
		BasicDBObject value = new BasicDBObject();
		value.append("status", 0);
		value.append("operator", "admin");
		value.append("update_time", new Date());
		
		DBObject doc = collection.findAndModify(query, new BasicDBObject("$set", value));
		if(doc != null) {
			return true;
		}
		return false;
	}

}
