package com.jhonelee.security.authority.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.jhonelee.security.resource.entity.Resource;
import com.jhonelee.security.role.entity.Role;

/**
 * 权限模型
 */
@Entity
@Table(name = "sys_authority")
public class Authority implements Serializable {
	
	private static final long serialVersionUID = 4382105630022269739L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
     * 权限名称
     */
    private String authorityName;

    /**
     * 权限代码
     */
    private String authorityCode;

    /**
     * 权限描述
     */
    private String description;
    
    /**
     * 角色
     */
    @ManyToMany(mappedBy = "authorities")
    private List<Role> roles = new ArrayList<Role>();
    
    /**
     * 资源
     */
    @ManyToMany
    @JoinTable(
    			name = "sys_authority_resource",
    			joinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"),
    			inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id")
    		)
    private List<Resource> resources = new ArrayList<Resource>();
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
