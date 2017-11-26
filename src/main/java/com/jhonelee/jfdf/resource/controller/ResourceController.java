package com.jhonelee.jfdf.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jhonelee.jfdf.resource.service.ResourceService;

@Controller
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/resource/page", method = RequestMethod.GET)
	public String resource(Model model) {
		
//		LinkedHashMap<String, List<Icon>> fontAwesomeMap = this.iconService.findFontAwesomeGroupBySubGroup();
//		List<Icon> glyphicons = this.iconService.findGlyphicons();
//		
//		model.addAttribute("fontAwesomeMap", fontAwesomeMap);
//		model.addAttribute("glyphicons", glyphicons);
		
		return "resource/resource";
	}
	

}
