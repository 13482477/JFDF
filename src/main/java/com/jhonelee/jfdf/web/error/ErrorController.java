package com.jhonelee.jfdf.web.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhonelee.jfdf.security.utils.SecurityUtil;

@Controller
public class ErrorController {
	
	@RequestMapping(value = "/404")
	public String pageNotFound() {
		return SecurityUtil.getCurrentUser() == null ? "redirect:/login" : "error/404";
	}

	@RequestMapping(value = "/500")
	public String internalServerError() {
		return SecurityUtil.getCurrentUser() == null ? "redirect:/login" : "error/500";
	}

}
