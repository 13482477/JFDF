package com.jhonelee.jfdf.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.jhonelee.jfdf.user.entity.User;

public class UserDto {
	
	private Long id;

	private String username;

	private String password;

	private String email;

	private String mobile;

	private String nickname;

	private Boolean active;
	
	private List<UserRoleDto> roles = new ArrayList<UserRoleDto>();

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
	
	public List<UserRoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoleDto> roles) {
		this.roles = roles;
	}

	public User createEntity() {
		User user = new User();
		user.setId(this.id);
		user.setUsername(this.username);
		user.setPassword(this.password);
		user.setEmail(this.email);
		user.setMobile(this.mobile);
		user.setNickname(this.nickname);
		user.setActive(this.active);
		return user;
	}

}
