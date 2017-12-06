package com.jhonelee.jfdf.web.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	@RequestMapping(value = "/404")
	public String pageNotFound() {
		return "error/404";
	}

	@RequestMapping(value = "/500")
	public String internalServerError() {
		return "error/500";
	}

}
