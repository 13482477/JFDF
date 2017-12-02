package com.jhonelee.jfdf.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	public FieldValidationResult validateField(@RequestParam("resourceCode") String resourceCode) {
		Long result = this.resourceRepository.countByResourceCode(resourceCode);
		return new FieldValidationResult(!(result > 0));
	}

	@RequestMapping(value = "/resources", method = RequestMethod.GET)
	@ResponseBody
	public Page<ResourceDTO> find(Pageable pageable) {
		Page<Resource> result = this.resourceRepository.findAll(pageable);

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

	@RequestMapping(value = "/resource", method = RequestMethod.POST)
	@ResponseBody
	public ResourceDTO create(@RequestBody @Validated Resource resource) {
		this.resourceService.saveOrUpdate(resource);

		return new Converter<Resource, ResourceDTO>() {
			@Override
			public ResourceDTO convert(Resource source) {
				ResourceDTO resourceDTO = new ResourceDTO();
				resourceDTO.setId(resource.getId());
				resourceDTO.setResourceName(resource.getResourceName());
				resourceDTO.setResourceCode(resource.getResourceCode());
				resourceDTO.setUrl(resource.getUrl());
				resourceDTO.setDescription(resource.getDescription());
				return resourceDTO;
			}
		}.convert(resource);
	}

}
