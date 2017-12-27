package com.jhonelee.jfdf.role.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.role.entity.Role;
import com.jhonelee.jfdf.role.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Page<Role> find(String roleName, String roleCode, Pageable pageable) {
		return this.roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotBlank(roleName)) {
				predicates.add(criteriaBuilder.equal(root.get("roleName"), roleName));
			}

			if (StringUtils.isNotBlank(roleCode)) {
				predicates.add(criteriaBuilder.equal(root.get("roleCode"), roleCode));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
		}, pageable);
	}
	
	@Transactional
	public void saveOrUpdate(Role role) {
		this.roleRepository.save(role);
	}

}
