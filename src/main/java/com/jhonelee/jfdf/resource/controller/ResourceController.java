package com.jhonelee.jfdf.resource.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.service.ResourceService;

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
	public List<ResourceNode> loadResourceNodes(@RequestParam(name = "id", required = false) Long parentId) {
		List<Resource> resources = this.resourceService.loadResourcesByParentId(parentId);

		List<ResourceNode> result = new ArrayList<ResourceController.ResourceNode>();

		CollectionUtils.collect(resources, 
				input -> ResourceNode.builder().id(input.getId()).name(input.getResourceName()).isParent(input.getChildren().size() > 0).build(), 
				result
				);

		return result;
	}
	
	


	public static class ResourceNode {
		private Long id;

		private String name;

		private Boolean isParent;
		
		public static Builder builder() {
			return new Builder();
		}

		public static class Builder {
			private Long id;
			private String name;
			private Boolean isParent;

			public Builder id(Long id) {
				this.id = id;
				return this;
			}

			public Builder name(String name) {
				this.name = name;
				return this;
			}

			public Builder isParent(Boolean isParent) {
				this.isParent = isParent;
				return this;
			}

			public ResourceNode build() {
				ResourceNode resourceNode = new ResourceNode();
				resourceNode.setId(this.id);
				resourceNode.setName(this.name);
				resourceNode.setIsParent(this.isParent);
				return resourceNode;
			}
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
