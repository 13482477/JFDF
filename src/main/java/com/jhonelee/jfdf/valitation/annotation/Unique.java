package com.jhonelee.jfdf.valitation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.jhonelee.jfdf.valitation.validator.UniqueValidator;

@Constraint(validatedBy = UniqueValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
	
	String message() default "该字段已存在";
	
	Class<?> target();
	
	String field();
	

}
