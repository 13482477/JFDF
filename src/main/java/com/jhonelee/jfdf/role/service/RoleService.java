package com.jhonelee.jfdf.role.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.authority.entity.Authority;
import com.jhonelee.jfdf.authority.repository.AuthorityRepository;
import com.jhonelee.jfdf.role.entity.Role;
import com.jhonelee.jfdf.role.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Transactional
	public void initRole() {
		List<Authority> authorities = this.authorityRepository.findAll();
		
		Role role = new Role();
		role.setRoleCode("administrator");
		role.setRoleName("系统管理员");
		role.setDescription("系统管理员");
		role.getAuthorities().addAll(authorities);
		this.roleRepository.save(role);
	}
	

}
