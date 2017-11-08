package com.jhonelee.jfdf.requestmapping.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhonelee.jfdf.requestmapping.service.RequestMappingService;
import com.jhonelee.jfdf.ui.element.select2.Select2;
import com.jhonelee.jfdf.ui.element.select2.Select2ListResult;

@RestController
public class RequestMappingController {

	@Autowired
	private RequestMappingService requestMappingService;

	@RequestMapping(value = "/requestMappings", method = RequestMethod.GET)
	public Select2ListResult findRequestMappings(@RequestParam(name = "search", required = false) String search) {
		List<com.jhonelee.jfdf.requestmapping.dto.RequestMapping> requestMappings = this.requestMappingService.findRequestMappings(search);

		List<Select2> data = new ArrayList<Select2>();

		CollectionUtils.collect(requestMappings, requestMapping -> {
			Select2 select2 = new Select2();
			select2.setId(requestMapping.getUrl());
			select2.setText("Url=" + requestMapping.getUrl() + "; RequestMethod=" +  requestMapping.getHttpMethod());
			return select2;
		}, data);

		Select2ListResult result = new Select2ListResult();
		result.setResults(data);
		return result;
	}

}
