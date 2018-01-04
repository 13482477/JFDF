package com.jhonelee.jfdf.menu.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jhonelee.jfdf.menu.dto.MenuDto;
import com.jhonelee.jfdf.menu.entity.Menu;
import com.jhonelee.jfdf.valitation.utils.ValidatorUtils;

@Component
public class MenuDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MenuDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidatorUtils.validateEmpty(errors, target, "name", "menuCode");
		ValidatorUtils.validateUnique(errors, target, Menu.class, "id", "menuCode");
	}

}
