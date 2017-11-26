package com.jhonelee.jfdf.resource.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhonelee.jfdf.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ResourceRepositoryTest {
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Test
	@Transactional
	public void getTopLevelResourceTest() {
		
//		resource.getChildren().size();
		
	}

}
