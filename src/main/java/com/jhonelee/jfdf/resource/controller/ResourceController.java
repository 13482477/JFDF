package com.jhonelee.jfdf.resource.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.resource.dto.CreateResourceDto;
import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.service.ResourceService;
import com.jhonelee.jfdf.web.WebResult;
import com.jhonelee.jfdf.web.utils.BindingResultUtil;

@Controller
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/resource/page", method = RequestMethod.GET)
	public String resource() {
		return "resource/resource";
	}

	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	@ResponseBody
	public List<ResourceNode> loadResourceNodes(@RequestParam(name = "id", required = false)Long id) {
		List<Resource> resources = this.resourceService.loadResourcesByParentId(id);

		List<ResourceNode> result = new ArrayList<ResourceController.ResourceNode>();

		CollectionUtils.collect(resources, input -> new ResourceNode(input.getId(), input.getResourceName(), input.getChildren().size() > 0), result);

		return result;
	}
	
	@RequestMapping(value = "/resource", method = RequestMethod.POST)
	public WebResult<?> create(@RequestBody @Validated CreateResourceDto createResourceDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return WebResult.builder().returnCode("500").returnMessage(BindingResultUtil.parseErrorMessage(bindingResult)).build();
		}
		
		return null;
	}

	public static class ResourceNode {
		private Long id;

		private String name;

		private Boolean isParent;
		
		public ResourceNode() {
			
		}

		public ResourceNode(Long id, String name, Boolean isParent) {
			super();
			this.id = id;
			this.name = name;
			this.isParent = isParent;
		}

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

	}

}
