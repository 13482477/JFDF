package com.jhonelee.jfdf.role.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jhonelee.jfdf.role.dto.RoleDto;
import com.jhonelee.jfdf.role.entity.Role;
import com.jhonelee.jfdf.valitation.utils.ValidatorUtils;

@Component
public class RoleDtoValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RoleDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidatorUtils.validateEmpty(errors, target, new String[] {"roleName", "roleCode"});
		ValidatorUtils.validateUnique(errors, target, new String[] {"roleCode"}, Role.class, "id");
	}

}
