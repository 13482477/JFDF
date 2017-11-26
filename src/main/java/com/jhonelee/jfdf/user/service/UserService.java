package com.jhonelee.jfdf.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.user.entity.User;
import com.jhonelee.jfdf.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		return this.userRepository.save(user);
	}

	@Transactional
	public User loadUserByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}



}
