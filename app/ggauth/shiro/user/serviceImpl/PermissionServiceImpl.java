
package ggauth.shiro.user.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import framework.store.mongo.pool.DBCollection;
import framework.store.mongo.pool.DBCursor;
import framework.store.mongo.pool.GGDBCursor;
import framework.store.mongo.pool.GGMongoOperator;
import framework.store.mongo.pool.WriteResult;
import ggauth.shiro.user.model.Permission;
import ggauth.shiro.user.service.PermissionService;

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
	private DBCollection collection = GGMongoOperator.getGGBusinessDBCollection("gg_permission");

	@Override
	public void deletePermission(Long permissionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> findPermissions(Integer rows, Integer page) {
		Document query = new Document();
		query.put("status", 1);
		
		Document sort = new Document();
    	sort.put("create_time", -1);
    	
    	DBCursor cursor = collection.find(query).sort(sort).limit(rows).skip((page-1)*rows);
    	List<Map<String, Object>> list = GGDBCursor.getListMap(cursor);
    	
		return list;
	}

	@Override
	public long findPermissionCount() {
		Document query = new Document();
		query.put("status", 1);
		
		long count = collection.count(query);
		return count;
	}

	@Override
	public boolean createPermission(Permission permission) {
		Document doc = GGMongoOperator.newId(collection);
		doc.append("permission", permission.getPermission());
		doc.append("description", permission.getDescription());
		doc.append("available", permission.getAvailable());
		doc.append("status", 1);
		doc.append("creator", "admin");
		doc.append("operator", "admin");
		doc.append("create_time", new Date());
		doc.append("update_time", new Date());
		
		WriteResult result = collection.insertOne(doc);
		if(result.getModifiedCount() == 1) {
			return true;
		}
		return false;	}

	@Override
	public boolean findPermissionAndDel(Long permissionId) {
		Document query = new Document();
		query.append("_id", permissionId);
		
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

	@Override
	public boolean findPermissionAndUpdate(Permission permission) {
		Document query = new Document();
		query.append("_id", permission.getId());
		
		Document update = new Document();
		update.append("permission", permission.getPermission());
		update.append("description", permission.getDescription());
		update.append("available", permission.getAvailable());
		update.append("operator", "admin");
		update.append("update_time", new Date());
		
		Document doc = collection.findOneAndUpdate(query, new Document("$set", update));	
		if(doc != null){
			return true;
		}
		return false;
	}

	@Override
	public Permission findByPermissionId(Long permissionid) {
		Document query = new Document();
		query.append("status", 1);
		query.append("_id", permissionid);
		
		Document fields = new Document();
		fields.append("permission", 1);
		fields.append("description", 1);
		fields.append("available", 1);
		Document p = collection.findOne(query, fields);
		
		Permission permission = new Permission();
		permission.setId(p.getLong("_id"));
		permission.setAvailable(p.getBoolean("available"));
		permission.setDescription(p.getString("description"));
		permission.setPermission(p.getString("permission"));
		
		return permission;
	}

}
