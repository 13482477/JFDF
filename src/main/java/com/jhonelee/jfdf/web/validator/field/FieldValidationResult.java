package com.jhonelee.jfdf.web.validator.field;

public class FieldValidationResult {
	
	private Boolean valid;
	
	public FieldValidationResult(Boolean valid) {
		this.valid = valid;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}