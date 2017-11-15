package com.jhonelee.jfdf.resource.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.icon.entity.Icon;
import com.jhonelee.jfdf.icon.service.IconService;
import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.service.ResourceService;

@Controller
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private IconService iconService;

	@RequestMapping(value = "/resource/page", method = RequestMethod.GET)
	public String resource(Model model) {
		
		LinkedHashMap<String, List<Icon>> fontAwesomeMap = this.iconService.findFontAwesomeGroupBySubGroup();
		List<Icon> glyphicons = this.iconService.findGlyphicons();
		
		model.addAttribute("fontAwesomeMap", fontAwesomeMap);
		model.addAttribute("glyphicons", glyphicons);
		
		return "resource/resource";
	}

	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	@ResponseBody
	public List<ResourceNode> loadResourceNodes(@RequestParam(name = "id", required = false) Long parentId) {
		List<Resource> resources = this.resourceService.loadResourcesByParentId(parentId);

		List<ResourceNode> result = new ArrayList<ResourceController.ResourceNode>();

		CollectionUtils.collect(resources, 
				input -> {
					ResourceNode resourceNode = new ResourceNode();
					resourceNode.setId(input.getId());
					resourceNode.setName(input.getResourceName());
					resourceNode.setIsParent(input.getChildren().size() > 0);
					return resourceNode;
				}, 
				result
				);

		return result;
	}
	
	


	public static class ResourceNode {
		private Long id;

		private String name;

		private Boolean isParent;
		
		private String iconSkin;
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Boolean getIsParent() {
			return isParent;
		}

		public void setIsParent(Boolean isParent) {
			this.isParent = isParent;
		}

		public String getIconSkin() {
			return iconSkin;
		}

		public void setIconSkin(String iconSkin) {
			this.iconSkin = iconSkin;
		}

	}

}
