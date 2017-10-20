package com.jhonelee.security.role.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonelee.security.role.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	

}
