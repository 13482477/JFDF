package com.jhonelee.jfdf.role.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.menu.entity.Menu;
import com.jhonelee.jfdf.menu.repository.MenuRepository;
import com.jhonelee.jfdf.role.dto.RoleMenuDto;
import com.jhonelee.jfdf.role.entity.Role;
import com.jhonelee.jfdf.role.repository.RoleRepository;
import com.jhonelee.jfdf.web.convert.ConvertUtils;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MenuRepository menuRepository;

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

	public List<RoleMenuDto> loadRoleMenuElements(Long parentId, Long roleId) {
		List<RoleMenuDto> result = new ArrayList<RoleMenuDto>();
		
		List<Menu> menuList = this.menuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			return parentId == 0 ? criteriaBuilder.isNull(root.get("parent")) : criteriaBuilder.equal(root.get("parent").get("id"), parentId);
		}, new Sort(Sort.Direction.ASC, "id"));
		
		if (CollectionUtils.isNotEmpty(menuList)) {
			for (Menu menu : menuList) {
				result.add(ConvertUtils.convert(menu, input -> {
					RoleMenuDto roleMenuDto = new RoleMenuDto();
					
					return roleMenuDto;
				}));
				
			}
		}
		
		

		return null;
	}

}
