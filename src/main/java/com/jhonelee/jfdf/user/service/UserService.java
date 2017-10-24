package com.jhonelee.jfdf.user.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.jhonelee.jfdf.role.entity.Role;
import com.jhonelee.jfdf.role.repository.RoleRepository;
import com.jhonelee.jfdf.user.entity.User;
import com.jhonelee.jfdf.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User createUser(User user) {
		return this.userDao.save(user);
	}

	@Transactional
	public User loadUserByUsername(String username) {
		return this.userDao.findByUsername(username);
	}

	@Transactional
	public void initAdminUser() {
		List<Role> roles = this.roleRepository.findAll();

		User user = new User();
		user.setUsername("admin");
		user.setPassword("password");
		user.setActive(true);
		user.setEmail("13482477@qq.com");
		user.setMobile("13482432953");
		user.setNickname("李志强");
		user.getRoles().addAll(roles);
		
		//密码加密
		user.setPassword(this.encodePasswordFromUser(user));
		this.userDao.save(user);
	}

	@SuppressWarnings("deprecation")
	private String encodePasswordFromUser(User user) {
		final PasswordEncoder passwordEncoderDelegate = this.passwordEncoder;

		org.springframework.security.authentication.encoding.PasswordEncoder passwordEncoder2 = new org.springframework.security.authentication.encoding.PasswordEncoder() {
			public String encodePassword(String rawPass, Object salt) {
				checkSalt(salt);
				return passwordEncoderDelegate.encode(rawPass);
			}

			public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
				checkSalt(salt);
				return passwordEncoderDelegate.matches(rawPass, encPass);
			}

			private void checkSalt(Object salt) {
				Assert.isNull(salt, "Salt value must be null when used with crypto module PasswordEncoder");
			}
		};

		return passwordEncoder2.encodePassword(user.getPassword(), null);
	}

}
