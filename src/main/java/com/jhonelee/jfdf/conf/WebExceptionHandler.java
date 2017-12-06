package com.jhonelee.jfdf.conf;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

//@ControllerAdvice
public class WebExceptionHandler extends DefaultHandlerExceptionResolver {
	
	@ExceptionHandler(value = NoHandlerFoundException.class)
	@Override
	protected ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		return super.handleNoHandlerFoundException(ex, request, response, handler);
	}

}
