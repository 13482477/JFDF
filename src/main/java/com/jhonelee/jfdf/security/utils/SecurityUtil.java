package com.jhonelee.jfdf.security.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SecurityUtil {
	
	private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";
	
	public static HttpServletRequest getCurrentRequest() {
		return RequestContextHolder.getRequestAttributes() == null ? null : ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static HttpSession getCurrentSession() {
		return getCurrentRequest() == null ? null : getCurrentRequest().getSession();
	}
	
	public SecurityContext getCurrentSecurityContext() {
		return getCurrentSession() == null ? null : (SecurityContext) getCurrentSession().getAttribute(SPRING_SECURITY_CONTEXT);
	}
	
	
	

}
