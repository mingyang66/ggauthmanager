
package ggauth.shiro.user.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;

import framework.yaomy.log.GGLogger;
import framework.yaomy.mongo.pool.DBCollection;
import framework.yaomy.mongo.pool.DBCursor;
import framework.yaomy.mongo.pool.GGDBCursor;
import framework.yaomy.mongo.pool.GGMongoOperator;
import framework.yaomy.mongo.pool.WriteResult;
import ggauth.shiro.user.common.PasswordHelper;
import ggauth.shiro.user.model.Role;
import ggauth.shiro.user.model.User;
import ggauth.shiro.user.service.RoleService;

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
	
	private DBCollection collection = GGMongoOperator.getGGBusinessDBCollection("gg_role");
	
	@Override
	public boolean createRole(Role role) {
		Document doc = GGMongoOperator.newId(collection);
		doc.append("role", role.getRole());
		doc.append("description", role.getDescription());
		doc.append("available", role.getAvailable());
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
		Document query = new Document();
		query.append("_id", role.getId());
		
		Document update = new Document();
		update.append("role", role.getRole());
		update.append("description", role.getDescription());
		update.append("available", role.getAvailable());
		update.append("operator", "admin");
		update.append("update_time", new Date());
		
		Document doc = collection.findOneAndUpdate(query, new Document("$set", update));	
		if(doc != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean findRoleAndDel(Long roleId) {
		Document query = new Document();
		query.append("_id", roleId);
		
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
	public long findRoleCount() {
		Document query = new Document();
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
		Document query = new Document();
		query.put("status", 1);
		
		Document sort = new Document();
    	sort.put("create_time", -1);
    	
    	DBCursor cursor = collection.find(query).sort(sort).limit(rows).skip((page-1)*rows);
    	List<Map<String, Object>> list = GGDBCursor.getListMap(cursor);
    	
		return list;
	}

	@Override
	public Role findByRoleId(Long roleId) {
		Document query = new Document();
		query.append("status", 1);
		query.append("_id", roleId);
		
		Document fields = new Document();
		fields.append("role", 1);
		fields.append("description", 1);
		fields.append("available", 1);
		Document p = collection.findOne(query, fields);
		
		Role role = new Role();
		role.setId(p.getLong("_id"));
		role.setAvailable(p.getBoolean("available"));
		role.setDescription(p.getString("description"));
		role.setRole(p.getString("role"));
		return role;
	}

}
