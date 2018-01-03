package com.jhonelee.jfdf.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.user.entity.User;
import com.jhonelee.jfdf.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		return this.userRepository.save(user);
	}

	public void saveFlush(User user) {
		this.userRepository.saveAndFlush(user);
	}

	@Transactional
	public User loadUserByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Transactional
	public void delete(Long id) {
		this.userRepository.delete(id);
	}

	public Page<User> find(String username, String email, String mobile, String nickname, Boolean active, Pageable pageable) {
		return this.userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotBlank(username)) {
				predicates.add(criteriaBuilder.equal(root.get("username"), username));
			}

			if (StringUtils.isNotBlank(email)) {
				predicates.add(criteriaBuilder.equal(root.get("email"), email));
			}

			if (StringUtils.isNotBlank(mobile)) {
				predicates.add(criteriaBuilder.equal(root.get("mobile"), mobile));
			}

			if (StringUtils.isNotBlank(nickname)) {
				predicates.add(criteriaBuilder.equal(root.get("nickname"), nickname));
			}
			if (active != null) {
				predicates.add(criteriaBuilder.equal(root.get("active"), active));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
		}, pageable);
	}

}
