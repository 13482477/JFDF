package com.jhonelee.jfdf.user.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jhonelee.jfdf.user.dto.UserDto;
import com.jhonelee.jfdf.user.entity.User;
import com.jhonelee.jfdf.valitation.utils.ValidatorUtils;

@Component
public class UserDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidatorUtils.validateEmpty(errors, target, "username", "email", "mobile", "nickname");
		ValidatorUtils.validateUnique(errors, target, User.class, "id", "username");
	}

}
