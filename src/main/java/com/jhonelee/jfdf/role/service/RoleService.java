package com.jhonelee.jfdf.role.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.authority.entity.Authority;
import com.jhonelee.jfdf.menu.entity.Menu;
import com.jhonelee.jfdf.menu.repository.MenuRepository;
import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.repository.ResourceRepository;
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
	
	@Autowired
	private ResourceRepository resourceRepository;

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
			return parentId == null ? criteriaBuilder.isNull(root.get("parent")) : criteriaBuilder.equal(root.get("parent").get("id"), parentId);
		}, new Sort(Sort.Direction.ASC, "id"));
		
		if (CollectionUtils.isNotEmpty(menuList)) {
			buildElementFromMenu(result, menuList);
		} else {
			buildElementFromResource(parentId, result, roleId);
		}

		return result;
	}
	
	private Set<Resource> getSelectedResourceByRoleId(Long roleId) {
		Set<Resource> result = new HashSet<Resource>();
		
		Role role = roleRepository.findOne(roleId);
		for (Authority authority : role.getAuthorities()) {
			for (Resource resource : authority.getResources()) {
				result.add(resource);
			}
		}
		return result;
	}
	
	private void buildElementFromMenu(List<RoleMenuDto> result, List<Menu> menuList) {
		for (Menu menu : menuList) {
			result.add(ConvertUtils.convert(menu, input -> {
				RoleMenuDto roleMenuDto = new RoleMenuDto();
				roleMenuDto.setId(input.getId());
				roleMenuDto.setName(input.getName());
				roleMenuDto.setType(RoleMenuDto.Type.MENU);
				roleMenuDto.setIsParent(input.getChildren().size() > 0 || input.getResources().size() > 0);
				return roleMenuDto;
			}));
		}
	}

	private void buildElementFromResource(Long parentId, List<RoleMenuDto> result, Long roleId) {
		List<Resource> resourceList = this.menuRepository.findOne(parentId).getResources();
		Set<Resource> selectedResource = this.getSelectedResourceByRoleId(roleId);
		for (Resource resource : resourceList) {
			result.add(ConvertUtils.convert(resource, input -> {
				RoleMenuDto roleMenuDto = new RoleMenuDto();
				roleMenuDto.setId(input.getId());
				roleMenuDto.setName(input.getResourceName());
				roleMenuDto.setType(RoleMenuDto.Type.RESOURCE);
				roleMenuDto.setIsParent(Boolean.FALSE);
				roleMenuDto.setChecked(selectedResource.contains(input));
				return roleMenuDto;
			}));
		}
	}

	@Transactional
	public void authorize(Long roleId, List<Long> resourceIds) {
		Role role = this.roleRepository.findOne(roleId);
		
		List<Resource> selectedResouorces = this.resourceRepository.findAll(resourceIds);
		for (Resource resource : selectedResouorces) {
			for (Authority authority : resource.getAuthorities() ) {
				role.getAuthorities().add(authority);
			}
		}
		
		this.roleRepository.saveAndFlush(role);
	}


}
