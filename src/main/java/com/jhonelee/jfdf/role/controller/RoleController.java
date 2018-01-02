package com.jhonelee.jfdf.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.role.dto.RoleDto;
import com.jhonelee.jfdf.role.dto.RoleMenuDto;
import com.jhonelee.jfdf.role.entity.Role;
import com.jhonelee.jfdf.role.repository.RoleRepository;
import com.jhonelee.jfdf.role.service.RoleService;
import com.jhonelee.jfdf.role.validator.RoleDtoValidator;
import com.jhonelee.jfdf.valitation.utils.ValidatorUtils;
import com.jhonelee.jfdf.web.convert.ConvertUtils;
import com.jhonelee.jfdf.web.validator.field.FieldValidationResult;

/**
 * @author lizhiqiang
 */
@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleDtoValidator roleDtoValidator;
	
	/**
	 * Initialize role validation.
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.roleDtoValidator);
	}
	
	/**
	 * Render role management page.
	 * Return the internal path of the page.
	 * @return {@link String}
	 */
	@RequestMapping(value = "/role/page", method = RequestMethod.GET)
	public String renderPage() {
		return "role/role";
	}
	
	/**
	 * This service can validate field error.
	 * It return thie {@link FieldValidationResult}, include two attribute to descript processing statement.
	 * @param roleDto
	 * @return {@link FieldValidationResult}
	 */
	@RequestMapping(value = "/role/validation", method = RequestMethod.GET)
	@ResponseBody
	public FieldValidationResult validateField(RoleDto roleDto) {
		FieldValidationResult result = new FieldValidationResult();
		result.setValid(ValidatorUtils.evaluateUnique(roleDto, new String[] {"roleCode"}, Role.class, "id"));
		return result;
	}
	
	/**
	 * This service can save role data into the database.
	 * @param roleDto
	 * @return
	 */
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	@ResponseBody
	public RoleDto create(@RequestBody @Validated RoleDto roleDto) {
		Role role = roleDto.createEntity();
		this.roleService.save(role);
		return ConvertUtils.convert(role, input -> {
			RoleDto result = new RoleDto();
			result.setId(input.getId());
			result.setRoleCode(input.getRoleCode());
			result.setRoleName(input.getRoleName());
			result.setDescription(input.getDescription());
			return result;
		});
	}
	
	/**
	 * 加载当前角色明细数据
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/role/{roleId}", method = RequestMethod.GET)
	@ResponseBody
	public RoleDto read(@PathVariable Long roleId) {
		Role role = this.roleRepository.findOne(roleId);
		return ConvertUtils.convert(role, input -> {
			RoleDto result = new RoleDto();
			result.setId(input.getId());
			result.setRoleCode(input.getRoleCode());
			result.setRoleName(input.getRoleName());
			result.setDescription(input.getDescription());
			return result;
		});
	}
	
	/**
	 * 更新角色数据
	 * @param id
	 * @param target
	 * @return
	 */
	@RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public RoleDto update(@PathVariable("id") Long id, @RequestBody @Validated RoleDto target) {
		Role role = this.roleRepository.findOne(id);
		role.setRoleCode(target.getRoleCode());
		role.setRoleName(target.getRoleName());
		role.setDescription(target.getDescription());
		this.roleService.saveAndFlush(role);

		return ConvertUtils.convert(role, input -> {
			RoleDto result = new RoleDto();
			result.setId(input.getId());
			result.setRoleCode(input.getRoleCode());
			result.setRoleName(input.getRoleName());
			result.setDescription(input.getDescription());
			return result;
		});
	}
	
	/**
	 * This service can delete role data from databse by the path variable. 
	 * @param id
	 */
	@RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		this.roleService.delete(id);
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
	
	/**
	 * Ansyc to load tree data,the tree data contains menu nodes and resource nodes.
	 * @param roleId
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "/role/menu/children", method = RequestMethod.GET)
	@ResponseBody
	public List<RoleMenuDto> findStatefulMenus(@RequestParam Long roleId, @RequestParam(required = false) Long parentId) {
		return this.roleService.loadRoleMenuElements(parentId, roleId);
	}
	
	/**
	 * 角色授权
	 * 为选中的角色授权系统资源
	 * @param roleId 角色id
	 * @param resourceIds 被授权的资源id
	 */
	@RequestMapping(value = "/role/{roleId}/resource", method = RequestMethod.PUT)
	@ResponseBody
	public void authorize(@PathVariable(name = "roleId")Long roleId, @RequestParam(name = "resourceIds[]")List<Long> resourceIds) {
		this.roleService.authorize(roleId, resourceIds);
	}

}
