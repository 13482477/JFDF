package com.jhonelee.jfdf.menu.dto;

public class SelectedResourceDto {
	
	private Long resourceId;
	
	private String resourceName;
	
	private Boolean selected = Boolean.FALSE;

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

}
