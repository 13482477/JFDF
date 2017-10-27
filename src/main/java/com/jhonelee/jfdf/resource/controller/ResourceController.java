package com.jhonelee.jfdf.resource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ResourceController {
	
	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	public String resource() {
		return "resource/resource";
	}

}
