package com.jhonelee.jfdf.requestmapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jhonelee.jfdf.requestmapping.service.RequestMappingService;

@RestController
public class RequestMappingController {
	
	@Autowired
	private RequestMappingService requestMappingService;
	
	@RequestMapping(value = "/requestMappings", method = RequestMethod.GET)
	public ResponseEntity<List<com.jhonelee.jfdf.requestmapping.dto.RequestMapping>> findRequestMappings() {
		List<com.jhonelee.jfdf.requestmapping.dto.RequestMapping> requestMappings = this.requestMappingService.extractRequestMappingsFromWebApplicationContext();
		return ResponseEntity.ok(requestMappings);
	}
	

}
