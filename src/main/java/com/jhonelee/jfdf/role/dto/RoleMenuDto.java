package com.jhonelee.jfdf.role.dto;

public class RoleMenuDto {
	
	private Long id;
	
	private String name;
	
	private Boolean checked = Boolean.FALSE;
	
	private Boolean isParent = Boolean.FALSE;
	
	private Type type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public static enum Type {
		MENU, RESOURCE;
	}
	
}
