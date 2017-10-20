package com.jhonelee.security.authority.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonelee.security.authority.repository.AuthorityRepository;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;

}
