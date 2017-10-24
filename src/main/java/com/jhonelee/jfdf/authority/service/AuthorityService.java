package com.jhonelee.jfdf.authority.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.authority.entity.Authority;
import com.jhonelee.jfdf.authority.repository.AuthorityRepository;
import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.repository.ResourceRepository;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Transactional
	public void initAuthority() {
		List<Resource> allResource = this.resourceRepository.findAll();
		allResource.forEach(resource -> {
			Authority authority = new Authority();
			authority.setAuthorityCode("role_" + resource.getResourceCode());
			authority.setAuthorityName(resource.getResourceName());
			authority.setDescription(resource.getDescription());
			authority.getResources().add(resource);
			this.authorityRepository.save(authority);
		});
	}

}
