package com.jhonelee.jfdf.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestfulExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> errorAttributes = this.getErrorAttribute(ex.getBindingResult(), request, status);
		
		return this.handleExceptionInternal(ex, errorAttributes, headers, status, request);
	}

	private Map<String, Object> getErrorAttribute(BindingResult bindingResult, RequestAttributes requestAttributes, HttpStatus status) {
		Map<String, Object> errorAttributes = new HashMap<String, Object>();

		this.addStatus(errorAttributes, status, "错误的请求参数");
		this.addErrors(errorAttributes, bindingResult);
		
		return errorAttributes;
	}
	
	@SuppressWarnings("unchecked")
	private void addErrors(Map<String, Object> errorAttributes, BindingResult bindingResult) {
		if (!CollectionUtils.isEmpty(bindingResult.getAllErrors())) {
			errorAttributes.put("errors", new ArrayList<String>());
		}
		
		for (ObjectError objectError : bindingResult.getAllErrors()) {
			((List<String>)errorAttributes.get("errors")).add(objectError.getDefaultMessage());
		}
	}

	private void addStatus(Map<String, Object> errorAttributes, HttpStatus status, String message) {
		errorAttributes.put("status", status.value());
		errorAttributes.put("message", message);
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleExceptio(Exception exception, WebRequest webRequest) {
		HttpHeaders httpHeaders = new HttpHeaders();
		
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		
		Map<String, Object> body = new HashMap<String, Object>();
		this.addStatus(body, httpStatus, "服务器内部错误");
		
		return this.handleExceptionInternal(exception, body, httpHeaders, httpStatus, webRequest);
	}
	
}
