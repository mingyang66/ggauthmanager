
package ggauth.shiro.user.serviceImpl;

import ggauth.shiro.user.model.Role;
import ggauth.shiro.user.service.RoleService;
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

import utils.MongoUtil;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月21日 下午4:38:08
 */
public class RoleServiceImpl implements RoleService{
	
	@Override
	public boolean createRole(Role role) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_role");
		BasicDBObject doc = GGMongoOperator.newDBObject(collection);
		doc.append("role", role.getRole());
		doc.append("description", role.getDescription());
		doc.append("available", role.getAvailable());
		doc.append("status", 1);
		doc.append("creator", "admin");
		doc.append("operator", "admin");
		doc.append("create_time", new Date());
		doc.append("update_time", new Date());
		
		collection.insert(doc);
		return true;
	}

	@Override
	public void deleteRole(Long roleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean findRoleAndUpdate(Role role) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_role");
		BasicDBObject query = new BasicDBObject();
		query.append("_id", role.getId());
		
		BasicDBObject update = new BasicDBObject();
		update.append("role", role.getRole());
		update.append("description", role.getDescription());
		update.append("available", role.getAvailable());
		update.append("operator", "admin");
		update.append("update_time", new Date());
		
		DBObject doc = collection.findAndModify(query, new BasicDBObject("$set", update));	
		if(doc != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean findRoleAndDel(Long roleId) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_role");
		BasicDBObject query = new BasicDBObject();
		query.append("_id", roleId);
		
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

	@Override
	public long findRoleCount() {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_role");
		BasicDBObject query = new BasicDBObject();
		query.put("status", 1);
		
		long count = collection.count(query);
		return count;
	}

	@Override
	public Set<String> findRoles(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Description:查询角色信息
	 * @author yaomy
	 * @date 2017年9月21日 下午5:09:36
	 */
	@Override
	public List<Map<String, Object>> findRoles(Integer rows, Integer page) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_role");
		BasicDBObject query = new BasicDBObject();
		query.put("status", 1);
		
		BasicDBObject sort = new BasicDBObject();
    	sort.put("create_time", -1);
    	
    	DBCursor cursor = collection.find(query).sort(sort).limit(rows).skip((page-1)*rows);
    	List<Map<String, Object>> list = GGDBCursor.find(cursor);
    	
		return list;
	}

	@Override
	public Role findByRoleId(Long roleId) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_role");
		BasicDBObject query = new BasicDBObject();
		query.append("status", 1);
		query.append("_id", roleId);
		
		BasicDBObject fields = new BasicDBObject();
		fields.append("role", 1);
		fields.append("description", 1);
		fields.append("available", 1);
		DBObject p = collection.findOne(query, fields);
		
		Role role = new Role();
		role.setId(p.getLong("_id"));
		role.setAvailable(p.getBoolean("available"));
		role.setDescription(p.getString("description"));
		role.setRole(p.getString("role"));
		return role;
	}

}
