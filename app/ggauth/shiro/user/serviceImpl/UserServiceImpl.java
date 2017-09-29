
package ggauth.shiro.user.serviceImpl;

import framework.store.log.GGLogger;
import framework.store.mongo.pool.DBCollection;
import framework.store.mongo.pool.DBCursor;
import framework.store.mongo.pool.GGDBCursor;
import framework.store.mongo.pool.GGMongoOperator;
import framework.store.mongo.pool.WriteResult;
import ggauth.shiro.user.common.PasswordHelper;
import ggauth.shiro.user.model.User;
import ggauth.shiro.user.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.bson.Document;

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
	private DBCollection collection = GGMongoOperator.getGGBusinessDBCollection("gg_user");
	
	@Override
	public boolean createUser(User user) {
		passwordHelper.encryptPassword(user);
		GGLogger.info(user.toString());
		
		Document doc = GGMongoOperator.newId(collection);
		doc.append("username", user.getUsername());
		doc.append("password", user.getPassword());
		doc.append("salt", user.getSalt());
		doc.append("locked", user.getLocked());
		doc.append("status", 1);
		doc.append("creator", "admin");
		doc.append("operator", "admin");
		doc.append("create_time", new Date());
		doc.append("update_time", new Date());
		
		WriteResult result = collection.insertOne(doc);
		if(result.getModifiedCount() == 1) {
			return true;
		}
		return false;
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
		Document query = new Document();
		query.append("status", 1);
		query.append("username", new Document("$regex", Pattern.compile(username, Pattern.CASE_INSENSITIVE)));
		
		Document fields = new Document();
		fields.append("username", 1);
		fields.append("password", 1);
		fields.append("salt", 1);
		fields.append("locked", 1);
		
		Document obj = collection.findOne(query, fields);
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
		
		Document query = new Document();
		query.put("status", 1);
		
		Document sort = new Document();
    	sort.put("create_time", -1);
    	
    	DBCursor cursor = collection.find(query).sort(sort).limit(rows).skip((page-1)*rows);
    	List<Map<String, Object>> list = GGDBCursor.getListMap(cursor);
    	
		return list;
	}

	@Override
	public long findUserCount() {
		Document query = new Document();
		query.put("status", 1);
		
		long count = collection.count(query);
		return count;
	}

	@Override
	public User findByUserId(Long userId) {
		
		Document query = new Document();
		query.append("_id", userId);
		
		Document fields = new Document();
		fields.append("username", 1);
		fields.append("password", 1);
		fields.append("salt", 1);
		fields.append("locked", 1);
		Document p = collection.findOne(query, fields);
		
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
		Document query = new Document();
		query.append("_id", user.getId());
		
		Document update = new Document();
		update.append("username", user.getUsername());
		update.append("password", user.getPassword());
		update.append("salt", user.getSalt());
		update.append("operator", "admin");
		update.append("update_time", new Date());
		
		Document doc = collection.findOneAndUpdate(query, new Document("$set", update));	
		if(doc != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean findUserAndDel(Long userId) {
		
		Document query = new Document();
		query.append("_id", userId);
		
		Document value = new Document();
		value.append("status", 0);
		value.append("operator", "admin");
		value.append("update_time", new Date());
		
		Document doc = collection.findOneAndUpdate(query, new Document("$set", value));
		if(doc != null) {
			return true;
		}
		return false;
	}

}
