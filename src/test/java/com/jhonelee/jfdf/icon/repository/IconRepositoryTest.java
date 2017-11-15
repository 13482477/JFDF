package com.jhonelee.jfdf.icon.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhonelee.jfdf.Application;
import com.jhonelee.jfdf.icon.entity.Icon;
import com.jhonelee.jfdf.icon.entity.IconType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class IconRepositoryTest {
	
	@Autowired
	private IconRepository iconRepository;
	
	@Test
	public void findByIconTypeTest() {
		List<Icon> icons = this.iconRepository.findByIconType(IconType.FONT_AWESOME);
		
		Assert.assertTrue(icons.size() >= 0);
	}

}
