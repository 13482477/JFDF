package com.jhonelee.security.metadatasource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.jhonelee.security.authority.entity.Authority;
import com.jhonelee.security.resource.entity.Resource;
import com.jhonelee.security.resource.repository.ResourceRepository;

public class DatabaseMetadataSource implements FilterInvocationSecurityMetadataSource {

	// 采用线程安全的
	private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = new ConcurrentHashMap<RequestMatcher, Collection<ConfigAttribute>>();

	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		Set<ConfigAttribute> configAttribute = new HashSet<ConfigAttribute>();
		if (requestMap.isEmpty()) {
			return configAttribute;
		}

		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		for (Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			RequestMatcher requestMatcher = entry.getKey();
			if (requestMatcher.matches(request)) {
				configAttribute.addAll(entry.getValue());
				break;
			}
		}

		return configAttribute;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		if (requestMap.isEmpty()) {
			return null;
		}
		Collection<ConfigAttribute> result = new ArrayList<ConfigAttribute>();

		for (Collection<ConfigAttribute> ca : requestMap.values()) {
			result.addAll(ca);
		}
		return result;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public void refreshRequestMap() {
		List<Resource> resources = this.resourceRepository.findAll();
		this.requestMap.clear();
		for (Resource resource : resources) {
			addResource(resource);
		}
	}

	public void addResource(Resource resource) {
		if (StringUtils.isNotEmpty(resource.getUrl())) {
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resource.getUrl());
			String[] authorityCodes = this.getAuthorityCodes(resource.getAuthorities());
			Collection<ConfigAttribute> configAttributes = SecurityConfig.createList(authorityCodes);
			requestMap.put(requestMatcher, configAttributes);
		}
	}

	public void removeResource(Resource resource) {
		if (StringUtils.isNotEmpty(resource.getUrl())) {
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resource.getUrl());
			requestMap.remove(requestMatcher);
		}
	}

	private String[] getAuthorityCodes(List<Authority> allAuthorities) {
		List<String> outputCollection = new ArrayList<String>();
		CollectionUtils.collect(allAuthorities, new Transformer<Authority, String>() {
			public String transform(Authority input) {
				return input.getAuthorityCode();
			}
		}, outputCollection);
		return outputCollection.toArray(new String[] {});
	}
}
