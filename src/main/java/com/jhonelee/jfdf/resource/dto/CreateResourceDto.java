package com.jhonelee.jfdf.resource.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.jhonelee.jfdf.resource.entity.Resource.ResourceType;

public class CreateResourceDto {
	private Long id;
	
	private Long parentId;
	
	@NotEmpty(message = "资源类型必填")
	private ResourceType resourceType;
	
	@NotEmpty(message = "资源名称必填")
	private String resourceName;
	
	@NotEmpty(message = "资源代码必填")
	private String resourceCode;
	
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
