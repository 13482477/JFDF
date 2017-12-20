package com.jhonelee.jfdf.resource.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhonelee.jfdf.Application;
import com.jhonelee.jfdf.menu.entity.Menu;
import com.jhonelee.jfdf.menu.repository.MenuRepository;
import com.jhonelee.jfdf.resource.entity.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ResourceRepositoryTest {
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Test
	@Transactional
	public void getTopLevelResourceTest() {
		
//		resource.getChildren().size();
	}
	
	@Test
	public void findResourceByMenusTest() {
		List<Menu> menus = new ArrayList<Menu>();
		
		Menu menu = this.menuRepository.findOne((long) 2);
		menus.add(menu);
		
		List<Resource> resources = this.resourceRepository.findByMenus(menus);
		
		System.out.println(resources.size());
	}

}
