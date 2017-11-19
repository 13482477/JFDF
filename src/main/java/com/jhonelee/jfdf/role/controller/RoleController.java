package com.jhonelee.jfdf.role.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jhonelee.jfdf.role.service.RoleService;

@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/role/page", method = RequestMethod.GET)
	public String renderPage() {
		return "role/role";
	}

}
