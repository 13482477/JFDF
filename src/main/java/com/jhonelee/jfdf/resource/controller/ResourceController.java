package com.jhonelee.jfdf.resource.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.resource.dto.ResourceDTO;
import com.jhonelee.jfdf.resource.entity.Resource;
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
	
	@RequestMapping(value = "/resources", method = RequestMethod.GET)
	@ResponseBody
	public List<ResourceDTO> find() {
		List<ResourceDTO> result = new ArrayList<ResourceDTO>();
		List<Resource> resourceList = this.resourceService.findAll();
		
		CollectionUtils.collect(resourceList, input -> {
			ResourceDTO dto = new ResourceDTO();
			dto.setId(input.getId());
			dto.setResourceName(input.getResourceName());
			dto.setResourceCode(input.getResourceCode());
			dto.setUrl(input.getUrl());
			dto.setHttpMethod(input.getHttpMethod());
			dto.setDescription(input.getDescription());
			return dto;
		}, result);
		
		return result;
	}
	

}
