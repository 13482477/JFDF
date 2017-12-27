package com.jhonelee.jfdf.role.dto;

import com.jhonelee.jfdf.role.entity.Role;

public class RoleDto {
	
	public Long id;
	
	public String roleName;
	
	public String roleCode;
	
	public String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Role createEntity() {
		Role role = new Role();
		role.setId(this.id);
		role.setRoleCode(this.roleCode);
		role.setRoleName(this.roleName);
		role.setDescription(this.description);
		return role;
	}
	
}
