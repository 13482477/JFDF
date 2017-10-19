package com.jhonelee.security.resource.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 资源模型
 */
@Entity
@Table(name = "sys_resource")
public class Resource implements Serializable {

	private static final long serialVersionUID = -1240798999881387816L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 资源类型
	 */
	@Column(name = "resource_type", length = 10)
	@Enumerated(EnumType.STRING)
	private ResourceType resourceType;
	
	/**
	 * 资源名称
	 */
	private String resourceName;
	
	/**
	 * 资源代码
	 */
	private String resourceCode;

	/**
     * 资源url
     */
    private String url;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 父级资源
     */
    private Resource parent;

    /**
     * 父级资源id
     */
    private Long parentId;

	/**
	 * 排序序列
	 */
	private Integer sequence;

	public static enum ResourceType {
		SYSTEM,
		MENU,
		RESOURCE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}
