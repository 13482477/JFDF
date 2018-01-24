package com.jhonelee.jfdf.web.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorResult {
	
	private Integer status;

	private String message;
	
	private List<String> errors = new ArrayList<String>();

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
