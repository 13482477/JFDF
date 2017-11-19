package com.jhonelee.jfdf.security.securityInterceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configurable
public class DelegateSecurityInterceptor extends FilterSecurityInterceptor {
	
	
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		super.doFilter(request, response, chain);
	}
	
	

}
