package com.jhonelee.security.user.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhonelee.security.user.dao.UserDao;
import com.jhonelee.security.user.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public User createUser(User user) {
		return this.userDao.save(user);
	}
	
	public User loadUserByUsername(String username) {
		return this.userDao.findByUsername(username);
	}
	
	public Page<User> findByPage(User user, Pageable pageable) {
		
		Example<User> example = Example.of(user);
		
		
		
		
		this.userDao.findAll(new Specification<User>() {
			
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return null;
			}
		}, pageable);
		
		
		return null;
	}
	

}
