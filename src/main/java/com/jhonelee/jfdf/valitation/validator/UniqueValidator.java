package com.jhonelee.jfdf.valitation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jhonelee.jfdf.valitation.annotation.Unique;

public class UniqueValidator implements ConstraintValidator<Unique, String> {
	
	private Class<?> target;
	
	private String fieldName;
	
	@Override
	public void initialize(Unique constraintAnnotation) {
		
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return false;
	}

}
