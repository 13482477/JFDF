package com.jhonelee.jfdf.resource.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.icon.entity.Icon;
import com.jhonelee.jfdf.icon.service.IconService;
import com.jhonelee.jfdf.resource.dto.CreateResourceDTO;
import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.entity.Resource.ResourceType;
import com.jhonelee.jfdf.resource.service.ResourceService;

@Controller
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private IconService iconService;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		
	}

	@RequestMapping(value = "/resource/page", method = RequestMethod.GET)
	public String resource(Model model) {
		
		LinkedHashMap<String, List<Icon>> fontAwesomeMap = this.iconService.findFontAwesomeGroupBySubGroup();
		List<Icon> glyphicons = this.iconService.findGlyphicons();
		
		model.addAttribute("fontAwesomeMap", fontAwesomeMap);
		model.addAttribute("glyphicons", glyphicons);
		
		return "resource/resource";
	}

	@RequestMapping(value = "/resource/children", method = RequestMethod.GET)
	@ResponseBody
	public List<ResourceNode> loadChildren(@RequestParam(name = "parentId", required = false) Long parentId) {
		List<Resource> resources = this.resourceService.loadResourcesByParentId(parentId);

		List<ResourceNode> result = new ArrayList<ResourceController.ResourceNode>();

		CollectionUtils.collect(resources, 
				input -> {
					ResourceNode resourceNode = new ResourceNode();
					resourceNode.setId(input.getId());
					resourceNode.setName(input.getResourceName());
					resourceNode.setIsParent(input.getChildren().size() > 0);
					resourceNode.setResourceType(input.getResourceType());
					resourceNode.setUrl(input.getUrl());
					resourceNode.setHttpMethod(input.getHttpMethod());
					resourceNode.setCode(input.getResourceCode());
					resourceNode.setResourceIconType(input.getResourceIconType());
					resourceNode.setIconPath(input.getIconPath());
					resourceNode.setParentId(input.getParent() == null ? null : input.getParent().getId());
					return resourceNode;
				}, 
				result
				);

		return result;
	}
	
	@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResourceNode getResourceNode(@PathVariable Long id) {
		Resource resource = this.resourceService.getById(id);
		
		ResourceNode resourceNode = new ResourceNode();
		resourceNode.setId(resource.getId());
		resourceNode.setName(resource.getResourceName());
		resourceNode.setIsParent(resource.getChildren().size() > 0);
		resourceNode.setResourceType(resource.getResourceType());
		resourceNode.setUrl(resource.getUrl());;
		resourceNode.setHttpMethod(resource.getHttpMethod());
		resourceNode.setCode(resource.getResourceCode());
		resourceNode.setResourceIconType(resource.getResourceIconType());
		resourceNode.setIconPath(resource.getIconPath());
		resourceNode.setParentId(resource.getParent() == null ? null : resource.getParent().getId());
		return resourceNode;
	}
	
	@RequestMapping(value = "/resource", method = RequestMethod.POST)
	@ResponseBody
	public Resource create(@RequestBody CreateResourceDTO resource) {
		
		return null;
	}
	

	public static class ResourceNode {
		private Long id;

		private String name;

		private Boolean isParent;
		
		private String iconSkin;
		
		private ResourceType resourceType;
		
		private String url;
		
		private String httpMethod;
		
		private String code;
		
		private Resource.ResourceIconType resourceIconType;
		
		private String iconPath;
		
		private Long parentId;
		
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

		public ResourceType getResourceType() {
			return resourceType;
		}

		public void setResourceType(ResourceType resourceType) {
			this.resourceType = resourceType;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getHttpMethod() {
			return httpMethod;
		}

		public void setHttpMethod(String httpMethod) {
			this.httpMethod = httpMethod;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Resource.ResourceIconType getResourceIconType() {
			return resourceIconType;
		}

		public void setResourceIconType(Resource.ResourceIconType resourceIconType) {
			this.resourceIconType = resourceIconType;
		}

		public String getIconPath() {
			return iconPath;
		}

		public void setIconPath(String iconPath) {
			this.iconPath = iconPath;
		}

		public Long getParentId() {
			return parentId;
		}

		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}

	}

}
