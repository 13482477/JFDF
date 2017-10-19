package com.jhonelee.security.user.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.jhonelee.security.role.entity.Role;

/**
 * 用户
 */
@Entity
@Table(name = "sys_user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 6210408035778291012L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 手机
	 */
	private String mobile;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 是否活动
	 */
	private Boolean active;
	
	/**
	 * 角色
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "sys_user_role",
			joinColumns = @JoinColumn(columnDefinition = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(columnDefinition = "role_id", referencedColumnName = "id"))
	private List<Role> roles = new ArrayList<Role>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
