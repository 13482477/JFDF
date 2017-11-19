package com.jhonelee.jfdf.security.securityInterceptor;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;

import com.jhonelee.jfdf.security.metadatasource.DatabaseMetadataSource;

@Configurable
public class CustomSecurityInterceptor implements Filter {

	@Autowired
	private DatabaseMetadataSource databaseMetadataSource;
	
	@Autowired
	private AccessDecisionManager accessDecisionManager;

	public void invoke(FilterInvocation fi) throws IOException, ServletException {

		this.doInvocation(fi);

		fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
	}

	private void doInvocation(Object object) {
		Collection<ConfigAttribute> attributes = this.databaseMetadataSource.getAttributes(object);

		if (attributes == null) {
			return; // no further work post-invocation
		}

		Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();

		// Attempt authorization
		try {
			this.accessDecisionManager.decide(authenticated, object, attributes);
		} catch (AccessDeniedException accessDeniedException) {
			throw accessDeniedException;
		}

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
