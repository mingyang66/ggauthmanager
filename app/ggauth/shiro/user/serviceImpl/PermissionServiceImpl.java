
package ggauth.shiro.user.serviceImpl;

import ggauth.shiro.user.model.Permission;
import ggauth.shiro.user.service.PermissionService;
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

import utils.MongoUtil;


/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月21日 下午5:27:11
 */
public class PermissionServiceImpl implements PermissionService{

	@Override
	public void deletePermission(Long permissionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> findPermissions(Integer rows, Integer page) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_permission");
		BasicDBObject query = new BasicDBObject();
		query.put("status", 1);
		
		BasicDBObject sort = new BasicDBObject();
    	sort.put("create_time", -1);
    	
    	DBCursor cursor = collection.find(query).sort(sort).limit(rows).skip((page-1)*rows);
    	List<Map<String, Object>> list = GGDBCursor.find(cursor);
    	
		return list;
	}

	@Override
	public long findPermissionCount() {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_permission");
		BasicDBObject query = new BasicDBObject();
		query.put("status", 1);
		
		long count = collection.count(query);
		return count;
	}

	@Override
	public boolean createPermission(Permission permission) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_permission");
		BasicDBObject doc = GGMongoOperator.newDBObject(collection);
		doc.append("permission", permission.getPermission());
		doc.append("description", permission.getDescription());
		doc.append("available", permission.getAvailable());
		doc.append("status", 1);
		doc.append("creator", "admin");
		doc.append("operator", "admin");
		doc.append("create_time", new Date());
		doc.append("update_time", new Date());
		
		collection.insert(doc);
		return true;
	}

	@Override
	public boolean findPermissionAndDel(Long permissionId) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_permission");
		BasicDBObject query = new BasicDBObject();
		query.append("_id", permissionId);
		
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
	public boolean findPermissionAndUpdate(Permission permission) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_permission");
		BasicDBObject query = new BasicDBObject();
		query.append("_id", permission.getId());
		
		BasicDBObject update = new BasicDBObject();
		update.append("permission", permission.getPermission());
		update.append("description", permission.getDescription());
		update.append("available", permission.getAvailable());
		update.append("operator", "admin");
		update.append("update_time", new Date());
		
		DBObject doc = collection.findAndModify(query, new BasicDBObject("$set", update));	
		if(doc != null){
			return true;
		}
		return false;
	}

	@Override
	public Permission findByPermissionId(Long permissionid) {
		DBCollection collection = MongoUtil.getGGUserCollection("gg_permission");
		BasicDBObject query = new BasicDBObject();
		query.append("status", 1);
		query.append("_id", permissionid);
		
		BasicDBObject fields = new BasicDBObject();
		fields.append("permission", 1);
		fields.append("description", 1);
		fields.append("available", 1);
		DBObject p = collection.findOne(query, fields);
		
		Permission permission = new Permission();
		permission.setId(p.getLong("_id"));
		permission.setAvailable(p.getBoolean("available"));
		permission.setDescription(p.getString("description"));
		permission.setPermission(p.getString("permission"));
		
		return permission;
	}

}
