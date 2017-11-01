package com.jhonelee.jfdf.resource.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.OrderBy;

import com.jhonelee.jfdf.authority.entity.Authority;

/**
 * 资源模型
 */
@Entity
@Table(name = "sys_resource")
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.NONE)
@XmlType(name = "resource")
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
	@XmlAttribute(name = "type")
	private ResourceType resourceType;
	
	/**
	 * 资源名称
	 */
	@XmlAttribute(name = "name")
	private String resourceName;
	
	/**
	 * 资源代码
	 */
	@XmlAttribute(name = "code")
	private String resourceCode;

	/**
     * 资源url
     */
	@XmlAttribute(name = "url")
    private String url;
	
	/**
	 * 请求方法
	 */
	@XmlAttribute(name = "httpMethod")
	private String httpMethod;
	
	/**
	 * 图标类型
	 */
	@XmlAttribute(name = "iconType")
	@Column(name = "resource_icon_type", length = 10)
	@Enumerated(EnumType.STRING)
	private ResourceIconType resourceIconType;
	
	/**
	 * 图标路径
	 */
	@XmlAttribute(name = "iconPath")
	private String iconPath;
	
    /**
     * 资源描述
     */
    private String description;

    /**
     * 父级资源
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Resource parent;

	/**
	 * 排序序列
	 */
	private Integer sequence;
	
	/**
	 * 权限
	 */
	@ManyToMany(mappedBy = "resources", cascade = CascadeType.ALL)
	private List<Authority> authorities = new ArrayList<Authority>();
	
	/**
	 * 子节点
	 */
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	@XmlElement(name = "resource")
	private List<Resource> children = new ArrayList<Resource>();

	@XmlEnum
	public static enum ResourceType {
		SYSTEM,
		MENU,
		RESOURCE,
		BUTTON;
	}
	
	@XmlEnum
	public static enum ResourceIconType {
		ICON,
		IMG;
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
	
	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public ResourceIconType getResourceIconType() {
		return resourceIconType;
	}

	public void setResourceIconType(ResourceIconType resourceIconType) {
		this.resourceIconType = resourceIconType;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

}
