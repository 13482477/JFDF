package com.jhonelee.jfdf.web.validator.field;

public class FieldValidationResult {
	
	private Boolean valid;
	
	private String message;
	
	public FieldValidationResult() {
	}
	
	public FieldValidationResult(Boolean valid) {
		new FieldValidationResult(valid, null);
	}
	
	public FieldValidationResult(Boolean valid, String message) {
		this.valid = valid;
		this.message = message;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}