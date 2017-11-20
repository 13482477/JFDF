package com.jhonelee.jfdf.security.metadatasource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.jhonelee.jfdf.resource.service.ResourceService;

public class DatabaseMetadataSource implements FilterInvocationSecurityMetadataSource {

	// 采用线程安全的
	private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = new ConcurrentHashMap<RequestMatcher, Collection<ConfigAttribute>>();


	@Autowired
	private ResourceService resourceService;

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
				return configAttribute;
			}
		}

		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
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
		this.resourceService.reflushRequestMap(this.requestMap);
	}
	
}
