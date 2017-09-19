
package ggauth.shiro.user.model;

import java.io.Serializable;

/**
 * @Description:角色实体类
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月18日 下午3:52:02
 */
public class Role implements Serializable{

	private Long id;//编号
	private String role;///角色标识 程序中判断使用,如"admin"
	private String description;//角色描述,UI界面显示使用
	private Boolean available = Boolean.FALSE;//是否可用,如果不可用将不会添加给用户
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public String toString(){
		return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
	}
}
