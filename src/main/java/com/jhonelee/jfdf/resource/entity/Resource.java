package com.jhonelee.jfdf.resource.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.jhonelee.jfdf.authority.entity.Authority;
import com.jhonelee.jfdf.menu.entity.Menu;

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
	 * 请求方法
	 */
	private String httpMethod;
	
    /**
     * 资源描述
     */
    private String description;

	/**
	 * 权限
	 */
	@ManyToMany(mappedBy = "resources", cascade = CascadeType.ALL)
	@OrderBy("id asc")
	private List<Authority> authorities = new ArrayList<Authority>();
	
	/**
	 * 菜单
	 */
	@ManyToMany(mappedBy = "resources", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@OrderBy("id asc")
	private List<Menu> menus = new ArrayList<Menu>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}
