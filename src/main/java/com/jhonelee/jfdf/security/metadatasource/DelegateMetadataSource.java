package com.jhonelee.jfdf.security.metadatasource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

@Configurable
public class DelegateMetadataSource implements FilterInvocationSecurityMetadataSource {

	private DefaultFilterInvocationSecurityMetadataSource defaultFilterInvocationSecurityMetadataSource;

	@Autowired
	private DatabaseMetadataSource databaseMetadataSource;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		 Collection<ConfigAttribute> configAttributes = this.defaultFilterInvocationSecurityMetadataSource.getAttributes(object);
		 
		 Collection<ConfigAttribute> configAttributes2 = this.databaseMetadataSource.getAttributes(object);
		
		return this.mergeConfigAttribute(configAttributes, configAttributes2);
	}
	
	private Collection<ConfigAttribute> mergeConfigAttribute(Collection<ConfigAttribute> configAttributes, Collection<ConfigAttribute> configAttributes2) {
		if (configAttributes == null || configAttributes2 == null) {
			return null;
		}
		
		List<ConfigAttribute> result = new ArrayList<ConfigAttribute>();
		
		if (configAttributes != null) {
			result.addAll(configAttributes);
		}
		
		if (configAttributes2 != null) {
			result.addAll(configAttributes2);
		}
		
		return result;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		List<ConfigAttribute> result = new ArrayList<ConfigAttribute>();
		
		result.addAll(this.defaultFilterInvocationSecurityMetadataSource.getAllConfigAttributes());
		result.addAll(this.databaseMetadataSource.getAllConfigAttributes());
		
		return result;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	public DefaultFilterInvocationSecurityMetadataSource getDefaultFilterInvocationSecurityMetadataSource() {
		return defaultFilterInvocationSecurityMetadataSource;
	}

	public void setDefaultFilterInvocationSecurityMetadataSource(DefaultFilterInvocationSecurityMetadataSource defaultFilterInvocationSecurityMetadataSource) {
		this.defaultFilterInvocationSecurityMetadataSource = defaultFilterInvocationSecurityMetadataSource;
	}

	public DatabaseMetadataSource getDatabaseMetadataSource() {
		return databaseMetadataSource;
	}

	public void setDatabaseMetadataSource(DatabaseMetadataSource databaseMetadataSource) {
		this.databaseMetadataSource = databaseMetadataSource;
	}

}
