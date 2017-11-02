package com.jhonelee.jfdf.requestmapping.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhonelee.jfdf.Application;
import com.jhonelee.jfdf.requestmapping.dto.RequestMapping;
import com.jhonelee.jfdf.requestmapping.service.RequestMappingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ResourceUrlServiceTest {
	
	@Autowired
	private RequestMappingService resourceUrlService;
	
	@Test
	public void extractRequestMappingsFromWebApplicationContextTest() {
		List<RequestMapping> requestMappings = this.resourceUrlService.extractRequestMappingsFromWebApplicationContext();
		Assert.assertTrue(CollectionUtils.isNotEmpty(requestMappings));
	}

}
