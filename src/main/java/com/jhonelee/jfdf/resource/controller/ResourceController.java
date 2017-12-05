package com.jhonelee.jfdf.resource.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.resource.dto.ResourceDTO;
import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.repository.ResourceRepository;
import com.jhonelee.jfdf.resource.service.ResourceService;
import com.jhonelee.jfdf.resource.validator.ResourceValidator;
import com.jhonelee.jfdf.web.convert.ConvertUtils;
import com.jhonelee.jfdf.web.validator.field.FieldValidationResult;

@Controller
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ResourceRepository resourceRepository;

	@Autowired
	private ResourceValidator resourceValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.resourceValidator);
	}

	@RequestMapping(value = "/resource/page", method = RequestMethod.GET)
	public String resource(Model model) {

		// LinkedHashMap<String, List<Icon>> fontAwesomeMap =
		// this.iconService.findFontAwesomeGroupBySubGroup();
		// List<Icon> glyphicons = this.iconService.findGlyphicons();
		//
		// model.addAttribute("fontAwesomeMap", fontAwesomeMap);
		// model.addAttribute("glyphicons", glyphicons);

		return "resource/resource";
	}

	@RequestMapping(value = "/resource/validation", method = RequestMethod.GET)
	@ResponseBody
	public FieldValidationResult validateField(@RequestParam(name = "id", required = false) Long id, @RequestParam(name = "resourceCode", required = false) String resourceCode,
			@RequestParam(name = "url", required = false) String url) {
		if (id != null) {
			Resource resource = this.resourceRepository.findOne(id);
			if (StringUtils.isNotBlank(resourceCode) && StringUtils.equals(resourceCode, resource.getResourceCode())) {
				return new FieldValidationResult(true);
			} else if (StringUtils.isNotBlank(url) && StringUtils.equals(url, resource.getUrl())) {
				return new FieldValidationResult(true);
			}
		}

		if (StringUtils.isNoneBlank(resourceCode)) {
			Long result = this.resourceRepository.countByResourceCode(resourceCode);
			return new FieldValidationResult(!(result > 0), result > 0 ? "资源代码已存在" : null);
		} else if (StringUtils.isNoneBlank(url)) {
			Long result = this.resourceRepository.countByUrl(url);
			return new FieldValidationResult(!(result > 0), result > 0 ? "url已存在" : null);
		}

		return new FieldValidationResult(true);
	}

	@RequestMapping(value = "/resource", method = RequestMethod.POST)
	@ResponseBody
	public ResourceDTO create(@RequestBody @Validated Resource resource) {
		this.resourceService.saveOrUpdate(resource);

		return ConvertUtils.convert(resource, input -> {
			ResourceDTO resourceDTO = new ResourceDTO();
			resourceDTO.setId(input.getId());
			resourceDTO.setResourceName(input.getResourceName());
			resourceDTO.setResourceCode(input.getResourceCode());
			resourceDTO.setUrl(input.getUrl());
			resourceDTO.setDescription(input.getDescription());
			return resourceDTO;
		});
	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResourceDTO read(@PathVariable("id") Long id) {
		Resource resource = this.resourceRepository.findOne(id);

		return resource == null ? null : ConvertUtils.convert(resource, input -> {
			ResourceDTO resourceDTO = new ResourceDTO();
			resourceDTO.setId(input.getId());
			resourceDTO.setResourceName(input.getResourceName());
			resourceDTO.setResourceCode(input.getResourceCode());
			resourceDTO.setHttpMethod(input.getHttpMethod());
			resourceDTO.setUrl(input.getUrl());
			resourceDTO.setDescription(input.getDescription());
			return resourceDTO;
		});
	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResourceDTO update(@PathVariable("id") Long id, @RequestBody Resource target) {
		Resource resource = this.resourceRepository.findOne(id);
		resource.setResourceName(target.getResourceName());
		resource.setResourceCode(target.getResourceCode());
		resource.setHttpMethod(target.getHttpMethod());
		resource.setUrl(target.getUrl());
		resource.setDescription(target.getDescription());
		this.resourceService.saveOrUpdate(resource);

		return ConvertUtils.convert(resource, input -> {
			ResourceDTO dto = new ResourceDTO();
			dto.setId(input.getId());
			dto.setResourceName(input.getResourceName());
			dto.setResourceCode(input.getResourceCode());
			dto.setUrl(input.getUrl());
			dto.setHttpMethod(input.getHttpMethod());
			dto.setDescription(input.getDescription());
			return dto;
		});
	}
	
	@RequestMapping(value = "/resource/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		this.resourceService.delete(id);
	}

	@RequestMapping(value = "/resources", method = RequestMethod.GET)
	@ResponseBody
	public Page<ResourceDTO> find(@RequestParam(name = "resourceName", required = false) String resourceName, @RequestParam(name = "resourceCode", required = false) String resourceCode,
			@RequestParam(name = "url", required = false) String url, @RequestParam(name = "httpMethod", required = false) String httpMethod, Pageable pageable) {

		Page<Resource> result = this.resourceService.find(resourceName, resourceCode, url, httpMethod, pageable);

		return result.map(input -> {
			ResourceDTO dto = new ResourceDTO();
			dto.setId(input.getId());
			dto.setResourceName(input.getResourceName());
			dto.setResourceCode(input.getResourceCode());
			dto.setUrl(input.getUrl());
			dto.setHttpMethod(input.getHttpMethod());
			dto.setDescription(input.getDescription());
			return dto;
		});
	}

}
