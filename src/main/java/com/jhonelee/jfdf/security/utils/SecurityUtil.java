package com.jhonelee.jfdf.security.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jhonelee.jfdf.security.userdetail.entity.CachedUserDetail;

public class SecurityUtil {
	
	private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

	public static HttpServletRequest getCurrentRequest() {
		return RequestContextHolder.getRequestAttributes() == null ? null : ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static HttpSession getCurrentSession() {
		return getCurrentRequest() == null ? null : getCurrentRequest().getSession();
	}

	public static SecurityContext getCurrentSecurityContext() {
		return getCurrentSession() == null ? null : (SecurityContext) getCurrentSession().getAttribute(SPRING_SECURITY_CONTEXT);
	}

	public static CachedUserDetail getCurrentUser() {
		
		if (getCurrentSecurityContext() == null || getCurrentSecurityContext().getAuthentication() == null) {
			return null;
		}
		
		Authentication auth = getCurrentSecurityContext().getAuthentication();
		if (auth.getPrincipal() instanceof CachedUserDetail) {
			return (CachedUserDetail) auth.getPrincipal();
		} else if (auth.getDetails() instanceof CachedUserDetail) {
			return (CachedUserDetail) auth.getDetails();
		} else {
			return null;
		}
	}
	

}
