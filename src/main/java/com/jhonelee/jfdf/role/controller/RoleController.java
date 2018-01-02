package com.jhonelee.jfdf.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.role.dto.RoleDto;
import com.jhonelee.jfdf.role.dto.RoleMenuDto;
import com.jhonelee.jfdf.role.entity.Role;
import com.jhonelee.jfdf.role.service.RoleService;
import com.jhonelee.jfdf.valitation.utils.ValidatorUtils;
import com.jhonelee.jfdf.web.convert.ConvertUtils;
import com.jhonelee.jfdf.web.validator.field.FieldValidationResult;

@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/role/page", method = RequestMethod.GET)
	public String renderPage() {
		return "role/role";
	}
	
	@RequestMapping(value = "/role/validation", method = RequestMethod.GET)
	@ResponseBody
	public FieldValidationResult validateField(RoleDto roleDto) {
		FieldValidationResult result = new FieldValidationResult();
		result.setValid(ValidatorUtils.evaluateUnique(roleDto, new String[] {"roleCode"}, Role.class, "id"));
		return result;
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	@ResponseBody
	public RoleDto create(@RequestBody @Validated RoleDto roleDto) {
		Role role = roleDto.createEntity();
		
		this.roleService.saveOrUpdate(role);

		return ConvertUtils.convert(role, input -> {
			RoleDto result = new RoleDto();
			result.setId(input.getId());
			result.setRoleCode(input.getRoleCode());
			result.setRoleName(input.getRoleName());
			result.setDescription(input.getDescription());
			return result;
		});
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	@ResponseBody
	public Page<RoleDto> find(@RequestParam(required = false) String roleName, @RequestParam(required = false) String roleCode, Pageable pageable) {
		Page<Role> result =  this.roleService.find(roleName, roleCode, pageable);
		return result.map(input -> {
			RoleDto roleDto = new RoleDto();
			roleDto.setId(input.getId());
			roleDto.setRoleCode(input.getRoleCode());
			roleDto.setRoleName(input.getRoleName());
			roleDto.setDescription(input.getDescription());
			return roleDto;
		});
	}
	
	@RequestMapping(value = "/role/menu/children", method = RequestMethod.GET)
	@ResponseBody
	public List<RoleMenuDto> findStatefulMenus(@RequestParam Long roleId, @RequestParam(required = false) Long parentId) {
		return this.roleService.loadRoleMenuElements(parentId, roleId);
	}
	
	@RequestMapping(value = "/role/{roleId}/resource", method = RequestMethod.PUT)
	@ResponseBody
	public void authorize(@PathVariable(name = "roleId")Long roleId, @RequestParam(name = "resourceIds[]")List<Long> resourceIds) {
		this.roleService.authorize(roleId, resourceIds);
	}

}
