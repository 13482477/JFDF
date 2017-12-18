package com.jhonelee.jfdf.security.exception.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

public class LoggerAccessDeniedHanlder extends AccessDeniedHandlerImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAccessDeniedHanlder.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		LOGGER.debug("Access Denied Exception!", accessDeniedException);
		super.handle(request, response, accessDeniedException);
	}

}
