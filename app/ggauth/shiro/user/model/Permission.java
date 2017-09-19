
package ggauth.shiro.user.model;

import java.io.Serializable;

/**
 * @Description:权限实体
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月18日 下午3:55:40
 */
public class Permission implements Serializable{

	private Long id;//编号
	private String permission;////权限标识 程序中判断使用,如"user:create"
	private String description;//权限描述,UI界面显示使用
	private Boolean available = Boolean.FALSE;//是否可用,如果不可用将不会添加给用户
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	@Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
