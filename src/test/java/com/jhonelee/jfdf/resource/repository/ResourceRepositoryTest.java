package com.jhonelee.jfdf.resource.repository;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhonelee.jfdf.Application;
import com.jhonelee.jfdf.resource.entity.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ResourceRepositoryTest {
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Test
	@Transactional
	public void getTopLevelResourceTest() {
		Resource resource = this.resourceRepository.getTopLevelResource();
		
		resource.getChildren().size();
		
		Assert.assertNotNull(resource);
	}

}
