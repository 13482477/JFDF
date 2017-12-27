package com.jhonelee.jfdf.resource.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.authority.entity.Authority;
import com.jhonelee.jfdf.menu.entity.Menu;
import com.jhonelee.jfdf.menu.repository.MenuRepository;
import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.repository.ResourceRepository;
import com.jhonelee.jfdf.role.entity.Role;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepository resourceRepository;
	
	@Autowired
	private MenuRepository menuRepository;

	@Transactional
	public void saveOrUpdate(Resource resource) {
		this.resourceRepository.save(resource);
	}
	
	@Transactional
	public void createResourceAndAuthority(Resource resource) {
		Authority authority = new Authority();
		authority.setAuthorityCode("role_" + resource.getResourceCode());
		authority.setAuthorityName(resource.getResourceName());
		authority.setDescription(resource.getDescription());
		authority.getResources().add(resource);
		resource.getAuthorities().add(authority);
		
		this.resourceRepository.save(resource);
	}
	
	@Transactional
	public void updateResourceAndAuthority(Resource resource) {
		Authority authority = resource.getAuthorities().get(0);
		authority.setAuthorityCode("role_" + resource.getResourceCode());
		authority.setAuthorityName(resource.getResourceName());
		authority.setDescription(resource.getDescription());

		this.resourceRepository.save(resource);
	}
	
	

	@Transactional
	public void delete(Long id) {
		Resource resource = this.resourceRepository.findOne(id);
		
		for (Authority authority : resource.getAuthorities()) {
			for (Role role : authority.getRoles()) {
				role.getAuthorities().remove(authority);
			}
		}
		
		for (Menu menu : resource.getMenus()) {
			menu.getResources().remove(resource);
		}
		
		this.resourceRepository.delete(resource);
	}

	public List<Resource> findAll() {
		return this.resourceRepository.findAll();
	}

	public Resource getById(Long id) {
		return this.resourceRepository.getOne(id);
	}

	@Transactional
	public void reflushRequestMap(Map<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
		List<Resource> resources = new ArrayList<Resource>();
		List<Menu> menus = this.menuRepository.findAll();
		
		for (Menu menu : menus) {
			for (Resource resource : menu.getResources()) {
				if (!resources.contains(resource)) {
					resources.add(resource);
				}
			}
		}
		
		requestMap.clear();
		for (Resource resource : resources) {
			addResource(requestMap, resource);
		}
	}

	private void addResource(Map<RequestMatcher, Collection<ConfigAttribute>> requestMap, Resource resource) {
		if (StringUtils.isNotEmpty(resource.getUrl())) {
			RequestMatcher requestMatcher = StringUtils.isEmpty(resource.getHttpMethod()) ? new AntPathRequestMatcher(resource.getUrl())
					: new AntPathRequestMatcher(resource.getUrl(), resource.getHttpMethod());
			String[] authorityCodes = this.getAuthorityCodes(resource.getAuthorities());
			Collection<ConfigAttribute> configAttributes = SecurityConfig.createList(authorityCodes);
			requestMap.put(requestMatcher, configAttributes);
		}
	}

	private String[] getAuthorityCodes(List<Authority> allAuthorities) {
		List<String> outputCollection = new ArrayList<String>();
		CollectionUtils.collect(allAuthorities, input -> input.getAuthorityCode(), outputCollection);
		return outputCollection.toArray(new String[] {});
	}

	public List<Resource> loadResourcesByParentId(Long parentId) {
		return this.resourceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			return parentId == null ? criteriaBuilder.isNull(root.get("parent")) : criteriaBuilder.equal(root.get("parent").get("id"), parentId);
		});
	}

	public Page<Resource> find(String resourceName, String resourceCode, String url, String httpMethod, Pageable pageable) {
		return this.resourceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotBlank(resourceName)) {
				predicates.add(criteriaBuilder.equal(root.get("resourceName"), resourceName));
			}

			if (StringUtils.isNotBlank(resourceCode)) {
				predicates.add(criteriaBuilder.equal(root.get("resourceCode"), resourceCode));
			}

			if (StringUtils.isNotBlank(url)) {
				predicates.add(criteriaBuilder.equal(root.get("url"), url));
			}

			if (StringUtils.isNotBlank(httpMethod)) {
				predicates.add(criteriaBuilder.equal(root.get("httpMethod"), httpMethod));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
		}, pageable);
	}

}
