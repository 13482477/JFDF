package com.jhonelee.jfdf.resource.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.authority.entity.Authority;
import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.repository.ResourceRepository;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepository resourceRepository;

	@Transactional
	public void saveOrUpdate(Resource resource) {
		this.resourceRepository.save(resource);
	}

	public List<Resource> findAll() {
		return this.resourceRepository.findAll();
	}
	
	public Resource getById(Long id) {
		return this.resourceRepository.getOne(id);
	}


	@Transactional
	public void reflushRequestMap(Map<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
		List<Resource> resources = this.resourceRepository.findAll();
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
		return this.resourceRepository.findAll(new Specification<Resource>() {
			@Override
			public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return parentId == null ? cb.isNull(root.get("parent")) : cb.equal(root.get("parent").get("id"), parentId);
			}
		});
	}

}
