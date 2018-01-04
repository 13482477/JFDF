package com.jhonelee.jfdf.user.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.jhonelee.jfdf.role.repository.RoleRepository;
import com.jhonelee.jfdf.user.dto.UserDto;
import com.jhonelee.jfdf.user.dto.UserRoleDto;
import com.jhonelee.jfdf.user.entity.User;
import com.jhonelee.jfdf.user.repository.UserRepository;
import com.jhonelee.jfdf.user.service.UserService;
import com.jhonelee.jfdf.user.validator.UserDtoValidator;
import com.jhonelee.jfdf.valitation.utils.ValidatorUtils;
import com.jhonelee.jfdf.web.convert.ConvertUtils;
import com.jhonelee.jfdf.web.validator.field.FieldValidationResult;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserDtoValidator userValidator;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Initialize role validation.
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.userValidator);
	}

	/**
	 * Render role management page. Return the internal path of the page.
	 * 
	 * @return {@link String}
	 */
	@RequestMapping(value = "/user/page", method = RequestMethod.GET)
	public String renderPage() {
		return "user/user";
	}

	/**
	 * This service can validate field error. It return thie
	 * {@link FieldValidationResult}, include two attribute to descript processing
	 * statement.
	 * 
	 * @param roleDto
	 * @return {@link FieldValidationResult}
	 */
	@RequestMapping(value = "/user/validation", method = RequestMethod.GET)
	@ResponseBody
	public FieldValidationResult validateField(UserDto roleDto) {
		FieldValidationResult result = new FieldValidationResult();
		result.setValid(ValidatorUtils.evaluateUnique(roleDto, new String[] { "username" }, User.class, "id"));
		return result;
	}

	/**
	 * This service can save role data into the database.
	 * 
	 * @param roleDto
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public UserDto create(@RequestBody @Validated UserDto userDto) {
		User user = userDto.createEntity();

		user.setPassword(this.passwordEncoder.encode("123456"));
		user.setActive(true);
		user.getRoles().addAll(this.roleRepository.findAll(CollectionUtils.collect(userDto.getRoles(), input -> input.getId())));

		this.userService.saveAndFlush(user);
		return ConvertUtils.convert(user, input -> {
			UserDto result = new UserDto();
			BeanUtils.copyProperties(input, result, "roles");

			result.getRoles().addAll(CollectionUtils.collect(input.getRoles(), inputRole -> {
				UserRoleDto userRoleDto = new UserRoleDto();
				userRoleDto.setId(inputRole.getId());
				userRoleDto.setRoleName(inputRole.getRoleName());
				return userRoleDto;
			}));

			return result;
		});
	}

	/**
	 * Load user detail data by userId, the paramater is path variable.It can be
	 * evaluate automatically.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public UserDto read(@PathVariable Long id) {
		User user = this.userRepository.findOne(id);
		return ConvertUtils.convert(user, input -> {
			UserDto result = new UserDto();
			BeanUtils.copyProperties(input, result, "roles");

			result.getRoles().addAll(CollectionUtils.collect(input.getRoles(), role -> {
				UserRoleDto userRoleDto = new UserRoleDto();
				userRoleDto.setId(role.getId());
				userRoleDto.setRoleName(role.getRoleName());
				return userRoleDto;
			}));

			return result;
		});
	}

	/**
	 * Update user data.
	 * 
	 * @param id
	 * @param target
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public UserDto update(@PathVariable("id") Long id, @RequestBody @Validated UserDto target) {
		User user = this.userRepository.findOne(id);
		BeanUtils.copyProperties(target, user, "roles");

		user.setActive(true);
		user.getRoles().clear();
		user.getRoles().addAll(this.roleRepository.findAll(CollectionUtils.collect(target.getRoles(), input -> input.getId())));

		this.userService.saveAndFlush(user);
		return ConvertUtils.convert(user, input -> {
			UserDto result = new UserDto();
			BeanUtils.copyProperties(input, result, "roles");

			return result;
		});
	}

	/**
	 * This service can delete role data from databse by the path variable.
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		this.userService.delete(id);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public Page<UserDto> find(@RequestParam(required = false) String username, @RequestParam(required = false) String email, @RequestParam(required = false) String mobile,
			@RequestParam(required = false) String nickname, @RequestParam(required = false) Boolean active, Pageable pageable) {
		Page<User> result = this.userService.find(username, email, mobile, nickname, active, pageable);
		return result.map(input -> {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(input, userDto, "roles");
			userDto.getRoles().addAll(CollectionUtils.collect(input.getRoles(), role -> {
				UserRoleDto userRoleDto = new UserRoleDto();
				userRoleDto.setId(role.getId());
				userRoleDto.setRoleName(role.getRoleName());
				return userRoleDto;
			}));
			return userDto;
		});
	}

}
